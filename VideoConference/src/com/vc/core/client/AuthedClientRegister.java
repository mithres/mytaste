package com.vc.core.client;

import java.util.Date;

import org.red5.server.ClientRegistry;
import org.red5.server.api.IClient;
import org.red5.server.api.IConnection;
import org.red5.server.api.IScope;
import org.red5.server.api.Red5;
import org.red5.server.exception.ClientNotFoundException;
import org.red5.server.exception.ClientRejectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;

import com.vc.core.constants.Constants;
import com.vc.service.cluster.IClientManager;
import com.vc.util.security.AesCrypt;
import com.vc.vo.ClientVO;

public class AuthedClientRegister extends ClientRegistry {

	@Autowired
	private IClientManager clientManager = null;

	@Override
	public IClient newClient(Object[] params) throws ClientNotFoundException, ClientRejectedException {

		if (params.length == 0) {
			throw new ClientRejectedException();
		}

		String clientAuthenticationToken = (String) params[0];
		ClientVO clientVO = clientManager.getClientByID(clientAuthenticationToken);
		SecurityContextHolder.getContext().setAuthentication(clientVO.getAuthentication());
		if (clientVO == null || clientVO.getAuthentication() == null) {
			throw new ClientRejectedException();
		} else {
			IConnection conn = Red5.getConnectionLocal();
			IScope scope = conn.getScope();
			IClient client = super.newClient(params);
			
			//TODO: support one session connection and multi red5 connection 
			if (clientVO.getClientKey() == null) {
				clientVO.setClientKey(AesCrypt.genKey());
			}
			clientVO.setClientID(client.getId());
			clientVO.setConnectSince(new Date());
			clientVO.setRemoteAddress(conn.getRemoteAddress());
			clientVO.setRemotePort(conn.getRemotePort());
			clientVO.setScopeName(scope.getName());
			
			// Put sessionId to current client attribute
			client.setAttribute(Constants.SESSION_ID, clientAuthenticationToken);
			return client;
		}

	}

}