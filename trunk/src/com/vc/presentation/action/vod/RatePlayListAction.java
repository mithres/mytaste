package com.vc.presentation.action.vod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.PlayList;
import com.vc.entity.PlayListRating;
import com.vc.entity.UserInfo;
import com.vc.service.user.IUserService;
import com.vc.service.vod.IPlayListService;

public class RatePlayListAction extends BaseAction {

	private static final long serialVersionUID = 3732493498935820932L;
	
	@Autowired
	private IUserService userService = null;
	@Autowired
	private IPlayListService playListService = null;
	
	private String playListId = null;
	private double rateValue ;
	
	@Override
	public String process() {
		UserInfo user = userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
		PlayList playList = playListService.findPlayListById(playListId);
		PlayListRating plr = new PlayListRating();
		plr.setPlayList(playList);
		plr.setUser(user);
		plr.setUserIndex(user.getUserIndex());
		plr.setPlayListIndex(playList.getPlayListIndex());
		plr.setRateVale(rateValue);
		Double averageRateValue = playListService.ratePlayList(plr);
		this.write(String.valueOf(averageRateValue.intValue()));
		return Action.NONE;
	}

	public void setPlayListId(String playListId) {
		this.playListId = playListId;
	}

	public void setRateValue(double rateValue) {
		this.rateValue = rateValue;
	}


}
