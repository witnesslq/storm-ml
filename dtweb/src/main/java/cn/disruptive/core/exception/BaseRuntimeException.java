package cn.disruptive.core.exception;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.apache.commons.lang.exception.Nestable;
import org.apache.commons.lang.exception.NestableDelegate;
import org.springframework.util.StringUtils;

import antlr.RecognitionException;
@SuppressWarnings("rawtypes")
public class BaseRuntimeException extends RuntimeException implements Nestable {
	private Throwable cause = null;
	private String errorCode = null;

	protected NestableDelegate delegate = new NestableDelegate(this);
	static final long serialVersionUID = 0L;

	public BaseRuntimeException() {
	}

	public BaseRuntimeException(String errorCode) {
		this.errorCode = errorCode;
	}

	public BaseRuntimeException(String errorCode, String msg) {
		super(msg);
		this.errorCode = errorCode;
	}

	public BaseRuntimeException(String errorCode, String msg, Throwable cause) {
		super(msg);
		this.errorCode = errorCode;
		this.cause = cause;
	}

	public Throwable getCause() {
		return this.cause;
	}

	public String getExceptionMessage() {
		if (super.getMessage() != null)
			return super.getMessage();
		if (this.cause != null) {
			return this.cause.toString();
		}
		return null;
	}

	public String getMessage() {
		if (StringUtils.hasText(this.errorCode))
			return ExceptionMessage.getMessage(this.errorCode);
		return getExceptionMessage();
	}

	public String getMessage(int index) {
		if (index == 0) {
			return super.getMessage();
		}
		return this.delegate.getMessage(index);
	}

	public String[] getMessages() {
		return this.delegate.getMessages();
	}

	public Throwable getThrowable(int index) {
		return this.delegate.getThrowable(index);
	}

	public int getThrowableCount() {
		return this.delegate.getThrowableCount();
	}

	public Throwable[] getThrowables() {
		return this.delegate.getThrowables();
	}

	public int indexOfThrowable( Class type) {
		return this.delegate.indexOfThrowable(type, 0);
	}

	public int indexOfThrowable(Class type, int fromIndex) {
		return this.delegate.indexOfThrowable(type, fromIndex);
	}

	public void printStackTrace() {
		this.delegate.printStackTrace();
	}

	public void printStackTrace(PrintStream out) {
		this.delegate.printStackTrace(out);
	}

	public void printStackTrace(PrintWriter out) {
		this.delegate.printStackTrace(out);
	}

	public final void printPartialStackTrace(PrintWriter out) {
		super.printStackTrace(out);
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		Throwable tempCause = this.cause;

		if (this.cause instanceof RecognitionException) {
			this.cause = null;
		}
		oos.defaultWriteObject();
		this.cause = tempCause;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
