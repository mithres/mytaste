package com.vc.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vc.dao.system.ConfigurationDao;
import com.vc.entity.Configuration;

@Service
public class SystemService implements ISystemService {

	@Autowired
	private ConfigurationDao configurationDao = null;
	
	@Override
	public Configuration checkStatus() {
		return configurationDao.loadConfiguration();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Configuration install(Configuration conf) {
		configurationDao.create(conf);
		return conf;
	}

}
