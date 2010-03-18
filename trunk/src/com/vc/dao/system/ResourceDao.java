package com.vc.dao.system;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.Resource;
import com.vc.entity.ResourceType;

@Repository
public class ResourceDao extends GenericDAO<Resource, String> {
	
	private static final String FIND_RESOURCE_BY_TYPE = " from Resource where resourceType = ? ";
	
	@SuppressWarnings("unchecked")
	public List<Resource> findResourceByType(ResourceType type){
		return find(FIND_RESOURCE_BY_TYPE, new Hints(0), type);
	}
	
}
