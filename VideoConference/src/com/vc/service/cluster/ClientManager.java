package com.vc.service.cluster;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.vc.util.security.AesCrypt;
import com.vc.vo.ClientVO;

@Service("vodClientManager")
public class ClientManager implements IClientManager {
	
	//TODO:Need test concurrent map effect
	
	private static final Logger log = Red5LoggerFactory.getLogger(ClientManager.class, "VideoConference");

	private static ConcurrentMap<String, ClientVO> CLIENT_LIST = new ConcurrentHashMap<String, ClientVO>();
	
	private static ConcurrentMap<String, String> CLIENT_SESSION_LIST = new ConcurrentHashMap<String, String>();
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.service.vod.IVODClientManager#addClientListItem(java.lang.String,
	 * java.lang.String, java.lang.Integer, java.lang.String)
	 */
	public ClientVO addClientListItem(String streamId, String scopeName, Integer remotePort, String remoteAddress) {

		ClientVO client = new ClientVO();
		client.setClientID(streamId);
		client.setConnectSince(new Date());
		client.setRemoteAddress(remoteAddress);
		client.setRemotePort(remotePort);
		client.setScopeName(scopeName);
		client.setClientKey(AesCrypt.genKey());

		if (CLIENT_LIST.containsKey(streamId)) {
			log.error("Tried to add an existing Client " + streamId);
			return null;
		}

		CLIENT_LIST.put(streamId, client);

		return client;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.service.vod.IVODClientManager#getClientByID(java.lang.String)
	 */
	public ClientVO getClientByID(String streamId) {
		return CLIENT_LIST.get(streamId);
	}

	@Override
	public void removeClient(String streamId) {

		if (CLIENT_LIST.containsKey(streamId)) {
			log.info("Remove client by id:" + streamId);
			CLIENT_LIST.remove(streamId);
		}
	}

}
