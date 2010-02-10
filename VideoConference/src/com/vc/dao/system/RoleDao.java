package com.vc.dao.system;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.Role;

@Repository
public class RoleDao extends GenericDAO<Role, String> {
	
	private static final String FIND_ROLE_BY_NAME = " from Role where roleName = ? ";
	
	public List<Role> findRoleByName(String roleName){
		return this.findPaged(FIND_ROLE_BY_NAME, new Hints(0), roleName);
	}
	
}
