package com.vc.presentation.action.installer;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.Configuration;
import com.vc.service.system.ISystemService;

public class InstallerAction extends BaseAction {

	private static final long serialVersionUID = -283822724913568930L;
	
	@Autowired
	private ISystemService systemService = null;
	
	private Configuration conf = null;

	public String index() {
		
		Configuration con = systemService.checkStatus();
		if(con == null || !con.getInited()){
			return Action.SUCCESS;
		}else{
			return HOME;
		}
		
	}

	@Override
	public String process() {
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
