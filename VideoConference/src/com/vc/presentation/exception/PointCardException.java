package com.vc.presentation.exception;

public class PointCardException extends Exception{

	private static final long serialVersionUID = 6454590528075951282L;
	
	private String message = null;
	
	public PointCardException(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
