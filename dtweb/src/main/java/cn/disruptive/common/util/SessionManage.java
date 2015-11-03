package cn.disruptive.common.util;

import javax.servlet.http.HttpServletRequest;

import cn.disruptive.core.listener.SessionBind;
import cn.disruptive.core.session.AuthUser;


public class SessionManage {

	public static AuthUser getSessionUser(HttpServletRequest request) {
		SessionBind sessionBind=(SessionBind) request.getSession().getAttribute(Constants.USER_SESSION_KEY);
		if(sessionBind!=null){
			return sessionBind.getAuthUser();
		}
		return null;
	}
	public static void setSessionUser(HttpServletRequest request, AuthUser auth) {
		
		request.getSession().setAttribute(Constants.USER_SESSION_KEY, new SessionBind(auth));
	}
}
