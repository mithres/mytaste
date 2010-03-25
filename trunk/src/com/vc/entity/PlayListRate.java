package com.vc.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * The rate value for videos.
 * @author ammen
 *
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PlayListRate {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id = null;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private PlayList playList = null;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private UserInfo rater = null;

	private int rateValue = 0;

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

	public UserInfo getRater() {
		return rater;
	}

	public void setRater(UserInfo rater) {
		this.rater = rater;
	}

	public int getRateValue() {
		return rateValue;
	}

	public void setRateValue(int rateValue) {
		this.rateValue = rateValue;
	}
	
}