package com.vc.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

//@Entity
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserInfo {
	
	@Id
	private String userName = null;
	
	private String passwrod = null;
	
	private String firstName = null;
	
	private String lastName = null;
	
	private String email = null;

	private Long userIndex = null;
	
	private UserLevel userLevel = UserLevel.User;
	
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

	public UserLevel getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(UserLevel userLevel) {
		this.userLevel = userLevel;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
}
