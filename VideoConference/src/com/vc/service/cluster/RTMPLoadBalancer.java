package com.vc.service.cluster;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.vc.util.configuration.ServerConfiguration;
import com.vc.vo.LBNode;

@Service("loadBalancer")
public class RTMPLoadBalancer implements ILoadBalancer {

	private static final Logger log = Red5LoggerFactory.getLogger(RTMPLoadBalancer.class, "VideoConference");
	// This set will distributed by terracotta
	private static List<LBNode> LOAD_BALANCERS = new ArrayList<LBNode>();

	private static LBNode node = null;

	@Override
	public LBNode getLBNode() {
		return LOAD_BALANCERS.get(0);
	}

	@Override
	public void registerLBNode(LBNode loadbalancer) {
		for (LBNode node : LOAD_BALANCERS) {
			if (node.equals(loadbalancer)) {
				LOAD_BALANCERS.remove(node);
				break;
			}
		}
		LOAD_BALANCERS.add(loadbalancer);
		if (ServerConfiguration.isCurrentHostRTMPServer()) {
			node = loadbalancer;
		}
	}

	@Override
	public synchronized void unregisterLBNode(List<LBNode> nodes) {
		LOAD_BALANCERS.removeAll(nodes);
	}

	@Override
	public LBNode getLocalHostLBNode() {
		return node;
	}

	@Override
	public synchronized void increaseConnection(LBNode node) {
		node.setConnections(node.getConnections() + 1);
		Collections.sort(LOAD_BALANCERS);
	}

	@Override
	public synchronized void reduceConnection(LBNode node) {
		if (node.getConnections() > 0) {
			node.setConnections(node.getConnections() - 1);
			Collections.sort(LOAD_BALANCERS);
		}
	}

	@Override
	public void checkLBNodeStatus() {

		Socket socket = null;
		ArrayList<LBNode> nodeNeedToRemove = null;

		for (LBNode node : LOAD_BALANCERS) {
			try {
				socket = new Socket(node.getNodeIP(), node.getPort());
				log.info("Server[" + node.getNodeIP() + ":" + node.getPort() + "] ok.");
			} catch (Exception e) {
				log.error("Could not connect to Server[" + node.getNodeIP() + ":" + node.getPort() + "].");
				if (nodeNeedToRemove == null) {
					nodeNeedToRemove = new ArrayList<LBNode>();
				}
				nodeNeedToRemove.add(node);
			}
		}
		socket = null;
		if (nodeNeedToRemove != null) {
			unregisterLBNode(nodeNeedToRemove);
		}
	}
}