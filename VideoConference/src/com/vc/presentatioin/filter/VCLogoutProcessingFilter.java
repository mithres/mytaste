package com.vc.presentatioin.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.ui.logout.LogoutHandler;

import com.vc.service.cluster.IClientManager;

public class VCLogoutProcessingFilter implements LogoutHandler {

	@Autowired
	private IClientManager clientManager = null;

	public void logout(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) {
		// Remove clientvo from cache
		clientManager.removeClient(request.getSession().getId());
	}

}