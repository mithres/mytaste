package com.vc.core.jobs;

import java.util.Map;

import org.quartz.JobExecutionException;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;

import com.vc.service.recommendation.ItemId;
import com.vc.service.recommendation.SlopeOneRecommender;
import com.vc.service.recommendation.UserId;
import com.vc.service.vod.IPlayListService;

public class SlopeOneRecommendJob {

	private static final Logger log = Red5LoggerFactory.getLogger(SlopeOneRecommendJob.class, "VideoConference");

	private IPlayListService playListService = null;

	public void doJob() throws JobExecutionException {
		log.info("------------------Job start---------------------");
		Map<UserId, Map<ItemId, Double>> data = playListService.loadBasicRatingData();
		SlopeOneRecommender recommender = new SlopeOneRecommender(data);
	}

	public IPlayListService getPlayListService() {
		return playListService;
	}

	public void setPlayListService(IPlayListService playListService) {
		this.playListService = playListService;
	}

}
