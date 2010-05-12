package com.vc.dao.vod;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.PlayListRating;

@Repository
public class PlayListRatingDao extends GenericDAO<PlayListRating, String> {

	private static final String FIND_RATING_BY_USER_PLAYLIST = " from PlayListRating plr where plr.playList.id = ? and plr.user.userName = ? ";

	private static final String FIND_PLAYLIST_AVERAGE_RATEVALUE = " select avg(plr.rateVale) from PlayListRating plr where plr.playList.id = ? ";

	private static final String FIND_ALL_RATEVALUE = " from PlayListRating plr order by plr.user.userName desc ";

	@SuppressWarnings("unchecked")
	public List<PlayListRating> findAllRateValue() {
		return this.find(FIND_ALL_RATEVALUE, new Hints(0));
	}

	public PlayListRating findUserPlayListRateValue(String playListId, String userName) {
		return (PlayListRating) this.findUnique(FIND_RATING_BY_USER_PLAYLIST, new Hints(0, 1), playListId, userName);
	}

	@SuppressWarnings("unchecked")
	public Double findPlayListAverageRateValue(String playListId) {
		List<Double> list = this.find(FIND_PLAYLIST_AVERAGE_RATEVALUE, new Hints(0), playListId);
		Double rateValue = (Double) list.get(0);
		return rateValue;
	}

	// Methods for mahout recommendation engine
	private static final String FIND_USERS_IDS = " select (plr.userIndex) from PlayListRating plr order by plr.userIndex ";

	public List<Long> findUsersIds() {
		return this.find(FIND_USERS_IDS, new Hints(0));
	}

	private static final String FIND_ITEMS_IDS = " select (plr.playListIndex) from PlayListRating plr order by plr.playListIndex ";

	public List<Long> findItemsIds() {
		return this.find(FIND_ITEMS_IDS, new Hints(0));
	}

	private static final String FIND_USER_PREFERENCES = " select new PlayListRating(plr.userIndex,plr.playListIndex,plr.rateVale) from PlayListRating plr where plr.userIndex = ? order by plr.playListIndex ";

	public List<PlayListRating> findUserPreferences(long userId) {
		return this.findPaged(FIND_USER_PREFERENCES, new Hints(0), userId);
	}

	private static final String FIND_ITEM_PREFERENCES = " select new PlayListRating(plr.userIndex,plr.playListIndex,plr.rateVale) from PlayListRating plr where plr.playListIndex = ? order by plr.userIndex ";

	public List<PlayListRating> findItemPreferences(long itemId) {
		return this.findPaged(FIND_ITEM_PREFERENCES, new Hints(0), itemId);
	}

	private static final String FIND_PREFERENCES_ = " select plr.rateValue from PlayListRating plr where plr.userIndex = ? and plr.playListIndex = ? ";

	public Float findPreference(long userIndexId, long itemIndexId) {
		List list = this.find(FIND_PREFERENCES_, new Hints(0), userIndexId, itemIndexId);
		if (list != null && !list.isEmpty()) {
			return (Float) list.iterator().next();
		}
		return null;
	}

	private static final String FIND_ITEM_NUMS = " select count(distinct plr.playListIndex) from PlayListRating plr ";

	public Long findItemNum() {
		return this.findRowCount(FIND_ITEM_NUMS);
	}

	private static final String FIND_USER_NUMS = " select count(distinct plr.userIndex) from PlayListRating plr ";

	public Long findUserNum() {
		return this.findRowCount(FIND_USER_NUMS);
	}

	private static final String REMOVE_PREFERENCE = " delete playlistrating plr where plr.userIndex = ? and plr.playListIndex = ? ";

	public void removePreference(long userIndex, long playListIndex) {
		String sql = " delete playlistrating plr where plr.userIndex = " + userIndex + " and plr.playListIndex = "
				+ playListIndex;
		this.nativeUpdate(sql);
	}

	public Long findNumPreferenceForItems(long... items) {
		String sql = "SELECT COUNT(1) FROM playlistrating tp1 JOIN playlistrating tp2 USING (userIndex) WHERE tp1.playListIndex = "
				+ items[0] + " and tp2.playListIndex = " + items[1] + " ";
		return (Long) (this.nativeQuery(sql, new Hints(0)).iterator().next());
	}

	public Long findNumPreferenceForItem(long item) {
		String sql = "SELECT COUNT(1) FROM playlistrating WHERE playListIndex =  " + item;
		return (Long) (this.nativeQuery(sql, new Hints(0)).iterator().next());
	}
}