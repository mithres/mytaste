package com.vc.service.vod;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;

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
import com.vc.dao.system.TagDao;
import com.vc.dao.user.UserInfoDao;
import com.vc.dao.vod.PlayListDao;
import com.vc.dao.vod.PlayListQueueDao;
import com.vc.dao.vod.PlayListRatingDao;
import com.vc.dao.vod.VideoCollectionDao;
import com.vc.dao.vod.VideoCommentsDao;
import com.vc.entity.PlayList;
import com.vc.entity.PlayListQueue;
import com.vc.entity.PlayListRating;
import com.vc.entity.Tags;
import com.vc.entity.UserInfo;
import com.vc.entity.VideoCollection;
import com.vc.entity.VideoComments;
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
	@Autowired
	private PlayListQueueDao playListQueueDao = null;
	@Autowired
	private VideoCommentsDao videoCommentsDao = null;
	@Autowired
	private PlayListRatingDao playListRatingDao = null;
	@Autowired
	private TagDao tagDao = null;

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
	public PlayList savePlayList(PlayList playList, String[] tags) throws FilePersistException {

		if (playList.getFilmFile() != null) {
			File destFile = new File(ServerConfiguration.getFsUri() + Constants.VIDEO_STREAM_PATH
					+ playList.getFileName());
			try {

				FileUtil.copyFile(playList.getFilmFile(), destFile);
				Long playListIndex = ((BigInteger) userInfoDao.nativeQuery("SELECT nextval('hibseq')", new Hints(0))
						.get(0)).longValue();
				playList.setPlayListIndex(playListIndex);

				// Update playlist tags
				for (String tag : tags) {
					Tags temp = tagDao.findById(tag);
					if(temp == null){
						temp = new Tags(tag);
					}else{
						temp.setCount(temp.getCount() + 1);
					}
					tagDao.update(temp);
					playList.getTags().add(temp);
				}

				playListDao.create(playList);

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
		Long index = ((BigInteger) userInfoDao.nativeQuery("SELECT nextval('hibseq')", new Hints(0)).get(0))
				.longValue();
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

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public PlayListQueue createQueue(PlayListQueue queue) {
		playListQueueDao.create(queue);
		return queue;
	}

	@Override
	public IPageList<PlayListQueue> findUserQueue(Hints hnts, String userName) {
		IPageList<PlayListQueue> list = new PageListImpl<PlayListQueue>();
		list.setRecordTotal(playListQueueDao.findUserPlayListQueueCount(userName));
		list.setRecords(playListQueueDao.findUserPlayListQueue(hnts, userName));
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void removeQueue(String id) {
		playListQueueDao.delete(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public PlayListQueue updateQueue(PlayListQueue queue) {
		playListQueueDao.update(queue);
		return queue;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addPlayListToQueue(String userName, String playListId) {

		PlayListQueue queue = playListQueueDao.findPlayListInUserQueue(userName, playListId);

		if (queue != null) {
			return;
		} else {
			PlayList playList = playListDao.findById(playListId);
			UserInfo user = userInfoDao.findById(userName);
			queue = new PlayListQueue();
			queue.setCreatedTime(new Timestamp(System.currentTimeMillis()));
			queue.setPlayList(playList);
			queue.setUser(user);
			playListQueueDao.create(queue);

			PlayListRating plr = playListRatingDao.findUserPlayListRateValue(playListId, userName);
			if (plr == null) {
				plr = new PlayListRating();
				plr.setPlayList(playList);
				plr.setUser(user);
				plr.setRateVale(Constants.COLLECTED);
				playListRatingDao.create(plr);
				playList.setAverageRateValue(playListRatingDao.findPlayListAverageRateValue(playList.getId()));
			} else {
				if (plr.getRateVale() < Constants.COLLECTED) {
					plr.setRateVale(Constants.COLLECTED);
				}
				playListRatingDao.update(plr);
			}
		}

	}

	@Override
	public IPageList<VideoComments> findPlayListComments(String playListId, Hints hnts) {
		IPageList<VideoComments> list = new PageListImpl<VideoComments>();
		list.setRecordTotal(videoCommentsDao.findPlayListCommentsCount(playListId));
		list.setRecords(videoCommentsDao.findPlayListComments(hnts, playListId));
		return list;
	}

	@Override
	public IPageList<PlayList> alsoLikedVideo(String userName, Hints hnts) {
		// TODO to do
		PlayListSearchCondition condition = new PlayListSearchCondition();

		IPageList<PlayList> list = new PageListImpl<PlayList>();
		list.setRecordTotal(playListDao.findPlayListCount(condition));
		if (list.getRecordTotal() > 0) {
			hnts.setHintParameters(Constants.ENABLE_QUERY_CACHE, Boolean.TRUE);
			list.setRecords(playListDao.findPlayList(condition, hnts));
		}
		return list;
	}

	@Override
	public IPageList<PlayList> recommentedVideo(Hints hnts) {
		// TODO Auto-generated method stub
		PlayListSearchCondition condition = new PlayListSearchCondition();
		IPageList<PlayList> list = new PageListImpl<PlayList>();
		list.setRecordTotal(playListDao.findPlayListCount(condition));
		if (list.getRecordTotal() > 0) {
			hnts.setHintParameters(Constants.ENABLE_QUERY_CACHE, Boolean.TRUE);
			list.setRecords(playListDao.findPlayList(condition, hnts));
		}
		return list;
	}

	@Override
	public Double findPlayListAverageRateValue(String playListId) {
		return playListRatingDao.findPlayListAverageRateValue(playListId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Double ratePlayList(PlayListRating rating) {

		PlayListRating plr = playListRatingDao.findUserPlayListRateValue(rating.getPlayList().getId(), rating.getUser()
				.getUsername());
		if (plr != null) {
			plr.setRateVale(rating.getRateVale());
			playListRatingDao.update(plr);
		} else {
			playListRatingDao.create(rating);
		}
		PlayList playList = rating.getPlayList();
		Double averageValue = playListRatingDao.findPlayListAverageRateValue(playList.getId());
		playList.setAverageRateValue(averageValue);
		playListDao.update(playList);
		return averageValue;
	}

}