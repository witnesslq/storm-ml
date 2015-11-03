package cn.disruptive.common.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.directwebremoting.Container;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.extend.ScriptSessionManager;
import org.directwebremoting.servlet.DwrServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.disruptive.core.listener.SessionBind;

/**
 * ScriptSession 管理类
 * @ClassName: DwrScriptSessionManagerUtil
 * @author shaqf
 * @date 2013-10-25
 *
 */
public class DwrScriptSessionManagerUtil extends DwrServlet {
	private static final long serialVersionUID = -7504612622407420071L;
	public final Logger logger = LoggerFactory.getLogger(MessagePush.class);
	
	/**
	 * 初始化dwr监听
	 */
	public void init() throws ServletException {
		Container container = ServerContextFactory.get().getContainer();
		ScriptSessionManager manager = container.getBean(ScriptSessionManager.class);
		ScriptSessionListener listener = new ScriptSessionListener() {
			public void sessionCreated(ScriptSessionEvent ev) {
				HttpSession session = WebContextFactory.get().getSession();
				String userId = "";
				SessionBind sessionBind = (SessionBind) session.getAttribute(Constants.USER_SESSION_KEY);
				if (sessionBind != null) {
					userId = sessionBind.getAuthUser().getUserId() + "";
				}
				ev.getSession().setAttribute(userId, userId);
				logger.debug("创建ScriptSession成功，userId:{}",new Object[]{userId});
			}

			public void sessionDestroyed(ScriptSessionEvent ev) {
//				HttpSession session = WebContextFactory.get().getSession();
//				String userId = ((Authentication)session.getAttribute(Constants.USER_SESSION_KEY)).getUserId()+"";
//				ev.getSession().removeAttribute(userId);
//				logger.debug("销毁ScriptSession成功，userId:{}",new Object[]{userId});
				logger.debug("a ScriptSession is distroyed");
			}
		};
		manager.addScriptSessionListener(listener);//添加监听器
	}
}
