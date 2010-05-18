package com.vc.service.user;

import java.io.File;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
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
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vc.core.constants.Constants;
import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.core.entity.PageListImpl;
import com.vc.dao.system.ResourceDao;
import com.vc.dao.system.RoleDao;
import com.vc.dao.user.UserInfoDao;
import com.vc.dao.user.UserNeighborhoodDao;
import com.vc.dao.vod.PlayListDao;
import com.vc.dao.vod.PlayListQueueDao;
import com.vc.dao.vod.PlayListRatingDao;
import com.vc.entity.PlayList;
import com.vc.entity.PlayListRating;
import com.vc.entity.Resource;
import com.vc.entity.ResourceType;
import com.vc.entity.Role;
import com.vc.entity.UserInfo;
import com.vc.entity.UserNeighborhood;
import com.vc.presentation.exception.UserExistException;
import com.vc.util.photo.PicUtil;
import com.vc.util.security.MD5;
import com.vc.util.server.TimeUtil;

@Service
public class UserService implements IUserService, UserDetailsService, ISecurityManager {

	private static Logger log = Red5LoggerFactory.getLogger(UserService.class, "VideoConference");

	@Autowired
	private RoleDao roleDao = null;
	@Autowired
	private UserInfoDao userInfoDao = null;
	@Autowired
	private ResourceDao resourceDao = null;
	@Autowired
	private PlayListDao playListDao = null;
	@Autowired
	private PlayListQueueDao playListQueueDao = null;
	@Autowired
	private PlayListRatingDao playListRatingDao = null;
	@Autowired
	private UserNeighborhoodDao userNeighborhoodDao = null;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public UserInfo signUp(UserInfo user) throws UserExistException {

		UserInfo ui = userInfoDao.findById(user.getUsername());
		if (ui != null) {
			throw new UserExistException("User " + user.getUsername() + " existed.");
		}
		user.getRoles().addAll(roleDao.findRoleByName(Constants.ROLE_USER));
		try {
			user.setPassword(MD5.do_checksum(user.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			log.error("MD5 error when user sign up.");
		}
		Long userIndex = ((BigInteger) userInfoDao.nativeQuery("SELECT nextval('hibseq')", new Hints(0)).get(0))
				.longValue();
		user.setUserIndex(userIndex);
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
	public boolean playVod(Authentication auth, String playListId) {

		PlayList playList = playListDao.findById(playListId);
		UserInfo user = userInfoDao.findById(auth.getName());

		if (user.getAccountBalance() >= playList.getPrice()) {
			user.setAccountBalance(user.getAccountBalance() - playList.getPrice());
			userInfoDao.update(user);
			playList.setViewCount(playList.getViewCount() + 1);

			int dayIndex = TimeUtil.getDateIndex(new Date(), "Day");
			int weekIndex = TimeUtil.getDateIndex(new Date(), "Week");
			int monthIndex = TimeUtil.getDateIndex(new Date(), "Month");

			if (playList.getDayIndex() == -1) {
				playList.setDayIndex(dayIndex);
				playList.setWeekIndex(weekIndex);
				playList.setMonthIndex(monthIndex);
				playList.setTodayViewCount(1);
				playList.setThisWeekViewCount(1);
				playList.setThisMonthViewCount(1);
			} else {
				if (playList.getDayIndex() != dayIndex) {
					playList.setDayIndex(dayIndex);
					playList.setTodayViewCount(1);
				} else {
					playList.setTodayViewCount(playList.getTodayViewCount() + 1);
				}
				if (playList.getWeekIndex() != weekIndex) {
					playList.setWeekIndex(weekIndex);
					playList.setThisWeekViewCount(1);
				} else {
					playList.setThisWeekViewCount(playList.getThisWeekViewCount() + 1);
				}
				if (playList.getMonthIndex() != monthIndex) {
					playList.setMonthIndex(monthIndex);
					playList.setThisWeekViewCount(1);
				} else {
					playList.setThisMonthViewCount(playList.getThisMonthViewCount() + 1);
				}
			}

			PlayListRating plr = playListRatingDao.findUserPlayListRateValue(playList.getId(), user.getUserName());
			if (plr == null) {
				plr = new PlayListRating();
				plr.setPlayList(playList);
				plr.setUser(user);
				plr.setRateVale(Constants.PLAYED);
				playListRatingDao.create(plr);
				playList.setAverageRateValue(playListRatingDao.findPlayListAverageRateValue(playList.getId()));
			} else {
				if (plr.getRateVale() < Constants.PLAYED) {
					plr.setRateVale(Constants.PLAYED);
				}
				playListRatingDao.update(plr);
			}

			playListDao.update(playList);

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

	@Override
	public IPageList<UserInfo> findPopularUser(Hints hint) {
		IPageList<UserInfo> users = new PageListImpl<UserInfo>();
		users.setRecordTotal(userInfoDao.findPopularUserCount());
		users.setRecords(userInfoDao.findPopularUser(hint));
		return users;
	}

	@Override
	public Long findUserQueueCount(String userName) {
		return playListQueueDao.findUserPlayListQueueCount(userName);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateProfilePhoto(File file) {
		UserInfo user = userInfoDao.findById(SecurityContextHolder.getContext().getAuthentication().getName());
		if (PicUtil.uploadImage(file, user)) {
			user.setUploadedAvatar(Boolean.TRUE);
			userInfoDao.update(user);
			return true;
		}
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void cleanUserNeighborhood() {
		userNeighborhoodDao.cleanUserNeighborhood();
	}

	@Override
	public List<UserNeighborhood> findUserNeighborhood(String userName) {
		return userNeighborhoodDao.findUserNeighborhood(userName);
	}

}
