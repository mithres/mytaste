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

	private String[] tags = null;

	private String userTags = null;

	private String playListId = null;
	
	private String reviewId = null;

	@Override
	public String process() {

		PlayList playList = playListService.findPlayListById(playListId);

		VideoComments review = new VideoComments();
		review.setId(reviewId);
		review.setContent(message);
		review.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		review.setAuthor(userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName()));
		review.setTitle(title);
		review.setPlayList(playList);

		String[] playListTags = null;

		if (userTags != null) {
			playListTags = userTags.split(Constants.TAG_SPLIT_EXPRESSION);
		}

		int length = (tags != null && tags.length > 0 ? tags.length : 0) + (playListTags != null && playListTags.length > 0 ? playListTags.length : 0);
		String[] tagArray = new String[length];

		if (tags != null) {
			System.arraycopy(tags, 0, tagArray, 0, tags.length);
		}
		if (playListTags != null) {
			System.arraycopy(playListTags, 0, tagArray, tags != null ? tags.length : 0, playListTags.length);
		}
		
		playListService.updateUserReview(review, tagArray);
		
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

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public void setUserTags(String userTags) {
		this.userTags = userTags;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

}
