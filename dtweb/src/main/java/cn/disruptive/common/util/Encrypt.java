package cn.disruptive.common.util;

import java.security.MessageDigest;

/**
 * 加密类
 */
public class Encrypt {
	public static String encryptMD5(String strInput) {
	    StringBuffer buf = null;
	    try {
	      MessageDigest md = MessageDigest.getInstance("MD5");
	      md.update(strInput.getBytes());
	      byte[] b = md.digest();
	      buf = new StringBuffer(b.length * 2);
	      for (int i = 0; i < b.length; ++i) {
	        if ((b[i] & 0xFF) < 16)
	          buf.append("0");

	        buf.append(Long.toHexString(b[i] & 0xFF));
	      }
	    } catch (Exception ex) {
	      ex.printStackTrace();
	    }
	    return buf.toString();
	}
	public static String generatePwd(String pwd,String salt) {
	   String mergePwd=pwd+"{"+salt+"}"; 
	   return encryptMD5(mergePwd);	   
	}
	public static void main(String[] args) {
		//String str=encryptMD5("111111{350102}");
//		String str=encryptMD5("111111");
		generatePwd("111111","42fx3h");
		System.out.println(generatePwd("111111","42fx3h"));
	}
}
