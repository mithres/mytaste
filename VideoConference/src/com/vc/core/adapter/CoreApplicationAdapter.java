package com.vc.core.adapter;

import java.io.IOException;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.adapter.ApplicationAdapter;
import org.red5.server.api.IClient;
import org.red5.server.api.IConnection;
import org.red5.server.api.IScope;
import org.slf4j.Logger;

import com.vc.service.vod.IPlayListService;

public class CoreApplicationAdapter extends ApplicationAdapter {

	private static final Logger log = Red5LoggerFactory.getLogger(CoreApplicationAdapter.class, "VideoConference");

	private IPlayListService playListService = null;

	// The Global WebApp Path
	public static String webAppPath = "";

	@Override
	public synchronized boolean start(IScope scope) {
		log.info("App start--------------------");
		return super.start(scope);
	}

	@Override
	public synchronized void stop(IScope scope) {
		// TODO Auto-generated method stub
		super.stop(scope);
	}

	@Override
	public synchronized boolean connect(IConnection conn, IScope scope, Object[] params) {
		log.info("App connect start--------------------");
		try {
			webAppPath = scope.getResource("/").getFile().getAbsolutePath();
		} catch (IOException e) {
			log.error("App start error:", e);
		}
		log.debug("webAppPath : " + webAppPath);
		return true;
	}

	@Override
	public synchronized void disconnect(IConnection conn, IScope scope) {
		log.info("App disconnect start--------------------");
		super.disconnect(conn, scope);
	}

	@Override
	public synchronized boolean join(IClient client, IScope scope) {
		// TODO Auto-generated method stub
		return super.join(client, scope);
	}

	@Override
	public synchronized void leave(IClient client, IScope scope) {
		// TODO Auto-generated method stub
		super.leave(client, scope);
	}

	public IPlayListService getPlayListService() {
		return playListService;
	}

	public void setPlayListService(IPlayListService playListService) {
		this.playListService = playListService;
	}

}
