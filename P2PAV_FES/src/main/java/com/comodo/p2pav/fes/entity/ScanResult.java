package com.comodo.p2pav.fes.entity;

import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ScanResult {
	
	@EmbeddedId
	private ScanResultPK pk = null;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "avId", insertable = false, updatable = false)
	private AVEngine avEngine = null;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "filehash", insertable = false, updatable = false)
	private FileSample file = null;
	
	private Timestamp updateTime = null;
	
	private String result = null;

	public ScanResultPK getPk() {
		return pk;
	}

	public void setPk(ScanResultPK pk) {
		this.pk = pk;
	}

	public AVEngine getAvEngine() {
		return avEngine;
	}

	public void setAvEngine(AVEngine avEngine) {
		this.avEngine = avEngine;
	}

	public FileSample getFile() {
		return file;
	}

	public void setFile(FileSample file) {
		this.file = file;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
