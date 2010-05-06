package com.vc.presentation.action.vod;

import java.io.File;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.constants.Constants;
import com.vc.entity.Channels;
import com.vc.entity.FilmType;
import com.vc.entity.PlayList;
import com.vc.entity.PlayListType;
import com.vc.service.system.ISystemService;
import com.vc.service.vod.IPlayListService;
import com.vc.util.photo.PicUtil;
import com.vc.util.security.ItemChecker;

public class SavePlayListAction extends BaseAction {

	private static final long serialVersionUID = -3518841049039573039L;

	@Autowired
	private IPlayListService playListService = null;
	@Autowired
	private ISystemService systemService = null;

	private PlayList playList = null;

	private List<FilmType> fileTypes = null;
	private List<PlayListType> playListTypes = null;

	private File screenShot = null;

	private String screenShotContentType = null;
	private String screenShotFileName = null;

	private File film = null;

	private String filmContentType = null;
	private String filmFileName = null;

	private String channel = null;
	private String tags = null;

	@Override
	public void prepare() throws Exception {
		fileTypes = Arrays.asList(FilmType.values());
		playListTypes = Arrays.asList(PlayListType.values());
	}

	@Override
	public void validate() {
		if (ItemChecker.checkNull(playList.getPlayListName())) {
			this.addActionError(this.getText("vc.playlist.playname.empty"));

		}
		if (playList.getPrice() != null) {
			if (!ItemChecker.checkPrice(playList.getPrice())) {
				this.addActionError(this.getText("vc.playlist.price.error"));
			}
		}
		if (playList.getDescription() != null) {
			if (!ItemChecker.checkLength(playList.getDescription(), 200)) {
				this.addActionError(this.getText("vc.playlist.description.error"));
			}
		}
	}

	@Override
	public String process() {
		
		String[] playListTags = tags.split(Constants.TAG_SPLIT_EXPRESSION);
		
		if (channel != null) {
			Channels channels = systemService.findChannelById(channel);
			if (channels != null) {
				playList.setChannel(channels);
			}
		}

		if (playList.getId() != null && playList.getId().length() > 0) {
			PlayList temp = playListService.findPlayListById(playList.getId());
			if(filmFileName != null){
				temp.setFileName(filmFileName);
			}
			temp.setFilmFile(film);
			temp.setPlayListName(playList.getPlayListName());
			temp.setPlayListType(playList.getPlayListType());
			temp.setFilmType(playList.getFilmType());
			temp.setPrice(playList.getPrice());
			temp.setDescription(playList.getDescription());

			if (temp.getPrice() == null) {
				temp.setPrice(new Float(0));
			}
			
			playList = playListService.updatePlayList(temp, playListTags);
		}else{
			playList.setId(null);
			playList.setFileName(filmFileName);
			playList.setFilmFile(film);
			playList.setAddedTime(new Timestamp(System.currentTimeMillis()));

			if (playList.getPrice() == null) {
				playList.setPrice(new Float(0));
			}
			
			playListService.savePlayList(playList, playListTags);
		}

		if (screenShot != null) {
			if (screenShot.length() > PicUtil.DEFAULT_AVATOR_SIZE) {
				addActionError(getText("action.picsizeerror"));
				return Action.INPUT;
			} else {
				if (PicUtil.uploadImage(screenShot, playList)) {
				} else {
					addActionError(getText("action.pictypeerror"));
					return Action.INPUT;
				}

			}
		}

		return Action.SUCCESS;
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

	public List<PlayListType> getPlayListTypes() {
		return playListTypes;
	}

	public void setPlayListTypes(List<PlayListType> playListTypes) {
		this.playListTypes = playListTypes;
	}

	public List<FilmType> getFileTypes() {
		return fileTypes;
	}

	public void setFileTypes(List<FilmType> fileTypes) {
		this.fileTypes = fileTypes;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

}