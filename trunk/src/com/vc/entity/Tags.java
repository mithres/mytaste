package com.vc.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tags {

	@Id
	private String tag = null;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<PlayList> playLists = new HashSet<PlayList>();

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Set<PlayList> getPlayLists() {
		return playLists;
	}

	public void setPlayLists(Set<PlayList> playLists) {
		this.playLists = playLists;
	}

}
