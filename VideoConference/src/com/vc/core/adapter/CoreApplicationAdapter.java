package com.vc.core.adapter;

import java.io.IOException;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.adapter.ApplicationAdapter;
import org.red5.server.api.IClient;
import org.red5.server.api.IConnection;
import org.red5.server.api.IScope;
import org.red5.server.api.service.IPendingServiceCall;
import org.red5.server.api.service.IPendingServiceCallback;
import org.red5.server.api.stream.IStreamAwareScopeHandler;
import org.slf4j.Logger;

import com.vc.core.vod.VODPlaybackSecurityHandler;
import com.vc.core.vod.VODSecurityHandler;

public class CoreApplicationAdapter extends ApplicationAdapter implements IPendingServiceCallback, IStreamAwareScopeHandler {

	private static final Logger log = Red5LoggerFactory.getLogger(CoreApplicationAdapter.class, "VideoConference");

	// The Global WebApp Path
	public static String webAppPath = "";

	private static CoreApplicationAdapter instance = null;

	public static synchronized CoreApplicationAdapter getInstance() {
		return instance;
	}

	@Override
	public synchronized boolean start(IScope scope) {

		log.info("App start--------------------");

		instance = this;
		
		//registerStreamPlaybackSecurity(new VODPlaybackSecurityHandler());
		
		scope.registerServiceHandler("vod", new VODSecurityHandler());

		return super.start(scope);
	}

	@Override
	public synchronized void stop(IScope scope) {
		// TODO Auto-generated method stub
		super.stop(scope);
	}

	@Override
	public synchronized boolean connect(IConnection conn, IScope scope, Object[] params) {

		log.info("App connect start--------------------" + conn.getClient().getId() + ":" + params.length + ":" + conn.getType());

		try {
			webAppPath = scope.getResource("/").getFile().getAbsolutePath();
		} catch (IOException e) {
			log.error("App start error:", e);
		}
		log.debug("webAppPath : " + webAppPath);

		if (params.length > 0) {
			scope.setAttribute("Signature", (String)params[0]);
		}

		return true;
	}

	@Override
	public synchronized void disconnect(IConnection conn, IScope scope) {
		log.info("----------------------disconnect-----------------------");
		super.disconnect(conn, scope);
	}

	@Override
	public synchronized boolean join(IClient client, IScope scope) {
		log.info("----------------------Join start-----------------------");
		return super.join(client, scope);
	}

	@Override
	public synchronized void leave(IClient client, IScope scope) {
		log.info("----------------------leave start-----------------------");
		super.leave(client, scope);
	}

	@Override
	public boolean appConnect(IConnection conn, Object[] arg1) {
		log.info("----------------------appConnect-----------------------");
		return super.appConnect(conn, arg1);
	}

	@Override
	public void appDisconnect(IConnection arg0) {
		log.info("----------------------appDisconnect-----------------------");
		super.appDisconnect(arg0);
	}

	@Override
	public void resultReceived(IPendingServiceCall call) {
		try {
			log.info("resultReceived " + call);
			log.info("resultReceived Arguments " + call.getArguments());
			log.info("resultReceived Arguments Number " + call.getArguments().length);
			log.info("resultReceived Result " + call.getResult());
			log.info("resultReceived ServiceMethod Name " + call.getServiceMethodName());
		} catch (Exception err) {
			log.error("resultReceived", err);
		}

	}

}
