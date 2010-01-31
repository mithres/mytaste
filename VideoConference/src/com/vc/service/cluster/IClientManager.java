package com.vc.service.cluster;

import org.springframework.security.Authentication;

import com.vc.vo.ClientVO;

public interface IClientManager {

	// TODO:remove
	public abstract ClientVO addClientListItem(String streamId, String scopeName, Integer remotePort, String remoteAddress);

	public abstract ClientVO addClientListItem(String sessionID, Authentication auth);

	public abstract ClientVO getClientBySessionID(String sessionID);

	public abstract ClientVO getClientByID(String streamId);

	public abstract void removeClient(String streamId);

}