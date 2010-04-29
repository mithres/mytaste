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

}