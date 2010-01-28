package com.vc.core.adapter;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.adapter.ApplicationAdapter;
import org.red5.server.api.IConnection;
import org.red5.server.api.IScope;
import org.red5.server.api.service.IServiceCapableConnection;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.vc.core.vod.VODSecurityHandler;
import com.vc.service.vod.IVODClientManager;

public class VODApplicationAdapter extends ApplicationAdapter {

	private static final Logger log = Red5LoggerFactory.getLogger(VODApplicationAdapter.class, "VideoConference");

	// The Global WebApp Path
	public static String webAppPath = "";

	@Autowired
	private IVODClientManager vodClientManager = null;

	@Override
	public synchronized boolean start(IScope scope) {

		VODSecurityHandler vodHandler = new VODSecurityHandler();
		vodHandler.setVodClientManager(vodClientManager);
		scope.registerServiceHandler("vod", vodHandler);
		return super.start(scope);
	}

	@Override
	public synchronized boolean connect(IConnection conn, IScope scope, Object[] params) {
		log.info("------Client connect to vod scope ----------");

		if (conn instanceof IServiceCapableConnection) {
			IServiceCapableConnection sc = (IServiceCapableConnection) conn;
			sc.invoke("xxx");
		}

		return true;
	}

	@Override
	public synchronized void disconnect(IConnection conn, IScope scope) {
		log.info("----------------------disconnect-----------------------");
		vodClientManager.removeClient(conn.getClient().getId());
		super.disconnect(conn, scope);
	}

}
