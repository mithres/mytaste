package com.vc.core.client;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.ClientRegistry;
import org.red5.server.api.IClient;
import org.red5.server.exception.ClientNotFoundException;
import org.red5.server.exception.ClientRejectedException;
import org.slf4j.Logger;

public class ClientRegister extends ClientRegistry {

	private static final Logger log = Red5LoggerFactory.getLogger(ClientRegister.class, "VideoConference");

	@Override
	public IClient newClient(Object[] params) throws ClientNotFoundException, ClientRejectedException {

		return super.newClient(params);
	}

}
