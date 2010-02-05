package com.vc.service.user;

import org.springframework.security.Authentication;

import com.vc.entity.PlayList;
import com.vc.entity.UserInfo;

public interface IUserService {

	public abstract UserInfo signIn(String userName, String password);

	public abstract UserInfo findUserByName(String userName);

	public abstract boolean playVod(Authentication auth, PlayList playList);

}
