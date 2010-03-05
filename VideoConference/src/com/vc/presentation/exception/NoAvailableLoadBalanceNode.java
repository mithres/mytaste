package com.vc.presentation.exception;

public class NoAvailableLoadBalanceNode extends RuntimeException {

	private static final long serialVersionUID = 5635876228334603726L;

	private String message = null;

	public NoAvailableLoadBalanceNode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
