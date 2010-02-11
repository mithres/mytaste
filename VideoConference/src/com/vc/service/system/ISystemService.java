package com.vc.service.system;

import java.util.List;

import com.vc.entity.Configuration;
import com.vc.entity.Role;

public interface ISystemService {
	
	public abstract Configuration install(Configuration conf);
	
	public abstract Configuration checkStatus();
	
	public abstract List<Role> finaAllRole();
	
}
