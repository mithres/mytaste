package com.vc.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.Authentication;

import com.vc.core.adapter.VODApplicationAdapter;

public class ClientVO {

	private static final Logger log = Red5LoggerFactory.getLogger(VODApplicationAdapter.class, "VideoConference");

	private final Lock lock = new ReentrantLock();

	private String sessionID = null;
	
	private Authentication authentication = null;
	
	private Set<ConnectionVO> connectionVO = new HashSet<ConnectionVO>();
	
	public ClientVO() {

	}

	public ClientVO(String sessionID, Authentication auth) {
		this.sessionID = sessionID;
		this.authentication = auth;
	}

	public void setClientConnectionInfo(String clientId, String clientKey, String scopeName, String remoteAddress, int remotePort) {
		try {
			if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
				ConnectionVO vo = new ConnectionVO();
				vo.setClientKey(clientId);
				vo.setClientKey(clientKey);
				vo.setScopeName(scopeName);
				vo.setRemoteAddress(remoteAddress);
				vo.setRemotePort(remotePort);
				vo.setConnectSince(new Date());
				connectionVO.add(vo);
				lock.unlock();
			} else {
				log.error("Current thread want to lock client vo but the operation is failure.");
			}
		} catch (InterruptedException e) {
			log.error("Lock client vo error", e);
		}
	}

	public String getSessionID() {
		return sessionID;
	}
	
	public Authentication getAuthentication() {
		return authentication;
	}

	public Set<ConnectionVO> getConnectionVO() {
		return connectionVO;
	}

}