package com.vc.service.vod;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vc.core.constants.Constants;
import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.core.entity.PageListImpl;
import com.vc.dao.user.UserInfoDao;
import com.vc.dao.vod.PlayListDao;
import com.vc.entity.PlayList;
import com.vc.entity.PlayListType;
import com.vc.entity.UserInfo;

@Service
public class PlayListService implements IPlayListService {

	private static Logger log = Red5LoggerFactory.getLogger(PlayListService.class, "VideoConference");

	@Autowired
	private PlayListDao playListDao = null;
	@Autowired
	private UserInfoDao userInfoDao = null;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public PlayList savePlayList(PlayList playList) {
		playListDao.create(playList);
		playList.setPlayListIndex(playListDao.findPlayListCount());
		return playList;
	}

	@Override
	public IPageList<PlayList> findPlayList(Hints hints) {
		IPageList<PlayList> list = new PageListImpl<PlayList>();
		list.setRecordTotal(playListDao.findPlayListCount());
		if (list.getRecordTotal() > 0) {
			hints.setHintParameters(Constants.ENABLE_QUERY_CACHE, Boolean.TRUE);
			list.setRecords(playListDao.findPlayList(hints));
		}
		return list;
	}

	@Override
	public IPageList<PlayList> findPlayListByType(Hints hints, PlayListType type) {
		IPageList<PlayList> list = new PageListImpl<PlayList>();
		list.setRecordTotal(playListDao.findPlayListCountByType(type));
		if (list.getRecordTotal() > 0) {
			hints.setHintParameters(Constants.ENABLE_QUERY_CACHE, Boolean.TRUE);
			list.setRecords(playListDao.findPlayListByType(type, hints));
		}
		return list;
	}

	@Override
	public PlayList findPlayListById(String playListID) {
		return playListDao.findById(playListID);
	}

	@Override
	public Boolean canPlay(Authentication auth, String playListID) {

		UserInfo user = userInfoDao.findById(auth.getName());
		if (user == null) {
			return Boolean.FALSE;
		}

		PlayList playList = playListDao.findById(playListID);
		if (playList == null) {
			return Boolean.FALSE;
		}

		return user.getAccountBalance().longValue() >= playList.getPrice().longValue();
	}

}
