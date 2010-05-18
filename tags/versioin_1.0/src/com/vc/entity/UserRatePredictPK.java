package com.vc.entity;

import java.io.Serializable;

import javax.persistence.Column;

public class UserRatePredictPK implements Serializable {

	private static final long serialVersionUID = 3924789960523402258L;

	@Column(name = "username")
	private String userName = null;

	@Column(name = "playlistid")
	private String playListId = null;

	public UserRatePredictPK() {

	}

	public UserRatePredictPK(String userName, String playListId) {
		this.userName = userName;
		this.playListId = playListId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPlayListId() {
		return playListId;
	}

	public void setPlayListId(String playListId) {
		this.playListId = playListId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((playListId == null) ? 0 : playListId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		UserRatePredictPK other = (UserRatePredictPK) obj;

		if (playListId == null) {
			if (other.playListId != null) {
				return false;
			}
		} else if (!playListId.equals(other.playListId)) {
			return false;
		}

		if (userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!userName.equals(other.userName)) {
			return false;
		}

		return true;
	}

}
