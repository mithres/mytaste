package com.vc.dao.user;

import org.springframework.stereotype.Repository;

import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.UserInfo;

@Repository
public class UserInfoDao extends GenericDAO<UserInfo, String> {

	private static final String FIND_USER_BY_USERPASSWORD = " from UserInfo where userName = ? and enable = ? ";

	public UserInfo findUserByName(String userName, boolean enable) {
		return (UserInfo) findUnique(FIND_USER_BY_USERPASSWORD, new Hints(0), userName, enable);
	}

}
