package com.vc.vo;

public class LBNode {

	private String nodeIP = null;

	private int port = 1935;

	private String protocol = "rtmp";

	public String getUrl() {
		return protocol + "://" + nodeIP + ":" + port+"/vod";
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

}
