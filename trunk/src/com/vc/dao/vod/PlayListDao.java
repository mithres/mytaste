package com.vc.dao.vod;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.PlayList;
import com.vc.service.vod.PlayListSearchCondition;

@Repository
public class PlayListDao extends GenericDAO<PlayList, String> {

	private static final String FIND_PLAYLIST_COUNT_BASE = " select count(pl.id) from PlayList pl ";

	private static final String FIND_PLAYLIST_BASE = " from PlayList pl ";

	private static final String FIND_USER_PLAYLIST_QUEUE_COUNT = " select count(pl.id) from PlayList pl join pl.queues queue where queue.user.userName = ? ";

	private static final String FIND_USER_PLAYLIST_QUEUE = " from PlayList pl join pl.queues queue where queue.user.userName = ? order by queue.createdTime desc ";

	public List<PlayList> findUserPlayListQueue(String userName, Hints hnts) {
		return this.findPaged(FIND_USER_PLAYLIST_QUEUE, hnts, userName);
	}

	public Long findUserPlayListQueueCount(String userName) {
		return this.findRowCount(FIND_USER_PLAYLIST_QUEUE_COUNT, userName);
	}

	public Long findPlayListCount(PlayListSearchCondition condition) {
		String hql = FIND_PLAYLIST_COUNT_BASE + createHqlCondition(condition);
		return this.findRowCount(hql);
	}

	public List<PlayList> findPlayList(PlayListSearchCondition condition, Hints hnts) {
		String hql = createHqlBodyByCondition(condition) + createHqlCondition(condition)
				+ createHqlOrderByCondition(condition);
		return this.findPaged(hql, hnts);
	}

	private static final String createHqlBodyByCondition(PlayListSearchCondition condition) {
		if (condition.isWithComments()) {
			return FIND_PLAYLIST_BASE + " left join fetch pl.comments ";
		} else {
			return FIND_PLAYLIST_BASE;
		}
	}

	private static final String createHqlOrderByCondition(PlayListSearchCondition condition) {

		if (condition.getOrderBy() != null) {
			if ("Today".equals(condition.getOrderBy())) {
				return " order by todayViewCount desc ";
			} else if ("ThisWeek".equals(condition.getOrderBy())) {
				return " order by thisWeekViewCount desc ";
			} else if ("ThisMonth".equals(condition.getOrderBy())) {
				return " order by thisMonthViewCount desc ";
			} else if ("AddedTime".equals(condition.getOrderBy())) {
				return " order by addedTime desc ";
			} else if ("RateValue".equals(condition.getOrderBy())) {
				return " order by averageRateValue desc ";
			} else if ("All".equals(condition.getOrderBy())) {
				return " order by viewCount desc ";
			}
		}
		return "";
	}

	private static final String createHqlCondition(PlayListSearchCondition condition) {

		StringBuffer sb = new StringBuffer();

		if (condition.getPlayListType() != null) {
			if (sb.length() == 0) {
				sb.append(" where ");
			} else {
				sb.append(" and ");
			}
			sb.append(" pl.playListType = " + condition.getPlayListType().ordinal());
		}

		if (condition.getChannelId() != null) {
			if (sb.length() == 0) {
				sb.append(" where ");
			} else {
				sb.append(" and ");
			}
			sb.append(" (pl.channel.parentChannel.id = '" + condition.getChannelId() + "' or pl.channel.id = '"
					+ condition.getChannelId() + "' )");
		}

		return sb.toString();
	}

}
