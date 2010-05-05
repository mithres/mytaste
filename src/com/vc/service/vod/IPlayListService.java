package com.vc.service.vod;

import org.springframework.security.Authentication;
import org.springframework.security.annotation.Secured;

import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.PlayList;
import com.vc.entity.PlayListQueue;
import com.vc.entity.PlayListRating;
import com.vc.entity.VideoCollection;
import com.vc.entity.VideoComments;
import com.vc.presentation.exception.FilePersistException;

public interface IPlayListService {

	public abstract PlayList findPlayListById(String playListID);

	@Secured( { "ROLE_ADMIN" })
	public abstract PlayList savePlayList(PlayList playList, String[] tags) throws FilePersistException;
	
	@Secured( { "ROLE_ADMIN" })
	public abstract PlayList updatePlayList(PlayList playList, String[] tags)throws FilePersistException;

	public abstract Boolean canPlay(Authentication auth, String playListID);

	public abstract IPageList<PlayList> findPlayListByCondition(Hints hints, PlayListSearchCondition condition);

	public abstract IPageList<VideoCollection> findVideoCollectionByName(Hints hints, String name);

	@Secured( { "ROLE_ADMIN" })
	public abstract VideoCollection createVideoCollection(VideoCollection collection);

	@Secured( { "ROLE_ADMIN" })
	public abstract VideoCollection updateVideoCollection(VideoCollection collection);

	public abstract IPageList<VideoCollection> findAllVideoCollections(Hints hnts);

	@Secured( { "ROLE_ADMIN" })
	public abstract void removeVideoCollection(String id);

	public abstract PlayListQueue createQueue(PlayListQueue queue);

	public abstract PlayListQueue updateQueue(PlayListQueue queue);

	public abstract void removeQueue(String id);

	public abstract IPageList<PlayListQueue> findUserQueue(Hints hnts, String userName);

	// Play list queue
	public abstract void addPlayListToQueue(String userName, String playListId);

	public abstract IPageList<VideoComments> findPlayListComments(String playListId, Hints hnts);

	public abstract IPageList<PlayList> alsoLikedVideo(String userName, Hints hnts);

	public abstract Double findPlayListAverageRateValue(String playListId);

	@Secured( { "ROLE_ADMIN", "ROLE_USER" })
	public abstract Double ratePlayList(PlayListRating rating);

	@Secured( { "ROLE_ADMIN", "ROLE_USER" })
	public abstract void updateUserReview(VideoComments vc, String[] tags);

	@Secured( { "ROLE_ADMIN", "ROLE_USER" })
	public abstract IPageList<PlayListQueue> findUserPlayListQueue(String userName, Hints hnts);

	@Secured( { "ROLE_ADMIN", "ROLE_USER" })
	public abstract void removePlayListQueue(String id);

}