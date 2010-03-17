package com.vc.service.cluster;

import java.util.List;

import com.vc.presentation.exception.NoAvailableLoadBalanceNode;
import com.vc.vo.LBNode;

public interface ILoadBalancer {

	public static final String LOAD_BALACENER_NAME = "loadBalancer";

	public abstract void registerLBNode(LBNode loadbalancer);

	public abstract void unregisterLBNode(List<LBNode> nodes);

	public abstract LBNode getLBNode() throws NoAvailableLoadBalanceNode;

	public abstract LBNode getLocalHostLBNode();

	public abstract void increaseConnection(LBNode node);

	public abstract void reduceConnection(LBNode node);

	public abstract LBNode[] getAllLBNodes();

}
