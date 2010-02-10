package com.vc.service.user;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vc.core.constants.Constants;
import com.vc.dao.system.ResourceDao;
import com.vc.dao.system.RoleDao;
import com.vc.dao.user.UserInfoDao;
import com.vc.entity.PlayList;
import com.vc.entity.Resource;
import com.vc.entity.ResourceType;
import com.vc.entity.Role;
import com.vc.entity.UserInfo;
import com.vc.presentatioin.exception.UserExistException;
import com.vc.util.security.MD5;

@Service
public class UserService implements IUserService, UserDetailsService, ISecurityManager {

	private static Logger log = Red5LoggerFactory.getLogger(UserService.class, "VideoConference");

	@Autowired
	private UserInfoDao userInfoDao = null;
	@Autowired
	private ResourceDao resourceDao = null;
	@Autowired
	private RoleDao roleDao = null;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public UserInfo signUp(UserInfo user) throws UserExistException{
		UserInfo ui = userInfoDao.findById(user.getUsername());
		if(ui != null){
			throw new UserExistException("User "+user.getUsername()+" existed.");
		}
		user.getRoles().addAll(roleDao.findRoleByName(Constants.ROLE_USER));
		try {
			user.setPassword(MD5.do_checksum(user.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			log.error("MD5 error when user sign up.");
		}
		userInfoDao.create(user);
		return user;
	}

	@Override
	public UserInfo findUserByName(String userName) {
		return userInfoDao.findById(userName);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
		UserInfo user = userInfoDao.findUserByName(userName, Boolean.TRUE);
		if (user == null) {
			throw new UsernameNotFoundException("User " + userName + " has no GrantedAuthority");
		}
		GrantedAuthority[] grantedAuths = obtainGrantedAuthorities(user);
		user.setAuthorities(grantedAuths);
		return user;
	}

	@Override
	public Map<String, String> loadUrlAuthorities() {

		Map<String, String> urlAuthorities = new HashMap<String, String>();
		List<Resource> urlResources = resourceDao.findResourceByType(ResourceType.URL);
		for (Resource resource : urlResources) {
			urlAuthorities.put(resource.getResourceValue(), resource.getRoleAuthorities());
		}
		return urlAuthorities;
	}

	private GrantedAuthority[] obtainGrantedAuthorities(UserInfo user) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (Role role : user.getRoles()) {
			authSet.add(new GrantedAuthorityImpl(role.getRoleName()));
		}
		return authSet.toArray(new GrantedAuthority[authSet.size()]);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean playVod(Authentication auth, PlayList playList) {

		UserInfo user = userInfoDao.findById(auth.getName());
		if (user.getAccountBalance().floatValue() >= playList.getPrice().floatValue()) {
			user.setAccountBalance(user.getAccountBalance() - playList.getPrice().floatValue());
			userInfoDao.update(user);
		} else {
			return false;
		}
		return true;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public UserInfo createUser(UserInfo user) throws UserExistException {
		UserInfo ui = userInfoDao.findById(user.getUsername());
		if (ui != null) {
			throw new UserExistException("User " + user.getUsername() + " existed.");
		}
		try {
			user.setPassword(MD5.do_checksum(user.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			log.error("MD5 error.");
		}
		userInfoDao.create(user);
		return user;
	}

}
