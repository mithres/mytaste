package com.vc.presentation.action.captcha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.octo.captcha.service.CaptchaServiceException;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.vc.core.action.BaseAction;
import com.vc.core.constants.Constants;
import com.vc.util.http.HeaderCacheUtil;

public class CaptchaAction extends BaseAction {

	private static final long serialVersionUID = 2627633413354929360L;

	private String code;

	/**
	 * Action method for <code>/captcha/text</code> Verify CAPTCHA text.
	 * 
	 * @return
	 */
	public String verify() {
		render(code);
		return ActionSupport.SUCCESS;
	}

	/**
	 * @param context
	 * @return
	 */
	public String process() {

		byte[] captchaChallengeAsJpeg = null;
		// the output stream to render the captcha image as jpeg into
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		try {
			// a jpeg encoder
			JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(jpegOutputStream);
			jpegEncoder.encode(loadCheckCode());
			captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
			outputCheckCode(response, captchaChallengeAsJpeg);

		} catch (Exception e) {
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} catch (Exception e2) {
			}
			return Action.ERROR;
		}

		return Action.NONE;
	}

	private BufferedImage loadCheckCode() {
		// get the session id that will identify the generated captcha.
		// the same id must be used to validate the response, the session id
		// is a good candidate!
		String captchaId = this.getSession().getId();
		// call the ImageCaptchaService getChallenge method
		BufferedImage challenge = CaptchaServiceSingleton.getInstance().getImageChallengeForID(captchaId, request.getLocale());
		return challenge;
	}

	private void outputCheckCode(HttpServletResponse response, byte[] captchaChallengeAsJpeg) throws IOException {

		// flush it in the response
		HeaderCacheUtil.setNoCacheHeaders(response);
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		ServletOutputStream responseOutputStream = response.getOutputStream();
		responseOutputStream.write(captchaChallengeAsJpeg);
		responseOutputStream.flush();
		responseOutputStream.close();

	}

	/**
	 * validate code
	 * 
	 * @param context
	 * @param code
	 */
	public void render(String code) {
		try {
			boolean result = CaptchaServiceSingleton.getInstance().validateResponseForID(getSession().getId(), code);
			if (result) {
				createCaptchaTicket();
				getResponse().setStatus(HttpServletResponse.SC_OK);
			} else {
				acquireCaptchaTicket(getSession());
				getResponse().setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		} catch (CaptchaServiceException e) {
			acquireCaptchaTicket(getSession());
			getResponse().setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	public static boolean acquireCaptchaTicket(HttpSession session) {
		synchronized (session) {
			if (session.getAttribute(Constants.CAPTCHA_TICKET) == null) {
				return false;
			}
			session.removeAttribute(Constants.CAPTCHA_TICKET);
			return true;
		}

	}

	protected void createCaptchaTicket() {
		getSession().setAttribute(Constants.CAPTCHA_TICKET, new Date());
	}

	/**
	 * @return the response
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param response
	 *            the response to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
