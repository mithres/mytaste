package com.vc.core.adapter;

import org.red5.server.adapter.ApplicationAdapter;
import org.red5.server.api.IConnection;
import org.red5.server.api.IContext;
import org.red5.server.api.Red5;
import org.springframework.context.ApplicationContext;

public class ApplicationAdapterHelper {

	public static final ApplicationAdapter getAppAdapter(AdapterType type) {
		IConnection conn = Red5.getConnectionLocal();
		IContext context = conn.getScope().getContext();
		ApplicationContext appCtx = context.getApplicationContext();
		if (type == AdapterType.VOD) {
			return (ApplicationAdapter) appCtx.getBean("web.handler.vod");
		} else {
			return (ApplicationAdapter) appCtx.getBean("web.handler.conference");
		}
	}

	public static final IConnection getCurrentConnection() {
		return Red5.getConnectionLocal();
	}

	public static final void disConnectVODClient() {
		IConnection conn = Red5.getConnectionLocal();
		getAppAdapter(AdapterType.VOD).disconnect(conn, conn.getScope());
	}
}
