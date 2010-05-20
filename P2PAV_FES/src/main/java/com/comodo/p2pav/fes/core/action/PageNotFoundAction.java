package com.comodo.p2pav.fes.core.action;

import com.opensymphony.xwork2.Action;

public class PageNotFoundAction extends BaseAction {

	private static final long serialVersionUID = -7120779382347260988L;

	@Override
	public String process() {
		// Do some security record 
		return Action.SUCCESS;
	}

}
