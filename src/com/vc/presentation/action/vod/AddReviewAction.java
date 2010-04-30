package com.vc.presentation.action.vod;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.constants.Constants;
import com.vc.entity.PlayList;
import com.vc.entity.VideoComments;
import com.vc.service.user.IUserService;
import com.vc.service.vod.IPlayListService;

public class AddReviewAction extends BaseAction {

	private static final long serialVersionUID = 3902114114969670899L;

	@Autowired
	private IPlayListService playListService = null;
	@Autowired
	private IUserService userService = null;

	private String title = null;

	private String message = null;

	private String tags = null;

	private String playListId = null;

	@Override
	public String process() {

		PlayList playList = playListService.findPlayListById(playListId);

		VideoComments review = new VideoComments();
		review.setContent(message);
		review.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		review.setAuthor(userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName()));
		review.setTitle(title);
		review.setPlayList(playList);
		
		String[] playListTags = null;
		
		if (tags != null) {
			playListTags = tags.split(Constants.TAG_SPLIT_EXPRESSION);
		}
		playListService.updateUserReview(review, playListTags);

		String json = "{\"status\":\"success\"}";
		this.write(json);

		return Action.NONE;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setPlayListId(String playListId) {
		this.playListId = playListId;
	}

}
