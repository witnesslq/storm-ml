package cn.disruptive.common.util;

import java.util.Collection;

import javax.servlet.ServletException;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.directwebremoting.WebContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息主动推送到页面
 * @ClassName: MessagePush
 * @author shaqf
 * @date 2013-10-25
 */
public class MessagePush {
	public final Logger logger = LoggerFactory.getLogger(MessagePush.class);
	
	/**
	 * 用户会话注册
	 * @param userId 登录用户id
	 */
	public void register(String userId) {
		logger.info("系统注册DWR用户-->userId:{}",new Object[]{userId});
		ScriptSession scriptSession = WebContextFactory.get().getScriptSession();
		if(scriptSession.getAttribute(userId) == null){
			scriptSession.setAttribute(userId, userId);
			DwrScriptSessionManagerUtil dwrScriptSessionManagerUtil = new DwrScriptSessionManagerUtil();
			try {
				dwrScriptSessionManagerUtil.init();
				logger.info("系统注册DWR用户-->userId:{},成功!",new Object[]{userId});
			} catch (ServletException e) {
				e.printStackTrace();
				logger.info("系统注册DWR用户-->userId:{},失败：{}",new Object[]{userId,e.toString()});
			}
		}
	}

	/**
	 * 
	 * Description：推送告警信息到客户端
	 * Data：		2015年10月9日
	 * @author 		shaqf
	 * @email 		sqfsky@163.com
	 * @param userid
	 * @param sceneCode
	 * @param message
	 */
	public void pushAlertMsg(String userid, String sceneCode, String message) {
		logger.debug("推送警告信息，userid={}, sceneCode={}, message={}", new Object[] { userid, sceneCode, message });
		final String UID_ = userid;
		final String SCENECODE_ = sceneCode;
		final String MESSAGE_ = message;

		Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
			public boolean match(ScriptSession session) {
				return true;
/*				if (session.getAttribute(UID_) == null) {
					return false;
				} else {
					return (session.getAttribute(UID_)).equals(UID_);
				}*/
			}
		}, new Runnable() {
			private ScriptBuffer script = new ScriptBuffer();

			public void run() {
				script.appendCall("alert_msg", SCENECODE_, MESSAGE_);
				logger.debug("调用js函数alert_msg，参数:SCENECODE_:{}, MESSAGE_:{}", new Object[] { SCENECODE_, MESSAGE_ });
				Collection<ScriptSession> sessions = Browser.getTargetSessions();
				for (ScriptSession scriptSession : sessions) {
					scriptSession.addScript(script);
				}
			}
		});
	}

}
