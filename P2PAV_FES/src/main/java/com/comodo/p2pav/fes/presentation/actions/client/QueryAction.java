package com.comodo.p2pav.fes.presentation.actions.client;

import com.comodo.p2pav.fes.core.action.BaseAction;
import com.opensymphony.xwork2.Action;

public class QueryAction extends BaseAction {


	private static final long serialVersionUID = 3118342850645958911L;
	
	private String avId = null;
	private String hash = null;
	
	@Override
	public String process() {
		this.write("ok");
		return Action.NONE;
	}

	public void setAvId(String avId) {
		this.avId = avId;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

}
