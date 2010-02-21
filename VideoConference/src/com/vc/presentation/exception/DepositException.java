package com.vc.presentation.exception;

public class DepositException extends Exception{

	private static final long serialVersionUID = -4527204855955330290L;
	
	private String message = null;
	
	public DepositException(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
