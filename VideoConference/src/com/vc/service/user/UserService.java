package com.vc.service.user;

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

import org.acegisecurity.AuthenticationException;
import org.acegisecurity.AuthenticationServiceException;
import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.providers.dao.AbstractUserDetailsAuthenticationProvider;
import org.acegisecurity.userdetails.User;
import org.acegisecurity.userdetails.UserDetails;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.vc.dao.user.UserInfoDao;
import com.vc.entity.Role;
import com.vc.entity.UserInfo;
import com.vc.util.security.MD5;

@Service
public class UserService extends AbstractUserDetailsAuthenticationProvider implements IUserService {

	private static Logger log = Red5LoggerFactory.getLogger(UserService.class, "VideoConference");

	@Autowired
	private UserInfoDao userInfoDao = null;

	private boolean includeDetailsObject = true;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {

		if (authentication.getCredentials() == null) {
			throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"), includeDetailsObject ? userDetails : null);
		}

		String presentedPassword = authentication.getCredentials() == null ? "" : authentication.getCredentials().toString();

		if (!isPasswordValid(userDetails.getPassword(), presentedPassword)) {
			throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"), includeDetailsObject ? userDetails : null);
		}

	}

	private boolean isPasswordValid(String password, String presentedPassword) {
		try {
			String pass = MD5.do_checksum(presentedPassword);
			return pass.equalsIgnoreCase(password);
		} catch (NoSuchAlgorithmException e) {
			log.error("MD5 encode error.", e);
			return Boolean.FALSE;
		}
	}

	@Override
	protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

		UserDetails loadedUser = null;

		try {

			UserInfo user = userInfoDao.findUserByName(userName, Boolean.TRUE);
			if (user != null) {
				GrantedAuthority[] grantedAuths = obtainGrantedAuthorities(user);
				loadedUser = new User(user.getUsername(), user.getPassword(), user.getEnable(), true, true, true, grantedAuths);
			} else {
				throw new AuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
			}

		} catch (DataAccessException repositoryProblem) {
			throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
		}

		return loadedUser;
	}

	private GrantedAuthority[] obtainGrantedAuthorities(UserInfo user) {

		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();

		for (Role role : user.getRoles()) {
			authSet.add(new GrantedAuthorityImpl(role.getRoleName()));
		}

		return authSet.toArray(new GrantedAuthority[authSet.size()]);
	}

	public boolean isIncludeDetailsObject() {
		return includeDetailsObject;
	}

	public void setIncludeDetailsObject(boolean includeDetailsObject) {
		this.includeDetailsObject = includeDetailsObject;
	}

	@Override
	public UserInfo signIn(String userName, String password) {

		return null;
	}

	@Override
	public UserInfo findUserByName(String userName) {
		return userInfoDao.findById(userName);
	}

}
