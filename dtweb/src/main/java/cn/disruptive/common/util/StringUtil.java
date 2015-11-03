package cn.disruptive.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @description 功能函数
 * @version 1.0
 * @update May 31, 2010 2:18:29 PM
 */
public class StringUtil {
	public static void main(String[] args){
		System.out.println(getFileName("e:/23/234as.d/gfhgh.txt"));
	}
	
	public static void whereAppend(StringBuilder hql) {
		if(hql != null){
			String str = hql.toString();
			if (str != null && str.toLowerCase().indexOf(" where ") == -1){
				hql.append(" where ");   
			}else {
	        	hql.append(" and ");
			}
		}
	}
	
	/**
	 * 获取文件名
	 * @param path
	 * @return
	 * @description TODO(这里用一句话描述这个方法的作用) 
	 * @version 1.0
	 * @update Jun 28, 2010 1:02:35 PM
	 */
	public static String getFileName(String path){
		int beginIndex=path.lastIndexOf("/");
		if(beginIndex>=0){
			beginIndex=beginIndex+1;
		}
		int endIndex=path.length();		
		if(endIndex<=0) return "";
		return path.substring(beginIndex, endIndex);
	}
	// 用户特殊字符的转义
	public static String dealString(String src) {
		src = src.replace("\\", "\\\\");
		src = src.replace("'", "\\'");
		src = src.replace("%", "\\%");
		src = src.replace("_", "\\_");
		return src;
	}
	/**
	 * 获取随机数字
	 * @param length 指定随机数的长度
	 * @return
	 */
	public static String getRandomNumber(int length){
		return StringUtil.getRandomString(length,2);
	}
	/**
	 * 获取随机字符串

	 * @param length 指定随机数的长度
	 * @return
	 */
	public static String getRandomChar(int length){
		return StringUtil.getRandomString(length,1);
	}
	/**
	 * 获取随机数或随机字符串

	 * @param length
	 * @param type  0－－数字+大小写字母；1－－大小写字母；2－－数字；3－－数字+小写字母；其它－－同0
	 * @return
	 */
	public static synchronized String getRandomString(int length,int type) { 
		StringBuffer sb = new StringBuffer();
		Random random = new Random(); 
		for (int i = 0; i < length; i++) { 
			if(type==0) {
				sb.append(_ALLCHAR.charAt(random.nextInt(_ALLCHAR.length()))); 
			}else if(type==1) {
				sb.append(_LETTERCHAR.charAt(random.nextInt(_LETTERCHAR.length()))); 
			}else if(type==2) {
				sb.append(_NUMBERCHAR.charAt(random.nextInt(_NUMBERCHAR.length()))); 
			}else if(type==3) {
				sb.append(_NUMBERCHAR_LETTERCHAR.charAt(random.nextInt(_NUMBERCHAR_LETTERCHAR.length()))); 
			}else {
				sb.append(_ALLCHAR.charAt(random.nextInt(_ALLCHAR.length()))); 
			}
		}             
		return sb.toString(); 
	} 
	
	public static Map<String,Object> xmlElementsMap(String xmlDoc) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("flag", false);
		map.put("msg", "加载失败");
		try {
			Document document = DocumentHelper.parseText(xmlDoc);
			Element root = document.getRootElement();
			String statusCode = root.elementText("statuscode");
			try {
				map.put("msg", root.elementText("statusmsg"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(statusCode != null && statusCode.equals("0")){
				map.put("flag", true);
			}
			if(statusCode != null && statusCode.equals("000000")){
				map.put("flag", true);
			}
			
		} catch (DocumentException e) {
			map.put("msg", e.toString());
			return map;
		}
		return map;
	}


	// 保留粘贴前的文本的格式效果
	public static String translate(String s) {
		if (s == null || s.length()<1){
			return "";
		}
		StringBuffer buf = new StringBuffer();
		for (int i = 0, limit = s.length(); i < limit; i++) {
			int c = s.charAt(i);
			switch (c) {
			case ' ':
				buf.append("&nbsp;");
				break;
			case '<':
				buf.append("&lt;");
				break;
			case '>':
				buf.append("&gt;");
				break;
			case '\n':
				buf.append("<br>");
				break;
			default:
				buf.append((char) c);
			}
		}
		return buf.toString();
	}
	/**
	 * 处理空字符串 
	 * @param str
	 * @return String
	 */
	public static String doEmpty(String str) {
		return doEmpty(str, "");
	}

	/**
	 * 处理空字符串
	 * @param str
	 * @param defaultValue
	 * @return String
	 */
	public static String doEmpty(String str, String defaultValue) {
		if (str == null || str.equalsIgnoreCase("null") 
				|| str.trim().equals("") || str.trim().equals("－请选择－")) {
			str = defaultValue;
		}else if(str.startsWith("null")){
			str = str.substring(4,str.length());
		}
		return str.trim();
	}


	/**
	 * 移去字符串数组中的null值和空字符串
	 * @param str 要处理的字符串数组
	 * @return
	 */
	public static String[] removeNull(String[] str){
		List<String> list = new ArrayList<String>() ;
		for(int i=0;i<str.length;i++){
			if(str[i] != null && !str[i].trim().equals("")){
				list.add(str[i]);
			}
		}	
		String[] newStr = new String[list.size()];
		for(int i=0;i<list.size();i++){
			newStr[i] = list.get(i).toString();
		}
		return newStr;
	}

/**
 * @description 验证是否为空
 * @version 1.0
 * @update May 31, 2010 2:16:46 PM
 */
	/*public static boolean isEmpty(String str){
		if(str == null || str.equals("null") || str.trim().equals(""))
			return true;
		else 
			return false;

	}*/


	/**
	 * 根据标识符截取字符串返回数组
	 * @param str 要截取的字符串
	 * @param regex 标识符
	 * @return
	 */
	public static String[] getArryByRegex(String str,String regex){
		String[] strArray = null;
		if(str!=null && !str.equals("") && !regex.equals("") && regex!=null){
			strArray = str.split(regex);
		}		
		return strArray;
	}
	/**
	 * 判断一个字符串数组中是否含有某个字符串
	 * @param strArray
	 * @param str
	 * @return
	 */
	public static boolean isContainStr(String[] strArray,String str){
		boolean result = false;
		if(strArray!=null&&strArray.length>0){
			for(int i=0; i<strArray.length; i++){

				if(strArray[i].equals(str)){
					result = true;
				}
			}			
		}
		return result;
	}

	/**
	 * 判断是否是字符
	 */
	private static boolean isLetter(char c) { 
		int k = 0x80; 
		return c / k == 0 ? true : false; 
	} 

	/** 
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1 
	 */ 
	private static int length(String s) { 
		if (s == null) 
			return 0; 
		char[] c = s.toCharArray(); 
		int len = 0; 
		for (int i = 0; i < c.length; i++) { 
			len++; 
			if (!isLetter(c[i])) { 
				len++; 
			} 
		} 
		return len; 
	}

	/**  
	 * 注意： 一个汉字占两个长度<br>
	 * 截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位 
	 */ 
	public static String substring(String origin, int len) { 
		if (origin == null || origin.equals("")||len<1) 
			return ""; 
		byte[] strByte = new byte[len]; 
		if (len > length(origin)){ 
			return origin;} 
		System.arraycopy(origin.getBytes(), 0, strByte, 0, len); 
		int count = 0; 
		for (int i = 0; i < len; i++) { 
			int value = (int) strByte[i]; 
			if (value < 0) { 
				count++; 
			} 
		} 
		if (count % 2 != 0) { 
			len = (len == 1) ? ++len : --len; 
		} 
		return new String(strByte, 0, len); 
	} 

	/**
	 * 截取字符长度
	 * @param origin
	 * @param len 返回截取和替代后的字符串最大的长度（一个汉字占两个长度）
	 * @param replaceString 如果源字符长度超过指定长度的后缀替代字符
	 * @return
	 */
	public static String substring(String origin, int len , String replaceString) {
		if(origin == null || origin.equals("")||len<1)  return ""; 
		if(replaceString == null || replaceString.equals("")) return substring(origin,len);
		int originLen = length(origin);
		int replaceStrLen = length(replaceString);
		if(originLen+replaceStrLen<=len) return origin;
		if(replaceStrLen>=len) return substring(replaceString,len);
		return substring(origin,len - replaceStrLen)+replaceString;
	}
	
	public static boolean isIn(String substring, String[] source) {
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
	
	public static String stringArrayToStr(String [] strArray){
		StringBuffer sb = new StringBuffer();
		if(strArray!=null&&strArray.length>0){
			for(int i = 0; i < strArray.length; i++){
				sb.append(strArray[i]);
			}
		}
		return sb.toString();
	}
	public static void whereAppend(StringBuffer hql) {
		if(hql != null){
			String str = hql.toString();
			if (str != null && str.toLowerCase().indexOf(" where ") == -1){
				hql.append(" where ");   
			}else {
	        	hql.append(" and ");
			}
		}
	}

	private static final String _ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	private static final String _LETTERCHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	private static final String _NUMBERCHAR_LETTERCHAR = "0123456789abcdefghijklmnopqrstuvwxyz"; 
	private static final String _NUMBERCHAR = "0123456789"; 
	
}
