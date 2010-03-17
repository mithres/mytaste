package com.vc.vo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;

import com.vc.core.constants.Constants;

public class LBNode implements Comparable<LBNode> {

	private static final Logger log = Red5LoggerFactory.getLogger(LBNode.class, "VideoConference");

	private final Lock lock = new ReentrantLock();

	private String nodeIP = null;

	private String protocol = "rtmp";

	private int port = 1935;

	private int connections = 0;

	public LBNode(String ip, int port, String protocol) {

		try {
			if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
				this.nodeIP = ip;
				this.port = port;
				this.protocol = protocol;
				lock.unlock();
			} else {
				log.error("Current thread want to lock client vo but the operation is failure.");
			}
		} catch (InterruptedException e) {
			log.error("Lock client vo error", e);
		}

	}

	public int getConnections() {
		return connections;
	}

	public String getVodServiceUrl() {
		return protocol + "://" + nodeIP + ":" + port + "/" + Constants.VOD_SCOPE_NAME;
	}

	public String getConferenceServiceUrl() {
		return protocol + "://" + nodeIP + ":" + port + "/" + Constants.CONFERENCE_SCOPE_NAME;
	}

	public String getNodeIP() {
		return nodeIP;
	}

	public void setNodeIP(String nodeIP) {
		this.nodeIP = nodeIP;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	@Override
	public int compareTo(LBNode o) {
		if (this.equals(o)) {
			return 0;
		}
		return this.connections - o.getConnections();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodeIP == null) ? 0 : nodeIP.hashCode());
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

		LBNode other = (LBNode) obj;
		if (nodeIP == null) {
			if (other.nodeIP != null) {
				return false;
			}
		} else if (!nodeIP.equals(other.nodeIP)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "[" + getNodeIP() + ":" + getPort() + "]" + getConnections() + ":" + getProtocol() + ":"
				+ getVodServiceUrl() + ":" + getConferenceServiceUrl();
	}

	public void setConnections(int connections) {
		this.connections = connections;
	}
}
