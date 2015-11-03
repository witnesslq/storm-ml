package cn.disruptive.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.Date;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;



public class HttpClient {
	private static Log log = LogFactory.getLog(HttpClient.class);
	/**
	 * 调用rest请求
	 * @param url
	 * @param xmlData
	 * @param accountId
	 * @param curdate
	 * @return
	 */
	public static String postRestData(String url, String xmlData,String accountId,Date curDate)  {
		log.info("请求rest地址:"+url);
		log.info("请求rest内容:"+xmlData);
		String result="";
		CloseableHttpClient client = null;
		String encode="UTF-8";
		try{
			String timeStamp = DateUtils.formatDate(curDate, "yyyyMMddHHmmss");
			String Authorization = new String(Base64.encodeBase64((accountId
				+ ":" + timeStamp).getBytes()), encode);
			if (isHttpsUrl(url)) {
				client = registerSSL(getHost(url), "TLS", 8883, "https");
			} else {
				client = HttpClients.createDefault();
			}
//			client =  new DefaultHttpClient(new ThreadSafeClientConnManager());
//			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,30000); //连接时间30s
//			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,60000);    //数据传输60s
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
			HttpPost post = new HttpPost(url);
			post.setConfig(requestConfig);
//			post.removeHeaders("Content-Length");		        
			post.setHeader("Accept", "application/xml");
			post.setHeader("Content-Type", "application/xml;charset="+encode);
//			post.setHeader("Content-Length",String.valueOf(xmlData.getBytes().length)) ;
			post.setHeader("Authorization", Authorization);
//			HttpEntity requestBody = new InputStreamEntity(new ByteArrayInputStream(xmlData.getBytes(encode)), -1);
//	        post.setEntity(requestBody);
//			byte[] requestByte =xmlData.getBytes("UTF-8");
			HttpEntity httpEntity = new ByteArrayEntity(xmlData.getBytes("UTF-8"));
			post.setEntity(httpEntity);

			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity(); 
            
            StringBuffer sb = new StringBuffer();   
            InputStreamReader iReader = null;  
            InputStream inputStream = entity.getContent();   
            iReader = new InputStreamReader(inputStream,encode);   
            BufferedReader reader = new BufferedReader(iReader);   
            String line = null;   
            
            while ((line = reader.readLine()) != null) {   
            sb.append(line + "\r\n");   
            }
            iReader.close(); 
            result=sb.toString();			
			log.debug("rest返回结果 :"+result);		
		}catch(Exception e){
			log.error("HttpClient请求rest地址错误,"+e.getCause());
        	e.printStackTrace();
		}finally{
			if (client != null ) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				} 
            }
		}
		return result;
	}
	public static String getSig(String accountId,String token,Date curDate){
		String timeStamp = DateUtils.formatDate(curDate, "yyyyMMddHHmmss");
		String sig = Encrypt.encryptMD5(accountId + token + timeStamp).toUpperCase();
		log.debug("sig:"+sig);
		return sig;
	}
	static CloseableHttpClient registerSSL(String hostname, String protocol, int port, String scheme)
			throws NoSuchAlgorithmException, KeyManagementException {
//		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建SSL上下文实例
		SSLContext ctx = SSLContext.getInstance(protocol);
		// 服务端证书验证
		X509TrustManager tm = new X509TrustManager() {
			public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
					throws java.security.cert.CertificateException {				
			}
			public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
					throws java.security.cert.CertificateException {
				if (chain == null || chain.length == 0)
					throw new IllegalArgumentException("null or zero-length certificate chain");
				if (authType == null || authType.length() == 0)
					throw new IllegalArgumentException("null or zero-length authentication type");

				boolean br = false;
				Principal principal = null;
				for (java.security.cert.X509Certificate x509Certificate : chain) {
					principal = x509Certificate.getSubjectX500Principal();
					log.debug("服务器证书信息：" + principal.getName());
					if (principal != null) {
						br = true;
						return;
					}
				}
				if (!br) {
					log.error("服务端证书验证失败！");
				}
			}
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return new java.security.cert.X509Certificate[0];
			}
		};

		// 初始化SSL上下文
		ctx.init(null, new TrustManager[] { tm }, new java.security.SecureRandom());
//		// 创建SSL连接
//		SSLSocketFactory socketFactory = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//		Scheme sch = new Scheme(scheme, port, socketFactory);
//		// 注册SSL连接
//		httpclient.getConnectionManager().getSchemeRegistry().register(sch);
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(ctx,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}
	private static boolean isHttpsUrl(String url) {
		return (null != url) && (url.length() > 7) && url.substring(0, 8).equalsIgnoreCase("https://");
	}
	public static String getHost(String url) {
		int index = url.indexOf("//");
		String host = url.substring(index + 2);
		index = host.indexOf("/");
		if (index > 0) {
			host = host.substring(0, index);
		}
		log.debug("host:"+host);
		return host;
	}

	public static String postJsonData(String url, String jsonData) {
		log.info("请求地址:" + url);
		log.info("发送内容:" + jsonData);
		String result = "";
		CloseableHttpClient client = null;
		String encode = "UTF-8";
		try {
			if (isHttpsUrl(url)) {
				client = registerSSL(getHost(url), "TLS", 8883, "https");
			} else {
				client = HttpClients.createDefault();
			}
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
			HttpPost post = new HttpPost(url);
			post.setConfig(requestConfig);
			// post.removeHeaders("Content-Length");
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-Type", "application/json;charset=" + encode);
			// post.setHeader("Content-Length",String.valueOf(xmlData.getBytes().length)) ;
			HttpEntity httpEntity = new ByteArrayEntity(jsonData.getBytes("UTF-8"));
			post.setEntity(httpEntity);

			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();

			StringBuffer sb = new StringBuffer();
			InputStreamReader iReader = null;
			InputStream inputStream = entity.getContent();
			iReader = new InputStreamReader(inputStream, encode);
			BufferedReader reader = new BufferedReader(iReader);
			String line = null;

			while ((line = reader.readLine()) != null) {
				sb.append(line + "\r\n");
			}
			iReader.close();
			result = sb.toString();
			log.debug("第三方返回结果 :" + result);
		} catch (Exception e) {
			log.error("HttpClient请求第三方地址错误," + e.getCause());
			e.printStackTrace();
		} finally {
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	/**
	 * 调用rest请求,发送流
	 * @param url
	 * @param streamData 流的二进制代码
	 * @param accountId
	 * @param curdate
	 * @return
	 */
	public static String postRestStreamData(String url, byte [] streamData, String accountId, Date curDate) {
		log.info("请求rest地址:" + url);
		log.info("请求rest内容:" + streamData);
		String result = "";
		CloseableHttpClient client = null;
		String encode = "UTF-8";
		try {
			String timeStamp = DateUtils.formatDate(curDate, "yyyyMMddHHmmss");
			String Authorization = new String(Base64.encodeBase64((accountId + ":" + timeStamp).getBytes()), encode);
			if (isHttpsUrl(url)) {
				client = registerSSL(getHost(url), "TLS", 8883, "https");
			} else {
				client = HttpClients.createDefault();
			}
			// client = new DefaultHttpClient(new ThreadSafeClientConnManager());
			// client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,30000); //连接时间30s
			// client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,60000); //数据传输60s
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
			HttpPost post = new HttpPost(url);
			post.setConfig(requestConfig);
			// post.removeHeaders("Content-Length");
			post.setHeader("Accept", "application/octet-stream");
			post.setHeader("Content-Type", "application/octet-stream;charset=" + encode);
			// post.setHeader("Content-Length",String.valueOf(xmlData.getBytes().length)) ;
			post.setHeader("Authorization", Authorization);
			// HttpEntity requestBody = new InputStreamEntity(new ByteArrayInputStream(xmlData.getBytes(encode)), -1);
			// post.setEntity(requestBody);
			// byte[] requestByte =xmlData.getBytes("UTF-8");
			HttpEntity httpEntity = new ByteArrayEntity(streamData);
			post.setEntity(httpEntity);

			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();

			StringBuffer sb = new StringBuffer();
			InputStreamReader iReader = null;
			InputStream inputStream = entity.getContent();
			iReader = new InputStreamReader(inputStream, encode);
			BufferedReader reader = new BufferedReader(iReader);
			String line = null;

			while ((line = reader.readLine()) != null) {
				sb.append(line + "\r\n");
			}
			iReader.close();
			result = sb.toString();
			log.debug("rest返回结果 :" + result);
		} catch (Exception e) {
			log.error("HttpClient请求rest地址错误," + e.getCause());
			e.printStackTrace();
		} finally {
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	public static void main(String [] args){
//		Date curDate=new Date();
//		String sig=HttpClientConnect.getSig(accountId, token, curDate);
//		String url="url?sig="+sig;
//		HttpClientConnect.postRestData(url, xmlData, accountId, curDate);
//		String url="http://ulink.unicall.cc:8082/softphone/AccountLookup";
//		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><Request><action>AccountLookup</action><id>8002</id><type>TOKEN</type></Request>";
//		HttpClient.testData(url,xml);
		String url="https://118.194.243.246:8443";
//		String url="https://test.cloopen.com:8443/2013-12-26";
		String xmlData="";
		HttpClient.postRestData(url, xmlData, "aaaa", new Date());
		
	}
	
}
