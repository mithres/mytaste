package com.vc.service.cluster;

import com.vc.vo.ClientVO;

public interface IClientManager {

	public abstract ClientVO addClientListItem(String streamId, String scopeName, Integer remotePort, String remoteAddress);

	public abstract ClientVO getClientByID(String streamId);
	
	public abstract void removeClient(String streamId);

}