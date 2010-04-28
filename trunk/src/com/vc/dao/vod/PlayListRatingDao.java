package com.vc.dao.vod;

import org.springframework.stereotype.Repository;

import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.PlayListRating;

@Repository
public class PlayListRatingDao extends GenericDAO<PlayListRating, String> {

	private static final String FIND_RATING_BY_USER_PLAYLIST = " from PlayListRating plr where plr.playList.id = ? and plr.user.userName = ? ";

	private static final String FIND_PLAYLIST_AVERAGE_RATEVALUE = " select avg(plr.rateVale) from PlayListRating plr where plr.playList.id = ? ";
	
	public PlayListRating findUserPlayListRateValue(String playListId, String userName) {
		return (PlayListRating)this.findUnique(FIND_RATING_BY_USER_PLAYLIST, new Hints(0, 1), playListId, userName);
	}
	
	public int findPlayListAverageRateValue(String playListId){
		this.find(FIND_PLAYLIST_AVERAGE_RATEVALUE, new Hints(0), playListId);
		return 0;
	}
	
}