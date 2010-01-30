package com.vc.vo;

import java.util.Date;

import org.springframework.security.Authentication;

public class ClientVO {
	
	private String clientID = null;
	
	private String clientKey = null;
	
	private String scopeName = null;
	
	private Date connectSince = null;
	
	private String remoteAddress = null;
	
	private Integer remotePort = null;
	
	private Authentication authentication = null;

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getClientKey() {
		return clientKey;
	}

	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}

	public String getScopeName() {
		return scopeName;
	}

	public void setScopeName(String scopeName) {
		this.scopeName = scopeName;
	}

	public Date getConnectSince() {
		return connectSince;
	}

	public void setConnectSince(Date connectSince) {
		this.connectSince = connectSince;
	}

	public String getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public Integer getRemotePort() {
		return remotePort;
	}

	public void setRemotePort(Integer remotePort) {
		this.remotePort = remotePort;
	}
	
	
	
}
