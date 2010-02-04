package com.vc.service.cluster;

import com.vc.vo.LBNode;

public interface ILoadBalancer {

	public abstract LBNode getLBNode();

}
