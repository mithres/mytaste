package com.vc.presentation.exception;

public class FilePersistException extends RuntimeException {

	private static final long serialVersionUID = -3460138619682873069L;

	private String message = null;

	private Exception e = null;

	public FilePersistException(String message) {
		this.message = message;
	}

	public FilePersistException(String message, Exception e) {
		this.message = message;
		this.e = e;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Exception getE() {
		return e;
	}

	public void setE(Exception e) {
		this.e = e;
	}

}
