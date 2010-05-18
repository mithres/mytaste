package com.vc.presentation.action.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.UserInfo;
import com.vc.service.user.IUserService;

public class ProfileMenuAction extends BaseAction {

	private static final long serialVersionUID = 4766554350494480817L;
	
	@Autowired
	private IUserService userServive = null;
	
	private String type = null;
	
	private long count ;
	
	@Override
	public String process() {
		
		String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
		count = userServive.findUserQueueCount(currentName);
		return Action.SUCCESS;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getCount() {
		return count;
	}
}
