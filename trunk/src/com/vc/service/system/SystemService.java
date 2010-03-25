package com.vc.service.system;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vc.core.constants.Constants;
import com.vc.core.dao.Hints;
import com.vc.dao.system.CategoryDao;
import com.vc.dao.system.ChannelDao;
import com.vc.dao.system.ConfigurationDao;
import com.vc.dao.system.ResourceDao;
import com.vc.dao.system.RoleDao;
import com.vc.dao.user.UserInfoDao;
import com.vc.entity.Category;
import com.vc.entity.Channels;
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
	@Autowired
	private ChannelDao channelDao = null; 
	@Autowired
	private CategoryDao categoryDao = null;

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

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Channels createChannel(Channels channel) {
		channelDao.create(channel);
		return channel;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteChannel(String channelId) {
		channelDao.delete(channelId);
	}

	@Override
	public List<Channels> findParentChannels() {
		return channelDao.findParentChannels();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Channels updateChannel(Channels channel) {
		channelDao.update(channel);
		return channel;
	}

	@Override
	public Channels findChannelById(String channelId) {
		return channelDao.findById(channelId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Category createCategory(Category category) {
		categoryDao.create(category);
		return category;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteCategory(String categoryId) {
		categoryDao.delete(categoryId);
	}

	@Override
	public List<Category> findAllCategories() {
		Hints hints = new Hints(0);
		hints.setHintParameters(Constants.ENABLE_QUERY_CACHE, true);
		return categoryDao.findAll(hints);
	}

	@Override
	public Category findCategoryById(String categoryId) {
		return categoryDao.findById(categoryId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Category updateCategory(Category category) {
		categoryDao.update(category);
		return category;
	}

}
