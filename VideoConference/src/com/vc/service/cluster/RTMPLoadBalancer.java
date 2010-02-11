package com.vc.service.cluster;

import org.springframework.stereotype.Service;

import com.vc.vo.LBNode;

@Service("loadBalancer")
public class RTMPLoadBalancer implements ILoadBalancer {

	@Override
	public LBNode getLBNode() {
		
		LBNode node = new LBNode();
		node.setNodeIP("192.168.0.119");
		
		return node;
	}
	
}