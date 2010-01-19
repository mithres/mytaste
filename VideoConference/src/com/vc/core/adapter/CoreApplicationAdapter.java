package com.vc.core.adapter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.adapter.ApplicationAdapter;
import org.red5.server.api.IClient;
import org.red5.server.api.IConnection;
import org.red5.server.api.IScope;
import org.red5.server.api.Red5;
import org.red5.server.api.service.IPendingServiceCall;
import org.red5.server.api.service.IPendingServiceCallback;
import org.red5.server.api.stream.IStreamAwareScopeHandler;
import org.red5.server.api.stream.ISubscriberStream;
import org.slf4j.Logger;

import com.vc.core.vod.VODSecurityHandler;

public class CoreApplicationAdapter extends ApplicationAdapter implements IPendingServiceCallback, IStreamAwareScopeHandler {

	private static final Logger log = Red5LoggerFactory.getLogger(CoreApplicationAdapter.class, "VideoConference");

	// The Global WebApp Path
	public static String webAppPath = "";
	
	public static ConcurrentMap<String,String> VOD_CLIENT_SIGNATURE = new  ConcurrentHashMap<String,String>();
	
	private static CoreApplicationAdapter instance = null;

	public static synchronized CoreApplicationAdapter getInstance() {
		return instance;
	}

	@Override
	public synchronized boolean start(IScope scope) {
		
		log.info("App start--------------------");
		
		instance = this;
		
		Object handler = new VODSecurityHandler();
		scope.registerServiceHandler("vod", handler);
		
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

		// if (!checkConnection(conn, scope, params)) {
		// return false;
		// }

		try {
			webAppPath = scope.getResource("/").getFile().getAbsolutePath();
		} catch (IOException e) {
			log.error("App start error:", e);
		}
		log.debug("webAppPath : " + webAppPath);

		return true;
	}

	// Check whether the connection is legal
	private boolean checkConnection(IConnection conn, IScope scope, Object[] params) {

		String encryptedMes = params[0].toString();
		// TODO: get user name from http session

		if ("Hello Server".equals(params[0].toString())) {
			return true;
		}
		return false;
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

	@Override
	public void streamSubscriberClose(ISubscriberStream stream) {
		log.info("----------------------streamSubscriberClose-----------------------");
		super.streamSubscriberClose(stream);
	}

	@Override
	public void streamSubscriberStart(ISubscriberStream stream) {
		log.info("----------------------streamSubscriberStart-----------------------");
		
		IConnection conn = Red5.getConnectionLocal();
		IClient client = conn.getClient();
		Map<String,Object> paras = conn.getConnectParams();
		
		String key = VOD_CLIENT_SIGNATURE.get(client.getId());


//		AesCrypt ac = new AesCrypt();
//		try {
//			ac.setKey(ac.hexToByte(MD5.do_checksum(uuid)));
//		} catch (NoSuchAlgorithmException e) {
//			log.error("Make MD5 key error", e);
//		}
//
//		String signature = client.getId() + "-" + uuid;
//		String encryptedSignature = ac.encrypt(signature);
//		log.debug("Encrypted signature is:" + encryptedSignature);
		
		super.streamSubscriberStart(stream);
	}
}
