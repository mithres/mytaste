package com.vc.service.vod;

import org.springframework.security.Authentication;
import org.springframework.security.annotation.Secured;

import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.PlayList;
import com.vc.presentation.exception.FilePersistException;

public interface IPlayListService {

	public abstract PlayList findPlayListById(String playListID);

	@Secured( { "ROLE_ADMIN" })
	public abstract PlayList savePlayList(PlayList playList) throws FilePersistException;

	public abstract Boolean canPlay(Authentication auth, String playListID);

	public abstract IPageList<PlayList> findPlayListByCondition(Hints hints, PlayListSearchCondition condition);

}
