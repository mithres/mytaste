package com.vc.presentation.action.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.service.user.IUserService;

public class FindQueueCountAction extends BaseAction {

	private static final long serialVersionUID = 2978664512939438357L;

	@Autowired
	private IUserService userService = null;

	@Override
	public String process() {
		//TODO: Use cache to improve performance
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		Long count = userService.findUserQueueCount(userName);
		this.write("{\"count\":" + count + "}");
		return Action.NONE;
	}

}
