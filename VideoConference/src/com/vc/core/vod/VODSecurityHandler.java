package com.vc.core.vod;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.api.IConnection;
import org.red5.server.api.IScope;
import org.red5.server.api.Red5;
import org.slf4j.Logger;

import com.vc.bo.vod.VODClient;
import com.vc.service.vod.IVODClientManager;

public class VODSecurityHandler {

	private static final Logger log = Red5LoggerFactory.getLogger(VODSecurityHandler.class, "VideoConference");

	private IVODClientManager vodClientManager = null;

	public String getSignature() {
		
		IConnection conn = Red5.getConnectionLocal();
		IScope scope = conn.getScope();
		
		VODClient client = vodClientManager.addClientListItem(conn.getClient().getId(), scope.getName(), conn.getRemotePort(), conn
				.getRemoteAddress());
		return client.getClientKey();
	}

	public void setVodClientManager(IVODClientManager vodClientManager) {
		this.vodClientManager = vodClientManager;
	}
}