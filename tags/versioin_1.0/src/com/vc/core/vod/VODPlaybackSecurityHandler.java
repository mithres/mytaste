package com.vc.core.vod;

import org.apache.commons.lang.ArrayUtils;
import org.red5.logging.Red5LoggerFactory;
import org.red5.server.api.IConnection;
import org.red5.server.api.IScope;
import org.red5.server.api.Red5;
import org.red5.server.api.service.IServiceCapableConnection;
import org.red5.server.api.stream.IStreamPlaybackSecurity;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;

import com.vc.core.adapter.SecurityHandlerBase;
import com.vc.core.constants.Constants;
import com.vc.service.user.IUserService;
import com.vc.util.configuration.ServerConfiguration;

public class VODPlaybackSecurityHandler extends SecurityHandlerBase implements IStreamPlaybackSecurity {

	private static final Logger log = Red5LoggerFactory.getLogger(VODPlaybackSecurityHandler.class, "VideoConference");

	private Boolean HTMLDomainsAuth = true;
	private Boolean SWFDomainsAuth = true;

	private String[] allowedHTMLDomains;
	private String[] allowedSWFDomains;

	@Autowired
	private IUserService userService = null;

	@Override
	public boolean isPlaybackAllowed(IScope scope, String name, int arg2, int arg3, boolean arg4) {

		IConnection conn = Red5.getConnectionLocal();

		try {

			String pageUrl = conn.getConnectParams().get("pageUrl").toString();
			String swfUrl = conn.getConnectParams().get("swfUrl").toString();
			String ip = conn.getRemoteAddress();

			if ((ip != Constants.LOCAL_IP) && HTMLDomainsAuth && !this.validate(pageUrl, this.allowedHTMLDomains)) {
				log.debug("Authentication failed for pageurl: " + pageUrl + ", rejecting connection from " + ip);
				return false;
			}

			if ((ip != Constants.LOCAL_IP) && SWFDomainsAuth && !this.validate(swfUrl, this.allowedSWFDomains)) {
				log.debug("Authentication failed for referrer: " + swfUrl + ", rejecting connection from " + ip);
				return false;
			}
		} catch (Exception e) {
			if (HTMLDomainsAuth || SWFDomainsAuth) {
				return false;
			}
		}

		boolean result = userService.playVod(SecurityContextHolder.getContext().getAuthentication(), name);

		if (!result) {
			
			if (conn instanceof IServiceCapableConnection) {
				IServiceCapableConnection sc = (IServiceCapableConnection) conn;
				sc.invoke("showErrorMessage", new Object[] { "Sorry you have no enough money to play film." });
			}
		}

		return result;
	}

	public void init() {

		allowedHTMLDomains = ServerConfiguration.getAllowedHTMLDomains().split(",");
		allowedSWFDomains = ServerConfiguration.getAllowedSWFDomains().split(",");

		if (this.HTMLDomainsAuth) {
			log.info("Authentication of HTML page URL domains is enabled");
		}
		if (this.SWFDomainsAuth) {
			log.info("Authentication of SWF URL domains is enabled");
		}

		log.info("...loading completed.");
	}

	private Boolean validate(String url, String[] patterns) {
		// Convert to lower case
		url = url.toLowerCase();
		int domainStartPos = 0; // domain start position in the URL
		int domainEndPos = 0; // domain end position in the URL

		switch (url.indexOf("://")) {
		case 4:
			if (url.indexOf("http://") == 0)
				domainStartPos = 7;
			break;
		case 5:
			if (url.indexOf("https://") == 0)
				domainStartPos = 8;
			break;
		}
		if (domainStartPos == 0) {
			// URL must be HTTP or HTTPS protocol based
			return false;
		}
		domainEndPos = url.indexOf("/", domainStartPos);
		if (domainEndPos > 0) {
			int colonPos = url.indexOf(":", domainStartPos);
			if ((colonPos > 0) && (domainEndPos > colonPos)) {
				// probably URL contains a port number
				domainEndPos = colonPos; // truncate the port number in the URL
			}
		}

		url = url.substring(domainStartPos, domainEndPos);

		if (ArrayUtils.indexOf(patterns, url) > 0) {
			return true;
		}

		return false;
	}

}