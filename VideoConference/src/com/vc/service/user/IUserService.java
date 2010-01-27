package com.vc.service.user;

import com.vc.entity.UserInfo;

public interface IUserService {
	
	public abstract UserInfo signIn(String userName,String password);
	
}
