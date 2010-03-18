package com.vc.core.adapter;

import org.red5.server.api.stream.IStreamPlaybackSecurity;

public class SecurityHandlerLoader extends SecurityHandlerBase{

	private IStreamPlaybackSecurity playbackSecurity;

	public void setPlaybackSecurity(IStreamPlaybackSecurity playbackSecurity) {
		this.playbackSecurity = playbackSecurity;
	}

	public void init() {
		application.registerStreamPlaybackSecurity(playbackSecurity);
	}

}
