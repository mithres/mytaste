package com.vc.presentation.action.vod;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.Channels;
import com.vc.entity.FilmType;
import com.vc.entity.PlayList;
import com.vc.entity.PlayListType;
import com.vc.service.system.ISystemService;
import com.vc.service.vod.IPlayListService;

public class EditPlayListAction extends BaseAction {

	private static final long serialVersionUID = 2288376762136217112L;
	
	@Autowired
	private ISystemService systemService = null;
	@Autowired
	private IPlayListService playListService = null;
	
	private String playListId = null;
	
	private PlayList playList = null;
	
	private File screenShot = null;

	private String screenShotContentType = null;
	private String screenShotFileName = null;

	private File film = null;

	private String filmContentType = null;
	private String filmFileName = null;
	
	
	private List<FilmType> fileTypes = null;
	private List<PlayListType> playListTypes = null;
	private List<Channels> channels = new ArrayList<Channels>();
	
	@Override
	public String process() {
		
		fileTypes = Arrays.asList(FilmType.values());
		playListTypes = Arrays.asList(PlayListType.values());
		channels = systemService.findParentChannels();
		
		playList = playListService.findPlayListById(playListId);
		return Action.SUCCESS;
	}

	public PlayList getPlayList() {
		return playList;
	}

	public void setPlayListId(String playListId) {
		this.playListId = playListId;
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

	public File getScreenShot() {
		return screenShot;
	}

	public void setScreenShot(File screenShot) {
		this.screenShot = screenShot;
	}

	public String getScreenShotContentType() {
		return screenShotContentType;
	}

	public void setScreenShotContentType(String screenShotContentType) {
		this.screenShotContentType = screenShotContentType;
	}

	public String getScreenShotFileName() {
		return screenShotFileName;
	}

	public void setScreenShotFileName(String screenShotFileName) {
		this.screenShotFileName = screenShotFileName;
	}

	public File getFilm() {
		return film;
	}

	public void setFilm(File film) {
		this.film = film;
	}

	public String getFilmContentType() {
		return filmContentType;
	}

	public void setFilmContentType(String filmContentType) {
		this.filmContentType = filmContentType;
	}

	public String getFilmFileName() {
		return filmFileName;
	}

	public void setFilmFileName(String filmFileName) {
		this.filmFileName = filmFileName;
	}
	
	

}
