package com.vc.service.recommendation;

import java.util.Collection;
import java.util.List;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vc.entity.PlayListRating;
import com.vc.service.vod.IPlayListService;

@Component
public class HibernateDataModel implements IVCDataModel {

	@Autowired
	private IPlayListService playListService = null;

	@Override
	public LongPrimitiveIterator getItemIDs() throws TasteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FastIDSet getItemIDsFromUser(String userID) throws TasteException {
		List<PlayListRating> list = playListService.findRateValueFromUser(userID);
		return null;
	}

	@Override
	public int getNumItems() throws TasteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumUsers() throws TasteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumUsersWithPreferenceFor(String... itemIDs) throws TasteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PreferenceArray getPreferencesForItem(String itemID) throws TasteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreferenceArray getPreferencesFromUser(String userID) throws TasteException {
		List<PlayListRating> list = playListService.findRateValueFromUser(userID);
		return null;
	}

	@Override
	public Double getPreferenceValue(String userID, String itemID) throws TasteException {
		return playListService.findUserPlayListRatingValue(userID,itemID);
	}

	@Override
	public LongPrimitiveIterator getUserIDs() throws TasteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasPreferenceValues() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removePreference(String userID, String itemID) throws TasteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPreference(String userID, String itemID, double value) throws TasteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		// TODO Auto-generated method stub
		
	}

}
