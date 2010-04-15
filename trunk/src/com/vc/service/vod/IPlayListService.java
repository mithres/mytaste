package com.vc.service.vod;

import org.springframework.security.Authentication;
import org.springframework.security.annotation.Secured;

import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.PlayList;
import com.vc.entity.VideoCollection;
import com.vc.presentation.exception.FilePersistException;

public interface IPlayListService {

	public abstract PlayList findPlayListById(String playListID);

	@Secured( { "ROLE_ADMIN" })
	public abstract PlayList savePlayList(PlayList playList) throws FilePersistException;

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

}