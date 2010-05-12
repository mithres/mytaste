package com.vc.service.recommendation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.mahout.cf.taste.common.NoSuchUserException;
import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.Cache;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.common.Retriever;
import org.apache.mahout.cf.taste.impl.model.GenericItemPreferenceArray;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;

import com.vc.entity.PlayListRating;
import com.vc.service.vod.IPlayListService;

public class PlayListDataModel implements DataModel {

	// TODO:need to be completed

	private int cachedNumUsers;
	private int cachedNumItems;

	private Cache<Long, Integer> itemPrefCounts;

	private IPlayListService playListService = null;

	public PlayListDataModel(IPlayListService playListService) {

		this.playListService = playListService;

		this.cachedNumUsers = -1;
		this.cachedNumItems = -1;
		this.itemPrefCounts = new Cache<Long, Integer>(new ItemPrefCountRetriever(playListService));

	}

	@Override
	public LongPrimitiveIterator getItemIDs() throws TasteException {
		return new PlayListLongPrimitiveIterator(playListService.findItemIds());
	}

	@Override
	public FastIDSet getItemIDsFromUser(long userID) throws TasteException {

		List<PlayListRating> list = playListService.findUserPreferences(userID);

		if (list == null || list.isEmpty()) {
			throw new NoSuchUserException();
		}
		
		FastIDSet result = new FastIDSet();
		
		Iterator<PlayListRating> ite = list.iterator();
		while (ite.hasNext()) {
			PlayListRating temp = ite.next();
			result.add(temp.getPlayListIndex());
		}

		return result;
	}

	@Override
	public int getNumItems() throws TasteException {
		if (cachedNumItems < 0) {
			cachedNumItems = playListService.findItemNum().intValue();
		}
		return cachedNumItems;
	}

	@Override
	public int getNumUsers() throws TasteException {
		if (cachedNumUsers < 0) {
			cachedNumUsers = playListService.findUserNum().intValue();
		}
		return cachedNumUsers;
	}

	@Override
	public int getNumUsersWithPreferenceFor(long... itemIDs) throws TasteException {
		if (itemIDs == null) {
			throw new IllegalArgumentException("itemIDs is null");
		}
		int length = itemIDs.length;
		if ((length == 0) || (length > 2)) {
			throw new IllegalArgumentException("Illegal number of item IDs: " + length);
		}
		return length == 1 ? itemPrefCounts.get(itemIDs[0]) : playListService.getNumPreferenceForItemsSQL(itemIDs)
				.intValue();
	}

	@Override
	public PreferenceArray getPreferencesForItem(long itemID) throws TasteException {

		List<Preference> prefs = new ArrayList<Preference>();

		List<PlayListRating> list = playListService.findItemPreferences(itemID);

		if (list == null || list.isEmpty()) {
			throw new NoSuchUserException();
		}

		Iterator<PlayListRating> ite = list.iterator();
		while (ite.hasNext()) {
			PlayListRating temp = ite.next();
			prefs.add(new GenericPreference(temp.getUserIndex(), temp.getPlayListIndex(), ((Double) temp.getRateVale())
					.floatValue()));
		}

		return new GenericItemPreferenceArray(prefs);
	}

	@Override
	public PreferenceArray getPreferencesFromUser(long userID) throws TasteException {

		List<Preference> prefs = new ArrayList<Preference>();

		List<PlayListRating> list = playListService.findUserPreferences(userID);

		if (list == null || list.isEmpty()) {
			throw new NoSuchUserException();
		}

		Iterator<PlayListRating> ite = list.iterator();
		while (ite.hasNext()) {
			PlayListRating temp = ite.next();
			prefs.add(new GenericPreference(temp.getUserIndex(), temp.getPlayListIndex(), ((Double) temp.getRateVale())
					.floatValue()));
		}

		return new GenericUserPreferenceArray(prefs);
	}

	@Override
	public Float getPreferenceValue(long userID, long itemID) throws TasteException {
		return playListService.findPreference(userID, itemID);
	}

	@Override
	public LongPrimitiveIterator getUserIDs() throws TasteException {
		return new PlayListLongPrimitiveIterator(playListService.findUsersIds());
	}

	@Override
	public boolean hasPreferenceValues() {
		return true;
	}

	@Override
	public void removePreference(long userID, long itemID) throws TasteException {
		playListService.removePreference(userID, itemID);
	}

	@Override
	public void setPreference(long userID, long itemID, float value) throws TasteException {
		playListService.setPlayListPreference(userID, itemID, value);
	}

	@Override
	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		cachedNumUsers = -1;
		cachedNumItems = -1;
		itemPrefCounts.clear();
	}

	private class ItemPrefCountRetriever implements Retriever<Long, Integer> {
		private IPlayListService playListService = null;

		private ItemPrefCountRetriever(IPlayListService playListService) {
			this.playListService = playListService;
		}

		@Override
		public Integer get(Long key) throws TasteException {
			return playListService.getNumPreferenceForItemSQL(key).intValue();
		}
	}

}
