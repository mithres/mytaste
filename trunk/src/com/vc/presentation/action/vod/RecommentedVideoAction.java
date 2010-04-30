package com.vc.presentation.action.vod;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.dao.Hints;
import com.vc.entity.UserRatePredict;
import com.vc.service.recommendation.IRecommendationService;

public class RecommentedVideoAction extends BaseAction {

	private static final long serialVersionUID = 8150785373313007330L;

	@Autowired
	private IRecommendationService recommendationService = null;
	
	private List<UserRatePredict> videos = null;
	
	private String type = "recommented";

	@Override
	public String process() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		videos = recommendationService.recommendVideo(userName, new Hints(0,8));
		return Action.SUCCESS;
	}

	public String getType() {
		return type;
	}

	public List<UserRatePredict> getVideos() {
		return videos;
	}


}
