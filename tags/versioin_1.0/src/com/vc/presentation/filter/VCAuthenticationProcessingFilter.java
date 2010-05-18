package com.vc.presentation.filter;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationException;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.ui.WebAuthenticationDetails;
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter;

import com.octo.captcha.service.CaptchaServiceException;
import com.vc.core.constants.Constants;
import com.vc.core.spring.ApplicationContextUtil;
import com.vc.presentation.action.captcha.CaptchaServiceSingleton;
import com.vc.presentation.exception.CaptchaCodeCheckException;
import com.vc.service.cluster.IClientManager;

public class VCAuthenticationProcessingFilter extends AuthenticationProcessingFilter {

	private final Logger log = Red5LoggerFactory.getLogger(VCAuthenticationProcessingFilter.class, "VideoConference");

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request) throws AuthenticationException {

		String ccode = request.getParameter("ccode");
		if (ccode == null || ccode.trim().length() == 0) {
			throw new CaptchaCodeCheckException("CheckCodeError");
		}

		boolean result = false;
		try {
			result = CaptchaServiceSingleton.getInstance().validateResponseForID(request.getSession().getId(), ccode);
		} catch (CaptchaServiceException e) {
			throw new CaptchaCodeCheckException("CheckCodeError");
		}

		if (result) {
			createCaptchaTicket(request.getSession());
		} else {
			acquireCaptchaTicket(request.getSession());
			throw new CaptchaCodeCheckException("CheckCodeError");
		}

		return super.attemptAuthentication(request);
	}

	@Override
	protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			Authentication authResult) throws IOException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication.getDetails() instanceof WebAuthenticationDetails) {
			IClientManager clientManager = (IClientManager) ApplicationContextUtil.getApplicationContext().getBean(
					"clientManager");
			WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
			clientManager.addClientListItem(details.getSessionId(), authentication);

			String message = "{ \"success\" : true,\"targetUrl\":\"" + this.getDefaultTargetUrl() + "\"}";
			writeMessageToClient(response, message);
		}

	}

	@Override
	protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException {

		String message = "{ \"success\": false, \"errors\": \"" + failed.getLocalizedMessage() + "\"}";
		writeMessageToClient(response, message);
	}

	private void writeMessageToClient(HttpServletResponse response, String jsonMessage) {
		HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);
		try {
			Writer out = responseWrapper.getWriter();
			out.write(jsonMessage);
			out.close();
		} catch (IOException e) {
			logger.error("return login state message to client error.", e);
		}
	}

	private boolean acquireCaptchaTicket(HttpSession session) {
		synchronized (session) {
			if (session.getAttribute(Constants.CAPTCHA_TICKET) == null) {
				return false;
			}
			session.removeAttribute(Constants.CAPTCHA_TICKET);
			return true;
		}

	}

	private void createCaptchaTicket(HttpSession session) {
		session.setAttribute(Constants.CAPTCHA_TICKET, new Date());
	}

}
