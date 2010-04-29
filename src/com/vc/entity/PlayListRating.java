package com.vc.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PlayListRating {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id = null;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private PlayList playList = null;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private UserInfo user = null;
	
	private Double rateVale = new Double(0);

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PlayList getPlayList() {
		return playList;
	}

	public void setPlayList(PlayList playList) {
		this.playList = playList;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public Double getRateVale() {
		return rateVale;
	}

	public void setRateVale(Double rateVale) {
		this.rateVale = rateVale;
	}

	
}
