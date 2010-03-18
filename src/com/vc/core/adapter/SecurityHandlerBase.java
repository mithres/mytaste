package com.vc.core.adapter;

import org.red5.server.adapter.ApplicationAdapter;

public class SecurityHandlerBase {
	
	protected ApplicationAdapter application;

	public void setApplication(ApplicationAdapter application) {
		this.application = application;
	}
	
}
