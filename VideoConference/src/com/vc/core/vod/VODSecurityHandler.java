package com.vc.core.vod;

import java.util.UUID;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.api.IClient;
import org.red5.server.api.IConnection;
import org.red5.server.api.Red5;
import org.slf4j.Logger;

import com.vc.core.adapter.CoreApplicationAdapter;

public class VODSecurityHandler {

	private static final Logger log = Red5LoggerFactory.getLogger(VODSecurityHandler.class, "VideoConference");

	public String getSignature() {

		IConnection conn = Red5.getConnectionLocal();
		IClient client = conn.getClient();
		String uuid = UUID.randomUUID().toString();
		
		log.debug(" Client id:" + client.getId() + ":" + uuid);
		
		CoreApplicationAdapter.VOD_CLIENT_SIGNATURE.put(client.getId(), uuid);
		
		return uuid;
	}

}