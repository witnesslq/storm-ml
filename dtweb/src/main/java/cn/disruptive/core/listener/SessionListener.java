package cn.disruptive.core.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
public class SessionListener implements HttpSessionListener {
	public final Logger logger = LoggerFactory.getLogger(SessionListener.class);
    /**
     * Default constructor. 
     */
    public SessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent sessionEvent) {
    	logger.debug("创建session");
//    	SessionContext.getInstance().addSession(sessionEvent.getSession());
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
    	logger.debug("销毁session");
//    	HttpSession session = sessionEvent.getSession();
//    	SessionContext.getInstance().delSession(session);
    }

}
