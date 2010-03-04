package com.vc.presentation.exception;

public class PointCardException extends Exception {

	private static final long serialVersionUID = 6454590528075951282L;

	private String messageKey = null;
	private String message = null;

	public PointCardException(String message) {
		this.message = message;
	}

	public PointCardException(String message, String messageKey) {
		this.message = message;
		this.messageKey = messageKey;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

}
