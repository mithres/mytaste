package com.vc.dao.user;

import org.springframework.stereotype.Repository;

import com.vc.core.constants.Constants;
import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.UserInfo;

@Repository
public class UserInfoDao extends GenericDAO<UserInfo, String> {
	
	private static final String FIND_USER_BY_USERPASSWORD = " from UserInfo where userName = ? and enable = ? ";

	public UserInfo findUserByName(String userName, boolean enable) {
		Hints hints = new Hints(0);
		hints.setHintParameters(Constants.ENABLE_QUERY_CACHE, Boolean.TRUE);
		return (UserInfo) findUnique(FIND_USER_BY_USERPASSWORD, hints, userName, enable);
	}

}