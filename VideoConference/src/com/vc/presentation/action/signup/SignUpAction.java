package com.vc.presentation.action.signup;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.UserInfo;
import com.vc.presentatioin.exception.UserExistException;
import com.vc.service.user.IUserService;
import com.vc.util.security.ItemChecker;

public class SignUpAction extends BaseAction {

	private static final long serialVersionUID = 8245040829666049184L;

	@Autowired
	private IUserService userService = null;

	private UserInfo user = null;

	@Override
	public String process() {
		
		if(this.getActionErrors().size() > 0){
			return Action.INPUT;
		}
		
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
			this.addActionError("User name can be empty.");
		}
		if(ItemChecker.checkUserName(user.getUsername())){
			this.addActionError("User name format error.");
		}
		if(ItemChecker.checkNull(user.getPassword())){
			this.addActionError("User password can be empty.");
		}
		if(ItemChecker.checkUserName(user.getPassword())){
			this.addActionError("User password format error.");
		}
		
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

}