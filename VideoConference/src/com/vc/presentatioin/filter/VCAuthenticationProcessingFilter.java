package com.vc.presentatioin.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.ui.WebAuthenticationDetails;
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter;

import com.vc.core.spring.ApplicationContextUtil;
import com.vc.service.cluster.IClientManager;

public class VCAuthenticationProcessingFilter extends AuthenticationProcessingFilter {

	private final Logger log = Red5LoggerFactory.getLogger(VCAuthenticationProcessingFilter.class, "VideoConference");

	@Override
	protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult)
			throws IOException {
		super.onSuccessfulAuthentication(request, response, authResult);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getDetails() instanceof WebAuthenticationDetails) {
			IClientManager clientManager = (IClientManager) ApplicationContextUtil.getApplicationContext().getBean("clientManager");
			WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
			clientManager.addClientListItem(details.getSessionId(), authentication);
			log.info("Client sid :" + details.getSessionId());
		}

	}

}
