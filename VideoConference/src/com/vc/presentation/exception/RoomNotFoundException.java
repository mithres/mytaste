package com.vc.presentation.exception;

public class RoomNotFoundException extends Exception {

	private static final long serialVersionUID = -1471982019060726835L;

	private String messageKey = null;
	private String message = null;

	public RoomNotFoundException(String message) {
		this.message = message;
	}

	public RoomNotFoundException(String message, String messageKey) {
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
