package com.vc.service.vod;

import java.util.List;

import org.springframework.security.Authentication;
import org.springframework.security.annotation.Secured;

import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.PlayList;
import com.vc.entity.PlayListType;
import com.vc.presentation.exception.FilePersistException;

public interface IPlayListService {

	@Secured( { "ROLE_USER", "ROLE_ADMIN" })
	public abstract PlayList findPlayListById(String playListID);

	@Secured( { "ROLE_ADMIN" })
	public abstract PlayList savePlayList(PlayList playList) throws FilePersistException;

	@Secured( { "ROLE_USER", "ROLE_ADMIN" })
	public abstract IPageList<PlayList> findPlayList(Hints hints);

	@Secured( { "ROLE_USER", "ROLE_ADMIN" })
	public abstract IPageList<PlayList> findPlayListByType(Hints hints, PlayListType type);

	@Secured( { "ROLE_USER", "ROLE_ADMIN" })
	public abstract Boolean canPlay(Authentication auth, String playListID);

	public abstract List<PlayList> findPlayListByViewCount(Hints hints);

}
