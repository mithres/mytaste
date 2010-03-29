package com.vc.dao.vod;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.PlayList;
import com.vc.entity.PlayListType;

@Repository
public class PlayListDao extends GenericDAO<PlayList, String> {

	private static final String FIND_PLAYLIST = " from PlayList order by addedTime desc ";
	private static final String FIND_PLAYLIST_COUNT = " select count(id) from PlayList ";

	private static final String FIND_PLAYLIST_COUNT_BY_TYPE = FIND_PLAYLIST_COUNT + " where playListType ? ";
	private static final String FIND_PLAYLIST_BY_TYPE = " from PlayList where playListType ?  order by addedTime desc  ";

	private static final String FIND_PLAYLIST_BY_VIEWCOUNT = " from PlayList pl left join fetch pl.comments order by viewCount desc ";

	private static final String FIND_PLAYLIST_BY_TIMEVIEWCOUNT = " from PlayList  where addedTime between ? and ? order by viewCount desc ";

	@SuppressWarnings("unchecked")
	public List<PlayList> findPlayListByTimeViewCount(Hints hints, Date begin, Date end) {
		return this.find(FIND_PLAYLIST_BY_TIMEVIEWCOUNT, hints, begin, end);
	}

	@SuppressWarnings("unchecked")
	public List<PlayList> findPlayListByViewCount(Hints hints) {
		return this.find(FIND_PLAYLIST_BY_VIEWCOUNT, hints);
	}

	public Long findPlayListCount() {
		return this.findRowCount(FIND_PLAYLIST_COUNT);
	}

	public List<PlayList> findPlayList(Hints hints) {
		return this.findPaged(FIND_PLAYLIST, hints);
	}

	public Long findPlayListCountByType(PlayListType type) {
		return this.findRowCount(FIND_PLAYLIST_COUNT_BY_TYPE, type);
	}

	public List<PlayList> findPlayListByType(PlayListType type, Hints hints) {
		return this.findPaged(FIND_PLAYLIST_BY_TYPE, hints, type);
	}

}
