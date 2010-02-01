package com.vc.presentatioin.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.Authentication;
import org.springframework.security.ui.logout.LogoutHandler;

public class VCLogoutProcessingFilter implements LogoutHandler {

	public void logout(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) {
		
	}

}