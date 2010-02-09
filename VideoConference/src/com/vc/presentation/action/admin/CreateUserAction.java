package com.vc.presentation.action.admin;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.UserInfo;
import com.vc.presentatioin.exception.UserExistException;
import com.vc.service.user.IUserService;

public class CreateUserAction extends BaseAction {

	private static final long serialVersionUID = 5366633080899650849L;

	@Autowired
	private IUserService userService = null;
	
	private String password = null;

	private UserInfo user = null;

	@Override
	public String process() {

		try {
			userService.createUser(user);
			return Action.SUCCESS;
		} catch (UserExistException e) {
			this.addActionError(e.getMessage());
			return Action.INPUT;
		}
	}

	@Override
	public void validate() {

	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
