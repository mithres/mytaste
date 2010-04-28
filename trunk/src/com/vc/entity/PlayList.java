package com.vc.entity;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

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

	private Float price = new Float(0);

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@OrderBy("createdTime desc")
	private List<VideoComments> comments = new ArrayList<VideoComments>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "playlist_tags", joinColumns = @JoinColumn(name = "playlist_id"), inverseJoinColumns = @JoinColumn(name = "tags_tag"))
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Tags> tags = new HashSet<Tags>();

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Channels channel = null;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private Set<PlayListRate> rates = new HashSet<PlayListRate>();

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private VideoCollection videoCollection = null;

	private String totalTime = null;

	// Total view count
	private Integer viewCount = new Integer(0);

	// Today view count
	private Integer todayViewCount = new Integer(0);

	// This week view count
	private Integer thisWeekViewCount = new Integer(0);

	// This month view count
	private Integer thisMonthViewCount = new Integer(0);

	private int dayIndex = -1;
	private int weekIndex = -1;
	private int monthIndex = -1;
	
	private float averageRateValue = 0 ;

	@Transient
	private File filmFile = null;

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

	public File getFilmFile() {
		return filmFile;
	}

	public void setFilmFile(File filmFile) {
		this.filmFile = filmFile;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public List<VideoComments> getComments() {
		return comments;
	}

	public Set<Tags> getTags() {
		return tags;
	}

	public void setTags(Set<Tags> tags) {
		this.tags = tags;
	}

	public Channels getChannel() {
		return channel;
	}

	public void setChannel(Channels channel) {
		this.channel = channel;
	}

	public Set<PlayListRate> getRates() {
		return rates;
	}

	public void setRates(Set<PlayListRate> rates) {
		this.rates = rates;
	}

	public void setComments(List<VideoComments> comments) {
		this.comments = comments;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	public Integer getTodayViewCount() {
		return todayViewCount;
	}

	public void setTodayViewCount(Integer todayViewCount) {
		this.todayViewCount = todayViewCount;
	}

	public Integer getThisWeekViewCount() {
		return thisWeekViewCount;
	}

	public void setThisWeekViewCount(Integer thisWeekViewCount) {
		this.thisWeekViewCount = thisWeekViewCount;
	}

	public Integer getThisMonthViewCount() {
		return thisMonthViewCount;
	}

	public void setThisMonthViewCount(Integer thisMonthViewCount) {
		this.thisMonthViewCount = thisMonthViewCount;
	}

	public int getDayIndex() {
		return dayIndex;
	}

	public void setDayIndex(int dayIndex) {
		this.dayIndex = dayIndex;
	}

	public int getWeekIndex() {
		return weekIndex;
	}

	public void setWeekIndex(int weekIndex) {
		this.weekIndex = weekIndex;
	}

	public int getMonthIndex() {
		return monthIndex;
	}

	public void setMonthIndex(int monthIndex) {
		this.monthIndex = monthIndex;
	}

	public VideoCollection getVideoCollection() {
		return videoCollection;
	}

	public void setVideoCollection(VideoCollection videoCollection) {
		this.videoCollection = videoCollection;
	}

	public float getAverageRateValue() {
		return averageRateValue;
	}

	public void setAverageRateValue(float averageRateValue) {
		this.averageRateValue = averageRateValue;
	}

}
