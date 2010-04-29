package com.vc.core.jobs;

import org.quartz.JobExecutionException;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;

public class SlopeOneRecommender {

	private static final Logger log = Red5LoggerFactory.getLogger(SlopeOneRecommender.class, "VideoConference");

	public void doJob() throws JobExecutionException {
		log.info("------------------Job start---------------------");
	}

}
