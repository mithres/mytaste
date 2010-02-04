package com.vc.service.cluster;

import org.springframework.stereotype.Service;

import com.vc.vo.LBNode;

@Service("loadBalancer")
public class RTMPLoadBalancer implements ILoadBalancer {

	@Override
	public LBNode getLBNode() {
		
		LBNode node = new LBNode();
		node.setNodeIP("172.0.2.193");
		
		return node;
	}
	
}