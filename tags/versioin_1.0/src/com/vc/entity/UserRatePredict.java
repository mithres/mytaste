package com.vc.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserRatePredict {

	@EmbeddedId
	private UserRatePredictPK pk = new UserRatePredictPK();

	private double rateValue;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username", insertable = false, updatable = false)
	private UserInfo userInfo = null;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "playlistid", insertable = false, updatable = false)
	private PlayList playList = null;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		UserRatePredict other = (UserRatePredict) obj;
		if (pk == null) {
			if (other.pk != null) {
				return false;
			}
		} else if (!pk.equals(other.pk)) {
			return false;
		}

		return true;
	}

	public UserRatePredictPK getPk() {
		return pk;
	}

	public void setPk(UserRatePredictPK pk) {
		this.pk = pk;
	}

	public double getRateValue() {
		return rateValue;
	}

	public void setRateValue(double rateValue) {
		this.rateValue = rateValue;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public PlayList getPlayList() {
		return playList;
	}

	public void setPlayList(PlayList playList) {
		this.playList = playList;
	}

}
