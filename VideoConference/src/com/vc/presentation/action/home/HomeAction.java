package com.vc.presentation.action.home;

import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;

public class HomeAction extends BaseAction {

	private static final long serialVersionUID = 7709948386767266338L;

	@Override
	public String process() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		log.info("Authentication:" + securityContext.getAuthentication());
		return Action.SUCCESS;
	}

}
