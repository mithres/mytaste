package com.vc.dao.vod;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.PlayList;
import com.vc.entity.PlayListType;

@Repository
public class PlayListDao extends GenericDAO<PlayList, String> {
	
	private static final String FIND_PLAYLIST_COUNT = " select count(pl.id) from PlayList pl ";
	
	private static final String FIND_PLAYLIST_BASE = " from PlayList pl left join fetch pl.comments ";
	
	private static final String FIND_PLAYLIST =  FIND_PLAYLIST_BASE + " order by addedTime desc ";

	private static final String FIND_PLAYLIST_BY_TYPE = FIND_PLAYLIST_BASE + " where playListType ?  order by addedTime desc  ";
	
	private static final String FIND_PLAYLIST_COUNT_BY_CHANNEL = FIND_PLAYLIST_COUNT + " where pl.channel.id = ? ";
	private static final String FIND_PLAYLIST_BY_CHANNEL = FIND_PLAYLIST_BASE + " where pl.channel.id = ? order by addedTime desc  ";
	
	public Long findPlayListCountByChannel(String channelId){
		return this.findRowCount(FIND_PLAYLIST_COUNT_BY_CHANNEL, channelId);
	}
	public List<PlayList> findPlayListByChannel(Hints hnts,String channelId){
		return this.findPaged(FIND_PLAYLIST_BY_CHANNEL, hnts, channelId);
	}
	
	public Long findPlayListCount() {
		return this.findRowCount(FIND_PLAYLIST_COUNT);
	}
	
	public List<PlayList> findPopularPlayList(Hints hnts, String type) {

		String hql = FIND_PLAYLIST_BASE;
		if ("Today".equals(type)) {
			hql += " order by todayViewCount desc ";
		} else if ("ThisWeek".equals(type)) {
			hql += " order by thisWeekViewCount desc ";
		} else if ("ThisMonth".equals(type)) {
			hql += " order by thisMonthViewCount desc ";
		}else{
			hql += " order by viewCount desc ";
		}
		return this.findPaged(hql, hnts);
	}

	public List<PlayList> findPlayList(Hints hints) {
		return this.findPaged(FIND_PLAYLIST, hints);
	}

	public Long findPlayListCountByType(PlayListType type) {
		String hql = FIND_PLAYLIST_COUNT + " where playListType ? ";
		return this.findRowCount(hql, type);
	}

	public List<PlayList> findPlayListByType(PlayListType type, Hints hints) {
		return this.findPaged(FIND_PLAYLIST_BY_TYPE, hints, type);
	}

}
