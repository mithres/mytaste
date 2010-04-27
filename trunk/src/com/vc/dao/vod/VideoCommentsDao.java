package com.vc.dao.vod;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.VideoComments;

@Repository
public class VideoCommentsDao extends GenericDAO<VideoComments, String> {

	public static final String FIND_PLAYLIST_COMMENTS_COUNT = " select count(id) from VideoComments vc where vc.playList.id = ? ";

	public static final String FIND_PLAYLIST_COMMENTS = " from VideoComments vc where vc.playList.id = ? order by createdTime desc ";

	public Long findPlayListCommentsCount(String playListId) {
		return this.findRowCount(FIND_PLAYLIST_COMMENTS_COUNT, playListId);
	}

	public List<VideoComments> findPlayListComments(Hints hnts, String playListId) {
		return this.findPaged(FIND_PLAYLIST_COMMENTS, hnts, playListId);
	}

}
