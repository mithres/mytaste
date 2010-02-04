package com.vc.presentatioin.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.vc.core.spring.ApplicationContextUtil;
import com.vc.service.cluster.IClientManager;

public class VCSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// Remove clientvo from cache
		IClientManager clientManager = (IClientManager) ApplicationContextUtil.getApplicationContext().getBean("clientManager");
		clientManager.removeClient(arg0.getSession().getId());
	}

}
