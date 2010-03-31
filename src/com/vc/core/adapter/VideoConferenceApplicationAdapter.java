package com.vc.core.adapter;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.adapter.ApplicationAdapter;
import org.red5.server.api.IClient;
import org.red5.server.api.IConnection;
import org.red5.server.api.IScope;
import org.red5.server.api.service.IPendingServiceCall;
import org.red5.server.api.service.IPendingServiceCallback;
import org.red5.server.api.stream.IBroadcastStream;
import org.red5.server.api.stream.IStreamAwareScopeHandler;
import org.red5.server.api.stream.ISubscriberStream;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.vc.core.conference.ConferenceSecurityHandler;
import com.vc.core.constants.Constants;
import com.vc.service.cluster.IClientManager;

public class VideoConferenceApplicationAdapter extends ApplicationAdapter implements IPendingServiceCallback,
		IStreamAwareScopeHandler {

	private static final Logger log = Red5LoggerFactory.getLogger(VideoConferenceApplicationAdapter.class,
			"VideoConference");

	@Autowired
	private IClientManager clientManager = null;

	@Override
	public synchronized boolean start(IScope scope) {

		ConferenceSecurityHandler conferenceHandler = new ConferenceSecurityHandler();
		conferenceHandler.setClientManager(clientManager);
		scope.registerServiceHandler(Constants.CONFERENCE_SCOPE_NAME, conferenceHandler);

		return super.start(scope);
	}

	@Override
	public synchronized void stop(IScope scope) {
		super.stop(scope);
	}

	@Override
	public synchronized boolean connect(IConnection conn, IScope scope, Object[] params) {
		log.info("App connect start--------------------" + conn.getClient().getId() + ":" + params.length + ":"
				+ conn.getType());
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

	@Override
	public void streamBroadcastClose(IBroadcastStream arg0) {
		// TODO Auto-generated method stub
		super.streamBroadcastClose(arg0);
	}

	@Override
	public void streamBroadcastStart(IBroadcastStream stream) {
		log.info("stream object = " + stream);
		log.info("stream broadcast start: " + stream.getPublishedName());
		log.info("has audio? " + stream.getCodecInfo().hasAudio());
		log.info("codec = " + stream.getCodecInfo().getAudioCodecName());
		log.info("stream name = " + stream.getName());

		super.streamBroadcastStart(stream);
	}

	@Override
	public void streamPublishStart(IBroadcastStream stream) {
		log.info("stream publish start: " + stream.getPublishedName());
		super.streamPublishStart(stream);
	}

	@Override
	public boolean roomConnect(IConnection arg0, Object[] arg1) {
		log.info("roomConnect start: " + arg0.getClient().getId());
		return super.roomConnect(arg0, arg1);
	}

	@Override
	public void roomDisconnect(IConnection arg0) {
		log.info("roomDisconnect start: " + arg0.getClient().getId());
		super.roomDisconnect(arg0);
	}

	@Override
	public boolean roomJoin(IClient arg0, IScope arg1) {
		log.info("roomJoin start: " + arg0.getId());
		return super.roomJoin(arg0, arg1);
	}

	@Override
	public void roomLeave(IClient arg0, IScope arg1) {
		log.info("roomLeave start: " + arg0.getId());
		super.roomLeave(arg0, arg1);
	}

	@Override
	public boolean roomStart(IScope arg0) {
		log.info("---------------------roomStart------------");
		return super.roomStart(arg0);
	}

	@Override
	public void roomStop(IScope arg0) {
		log.info("---------------------roomStop------------");
		super.roomStop(arg0);
	}

	@Override
	public void streamSubscriberClose(ISubscriberStream stream) {
		log.info("---------------------streamSubscriberClose------------");
		super.streamSubscriberClose(stream);
	}

	@Override
	public void streamSubscriberStart(ISubscriberStream stream) {
		log.info("---------------------streamSubscriberStart------------");
		super.streamSubscriberStart(stream);
	}

}
