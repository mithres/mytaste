package com.vc.service.cluster;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.Authentication;
import org.springframework.stereotype.Service;

import com.vc.vo.ClientVO;

@Service
public class ClientManager implements IClientManager {

	private static final Logger log = Red5LoggerFactory.getLogger(ClientManager.class, "VideoConference");

	private static ConcurrentMap<String, ClientVO> CLIENT_LIST = new ConcurrentHashMap<String, ClientVO>();

	@Override
	public ClientVO addClientListItem(String sessionID, Authentication auth) {
		ClientVO vo = new ClientVO(sessionID, auth);
		CLIENT_LIST.put(sessionID, vo);
		log.info("Put sessionId:" + sessionID);
		return vo;
	}

	@Override
	public ClientVO getClientBySessionID(String sessionID) {
		return CLIENT_LIST.get(sessionID);
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
