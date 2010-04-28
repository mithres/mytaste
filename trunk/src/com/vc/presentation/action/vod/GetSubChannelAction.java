package com.vc.presentation.action.vod;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.Channels;
import com.vc.service.system.ISystemService;

public class GetSubChannelAction extends BaseAction {

	private static final long serialVersionUID = -231696247755864104L;

	@Autowired
	private ISystemService systemService = null;

	private String cid = null;

	@Override
	public String process() {
		List<Channels> subChannels = systemService.findAllSubChannels(cid);
		StringBuffer sb = new StringBuffer("[");
		int i = 0;
		for (Channels channel : subChannels) {
			sb.append("{\"id\":\"").append(channel.getId()).append("\",\"name\":\"").append(channel.getChannelName()).append("\"}");
			if(i == subChannels.size() - 1){
				
			}else{
				sb.append(",");
			}
		}
		sb.append("]");
		log.info(sb.toString());
		this.write(sb.toString());
		return Action.NONE;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

}
