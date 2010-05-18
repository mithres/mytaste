package com.vc.dao.vod;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.VideoCollection;

@Repository
public class VideoCollectionDao extends GenericDAO<VideoCollection, String> {

	private static final String FIND_COLLECTION_COUNT_BASE = " select count(vc.id) from VideoCollection vc ";
	private static final String FIND_COLLECTION_COUNT_BY_NAME = FIND_COLLECTION_COUNT_BASE + " where vc.name like ? ";

	private static final String FIND_COLLECTION_BASE = "  from VideoCollection vc ";
	private static final String FIND_COLLECTION_BY_NAME = FIND_COLLECTION_BASE
			+ " where vc.name like ? order by addedTime desc ";
	
	public Long findAllCollectionCount(){
		return this.findRowCount(FIND_COLLECTION_COUNT_BASE);
	}
	
	public List<VideoCollection> findAllCollection(Hints hnts){
		return this.findPaged(FIND_COLLECTION_BASE, hnts);
	}
	
	public Long findCollectionCountByName(String name) {
		if (name == null || name.length() == 0) {
			return this.findRowCount(FIND_COLLECTION_COUNT_BASE);
		} else {
			return this.findRowCount(FIND_COLLECTION_COUNT_BY_NAME, name);
		}
	}

	public List<VideoCollection> findCollectionByName(Hints hnts, String name) {
		if (name == null || name.length() == 0) {
			return this.findPaged(FIND_COLLECTION_BY_NAME, hnts);
		} else {
			return this.findPaged(FIND_COLLECTION_BY_NAME, hnts, name);
		}
	}

}
