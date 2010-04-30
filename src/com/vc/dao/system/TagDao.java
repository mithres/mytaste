package com.vc.dao.system;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.Tags;

@Repository
public class TagDao extends GenericDAO<Tags, String> {

	private static final String FIND_TAGS = " from Tags t order by t.count desc ";

	public List<Tags> findTags(Hints hnts) {
		return this.findPaged(FIND_TAGS, hnts);
	}
}
