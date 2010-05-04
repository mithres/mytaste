package com.vc.presentation.action.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.UserInfo;
import com.vc.service.user.IUserService;

public class ProfileTitleAction extends BaseAction {

	private static final long serialVersionUID = -7792071025948945780L;
	
	@Autowired
	private IUserService userService = null;
	
	private UserInfo userAccount = null;
	
	@Override
	public String process() {
		
		String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
		userAccount = userService.findUserByName(currentName);
		
		return Action.SUCCESS;
	}

	public UserInfo getUserAccount() {
		return userAccount;
	}

}
