package com.vc.presentation.action.admin;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.UserInfo;
import com.vc.presentation.exception.UserExistException;
import com.vc.service.user.IUserService;
import com.vc.util.security.ItemChecker;

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
		if(ItemChecker.checkNull(user.getUsername())){
			this.addActionError("User name can be empty.");
		}
		if(!ItemChecker.checkUserName(user.getUsername())){
			this.addActionError("User name format error.");
		}
		if(ItemChecker.checkNull(user.getPassword())){
			this.addActionError("User password can be empty.");
		}
		if(!ItemChecker.checkPassword(user.getPassword())){
			this.addActionError("User password format error.");
		}
		if(!user.getPassword().equals(password)){
			this.addActionError("Password and Confirm password different");
		}
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
