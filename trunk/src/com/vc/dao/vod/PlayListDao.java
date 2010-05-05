package com.vc.dao.vod;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vc.core.constants.Constants;
import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.PlayList;
import com.vc.service.vod.PlayListSearchCondition;

@Repository
public class PlayListDao extends GenericDAO<PlayList, String> {

	private static final String FIND_PLAYLIST_COUNT_BASE = " select count(pl.id) from PlayList pl ";

	private static final String FIND_PLAYLIST_BASE = " from PlayList pl ";

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
			//return FIND_PLAYLIST_BASE + " left join fetch pl.comments ";
			return FIND_PLAYLIST_BASE;
		} else {
			return FIND_PLAYLIST_BASE;
		}
	}

	private static final String createHqlOrderByCondition(PlayListSearchCondition condition) {

		if (condition.getOrderBy() != null) {
			if (Constants.SEARCH_CONDITION_TODAY.equals(condition.getOrderBy())) {
				return " order by todayViewCount desc ";
			} else if (Constants.SEARCH_CONDITION_THIS_WEEK.equals(condition.getOrderBy())) {
				return " order by thisWeekViewCount desc ";
			} else if (Constants.SEARCH_CONDITION_THIS_MONTH.equals(condition.getOrderBy())) {
				return " order by thisMonthViewCount desc ";
			} else if (Constants.SEARCH_CONDITION_ADDED_TIME.equals(condition.getOrderBy())) {
				return " order by addedTime desc ";
			} else if (Constants.SEARCH_CONDITION_RATE.equals(condition.getOrderBy())) {
				return " order by averageRateValue desc ";
			} else if (Constants.SEARCH_CONDITION_ALL.equals(condition.getOrderBy())) {
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
			sb.append(" (pl.channel.id = '" + condition.getChannelId() + "' or pl.channel.parentId = '"
					+ condition.getChannelId() + "' )");
		}

		return sb.toString();
	}

}
