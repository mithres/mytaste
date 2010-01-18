package com.vc.presentatioin.util;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.util.security.AesCrypt;
import com.vc.util.security.MD5;

public class SessionHelper extends BaseAction {

	private static final long serialVersionUID = 7138359891205559140L;

	@Override
	public String process() {

		try {
			
			String uuid = UUID.randomUUID().toString();
			String userName = (String) getActionContext().getSession().get("UserName");

			getActionContext().getSession().put("ServerNO", uuid);

			Document doc = DocumentHelper.createDocument();
			Element rootElement = doc.addElement("sessionContent");
			Element uuidElement = rootElement.addElement("uuid");
			uuidElement.setText(uuid);
			Element userNameElement = rootElement.addElement("userName");
			AesCrypt ac = new AesCrypt();
			ac.setKey(ac.hexToByte(MD5.do_checksum(uuid)));
			String encryptedUserName = ac.encrypt(userName);
			
			userNameElement.setText(encryptedUserName);

			write(doc.asXML());
		} catch (IOException e) {
			log.error("Create session helper xml error", e);
		} catch (NoSuchAlgorithmException e) {
			log.error("MD5 algorithm error", e);
		}

		return Action.NONE;
	}

}
