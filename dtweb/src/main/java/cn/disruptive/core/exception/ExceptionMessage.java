package cn.disruptive.core.exception;

import cn.disruptive.core.ConfigurableConstants;

public class ExceptionMessage extends ConfigurableConstants {
	static {
		init("cn/disruptive/core/exception/errorCode.properties", "UTF-8");
	}

	public static String getMessage(String errorCode) {
		return getProperty(errorCode, "未定义的异常代码");
	}
}
