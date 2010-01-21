package com.vc.service.vod;

import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.PlayList;
import com.vc.entity.PlayListType;

public interface IPlayListService {

	public abstract PlayList savePlayList(PlayList playList);

	public abstract IPageList<PlayList> findPlayList(Hints hints);

	public abstract IPageList<PlayList> findPlayListByType(Hints hints, PlayListType type);

}
