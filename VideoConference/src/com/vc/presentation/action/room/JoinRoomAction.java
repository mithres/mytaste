package com.vc.presentation.action.room;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.service.cluster.ILoadBalancer;

public class JoinRoomAction extends BaseAction {

	private static final long serialVersionUID = 309073885953342504L;
	
	@Autowired
	private ILoadBalancer loadBalancer = null;
	
	private String roomId = null;
	
	private String sid = null;
	
	private String nodeUrl = null;
	
	@Override
	public String process() {
		
		sid = this.getRequest().getSession().getId();
		nodeUrl = loadBalancer.getLBNode().getUrl();
		
		return Action.SUCCESS;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getNodeUrl() {
		return nodeUrl;
	}

	public void setNodeUrl(String nodeUrl) {
		this.nodeUrl = nodeUrl;
	}

}
