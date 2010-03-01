package com.vc.service.cluster;

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
	public void unregisterLBNode(LBNode loadbalancer) {
		// TODO: need implement to unregister a load balancer node

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

}