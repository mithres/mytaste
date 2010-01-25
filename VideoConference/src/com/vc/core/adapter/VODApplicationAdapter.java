package com.vc.core.adapter;

import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.providers.ProviderManager;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.red5.logging.Red5LoggerFactory;
import org.red5.server.adapter.ApplicationAdapter;
import org.red5.server.api.IConnection;
import org.red5.server.api.IScope;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.vc.core.vod.VODSecurityHandler;
import com.vc.service.vod.IVODClientManager;

public class VODApplicationAdapter extends ApplicationAdapter{

	private static final Logger log = Red5LoggerFactory.getLogger(VODApplicationAdapter.class, "VideoConference");

	// The Global WebApp Path
	public static String webAppPath = "";

	@Autowired
	private IVODClientManager vodClientManager = null;

	@Override
	public synchronized boolean start(IScope scope) {

		// registerStreamPlaybackSecurity(new VODPlaybackSecurityHandler());
		VODSecurityHandler vodHandler = new VODSecurityHandler();
		vodHandler.setVodClientManager(vodClientManager);
		scope.registerServiceHandler("vod", vodHandler);
		return super.start(scope);
	}

	@Override
	public synchronized boolean connect(IConnection conn, IScope scope, Object[] params) {

		log.info("App connect start--------------------" + conn.getClient().getId() + ":" + params.length + ":" + conn.getType());

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("admin", "passed");

		// UsernamePasswordAuthenticationToken auth = new
		// UsernamePasswordAuthenticationToken("admin", "passed");
		ProviderManager providerManager = (ProviderManager) scope.getContext().getBean("authenticationManager");

		try {
			auth = (UsernamePasswordAuthenticationToken) providerManager.authenticate(auth);
		} catch (BadCredentialsException ex) {
			// rejectClient("Wrong login information");
			return false;
		}

		if (auth.isAuthenticated()) {
			conn.getClient().setAttribute("authInformation", auth);
			// The client is authenticated
			// You can use this in your functions called by the client
			// or event the StreamPublish Security handler
			log.debug("YESS!!! AUTHENTICATED!!!!!");
			return true;
		}

		return false;
	}

	@Override
	public synchronized void disconnect(IConnection conn, IScope scope) {
		log.info("----------------------disconnect-----------------------");
		vodClientManager.removeClient(conn.getClient().getId());
		super.disconnect(conn, scope);
	}

}
