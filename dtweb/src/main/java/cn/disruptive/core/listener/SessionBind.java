package cn.disruptive.core.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.disruptive.core.session.AuthUser;

public class SessionBind implements HttpSessionBindingListener {
	public final Logger logger = LoggerFactory.getLogger(SessionBind.class);
	private AuthUser authUser;
	public AuthUser getAuthUser() {
		return authUser;
	}
	public void setAuthUser(AuthUser authUser) {
		this.authUser = authUser;
	}
	public SessionBind(){
		
	}
	public SessionBind(AuthUser authUser){
		setAuthUser(authUser);
	}
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		logger.debug("session valueBound");		
		if (SessionContext.getInstance().isOnline("" + authUser.getUserId())) {
			String sessionId = SessionContext.getInstance().getSessionId("" + authUser.getUserId());
			logger.debug("待移除sessionId:"+sessionId);
			SessionContext.getInstance().delSession(sessionId);
		}else{
			logger.debug(authUser.getUserId()+"不在线,直接保存");
		}
		SessionContext.getInstance().addSession(event.getSession(), "" + authUser.getUserId());
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		logger.debug("session valueUnbound");
		HttpSession session = event.getSession();
		SessionContext.getInstance().delSession(session.getId());
	}

}
