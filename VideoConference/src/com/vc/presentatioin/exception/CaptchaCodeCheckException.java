package com.vc.presentatioin.exception;

import org.springframework.security.AuthenticationException;

public class CaptchaCodeCheckException extends AuthenticationException {

	private static final long serialVersionUID = 1184052781322587588L;

	public CaptchaCodeCheckException(String msg) {
		super(msg);
	}
	

}
