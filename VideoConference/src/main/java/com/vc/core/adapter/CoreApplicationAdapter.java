package com.vc.core.adapter;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.adapter.MultiThreadedApplicationAdapter;
import org.red5.server.api.IScope;
import org.slf4j.Logger;

public class CoreApplicationAdapter extends MultiThreadedApplicationAdapter {

	private static final Logger log = Red5LoggerFactory.getLogger(CoreApplicationAdapter.class, "VideoConference");

	@Override
	public synchronized boolean start(IScope scope) {
		log.info("App start------");
		return super.start(scope);
	}

	@Override
	public synchronized void stop(IScope scope) {
		log.info("App stop------");
		super.stop(scope);
	}

}