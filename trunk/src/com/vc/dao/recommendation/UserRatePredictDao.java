package com.vc.dao.recommendation;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.UserRatePredict;
import com.vc.entity.UserRatePredictPK;

@Repository
public class UserRatePredictDao extends GenericDAO<UserRatePredict, UserRatePredictPK> {

	private static final String CLEAN_DATA = " delete from UserRatePredict ";

	private static final String FIND_USER_RECOMMENDATION = " from UserRatePredict urp where urp.pk.userName = ? order by urp.rateValue desc ";

	public List<UserRatePredict> findUserRecommendation(String userName, Hints hnts) {
		return this.findPaged(FIND_USER_RECOMMENDATION, hnts, userName);
	}

	public void cleanData() {
		nativeUpdate(CLEAN_DATA);
	}

}
