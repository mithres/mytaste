package com.vc.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vc.core.constants.Constants;
import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.UserInfo;

@Repository
public class UserInfoDao extends GenericDAO<UserInfo, String> {
	
	private static final String FIND_POPULAR_USER_COUNT = " select count(userName) from UserInfo where enable = ? ";
	private static final String FIND_POPULAR_USER = " from UserInfo ui where ui.enable = ? ";
	
	private static final String FIND_USER_BY_USERPASSWORD = " from UserInfo where userName = ? and enable = ? ";

	public UserInfo findUserByName(String userName, boolean enable) {
		Hints hints = new Hints(0);
		//hints.setHintParameters(Constants.ENABLE_QUERY_CACHE, Boolean.TRUE);
		return (UserInfo) findUnique(FIND_USER_BY_USERPASSWORD, hints, userName, enable);
	}
	
	public Long findPopularUserCount(){
		return this.findRowCount(FIND_POPULAR_USER_COUNT, true);
	}
	
	public List<UserInfo> findPopularUser(Hints hints){
		return this.findPaged(FIND_POPULAR_USER, hints, true);
	}

}