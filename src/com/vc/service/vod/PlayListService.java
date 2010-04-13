package com.vc.service.vod;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

import org.aspectj.util.FileUtil;
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
import com.vc.dao.vod.VideoCollectionDao;
import com.vc.entity.PlayList;
import com.vc.entity.UserInfo;
import com.vc.entity.VideoCollection;
import com.vc.presentation.exception.FilePersistException;
import com.vc.util.configuration.ServerConfiguration;

@Service
public class PlayListService implements IPlayListService {

	private static Logger log = Red5LoggerFactory.getLogger(PlayListService.class, "VideoConference");

	@Autowired
	private PlayListDao playListDao = null;
	@Autowired
	private UserInfoDao userInfoDao = null;
	@Autowired
	private VideoCollectionDao videoCollectionDao = null;

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

		return user.getAccountBalance() >= playList.getPrice();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public PlayList savePlayList(PlayList playList) throws FilePersistException {
		Long playListIndex = ((BigInteger) userInfoDao.nativeQuery("SELECT nextval('hibseq')", new Hints(0)).get(0))
				.longValue();
		playList.setPlayListIndex(playListIndex);
		playListDao.create(playList);

		if (playList.getFilmFile() != null) {
			File destFile = new File(ServerConfiguration.getFsUri() + Constants.VIDEO_STREAM_PATH
					+ playList.getFileName());
			try {
				FileUtil.copyFile(playList.getFilmFile(), destFile);
			} catch (IOException e) {
				throw new FilePersistException("Save vod file:" + destFile.getPath() + " error.", e);
			}
		}
		return playList;
	}

	@Override
	public IPageList<PlayList> findPlayListByCondition(Hints hints, PlayListSearchCondition condition) {
		IPageList<PlayList> list = new PageListImpl<PlayList>();
		list.setRecordTotal(playListDao.findPlayListCount(condition));
		if (list.getRecordTotal() > 0) {
			hints.setHintParameters(Constants.ENABLE_QUERY_CACHE, Boolean.TRUE);
			list.setRecords(playListDao.findPlayList(condition, hints));
		}
		return list;
	}

	@Override
	public IPageList<VideoCollection> findVideoCollectionByName(Hints hints, String name) {
		IPageList<VideoCollection> list = new PageListImpl<VideoCollection>();
		list.setRecordTotal(videoCollectionDao.findCollectionCountByName(name));
		list.setRecords(videoCollectionDao.findCollectionByName(hints, name));
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public VideoCollection createVideoCollection(VideoCollection collection) {
		Long index = ((BigInteger) userInfoDao.nativeQuery("SELECT nextval('hibseq')", new Hints(0)).get(0)).longValue();
		collection.setCollectionIndex(index);
		videoCollectionDao.create(collection);
		return collection;
	}

	@Override
	public IPageList<VideoCollection> findAllVideoCollections(Hints hnts) {
		IPageList<VideoCollection> list = new PageListImpl<VideoCollection>();
		list.setRecordTotal(videoCollectionDao.findAllCollectionCount());
		list.setRecords(videoCollectionDao.findAllCollection(hnts));
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void removeVideoCollection(String id) {
		videoCollectionDao.delete(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public VideoCollection updateVideoCollection(VideoCollection collection) {
		videoCollectionDao.update(collection);
		return collection;
	}
	
}