package com.vc.service.user;

import org.springframework.security.Authentication;
import org.springframework.security.annotation.Secured;

import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.UserInfo;
import com.vc.presentation.exception.UserExistException;

public interface IUserService {

	public abstract UserInfo signUp(UserInfo user) throws UserExistException;

	public abstract UserInfo findUserByName(String userName);

	@Secured( { "ROLE_USER", "ROLE_ADMIN" })
	public abstract boolean playVod(Authentication auth, String playListId);

	@Secured( { "ROLE_ADMIN" })
	public abstract UserInfo createUser(UserInfo user) throws UserExistException;
	
	public abstract IPageList<UserInfo> findPopularUser(Hints hint);
	
	public Long findUserQueueCount(String userName);

}
