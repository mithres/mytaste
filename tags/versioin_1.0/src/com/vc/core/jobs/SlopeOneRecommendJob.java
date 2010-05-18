package com.vc.core.jobs;

import java.util.Map;

import org.quartz.JobExecutionException;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;

import com.vc.service.recommendation.IRecommendationService;
import com.vc.service.recommendation.ItemId;
import com.vc.service.recommendation.SlopeOneRecommender;
import com.vc.service.recommendation.UserId;

public class SlopeOneRecommendJob {

	private static final Logger log = Red5LoggerFactory.getLogger(SlopeOneRecommendJob.class, "VideoConference");

	private IRecommendationService recommendationService = null;

	public void doJob() throws JobExecutionException {
		log.info("------------------Job start---------------------");
		recommendationService.cleanRatePredictData();
		
		Map<UserId, Map<ItemId, Double>> data = recommendationService.loadBasicRatingData();
		SlopeOneRecommender recommender = new SlopeOneRecommender(data);
		
		for (UserId id : data.keySet()) {
			Map<ItemId, Double> temp = recommender.predict(data.get(id));
			recommendationService.updateRatePredictData(id, temp);
		}

	}

	public void setRecommendationService(IRecommendationService recommendationService) {
		this.recommendationService = recommendationService;
	}

}