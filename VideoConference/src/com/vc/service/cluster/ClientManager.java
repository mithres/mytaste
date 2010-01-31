package com.vc.service.cluster;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.Authentication;
import org.springframework.stereotype.Service;

import com.vc.util.security.AesCrypt;
import com.vc.vo.ClientVO;

@Service
public class ClientManager implements IClientManager {

	private static final Logger log = Red5LoggerFactory.getLogger(ClientManager.class, "VideoConference");

	// TODO:Need test concurrent map effects
	private static ConcurrentMap<String, ClientVO> CLIENT_LIST = new ConcurrentHashMap<String, ClientVO>();

	@Override
	public ClientVO addClientListItem(String sessionID, Authentication auth) {
		ClientVO vo = new ClientVO(sessionID, auth);
		CLIENT_LIST.put(sessionID, vo);
		return vo;
	}

	@Override
	public ClientVO getClientBySessionID(String sessionID) {
		return CLIENT_LIST.get(sessionID);
	}

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
