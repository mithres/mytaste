package com.vc.presentation.action.vod;

import java.util.Arrays;
import java.util.List;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.FilmType;
import com.vc.entity.PlayListType;

public class NewPlayListAction extends BaseAction {

	private static final long serialVersionUID = 7076437865438221434L;
	
	private List<FilmType> fileTypes = null;
	private List<PlayListType> playListTypes = null;
	
	@Override
	public String process() {

		fileTypes =  Arrays.asList(FilmType.values());
		playListTypes = Arrays.asList(PlayListType.values());
		
		return Action.SUCCESS;
	}

	public List<FilmType> getFileTypes() {
		return fileTypes;
	}

	public List<PlayListType> getPlayListTypes() {
		return playListTypes;
	}

}
