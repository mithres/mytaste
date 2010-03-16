package com.vc.presentation.exception;

public class RoomPeopleFullException extends Exception {

	private static final long serialVersionUID = 4660076988567104731L;
	
	private String messageKey = null;
	private String message = null;

	public RoomPeopleFullException(String message) {
		this.message = message;
	}

	public RoomPeopleFullException(String message, String messageKey) {
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
