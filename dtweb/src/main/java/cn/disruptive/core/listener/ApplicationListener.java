package cn.disruptive.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public final class ApplicationListener implements ServletContextListener {
//	private static final Logger log = LoggerFactory.getLogger(ApplicationListener.class);
	
	public void contextDestroyed(ServletContextEvent event) {
	}

	public void contextInitialized(ServletContextEvent event) {
		//加载初始数据
	}
}
