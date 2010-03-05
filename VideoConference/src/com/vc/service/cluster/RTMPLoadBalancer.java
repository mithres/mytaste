package com.vc.service.cluster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.vc.presentation.exception.NoAvailableLoadBalanceNode;
import com.vc.util.configuration.ServerConfiguration;
import com.vc.vo.LBNode;

@Service("loadBalancer")
public class RTMPLoadBalancer implements ILoadBalancer {

	private static final Logger log = Red5LoggerFactory.getLogger(RTMPLoadBalancer.class, "VideoConference");

	// This set will distributed by terracotta
	private static List<LBNode> LOAD_BALANCERS = new ArrayList<LBNode>();

	private static LBNode node = null;

	@Override
	public LBNode getLBNode() throws NoAvailableLoadBalanceNode {
		if (LOAD_BALANCERS.size() > 0) {
			return LOAD_BALANCERS.get(0);
		}
		throw new NoAvailableLoadBalanceNode("No Available LoadBalance Node");
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
	public LBNode[] getAllLBNodes() {
		LBNode[] addressNeedToTest = new LBNode[LOAD_BALANCERS.size()];
		for (int i = 0; i < addressNeedToTest.length; i++) {
			addressNeedToTest[i] = LOAD_BALANCERS.get(i);
		}
		return addressNeedToTest;
	}
}