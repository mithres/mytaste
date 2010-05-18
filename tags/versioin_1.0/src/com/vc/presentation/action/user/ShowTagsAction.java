package com.vc.presentation.action.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vc.core.action.BaseAction;
import com.vc.entity.Tags;
import com.vc.service.user.IUserService;

public class ShowTagsAction extends BaseAction {

	private static final long serialVersionUID = 6125009881019832993L;
	
	@Autowired
	private IUserService userService = null;
	
	private List<Tags> tags = null;
	
	@Override
	public String process() {
		
		
		
		return null;
	}

	public List<Tags> getTags() {
		return tags;
	}

}
