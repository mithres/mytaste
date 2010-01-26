package com.vc.service.vod;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.vc.bo.vod.VODClient;
import com.vc.util.security.AesCrypt;

@Service("vodClientManager")
public class VODClientManager implements IVODClientManager {

	private static final Logger log = Red5LoggerFactory.getLogger(VODClientManager.class, "VideoConference");

	private static HashMap<String, VODClient> VOD_CLIENT_LIST = new HashMap<String, VODClient>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.service.vod.IVODClientManager#addClientListItem(java.lang.String,
	 * java.lang.String, java.lang.Integer, java.lang.String)
	 */
	public synchronized VODClient addClientListItem(String streamId, String scopeName, Integer remotePort, String remoteAddress) {

		VODClient client = new VODClient();
		client.setClientID(streamId);
		client.setConnectSince(new Date());
		client.setRemoteAddress(remoteAddress);
		client.setRemotePort(remotePort);
		client.setScopeName(scopeName);
		client.setClientKey(AesCrypt.genKey());

		if (VOD_CLIENT_LIST.containsKey(streamId)) {
			log.error("Tried to add an existing Client " + streamId);
			return null;
		}

		VOD_CLIENT_LIST.put(streamId, client);

		return client;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.service.vod.IVODClientManager#getClientByID(java.lang.String)
	 */
	public synchronized VODClient getClientByID(String streamId) {
		Iterator<String> ite = VOD_CLIENT_LIST.keySet().iterator();
		while (ite.hasNext()) {
			log.info("-----------------" + ite.next());
		}
		return VOD_CLIENT_LIST.get(streamId);
	}

	@Override
	public synchronized void removeClient(String streamId) {

		if (VOD_CLIENT_LIST.containsKey(streamId)) {
			log.info("Remove client by id:" + streamId);
			VOD_CLIENT_LIST.remove(streamId);
		}
	}

}
