package com.vc.core.vod;

import org.red5.server.api.IClient;
import org.red5.server.api.IConnection;
import org.red5.server.api.Red5;

import com.vc.core.constants.Constants;
import com.vc.service.cluster.IClientManager;
import com.vc.vo.ClientVO;
import com.vc.vo.ConnectionVO;

public class VODSecurityHandler {

	private IClientManager vodClientManager = null;
	
	//Not use
	public String getSignature() {

		IConnection conn = Red5.getConnectionLocal();
		IClient client = conn.getClient();

		ClientVO clientVO = vodClientManager.getClientBySessionID((String) client.getAttribute(Constants.SESSION_ID));
		for (ConnectionVO vo : clientVO.getConnectionVO()) {
			if(vo.getClientID().equals(client.getId())){
				return vo.getClientKey();
			}
		}
		return "";
	}

	public void setVodClientManager(IClientManager vodClientManager) {
		this.vodClientManager = vodClientManager;
	}
}