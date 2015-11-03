package cn.disruptive.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.disruptive.core.ConfigurableConstants;


/**
 * 系统常量类
 */
public class Constants extends ConfigurableConstants {
	public static final Logger logger = LoggerFactory.getLogger(Constants.class);
	private static Constants _instance=new Constants();
	synchronized public static Constants getInstance(){
		return _instance;
	}	
	
	static {
		logger.info("常量赋值");
		init("cn/disruptive/common/util/Constants.properties", "UTF-8");
	
	}
	public static final int PAGE_SIZE = Integer.parseInt(getProperty("PAGE_SIZE", "10"));
	public static final String USER_SESSION_KEY = "web_user";
}
