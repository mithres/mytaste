package com.vc.service.vod;

import org.acegisecurity.annotation.Secured;

import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.PlayList;
import com.vc.entity.PlayListType;

public interface IPlayListService {

	@Secured( { "ROLE_USER", "ROLE_ADMIN" })
	public abstract PlayList findPlayListById(String playListID);

	@Secured( { "ROLE_ADMIN" })
	public abstract PlayList savePlayList(PlayList playList);

	@Secured( { "ROLE_USER", "ROLE_ADMIN" })
	public abstract IPageList<PlayList> findPlayList(Hints hints);

	@Secured( { "ROLE_USER", "ROLE_ADMIN" })
	public abstract IPageList<PlayList> findPlayListByType(Hints hints, PlayListType type);

}
