package com.vc.service.vod;

import java.util.Map;

import com.vc.entity.PlayList;

public interface IPlayListService {
	
	public abstract PlayList savePlayList(PlayList playList);
	
	public abstract Map<String, Map<String, Object>> getPlayList();
	
}
