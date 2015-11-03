package cn.disruptive.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JCaptchaValidate {
	public final static Logger logger = LoggerFactory.getLogger(JCaptchaValidate.class);
	/**
	 * @param request
	 * @param paramValue 用户输入的参数
	 * @return
	 */
	public static boolean validateCaptchaCode(HttpServletRequest request, String paramValue, boolean clean) {
		try {
			String kaptchaExpected =(String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
			boolean codeFlag=false;
			if (StringUtils.isNotBlank(paramValue) && paramValue.equalsIgnoreCase(kaptchaExpected)) {
	        	codeFlag=true;
	        }else{
	        	logger.debug("图片校验码校验失败,paramValue:{},kaptchaExpected:{}",new Object[]{paramValue,kaptchaExpected});
	        }
			if (clean) {
				request.getSession().setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, null);
			}
	        return codeFlag;
		} catch (Exception e) {
			logger.error("图片校验码校验异常");
			return false;
		}
	}
}
