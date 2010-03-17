package com.vc.dao.vod;

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

	
	public Long findPlayListCount(){
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
