package com.vc.service.recommendation;

import java.util.HashMap;
import java.util.Map;

public class SlopeOneRecommender {

	private Map<UserId, Map<ItemId, Double>> mData;
	private Map<ItemId, Map<ItemId, Double>> mDiffMatrix;
	private Map<ItemId, Map<ItemId, Integer>> mFreqMatrix;

	public SlopeOneRecommender(Map<UserId, Map<ItemId, Double>> data) {
		mData = data;
		buildDiffMatrix();
	}

	/**
	 * Based on existing data, and using weights, try to predict all missing
	 * ratings. The trick to make this more scalable is to consider only
	 * mDiffMatrix entries having a large (>1) mFreqMatrix entry.
	 * 
	 * It will output the prediction 0 when no prediction is possible.
	 */
	public Map<ItemId, Double> predict(Map<ItemId, Double> user) {
		HashMap<ItemId, Double> predictions = new HashMap<ItemId, Double>();
		HashMap<ItemId, Integer> frequencies = new HashMap<ItemId, Integer>();
		for (ItemId j : mDiffMatrix.keySet()) {
			frequencies.put(j, 0);
			predictions.put(j, 0.0d);
		}
		for (ItemId j : user.keySet()) {
			for (ItemId k : mDiffMatrix.keySet()) {
				try {
					double newval = (mDiffMatrix.get(k).get(j).doubleValue() + user.get(j).doubleValue())
							* mFreqMatrix.get(k).get(j).intValue();
					predictions.put(k, predictions.get(k) + newval);
					frequencies.put(k, frequencies.get(k) + mFreqMatrix.get(k).get(j).intValue());
				} catch (NullPointerException e) {
				}
			}
		}
		HashMap<ItemId, Double> cleanpredictions = new HashMap<ItemId, Double>();
		for (ItemId j : predictions.keySet()) {
			if (frequencies.get(j) > 0) {
				cleanpredictions.put(j, predictions.get(j).doubleValue() / frequencies.get(j).intValue());
			}
		}
		for (ItemId j : user.keySet()) {
			cleanpredictions.put(j, user.get(j));
		}
		return cleanpredictions;
	}

	/**
	 * Based on existing data, and not using weights, try to predict all missing
	 * ratings. The trick to make this more scalable is to consider only
	 * mDiffMatrix entries having a large (>1) mFreqMatrix entry.
	 */
	public Map<ItemId, Double> weightlesspredict(Map<ItemId, Double> user) {
		HashMap<ItemId, Double> predictions = new HashMap<ItemId, Double>();
		HashMap<ItemId, Integer> frequencies = new HashMap<ItemId, Integer>();
		for (ItemId j : mDiffMatrix.keySet()) {
			predictions.put(j, 0.0d);
			frequencies.put(j, 0);
		}
		for (ItemId j : user.keySet()) {
			for (ItemId k : mDiffMatrix.keySet()) {
				// System.out.println("Average diff between "+j+" and "+ k +
				// " is "+mDiffMatrix.get(k).get(j).floatValue()+" with n = "+mFreqMatrix.get(k).get(j).floatValue());
				double newval = (mDiffMatrix.get(k).get(j).doubleValue() + user.get(j).doubleValue());
				predictions.put(k, predictions.get(k) + newval);
			}
		}
		for (ItemId j : predictions.keySet()) {
			predictions.put(j, predictions.get(j).doubleValue() / user.size());
		}
		for (ItemId j : user.keySet()) {
			predictions.put(j, user.get(j));
		}
		return predictions;
	}

	public void buildDiffMatrix() {
		mDiffMatrix = new HashMap<ItemId, Map<ItemId, Double>>();
		mFreqMatrix = new HashMap<ItemId, Map<ItemId, Integer>>();
		// first iterate through users
		for (Map<ItemId, Double> user : mData.values()) {
			// then iterate through user data
			for (Map.Entry<ItemId, Double> entry : user.entrySet()) {
				if (!mDiffMatrix.containsKey(entry.getKey())) {
					mDiffMatrix.put(entry.getKey(), new HashMap<ItemId, Double>());
					mFreqMatrix.put(entry.getKey(), new HashMap<ItemId, Integer>());
				}
				for (Map.Entry<ItemId, Double> entry2 : user.entrySet()) {
					int oldcount = 0;
					if (mFreqMatrix.get(entry.getKey()).containsKey(entry2.getKey()))
						oldcount = mFreqMatrix.get(entry.getKey()).get(entry2.getKey()).intValue();
					double olddiff = 0.0d;
					if (mDiffMatrix.get(entry.getKey()).containsKey(entry2.getKey()))
						olddiff = mDiffMatrix.get(entry.getKey()).get(entry2.getKey()).doubleValue();
					double observeddiff = entry.getValue() - entry2.getValue();
					mFreqMatrix.get(entry.getKey()).put(entry2.getKey(), oldcount + 1);
					mDiffMatrix.get(entry.getKey()).put(entry2.getKey(), olddiff + observeddiff);
				}
			}
		}
		for (ItemId j : mDiffMatrix.keySet()) {
			for (ItemId i : mDiffMatrix.get(j).keySet()) {
				double oldvalue = mDiffMatrix.get(j).get(i).doubleValue();
				int count = mFreqMatrix.get(j).get(i).intValue();
				mDiffMatrix.get(j).put(i, oldvalue / count);
			}
		}
	}
}