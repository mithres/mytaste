package com.vc.core.vod;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.api.IConnection;
import org.red5.server.api.IScope;
import org.red5.server.api.Red5;
import org.slf4j.Logger;

import com.vc.service.cluster.IClientManager;
import com.vc.vo.ClientVO;

public class VODSecurityHandler {

	private static final Logger log = Red5LoggerFactory.getLogger(VODSecurityHandler.class, "VideoConference");

	private IClientManager vodClientManager = null;

	public String getSignature() {
		
		IConnection conn = Red5.getConnectionLocal();
		IScope scope = conn.getScope();
		
		ClientVO client = vodClientManager.addClientListItem(conn.getClient().getId(), scope.getName(), conn.getRemotePort(), conn
				.getRemoteAddress());
		return client.getClientKey();
	}

	public void setVodClientManager(IClientManager vodClientManager) {
		this.vodClientManager = vodClientManager;
	}
}