package com.vc.service.system;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vc.core.dao.Hints;
import com.vc.dao.system.ConfigurationDao;
import com.vc.dao.system.ResourceDao;
import com.vc.dao.system.RoleDao;
import com.vc.dao.user.UserInfoDao;
import com.vc.entity.Configuration;
import com.vc.entity.Resource;
import com.vc.entity.ResourceType;
import com.vc.entity.Role;
import com.vc.entity.UserInfo;
import com.vc.entity.UserLevel;
import com.vc.util.security.MD5;

@Service
public class SystemService implements ISystemService {

	protected final Logger log = Red5LoggerFactory.getLogger(SystemService.class, "VideoConference");

	@Autowired
	private ConfigurationDao configurationDao = null;
	@Autowired
	private ResourceDao resourceDao = null;
	@Autowired
	private RoleDao roleDao = null;
	@Autowired
	private UserInfoDao userInfoDao = null;

	@Override
	public Configuration checkStatus() {
		return configurationDao.loadConfiguration();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Configuration install(Configuration conf) {

		Resource resource = new Resource();
		resource.setResourceType(ResourceType.URL);
		resource.setResourceValue("/**");
		resourceDao.create(resource);

		Role role = new Role();
		role.setRoleName("ROLE_ADMIN");
		role.getResource().add(resource);

		Role roleUser = new Role();
		roleUser.setRoleName("ROLE_USER");

		roleDao.create(role);
		roleDao.create(roleUser);

		UserInfo admin = userInfoDao.findUserByName("admin", true);
		if (admin == null) {
			admin = new UserInfo();
		}
		try {
			admin.setPassword(MD5.do_checksum(conf.getAdminPassword()));
		} catch (NoSuchAlgorithmException e) {
			log.error("MD5 error", e);
		}
		admin.setUserName(conf.getAdminName());
		admin.setEmail(conf.getAdminEmail());
		admin.setUserLevel(UserLevel.Adminstrator);
		admin.getRoles().add(roleUser);
		admin.getRoles().add(role);
		userInfoDao.create(admin);
		
		conf.setInited(Boolean.TRUE);
		configurationDao.create(conf);
		
		return conf;
	}

	@Override
	public List<Role> finaAllRole() {
		return roleDao.findAll(new Hints(0));
	}

}
