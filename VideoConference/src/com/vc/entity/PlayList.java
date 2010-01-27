package com.vc.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PlayList {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id = null;

	@GeneratedValue(generator = "hibseq")
	private Long playListIndex = null;
	
	private String playListName = null;
	// The real film name
	private String fileName = null;

	private String description = null;

	private Timestamp addedTime = null;

	private PlayListType playListType = PlayListType.NoType;

	private FilmType filmType = FilmType.Normal;
	
	private Float price = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PlayListType getPlayListType() {
		return playListType;
	}

	public void setPlayListType(PlayListType playListType) {
		this.playListType = playListType;
	}

	public Timestamp getAddedTime() {
		return addedTime;
	}

	public void setAddedTime(Timestamp addedTime) {
		this.addedTime = addedTime;
	}

	public FilmType getFilmType() {
		return filmType;
	}

	public void setFilmType(FilmType filmType) {
		this.filmType = filmType;
	}

	public void setPlayListIndex(Long playListIndex) {
		this.playListIndex = playListIndex;
	}

	public Long getPlayListIndex() {
		return playListIndex;
	}

	public String getPlayListName() {
		return playListName;
	}

	public void setPlayListName(String playListName) {
		this.playListName = playListName;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

}
