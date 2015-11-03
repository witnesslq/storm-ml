package cn.disruptive.core.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionContext {
	public static final Logger logger = LoggerFactory.getLogger(SessionContext.class);
	private static SessionContext instance;
	// key:sessionId,value:userId
	private static Map<String, String> sessionMap;

	private SessionContext() {
		logger.debug("SessionContext");		
	}
	static {
		logger.debug("SessionContext static");
		sessionMap = new HashMap<String, String>();
	}
	synchronized public static SessionContext getInstance() {
		logger.debug("SessionContext getInstance");
		if (instance == null) {
			logger.debug("SessionContext getInstance new");
			instance = new SessionContext();
		}
		return instance;
	}

	public synchronized void addSession(HttpSession session, String userId) {
		if (session != null) {
			
			sessionMap.put(session.getId(), userId);
		}
	}

	public synchronized void delSession(String sessionId) {
		if (StringUtils.isNotBlank(sessionId)) {
			sessionMap.remove(sessionId);
		}
	}

	public synchronized String getSessionId(String userId) {
		if (StringUtils.isBlank(userId))
			return null;
		for (Map.Entry<String, String> entry : sessionMap.entrySet()) {  
			//System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue()); 
			String key = entry.getKey();
			String val = entry.getValue();
			if (val.equals(userId)) {
				return key;
			}
		}  
		
		return null;
	}

	public synchronized Object getSession(String sessionId) {
		if (StringUtils.isBlank(sessionId))
			return null;
		return sessionMap.get(sessionId);
	}

	public synchronized boolean isOnline(HttpSession session) {
		boolean flag = false;
		String sessionId=session.getId();
		logger.debug("sessionId:"+sessionId);
		if (sessionMap.containsKey(sessionId)) {
			flag = true;
		} 
		return flag;
	}

	public synchronized boolean isOnline(String userId) {
		boolean flag = false;
		if (sessionMap.containsValue(userId)) {
			flag = true;
		}
		return flag;
	}
}
