package com.vc.core.adapter;

import org.red5.server.adapter.ApplicationAdapter;
import org.red5.server.api.IConnection;
import org.red5.server.api.IContext;
import org.red5.server.api.Red5;
import org.springframework.context.ApplicationContext;

public class ApplicationAdapterHelper {
	
	public static final ApplicationAdapter getAppAdapter() {
		IConnection conn = Red5.getConnectionLocal();
		IContext context = conn.getScope().getContext();
		ApplicationContext appCtx = context.getApplicationContext();
		ApplicationAdapter app = (ApplicationAdapter) appCtx.getBean("web.handler.videoconference");
		return app;
	}

	public static final IConnection getCurrentConnection() {
		return Red5.getConnectionLocal();
	}
	
	public static final void disConnectClient(){
		IConnection conn = Red5.getConnectionLocal();
		getAppAdapter().disconnect(conn, conn.getScope());
	}
}
