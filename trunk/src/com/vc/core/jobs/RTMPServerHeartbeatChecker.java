package com.vc.core.jobs;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.vc.core.spring.ApplicationContextUtil;
import com.vc.service.cluster.ILoadBalancer;
import com.vc.vo.LBNode;

public class RTMPServerHeartbeatChecker extends QuartzJobBean {

	private static final Logger log = Red5LoggerFactory.getLogger(RTMPServerHeartbeatChecker.class, "VideoConference");

	private ILoadBalancer loadBalancer = null;

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		loadBalancer = (ILoadBalancer) ApplicationContextUtil.getApplicationContext().getBean(
				ILoadBalancer.LOAD_BALACENER_NAME);
		LBNode[] nodes = loadBalancer.getAllLBNodes();

		Socket socket = null;
		List<LBNode> nodeNeedToRemove = new ArrayList<LBNode>();

		for (LBNode node : nodes) {
			try {
				socket = new Socket(node.getNodeIP(), node.getPort());
			} catch (Exception e) {
				log.error(node.toString() + " error remove it.");
				nodeNeedToRemove.add(node);
			}
		}
		socket = null;
		if (nodeNeedToRemove.size() > 0) {
			loadBalancer.unregisterLBNode(nodeNeedToRemove);
		}
	}

}