package com.vc.presentation.action.vod;

import java.io.File;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.PlayList;
import com.vc.service.vod.IPlayListService;
import com.vc.util.photo.PicUtil;

public class SavePlayListAction extends BaseAction {

	private static final long serialVersionUID = -3518841049039573039L;

	@Autowired
	private IPlayListService playListService = null;

	private PlayList playList = null;

	private File screenShot = null;

	private String screenShotContentType = null;
	private String screenShotFileName = null;

	private File film = null;

	private String filmContentType = null;
	private String filmFileName = null;

	@Override
	public String process() {

		playList.setFileName(filmFileName);
		playList.setFilmFile(film);
		playList.setAddedTime(new Timestamp(System.currentTimeMillis()));
		playListService.savePlayList(playList);

		if (screenShot != null) {
			if (screenShot.length() > PicUtil.DEFAULT_AVATOR_SIZE) {
				addActionError(getText("action.picsizeerror"));
				return Action.SUCCESS;
			} else {
				if (PicUtil.uploadImage(screenShot, playList)) {
				} else {
					addActionError(getText("action.pictypeerror"));
					return Action.SUCCESS;
				}

			}
		}

		return Action.SUCCESS;
	}

	@Override
	public void validate() {
		// Do check here
	}

	public PlayList getPlayList() {
		return playList;
	}

	public void setPlayList(PlayList playList) {
		this.playList = playList;
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
