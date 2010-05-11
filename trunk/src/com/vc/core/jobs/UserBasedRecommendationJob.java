package com.vc.core.jobs;

import javax.sql.DataSource;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.similarity.AveragingPreferenceInferrer;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.quartz.JobExecutionException;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;

import com.vc.service.recommendation.IRecommendationService;
import com.vc.service.recommendation.PlayListDataModel;

public class UserBasedRecommendationJob {

	private static final Logger log = Red5LoggerFactory.getLogger(UserBasedRecommendationJob.class, "VideoConference");

	private IRecommendationService recommendationService = null;

	private DataSource ds = null;

	public void doJob() throws JobExecutionException {
		log.info("------------------UserBasedRecommendationJob start---------------------");
		
		try {
			
			PlayListDataModel model = new PlayListDataModel(ds);
			UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(model);
			userSimilarity.setPreferenceInferrer(new AveragingPreferenceInferrer(model));
			UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, userSimilarity, model);
			
		} catch (TasteException e) {
			log.error("Get user neighborhood error ", e);
		}

	}

	public void setRecommendationService(IRecommendationService recommendationService) {
		this.recommendationService = recommendationService;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}

}