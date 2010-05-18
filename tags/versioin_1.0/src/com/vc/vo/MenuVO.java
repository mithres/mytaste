package com.vc.vo;

import java.util.List;

import com.vc.core.constants.Constants;
import com.vc.entity.Channels;

public class MenuVO {
	
	private String menuStat = Constants.VOD_SCOPE_NAME;
	
	private List<Channels> channels = null;

	public String getMenuStat() {
		return menuStat;
	}

	public void setMenuStat(String menuStat) {
		this.menuStat = menuStat;
	}

	public List<Channels> getChannels() {
		return channels;
	}

	public void setChannels(List<Channels> channels) {
		this.channels = channels;
	}
	
}
