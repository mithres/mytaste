package com.vc.core.adapter;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.adapter.ApplicationAdapter;
import org.red5.server.api.IConnection;
import org.red5.server.api.IScope;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.vc.core.constants.Constants;
import com.vc.core.vod.VODSecurityHandler;
import com.vc.service.cluster.IClientManager;
import com.vc.service.cluster.ILoadBalancer;

public class VODApplicationAdapter extends ApplicationAdapter {

	private static final Logger log = Red5LoggerFactory.getLogger(VODApplicationAdapter.class, "VideoConference");

	@Autowired
	private IClientManager clientManager = null;

	@Override
	public synchronized boolean start(IScope scope) {

		VODSecurityHandler vodHandler = new VODSecurityHandler();
		vodHandler.setVodClientManager(clientManager);
		scope.registerServiceHandler(Constants.VOD_SCOPE_NAME, vodHandler);

		return super.start(scope);
	}

	@Override
	public synchronized boolean connect(IConnection conn, IScope scope, Object[] params) {
		log.info("------Client connect to vod scope ----------");
		ILoadBalancer loadBalancer = (ILoadBalancer) scope.getContext().getApplicationContext().getBean(
				ILoadBalancer.LOAD_BALACENER_NAME);
		loadBalancer.getLBNode().increaseConnection();
		return true;
	}

	@Override
	public synchronized void disconnect(IConnection conn, IScope scope) {
		log.info("----------------------disconnect-----------------------");
		ILoadBalancer loadBalancer = (ILoadBalancer) scope.getContext().getApplicationContext().getBean(
				ILoadBalancer.LOAD_BALACENER_NAME);
		loadBalancer.getLBNode().reduceConnection();
		super.disconnect(conn, scope);
	}

}
