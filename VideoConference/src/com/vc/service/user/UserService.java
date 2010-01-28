package com.vc.service.user;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vc.dao.system.ResourceDao;
import com.vc.dao.user.UserInfoDao;
import com.vc.entity.Resource;
import com.vc.entity.ResourceType;
import com.vc.entity.Role;
import com.vc.entity.UserInfo;

@Service
public class UserService implements IUserService, UserDetailsService, ISecurityManager {

	private static Logger log = Red5LoggerFactory.getLogger(UserService.class, "VideoConference");

	@Autowired
	private UserInfoDao userInfoDao = null;
	@Autowired
	private ResourceDao resourceDao = null;

	@Override
	public UserInfo signIn(String userName, String password) {

		return null;
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

	//	
	// @Override
	// protected void additionalAuthenticationChecks(UserDetails userDetails,
	// UsernamePasswordAuthenticationToken authentication)
	// throws AuthenticationException {
	//
	// if (authentication.getCredentials() == null) {
	// throw new
	// BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials",
	// "Bad credentials"), includeDetailsObject ? userDetails : null);
	// }
	//
	// String presentedPassword = authentication.getCredentials() == null ? "" :
	// authentication.getCredentials().toString();
	//
	// if (!isPasswordValid(userDetails.getPassword(), presentedPassword)) {
	// throw new
	// BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials",
	// "Bad credentials"), includeDetailsObject ? userDetails : null);
	// }
	//
	// }
	//
	// private boolean isPasswordValid(String password, String
	// presentedPassword) {
	// try {
	// String pass = MD5.do_checksum(presentedPassword);
	// return pass.equalsIgnoreCase(password);
	// } catch (NoSuchAlgorithmException e) {
	// log.error("MD5 encode error.", e);
	// return Boolean.FALSE;
	// }
	// }
	//
	// @Override
	// protected UserDetails retrieveUser(String userName,
	// UsernamePasswordAuthenticationToken authentication) throws
	// AuthenticationException {
	//
	// UserDetails loadedUser = null;
	//
	// try {
	//
	// UserInfo user = userInfoDao.findUserByName(userName, Boolean.TRUE);
	// if (user != null) {
	// GrantedAuthority[] grantedAuths = obtainGrantedAuthorities(user);
	// loadedUser = new User(user.getUsername(), user.getPassword(),
	// user.getEnable(), true, true, true, grantedAuths);
	// } else {
	// throw new
	// AuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
	// }
	//
	// } catch (DataAccessException repositoryProblem) {
	// throw new AuthenticationServiceException(repositoryProblem.getMessage(),
	// repositoryProblem);
	// }
	//
	// return loadedUser;
	// }
	//
	// private GrantedAuthority[] obtainGrantedAuthorities(UserInfo user) {
	//
	// Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
	//
	// for (Role role : user.getRoles()) {
	// authSet.add(new GrantedAuthorityImpl(role.getRoleName()));
	// }
	//
	// return authSet.toArray(new GrantedAuthority[authSet.size()]);
	// }

}
