package com.vc.service.user;

import java.util.Map;

public interface ISecurityManager {
	
	public static final String SECURITY_MANAGER_NAME = "userService";
	
    public abstract Map<String, String> loadUrlAuthorities();
    
}
