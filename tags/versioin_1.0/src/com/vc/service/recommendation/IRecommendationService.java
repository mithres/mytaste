package com.vc.service.recommendation;

import java.util.List;
import java.util.Map;

import com.vc.core.dao.Hints;
import com.vc.entity.UserRatePredict;

public interface IRecommendationService {

	public abstract Map<UserId, Map<ItemId, Double>> loadBasicRatingData();
	
	public abstract void cleanRatePredictData();
	
	public abstract void updateRatePredictData(UserId user,Map<ItemId, Double> data);
	
	public abstract List<UserRatePredict> recommendVideo(String userName, Hints hnts);
}
