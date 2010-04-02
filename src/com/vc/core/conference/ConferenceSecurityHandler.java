package com.vc.core.conference;

import com.vc.entity.UserInfo;
import com.vc.service.cluster.IClientManager;
import com.vc.vo.ClientVO;

public class ConferenceSecurityHandler {

	private IClientManager clientManager = null;

	public String loadUserInfo(String sessionId) {

		ClientVO clientVO = clientManager.getClientBySessionID(sessionId);
		if (clientVO != null) {
			
			UserInfo userInfo = (UserInfo) clientVO.getAuthentication().getPrincipal();
			
			StringBuffer json = new StringBuffer("{");
			json.append("\"userId\":\"" + userInfo.getUsername() + "\",");
			json.append("\"userName\":\"" + userInfo.getFirstName() + " " + userInfo.getLastName() + "\",");
			json.append("\"userEmail\":\"" + userInfo.getEmail() + "\",");
			json.append("\"userLevel\":\"" + userInfo.getUserLevel().toString() + "\",");
			json.append("\"remove\":" + false + ",");
			json.append("\"userPic\":\"http://172.0.2.193:8080/mytaste/images/rp_img2.jpg\"");
			json.append("}");
			
			return json.toString();
		}
		return "";
	}

	public IClientManager getClientManager() {
		return clientManager;
	}

	public void setClientManager(IClientManager clientManager) {
		this.clientManager = clientManager;
	}

}