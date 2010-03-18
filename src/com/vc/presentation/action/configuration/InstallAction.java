package com.vc.presentation.action.configuration;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.Configuration;
import com.vc.service.system.ISystemService;

public class InstallAction extends BaseAction {

	private static final long serialVersionUID = 5231721511344884491L;
	
	@Autowired
	private ISystemService systemService = null;
	
	private Configuration conf = null;
	
	@Override
	public String process() {
		Configuration conf = systemService.checkStatus();
		if(conf == null || !conf.getInited()){
			return Action.SUCCESS;
		}
		return HOME;
	}
	
	public String saveConf(){
		
		conf.setInited(Boolean.TRUE);
		systemService.install(conf);
		
		return Action.SUCCESS;
	}

	public Configuration getConf() {
		return conf;
	}

	public void setConf(Configuration conf) {
		this.conf = conf;
	}

}
