package com.vc.service.vod;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.core.entity.PageListImpl;
import com.vc.dao.vod.PlayListDao;
import com.vc.entity.PlayList;
import com.vc.entity.PlayListType;

@Service
public class PlayListService implements IPlayListService {

	private static Logger log = Red5LoggerFactory.getLogger(PlayListService.class, "VideoConference");
	
	@Autowired
	private PlayListDao playListDao = null;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public PlayList savePlayList(PlayList playList) {
		playListDao.create(playList);
		return playList;
	}

	@Override
	public IPageList<PlayList> findPlayList(Hints hints) {
		IPageList<PlayList> list = new PageListImpl<PlayList>();
		list.setRecordTotal(playListDao.findPlayListCount());
		if (list.getRecordTotal() > 0) {
			list.setRecords(playListDao.findPlayList(hints));
		}
		return list;
	}

	@Override
	public IPageList<PlayList> findPlayListByType(Hints hints, PlayListType type) {
		IPageList<PlayList> list = new PageListImpl<PlayList>();
		list.setRecordTotal(playListDao.findPlayListCountByType(type));
		if (list.getRecordTotal() > 0) {
			list.setRecords(playListDao.findPlayListByType(type, hints));
		}
		return list;
	}

}
