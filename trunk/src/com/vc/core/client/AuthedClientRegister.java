package com.vc.core.client;

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

		if (clientVO == null || clientVO.getAuthentication() == null) {
			throw new ClientRejectedException();
		} else {
			SecurityContextHolder.getContext().setAuthentication(clientVO.getAuthentication());

			IConnection conn = Red5.getConnectionLocal();
			IScope scope = conn.getScope();
			IClient client = super.newClient(params);

			String clientKey = AesCrypt.genKey();
			clientVO.setClientConnectionInfo(client.getId(), clientKey, scope.getName(), conn.getRemoteAddress(), conn
					.getRemotePort());

			// Put sessionId to current client attribute
			client.setAttribute(Constants.SESSION_ID, clientAuthenticationToken);
			return client;
		}

	}

}