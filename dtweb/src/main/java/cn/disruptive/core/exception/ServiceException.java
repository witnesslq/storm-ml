package cn.disruptive.core.exception;

public class ServiceException extends BaseRuntimeException {
	private static final long serialVersionUID = -5546352153414771000L;

	public ServiceException(String errorCode) {
		super(errorCode);
	}

	public ServiceException(String errorCode, String message) {
		super(errorCode, message);
	}

	public ServiceException(String errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}
}
