package com.vc.service.user;

import java.util.Map;

public interface ISecurityManager {
	
    public abstract Map<String, String> loadUrlAuthorities();
    
}
