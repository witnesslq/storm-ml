package cn.disruptive.web.interceptor;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.disruptive.common.util.SessionManage;
import cn.disruptive.core.listener.SessionContext;
import cn.disruptive.core.session.AuthUser;

public class SystemInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LoggerFactory.getLogger(SystemInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String uri = request.getRequestURI();
		// logger.debug("过滤拦截:"+uri);
		if (uri.indexOf("manage") != -1) {
			boolean beFilter = true;
			if (beFilter) {
				AuthUser auth = SessionManage.getSessionUser(request);
				if (null != auth) {
					if (!SessionContext.getInstance().isOnline(request.getSession())){
						//被踢出
						return loginExpired(request, response);
					}
				} else {
					logger.debug("Authentication为空,跳到登录");
					return unLogin(request, response);
				}
			}
		}
		return true;
	}
	
	
	public void afterCompletion(HttpServletRequest request,  
	        HttpServletResponse response,Object handler,Exception ex)throws Exception{  
	          
	}  
	
	public boolean unLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 未登录
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		StringBuilder builder = new StringBuilder();
		builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
		//builder.append("alert(\"页面过期,请重新登录\");");
		builder.append("window.top.location.href=\"");
		builder.append(request.getContextPath()+"/loginInput\";</script>");
		out.print(builder.toString());
		out.close();
		return false;
	}
	public boolean loginExpired(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 登录失效 被踢出
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		StringBuilder builder = new StringBuilder();
		builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
		builder.append("alert(\"页面过期,请重新登录\");");
		builder.append("window.top.location.href=\"");
		builder.append(request.getContextPath()+"/loginExpired\";</script>");
		out.print(builder.toString());
		out.close();
		return false;
	}
}
