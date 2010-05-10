package com.vc.presentation.action.vod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.Channels;
import com.vc.entity.FilmType;
import com.vc.entity.PlayListType;
import com.vc.service.system.ISystemService;

public class NewPlayListAction extends BaseAction {

	private static final long serialVersionUID = 7076437865438221434L;

	@Autowired
	private ISystemService systemService = null;

	private List<FilmType> fileTypes = null;
	private List<PlayListType> playListTypes = null;
	private List<Channels> channels = new ArrayList<Channels>();

	@Override
	public String process() {

		fileTypes = Arrays.asList(FilmType.values());
		playListTypes = Arrays.asList(PlayListType.values());
		channels = systemService.findAllChannels();

		return Action.SUCCESS;
	}

	public List<FilmType> getFileTypes() {
		return fileTypes;
	}

	public List<PlayListType> getPlayListTypes() {
		return playListTypes;
	}

	public List<Channels> getChannels() {
		return channels;
	}

}
