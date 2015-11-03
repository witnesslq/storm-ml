package cn.disruptive.core.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.disruptive.core.TokenProcessor;
import cn.disruptive.core.annotation.AvoidReSubmission;

public class AvoidReSubmissionInterceptor extends HandlerInterceptorAdapter{
	 private static Logger logger = LoggerFactory.getLogger(AvoidReSubmissionInterceptor.class);
	     @Override
	     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    	 logger.debug("判断是否重复提交");
	    	 HandlerMethod handlerMethod = (HandlerMethod) handler;	 
//	         Method method = handlerMethod.getMethod();	 
//	         AvoidReSubmission annotation = method.getAnnotation(AvoidReSubmission.class);
	         AvoidReSubmission annotation =handlerMethod.getMethodAnnotation(AvoidReSubmission.class);
	         if (annotation != null) {	 
	        	 boolean needSaveSession = annotation.needSaveToken();	 
	             logger.debug("needSaveSession:"+needSaveSession);
	             if (needSaveSession) {	 
	            	 request.getSession(false).setAttribute("token", TokenProcessor.getInstance().generateToken(request));	 
	             }   	 
	             boolean needRemoveSession = annotation.needRemoveToken();	
	             logger.debug("needRemoveSession:"+needRemoveSession);
	             if (needRemoveSession) {	 
	            	 if (isRepeatSubmit(request)) {
	                         //return false;	
	            		 return promptReSubmit(request,response);
	                 }	 
	            	 request.getSession(false).removeAttribute("token");	 
	             }	 
	         }
	         return true;	 
	     }
	     /**
	      * 判断是否重复提交
	      * @param request
	      * @return
	      */
	     private boolean isRepeatSubmit(HttpServletRequest request) {	 
	         String serverToken = (String) request.getSession(false).getAttribute("token");	 
	         if (serverToken == null) {	 
	             return true;	 
	         }	 
	         String clinetToken = request.getParameter("token");	 
	         if (clinetToken == null) {	 
	             return true;	 
	         }	 
	         if (!serverToken.equals(clinetToken)) {	 
	             return true;	 
	         }	 
	         return false;	 
	     }
	     /**
	      * 提示重复 提交
	      * @param request
	      * @param response
	      * @return
	      * @throws Exception
	      */
	     private boolean promptReSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 		// 重复提交
	    	logger.debug("重复提交提示");
	 		response.setCharacterEncoding("utf-8");
	 		response.setContentType("text/html; charset=utf-8");
	 		PrintWriter out = response.getWriter();
	 		StringBuilder builder = new StringBuilder();
	 		builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
	 		builder.append("alert(\"禁止重复提交\");");
	 		builder.append("history.back();");			
	 		builder.append("</script>");
	 		out.print(builder.toString());
	 		out.close();
	 		return false;
	 	}

}
