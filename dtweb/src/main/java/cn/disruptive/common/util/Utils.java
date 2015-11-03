package cn.disruptive.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**    
 * @ClassName: Utils    
 * @Description: 公用类    
 *        
 */

public class Utils {
	
	public final static Logger logger = LoggerFactory.getLogger(Utils.class);
	
	public static String checkMobile(String mobile){
		String result="-1";
		//移动
		String chinaMobile ="(13[4-9]|15[0-27-9]|125|177|147|18[278])\\d{8}";
		//联通
		String chinaUnicom ="(13[0-2]|15[56]|186|177)\\d{8}";
		//电信
		String chinaTelecom ="(133|153|180|189|177)\\d{8}";
	    if(mobile.matches(chinaMobile)){
	    	result="0";
		}else if(mobile.matches(chinaUnicom)){
			result="1";
		}else if(mobile.matches(chinaTelecom)){
			result="2";
		}
		return result;
	}
	/**
	 * 左侧补零
	 * 
	 * @param str
	 * @param strLength
	 * @return
	 */
	public static String stringLeftPading(String str, int strLength) {
		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				sb.append("0").append(str);// 左补0
				// sb.append(str).append("0");//右补0
				str = sb.toString();
				strLen = str.length();
			}
		}

		return str;
	}

	/**
	 * 右侧补零
	 * 
	 * @param str
	 * @param strLength
	 * @return
	 */
	public static String stringRightPading(String str, int strLength) {
		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				sb.append(str).append("0");// 右补0
				str = sb.toString();
				strLen = str.length();
			}
		}

		return str;
	}
	/**
	 * 验证日期
	 * @param dataStr
	 * @return
	 */
	public static boolean verifyData(String dataStr){
		Pattern p = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\-\\s]?((((0?" +"[13578])|(1[02]))[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))" +"|(((0?[469])|(11))[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(30)))|" +"(0?2[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12" +"35679])|([13579][01345789]))[\\-\\-\\s]?((((0?[13578])|(1[02]))" +"[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))" +"[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\-\\s]?((0?[" +"1-9])|(1[0-9])|(2[0-8]))))))"); 
        
		return p.matcher(dataStr).matches();
	}
	/**
	 * 判断字符串是否在数组中是否存在
	 * @param substring
	 * @param source
	 * @return
	 */
	public static boolean isInStringArray(String substring, String[] source) {
		if (source == null || source.length == 0) {
			return false;
		}
		for (int i = 0; i < source.length; i++) {
			String aSource = source[i];
			if (aSource.equals(substring)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 判断字符串是否存在另一个字符串
	 * @param substring
	 * @param source
	 * @return
	 */
	public static boolean isInString(String substring, String source) {
		if (source == null || source.length() == 0) {
			return false;
		}
		if (source.indexOf(substring, 0) >= 0) {
			// 要大于等于0
//			   System.out.println("找到字串：" + substring);
			return true;
		}
		return false;
	}
	/**
	 * 验证手机号
	 * @param mobile
	 * @return
	 */
	public static boolean verifyMobile(String mobile){
		Pattern p = Pattern.compile("^1[3,4,5,7,8]\\d{9}$");
		return p.matcher(mobile).matches();
	}
	
	/**
	 * 
	 * Description: 返回值
	 * Date:2012-9-13
	 * @param response
	 * @param content 
	 * @return void
	 */
	public static void render(HttpServletResponse response, String content) {
		try {
			response.setContentType("text/html;charset=UTF-8 ");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<List> getAllSubList(List list,int subSize){
		List<List> subLists=new ArrayList<List>();	
		if(list!=null&&list.size()>0){
				int pagesize=subSize;
				int totalcount=list.size();
				int pagecount=totalcount%pagesize==0?totalcount/pagesize:totalcount/pagesize+1;
				for(int i=1;i<=pagecount;i++){     
				    if (totalcount%pagesize==0){
				       List<Integer> subList= list.subList((i-1)*pagesize,pagesize*(i)); 
				       subLists.add(subList);   
				     }else{
				         if (i==pagecount){
				                List<Integer> subList= list.subList((i-1)*pagesize,totalcount); 
				                subLists.add(subList);     
				         	}else{
				                List<Integer> subList= list.subList((i-1)*pagesize,pagesize*(i));
				                subLists.add(subList);    
				             }
				          }
				  }
			}
				return subLists;
		}
	      
	
	public static boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(str).matches();    
	}
	/**
	 * 验证手机或固话
	 * @param str
	 * @return
	 */
	public static boolean isPhone(String str){
		String patt="(^1[3|4|5|8|7]+\\d{9})|(^0[1-9]+(\\d{9}|\\d{10}))";
		Pattern regex = Pattern.compile(patt);
		Matcher matcher = regex.matcher(str);
		boolean isMatched = matcher.matches();
		if (isMatched) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 判断hql中是加where 还是and
	 * @param hql
	 */
	public static void whereAppend(StringBuilder hql) {
			if(hql != null){
				String str = hql.toString();
				if (str != null && str.toLowerCase().indexOf(" where ") == -1){
					hql.append("  where  ");   
				}else {
		        	hql.append("  and  ");
				}
			}
	}
   /**
    * 将文件流转化成字符
    * @param is
    * @return
    */
   public static String convertStreamToString(InputStream is) {      
		 
	   BufferedReader reader = new BufferedReader(new InputStreamReader(is));      
	      StringBuilder sb = new StringBuilder();      
	      String line = null;      
	     try {      
	        while ((line = reader.readLine()) != null) {      
	             sb.append(line);      
	         }      
	      } catch (IOException e) {      
	          e.printStackTrace();      
	      } finally {      
	         try {      
	              is.close();      
	          } catch (IOException e) {      
	              e.printStackTrace();      
	          }      
	      }      
	     return sb.toString();      
   } 
   public static String fileToString(HttpServletRequest request) throws Exception {
		BufferedReader br;
		String data = null;
		StringBuffer input = new StringBuffer();
		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			while ((data = br.readLine()) != null) {
				input.append(data);
			}
			br.close();
		} catch (Exception e) {
			logger.info("获取流失败");
			throw new Exception("获取流失败");
		}
		String fileStr = input.toString();
		logger.info("接收到的文件数据：fileStr"+fileStr);
	
		return input.toString();
	}

   /**
    * 
    * Description：字符转换为数字
    * Data：			2012年8月23日
    * @author 		shaqf
    * @email 		sqfsky@163.com
    * @param str
    * @return
    */
	public static Integer strToInt(String str) {
		if (StringUtils.isNotBlank(str)) {
			try {
				return Integer.parseInt(str);
			} catch (Exception e) {
				logger.warn("strToInt转换错误");
				return null;
			}
		} else {
			return null;
		}
	}
	public static void main(String[] args) {
//		List list=new ArrayList();
//		for(int i=0;i<100;i++){
//			list.add(i);
//		}
//		getAllSubList(list,11);
//		String s1="08:30";
//		String s2="10:10";
//		System.out.println(s2.compareTo(s1));
	}
}
