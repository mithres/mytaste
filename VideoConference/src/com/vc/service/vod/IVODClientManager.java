package com.vc.service.vod;

import com.vc.bo.vod.VODClient;

public interface IVODClientManager {

	public abstract VODClient addClientListItem(String streamId, String scopeName, Integer remotePort, String remoteAddress);

	public abstract VODClient getClientByID(String streamId);
	
	public abstract void removeClient(String streamId);

}