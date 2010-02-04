package com.vc.service.cluster;

import org.springframework.security.Authentication;

import com.vc.vo.ClientVO;

public interface IClientManager {

	public abstract ClientVO addClientListItem(String sessionID, Authentication auth);

	public abstract ClientVO getClientBySessionID(String sessionID);

	public abstract ClientVO getClientByID(String streamId);

	public abstract void removeClient(String streamId);

}