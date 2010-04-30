package com.vc.service.recommendation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vc.core.dao.Hints;
import com.vc.dao.recommendation.UserRatePredictDao;
import com.vc.dao.vod.PlayListRatingDao;
import com.vc.entity.PlayListRating;
import com.vc.entity.UserRatePredict;
import com.vc.entity.UserRatePredictPK;

@Service
public class RecommendationService implements IRecommendationService {

	@Autowired
	private PlayListRatingDao playListRatingDao = null;
	@Autowired
	private UserRatePredictDao userRatePredictDao = null;

	@Override
	public Map<UserId, Map<ItemId, Double>> loadBasicRatingData() {

		UserId currentUser = null;
		Map<ItemId, Double> userData = new HashMap<ItemId, Double>();

		Map<UserId, Map<ItemId, Double>> data = new HashMap<UserId, Map<ItemId, Double>>();

		List<PlayListRating> plrs = playListRatingDao.findAllRateValue();

		for (PlayListRating plr : plrs) {
			UserId temp = new UserId(plr.getUser().getUserName());
			if (temp.equals(currentUser) || currentUser == null) {
				userData.put(new ItemId(plr.getPlayList().getId()), plr.getRateVale());
			} else {
				data.put(currentUser, userData);
				userData = new HashMap<ItemId, Double>();
				userData.put(new ItemId(plr.getPlayList().getId()), plr.getRateVale());
			}
			currentUser = temp;
		}
		data.put(currentUser, userData);

		return data;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void cleanRatePredictData() {
		userRatePredictDao.cleanData();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateRatePredictData(UserId user, Map<ItemId, Double> data) {
		for (ItemId id : data.keySet()) {
			UserRatePredictPK pk = new UserRatePredictPK(user.toString(), id.toString());
			UserRatePredict urp = new UserRatePredict();
			urp.setPk(pk);
			urp.setRateValue(data.get(id));
			userRatePredictDao.update(urp);
		}
	}

	@Override
	public List<UserRatePredict> recommendVideo(String userName, Hints hnts) {
		return userRatePredictDao.findUserRecommendation(userName, hnts);
	}

}
