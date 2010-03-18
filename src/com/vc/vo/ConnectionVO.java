package com.vc.vo;

import java.util.Date;

public class ConnectionVO {

	private String clientKey = null;

	private String clientID = null;

	private String scopeName = null;

	private Date connectSince = null;

	private String remoteAddress = null;

	private int remotePort = 0;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clientID == null) ? 0 : clientID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		ConnectionVO other = (ConnectionVO) obj;
		if (clientID == null) {
			if (other.clientID != null) {
				return false;
			}
		} else if (!clientID.equals(other.clientID)) {
			return false;
		}
		return true;
	}

	
	public String getClientKey() {
		return clientKey;
	}

	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
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

	public int getRemotePort() {
		return remotePort;
	}

	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}

}
