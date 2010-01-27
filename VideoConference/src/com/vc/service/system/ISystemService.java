package com.vc.service.system;

import com.vc.entity.Configuration;

public interface ISystemService {
	
	public abstract Configuration install(Configuration conf);
	
	public abstract Configuration checkStatus();
	
}
