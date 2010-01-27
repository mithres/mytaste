package com.vc.core.client;

import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.ProviderManager;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.red5.logging.Red5LoggerFactory;
import org.red5.server.ClientRegistry;
import org.red5.server.api.IClient;
import org.red5.server.api.IConnection;
import org.red5.server.api.Red5;
import org.red5.server.exception.ClientNotFoundException;
import org.red5.server.exception.ClientRejectedException;
import org.slf4j.Logger;

import com.vc.util.security.ServerUtil;

public class AuthedClientRegister extends ClientRegistry {

	private static final Logger log = Red5LoggerFactory.getLogger(AuthedClientRegister.class, "VideoConference");

	@Override
	public IClient newClient(Object[] params) throws ClientNotFoundException, ClientRejectedException {

		if(params.length == 0){
			throw new ClientRejectedException();
		}
		String[] authStr = ServerUtil.getUserAuthInfo((String)params[0]);
		
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(authStr[0], authStr[1]);
		IConnection conn = Red5.getConnectionLocal();
		ProviderManager providerManager = (ProviderManager) conn.getScope().getContext().getBean("authenticationManager");

		try {
			auth = (UsernamePasswordAuthenticationToken) providerManager.authenticate(auth);
			SecurityContextHolder.getContext().setAuthentication(auth);
		} catch (BadCredentialsException ex) {
			throw new ClientRejectedException();
		}

		if (auth.isAuthenticated()) {
			IClient client =  super.newClient(params);
			client.setAttribute("authInformation", auth);
			return client;
		} else {
			throw new ClientRejectedException();
		}
	}

}
