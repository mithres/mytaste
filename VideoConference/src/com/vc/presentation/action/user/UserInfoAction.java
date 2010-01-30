package com.vc.presentation.action.user;

import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.util.security.ServerUtil;

public class UserInfoAction extends BaseAction {

	private static final long serialVersionUID = -7872046595918983932L;

	@Override
	public String process() {

		return Action.NONE;
	}

	public String getAuthInfo() {

		try {

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			String authInfo = ServerUtil.createEncryptedAuthInfo(authentication);


			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("Credential");
			root.setText(authInfo);
			write(doc.asXML());

		} catch (IOException e) {
			log.error("Write user principal and credential to client error.", e);
		}

		return Action.NONE;

	}
}
