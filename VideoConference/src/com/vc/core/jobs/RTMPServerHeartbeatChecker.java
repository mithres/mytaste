package com.vc.core.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.vc.core.spring.ApplicationContextUtil;
import com.vc.service.cluster.ILoadBalancer;

public class RTMPServerHeartbeatChecker extends QuartzJobBean {

	private static final Logger log = Red5LoggerFactory.getLogger(RTMPServerHeartbeatChecker.class, "VideoConference");

	private ILoadBalancer loadBalancer = null;

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		loadBalancer = (ILoadBalancer) ApplicationContextUtil.getApplicationContext().getBean(ILoadBalancer.LOAD_BALACENER_NAME);
		loadBalancer.checkLBNodeStatus();
	}

}