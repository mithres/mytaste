package com.vc.vo;

import java.util.Date;
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

	private String clientID = null;

	private String clientKey = null;

	private String scopeName = null;

	private Date connectSince = null;

	private String remoteAddress = null;

	private int remotePort = 0;

	private Authentication authentication = null;

	public ClientVO() {

	}

	public ClientVO(String sessionID, Authentication auth) {
		this.sessionID = sessionID;
		this.authentication = auth;
	}

	public void setClientConnectionInfo(String clientId, String clientKey, String scopeName, String remoteAddress, int remotePort) {
		try {
			if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
				this.clientID = clientId;
				this.clientKey = clientKey;
				this.scopeName = scopeName;
				this.remoteAddress = remoteAddress;
				this.remotePort = remotePort;
				this.connectSince = new Date();
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

	public String getClientID() {
		return clientID;
	}

	public String getClientKey() {
		return clientKey;
	}

	public String getScopeName() {
		return scopeName;
	}

	public Date getConnectSince() {
		return connectSince;
	}

	public String getRemoteAddress() {
		return remoteAddress;
	}

	public int getRemotePort() {
		return remotePort;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

}