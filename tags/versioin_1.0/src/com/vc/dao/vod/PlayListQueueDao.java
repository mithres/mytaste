package com.vc.dao.vod;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.PlayListQueue;

@Repository
public class PlayListQueueDao extends GenericDAO<PlayListQueue, String> {

	private static final String FIND_USER_QUEUE_COUNT = " select count(plq.id) from PlayListQueue plq where plq.user.userName = ? ";

	private static final String FIND_USER_QUEUE = " from PlayListQueue plq left join fetch plq.playList where plq.user.userName = ? order by createdTime desc ";

	public static final String FIND_PLAYLIST_IN_USERQUEUE = " from PlayListQueue plq where plq.user.userName = ? and plq.playList.id = ? ";

	public PlayListQueue findPlayListInUserQueue(String userName, String playListId) {
		return (PlayListQueue)this.findUnique(FIND_PLAYLIST_IN_USERQUEUE, new Hints(0), userName, playListId);
	}

	public Long findUserPlayListQueueCount(String userName) {
		return this.findRowCount(FIND_USER_QUEUE_COUNT, userName);
	}

	public List<PlayListQueue> findUserPlayListQueue(Hints hnts, String userName) {
		return this.findPaged(FIND_USER_QUEUE, hnts, userName);
	}

}
