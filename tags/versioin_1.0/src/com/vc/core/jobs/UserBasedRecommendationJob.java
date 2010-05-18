package com.vc.core.jobs;

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
import com.vc.service.user.IUserService;
import com.vc.service.vod.IPlayListService;

public class UserBasedRecommendationJob {

	private static final Logger log = Red5LoggerFactory.getLogger(UserBasedRecommendationJob.class, "VideoConference");

	private IUserService userService = null;
	private IPlayListService playListService = null;
	private IRecommendationService recommendationService = null;

	public void doJob() throws JobExecutionException {
		//TODO: need do more test
		log.info("------------------UserBasedRecommendationJob start---------------------");

		try {

			PlayListDataModel model = new PlayListDataModel(playListService);
			UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(model);
			userSimilarity.setPreferenceInferrer(new AveragingPreferenceInferrer(model));
			UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, userSimilarity, model);
			
			
			
			long[] ids = neighborhood.getUserNeighborhood(9);
			log.info("ids--------------" + ids.length);
		} catch (TasteException e) {
			log.error("Get user neighborhood error ", e);
		}

	}

	public void setRecommendationService(IRecommendationService recommendationService) {
		this.recommendationService = recommendationService;
	}

	public void setPlayListService(IPlayListService playListService) {
		this.playListService = playListService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}