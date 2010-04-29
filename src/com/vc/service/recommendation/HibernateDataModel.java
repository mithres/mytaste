package com.vc.service.recommendation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.Cache;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.common.Retriever;
import org.apache.mahout.cf.taste.impl.model.GenericItemPreferenceArray;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vc.entity.PlayListRating;
import com.vc.service.vod.IPlayListService;

@Component
public class HibernateDataModel{// implements IVCDataModel {

//	private final Logger log = Red5LoggerFactory.getLogger(HibernateDataModel.class, "VideoConference");
//
//	@Autowired
//	private IPlayListService playListService = null;
//
//	private int cachedNumUsers;
//	private int cachedNumItems;
//
//	private final Cache<String, Long> itemPrefCounts = new Cache<String, Long>(new ItemPrefCountRetriever());
//
//	@Override
//	public LongPrimitiveIterator getItemIDs() throws TasteException {
//		log.info("Retrieving all items...");
//		return new ResultSetIDIterator(playListService.getItems());
//	}
//
//	@Override
//	public FastIDSet getItemIDsFromUser(String userID) throws TasteException {
//		List<PlayListRating> list = playListService.findRateValueFromUser(userID);
//		return null;
//	}
//
//	@Override
//	public int getNumItems() throws TasteException {
//		if (cachedNumItems < 0) {
//			cachedNumItems = playListService.getNumItems().intValue();
//		}
//		return cachedNumItems;
//	}
//
//	@Override
//	public int getNumUsers() throws TasteException {
//		if (cachedNumUsers < 0) {
//			cachedNumUsers = playListService.getNumUsers().intValue();
//		}
//		return cachedNumUsers;
//	}
//
//	@Override
//	public int getNumUsersWithPreferenceFor(String... itemIDs) throws TasteException {
//
//		if (itemIDs == null) {
//			throw new IllegalArgumentException("itemIDs is null");
//		}
//		int length = itemIDs.length;
//
//		if ((length == 0) || (length > 2)) {
//			throw new IllegalArgumentException("Illegal number of item IDs: " + length);
//		}
//
//		return length == 1 ? itemPrefCounts.get(itemIDs[0]).intValue() : playListService.getNumPreferenceForItems(itemIDs).intValue();
//	}
//
//	@Override
//	public PreferenceArray getPreferencesForItem(String itemID) throws TasteException {
//		List<PlayListRating> list = playListService.getPreferencesForItem(itemID);
//		List<Preference> prefs = new ArrayList<Preference>();
//		for (PlayListRating plr : list) {
//			prefs.add(this.buildPreference(plr));
//		}
//		return new GenericItemPreferenceArray(prefs);
//	}
//
//	protected Preference buildPreference(PlayListRating plr) {
//		// return new GenericPreference(plr.getUser().getUserName(),
//		// plr.getPlayList().getId(), plr.getRateVale());
//		return null;
//	}
//
//	@Override
//	public PreferenceArray getPreferencesFromUser(String userID) throws TasteException {
//		List<PlayListRating> list = playListService.findRateValueFromUser(userID);
//		return null;
//	}
//
//	@Override
//	public Double getPreferenceValue(String userID, String itemID) throws TasteException {
//		return playListService.findUserPlayListRatingValue(userID, itemID);
//	}
//
//	@Override
//	public LongPrimitiveIterator getUserIDs() throws TasteException {
//		log.debug("Retrieving all users...");
//		List<String> userIds = playListService.findAllUsers();
//		return new ResultSetIDIterator(playListService.getUsers());
//	}
//
//	@Override
//	public boolean hasPreferenceValues() {
//		return true;
//	}
//
//	@Override
//	public void removePreference(String userID, String itemID) throws TasteException {
//		log.info("Removing preference for user '{}', item '{}'", userID, itemID);
//		playListService.removePlayListRating(userID, itemID);
//	}
//
//	@Override
//	public void setPreference(String userID, String itemID, double value) throws TasteException {
//
//		if (Double.isNaN(value)) {
//			throw new IllegalArgumentException("Invalid value: " + value);
//		}
//		log.info("Setting preference for user {}, item {}", userID, itemID);
//		playListService.savePlayListRating(userID, itemID, value);
//	}
//
//	@Override
//	public void refresh(Collection<Refreshable> alreadyRefreshed) {
//		cachedNumUsers = -1;
//		cachedNumItems = -1;
//		itemPrefCounts.clear();
//	}
//
//	/**
//	 * <p>
//	 * An {@link java.util.Iterator} which returns items from a
//	 * {@link ResultSet}. This is a useful way to iterate over all user data
//	 * since it does not require all data to be read into memory at once. It
//	 * does however require that the DB connection be held open. Note that this
//	 * class will only release database resources after {@link #hasNext()} has
//	 * been called and has returned <code>false</code>; callers should make sure
//	 * to "drain" the entire set of data to avoid tying up database resources.
//	 * </p>
//	 */
//	private final class ResultSetIDIterator implements LongPrimitiveIterator {
//		
//		List<String> ids = null;
//		
//		private boolean closed;
//
//		private ResultSetIDIterator(List<String> ids) throws TasteException {
//			this.ids = ids;
//		}
//
//		@Override
//		public boolean hasNext() {
//			boolean nextExists = false;
//			if (!closed) {
//				try {
//					if (resultSet.isAfterLast()) {
//						close();
//					} else {
//						nextExists = true;
//					}
//				} catch (SQLException sqle) {
//					log.warn("Unexpected exception while accessing ResultSet; continuing...", sqle);
//					close();
//				}
//			}
//			return nextExists;
//		}
//
//		@Override
//		public Long next() {
//			return nextLong();
//		}
//
//		@Override
//		public long nextLong() {
//
//			if (!hasNext()) {
//				throw new NoSuchElementException();
//			}
//
//			try {
//				long ID = getLongColumn(resultSet, 1);
//				resultSet.next();
//				return ID;
//			} catch (SQLException sqle) {
//				// No good way to handle this since we can't throw an exception
//				log.warn("Exception while iterating", sqle);
//				close();
//				throw new NoSuchElementException("Can't retrieve more due to exception: " + sqle);
//			}
//
//		}
//
//		@Override
//		public long peek() {
//			if (!hasNext()) {
//				throw new NoSuchElementException();
//			}
//			try {
//				return getLongColumn(resultSet, 1);
//			} catch (SQLException sqle) {
//				// No good way to handle this since we can't throw an exception
//				log.warn("Exception while iterating", sqle);
//				close();
//				throw new NoSuchElementException("Can't retrieve more due to exception: " + sqle);
//			}
//
//		}
//
//		/**
//		 * @throws UnsupportedOperationException
//		 */
//		@Override
//		public void remove() {
//			throw new UnsupportedOperationException();
//		}
//
//		private void close() {
//			if (!closed) {
//				closed = true;
//				IOUtils.quietClose(resultSet, statement, connection);
//			}
//		}
//
//		@Override
//		public void skip(int n) {
//			if (n >= 1) {
//				try {
//					advanceResultSet(resultSet, n);
//				} catch (SQLException sqle) {
//					log.warn("Exception while iterating over items", sqle);
//					close();
//				}
//			}
//		}
//
//		@Override
//		protected void finalize() throws Throwable {
//			try {
//				close();
//			} finally {
//				super.finalize();
//			}
//		}
//
//	}
//
//	private class ItemPrefCountRetriever implements Retriever<String, Long> {
//		@Override
//		public Long get(String key) throws TasteException {
//			return playListService.getNumPreferenceForItem(key);
//		}
//	}

}
