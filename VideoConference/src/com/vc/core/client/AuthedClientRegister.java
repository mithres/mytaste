package com.vc.core.client;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.ClientRegistry;
import org.red5.server.api.IClient;
import org.red5.server.exception.ClientNotFoundException;
import org.red5.server.exception.ClientRejectedException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.vc.service.cluster.IClientManager;
import com.vc.vo.ClientVO;

public class AuthedClientRegister extends ClientRegistry {

	private static final Logger log = Red5LoggerFactory.getLogger(AuthedClientRegister.class, "VideoConference");

	@Autowired
	private IClientManager clientManager = null;

	@Override
	public IClient newClient(Object[] params) throws ClientNotFoundException, ClientRejectedException {

		if (params.length == 0) {
			throw new ClientRejectedException();
		}

		String clientAuthenticationToken = (String) params[0];
		// If token is a sessionid , it means user has signed in from web side
		ClientVO clientVO = clientManager.getClientByID(clientAuthenticationToken);
		if (clientVO == null) {
			throw new ClientRejectedException();
		} else {
			IClient client = super.newClient(params);
			return client;
		}

		// TODO:Maybe support later , now we just support client SignIn via web
		// pages.
		// String[] authStr = ServerUtil.getUserAuthInfo((String) params[0]);
		// UsernamePasswordAuthenticationToken auth = new
		// UsernamePasswordAuthenticationToken(authStr[0], authStr[1]);
		// IConnection conn = Red5.getConnectionLocal();
		// ProviderManager providerManager = (ProviderManager)
		// conn.getScope().getContext().getBean("authenticationManager");
		// try {
		// auth = (UsernamePasswordAuthenticationToken)
		// providerManager.authenticate(auth);
		// SecurityContextHolder.getContext().setAuthentication(auth);
		// } catch (BadCredentialsException ex) {
		// throw new ClientRejectedException();
		// }
		// if (auth.isAuthenticated()) {
		// IClient client = super.newClient(params);
		// return client;
		// } else {
		// throw new ClientRejectedException();
		// }
	}

}