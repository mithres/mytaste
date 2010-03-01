package com.vc.service.cluster;

import com.vc.vo.LBNode;

public interface ILoadBalancer {
	
	public static final String LOAD_BALACENER_NAME = "loadBalancer";
	
	public abstract void registerLBNode(LBNode loadbalancer);
	
	public abstract void unregisterLBNode(LBNode loadbalancer);

	public abstract LBNode getLBNode();
	
	public abstract LBNode getLocalHostLBNode();

}
