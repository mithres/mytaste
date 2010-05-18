package com.vc.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.UserNeighborhood;

@Repository
public class UserNeighborhoodDao extends GenericDAO<UserNeighborhood, String> {

	private static final String CLEAN__USER_NEIGHBORHOOD = " delete from UserNeighborhood ";

	private static final String FIND_USER_NEIGHBORHOOD = " from UserNeighborhood un where un.user.userName = ? ";

	public List<UserNeighborhood> findUserNeighborhood(String userName) {
		return this.findPaged(FIND_USER_NEIGHBORHOOD, new Hints(0), userName);
	}

	public void cleanUserNeighborhood() {
		this.nativeUpdate(CLEAN__USER_NEIGHBORHOOD);
	}
}
