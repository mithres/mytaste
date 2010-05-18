package com.vc.presentation.action.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.UserInfo;
import com.vc.service.user.IUserService;

public class AccountInfoAction extends BaseAction {

	private static final long serialVersionUID = -4957914706358757856L;

	@Autowired
	private IUserService userService = null;

	private UserInfo userAccount = null;
	
	private String type = "Main";
	

	@Override
	public String process() {
		
		String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
		userAccount = userService.findUserByName(currentName);

		return Action.SUCCESS;
	}

	public UserInfo getUserAccount() {
		return userAccount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}