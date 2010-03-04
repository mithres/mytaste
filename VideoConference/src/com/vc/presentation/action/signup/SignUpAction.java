package com.vc.presentation.action.signup;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.UserInfo;
import com.vc.presentation.exception.UserExistException;
import com.vc.service.user.IUserService;
import com.vc.util.security.ItemChecker;

public class SignUpAction extends BaseAction {

	private static final long serialVersionUID = 8245040829666049184L;

	@Autowired
	private IUserService userService = null;

	private UserInfo user = null;
	
	private String password = null;

	@Override
	public String process() {
		
		try {	
			userService.signUp(user);
			return Action.SUCCESS;
		} catch (UserExistException e) {
			this.addActionError(e.getMessage());
			return Action.INPUT;
		}
	}

	@Override
	public void validate() {
		
		if(ItemChecker.checkNull(user.getUsername())){
			this.addActionError(getText("vc.singup.username.empty"));
		}
		if(!ItemChecker.checkUserName(user.getUsername())){
			this.addActionError(getText("vc.singup.username.error"));
		}
		if(ItemChecker.checkNull(user.getPassword())){
			this.addActionError(getText("vc.singup.password.empty"));
		}
		if(!ItemChecker.checkPassword(user.getPassword())){
			this.addActionError(getText("vc.singup.password.error"));
		}
		if(!user.getPassword().equals(password)){
			this.addActionError(getText("vc.singup.cpassword.error"));
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