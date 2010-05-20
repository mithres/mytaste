package com.comodo.p2pav.fes.presentation.actions.client;

import com.comodo.p2pav.fes.core.action.BaseAction;
import com.opensymphony.xwork2.Action;

public class SaveScanResultAction extends BaseAction{
	
	private static final long serialVersionUID = -1608659700288114222L;
	
	private String avId = null;
	private String avLibId = null;
	private String hash = null;
	private String result = null;
	
	@Override
	public String process() {
		
		
		
		return Action.NONE;
	}

	public void setAvId(String avId) {
		this.avId = avId;
	}

	public void setAvLibId(String avLibId) {
		this.avLibId = avLibId;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
