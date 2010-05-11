package com.vc.service.recommendation;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.AveragingPreferenceInferrer;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class UserBasedRecommender implements Recommender {

	private Recommender recommender = null;

	public UserBasedRecommender(DataSource ds) throws IOException, TasteException {
		this(new PlayListDataModel(ds));
	}

	public UserBasedRecommender(DataModel model) throws TasteException {

		UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(model);
		userSimilarity.setPreferenceInferrer(new AveragingPreferenceInferrer(model));

		UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, userSimilarity, model);
		recommender = new CachingRecommender(new GenericUserBasedRecommender(model, neighborhood, userSimilarity));
	}

	@Override
	public float estimatePreference(long userID, long itemID) throws TasteException {
		return recommender.estimatePreference(userID, itemID);
	}

	@Override
	public DataModel getDataModel() {
		return recommender.getDataModel();
	}

	@Override
	public List<RecommendedItem> recommend(long userID, int howMany) throws TasteException {
		return recommender.recommend(userID, howMany);
	}

	@Override
	public List<RecommendedItem> recommend(long userID, int howMany, IDRescorer rescorer) throws TasteException {
		return recommender.recommend(userID, howMany, rescorer);
	}

	@Override
	public void removePreference(long userID, long itemID) throws TasteException {
		recommender.removePreference(userID, itemID);
	}

	@Override
	public void setPreference(long userID, long itemID, float value) throws TasteException {
		recommender.setPreference(userID, itemID, value);
	}

	@Override
	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		recommender.refresh(alreadyRefreshed);
	}

}
