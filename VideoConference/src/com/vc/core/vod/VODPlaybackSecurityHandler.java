package com.vc.core.vod;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.api.IScope;
import org.red5.server.api.stream.IStreamPlaybackSecurity;
import org.slf4j.Logger;

public class VODPlaybackSecurityHandler implements IStreamPlaybackSecurity {
	
	private static final Logger log = Red5LoggerFactory.getLogger(VODPlaybackSecurityHandler.class, "VideoConference");
	
	@Override
	public boolean isPlaybackAllowed(IScope scope, String arg1, int arg2, int arg3, boolean arg4) {
		
		log.info(scope.getAttribute("Signature").toString());
		return true;
	}

}
