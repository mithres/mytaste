package com.vc.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class UserInfo {
	
	@Id
	private String userName = null;
	
	private String passwrod = null;
	
	private String email = null;
	
	@GeneratedValue(generator = "hibseq")
	private Long userIndex = null;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswrod() {
		return passwrod;
	}

	public void setPasswrod(String passwrod) {
		this.passwrod = passwrod;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUserIndex() {
		return userIndex;
	}

	public void setUserIndex(Long userIndex) {
		this.userIndex = userIndex;
	}

	
}
