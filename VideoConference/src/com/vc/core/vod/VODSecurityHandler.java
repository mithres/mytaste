package com.vc.core.vod;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;

import com.vc.util.security.ServerUtil;

public class VODSecurityHandler {

	private static final Logger log = Red5LoggerFactory.getLogger(VODSecurityHandler.class, "VideoConference");

	public String getSignature() {
		return ServerUtil.getCurrentDate();
	}

}