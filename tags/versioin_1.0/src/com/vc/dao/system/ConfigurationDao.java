package com.vc.dao.system;

import org.springframework.stereotype.Repository;

import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.Configuration;

@Repository
public class ConfigurationDao extends GenericDAO<Configuration, String> {

	private static final String LOAD_CONFIGURATION = " from Configuration ";

	public Configuration loadConfiguration() {
		return (Configuration) findUnique(LOAD_CONFIGURATION, new Hints(0));
	}

}
