package com.vc.service.cluster;

import java.util.TreeSet;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.vc.util.configuration.ServerConfiguration;
import com.vc.vo.LBNode;

@Service("loadBalancer")
public class RTMPLoadBalancer implements ILoadBalancer {

	private static final Logger log = Red5LoggerFactory.getLogger(RTMPLoadBalancer.class, "VideoConference");
	// This set will distributed by terracotta
	private static TreeSet<LBNode> LOAD_BALANCERS = new TreeSet<LBNode>();

	private static LBNode node = null;
	
	@Override
	public LBNode getLBNode() {
		return LOAD_BALANCERS.first();
	}

	@Override
	public void registerLBNode(LBNode loadbalancer) {
		LOAD_BALANCERS.add(loadbalancer);
		if (ServerConfiguration.isCurrentHostRTMPServer()) {
			node = loadbalancer;
		}
	}
	
	@Override
	public void unregisterLBNode(LBNode loadbalancer) {
		//TODO: need implement to unregister a load balancer node
		
	}
	
	@Override
	public LBNode getLocalHostLBNode() {
		return node;
	}

}