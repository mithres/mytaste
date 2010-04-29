package com.vc.core.jobs;

import org.quartz.JobExecutionException;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;

import com.vc.service.recommendation.HibernateDataModel;

public class SlopeOneRecommender {

	private static final Logger log = Red5LoggerFactory.getLogger(SlopeOneRecommender.class, "VideoConference");
	
	private HibernateDataModel hibernateDataModel = null;
	
	
	public void doJob() throws JobExecutionException {
		log.info("------------------Job start---------------------"+hibernateDataModel);
	}

	public HibernateDataModel getHibernateDataModel() {
		return hibernateDataModel;
	}

	public void setHibernateDataModel(HibernateDataModel hibernateDataModel) {
		this.hibernateDataModel = hibernateDataModel;
	}

}
