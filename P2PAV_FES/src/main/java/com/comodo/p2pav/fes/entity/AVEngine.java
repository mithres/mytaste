package com.comodo.p2pav.fes.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AVEngine {
	
	@Id
	private String engineId = null;
	
	private int avLibVersion;
	
	private Timestamp updateTime = null;


	public String getEngineId() {
		return engineId;
	}

	public void setEngineId(String engineId) {
		this.engineId = engineId;
	}

	public int getAvLibVersion() {
		return avLibVersion;
	}

	public void setAvLibVersion(int avLibVersion) {
		this.avLibVersion = avLibVersion;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
}
