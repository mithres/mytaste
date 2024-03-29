package com.vc.presentation.action.home;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.constants.Constants;
import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.PlayList;
import com.vc.entity.UserInfo;
import com.vc.service.system.ISystemService;
import com.vc.service.user.IUserService;
import com.vc.service.vod.IPlayListService;
import com.vc.service.vod.PlayListSearchCondition;
import com.vc.vo.MenuVO;

public class IndexAction extends BaseAction {

	private static final long serialVersionUID = -8814106229563837658L;

	@Autowired
	private ISystemService systemService = null;
	@Autowired
	private IPlayListService playListService = null;
	@Autowired
	private IUserService userService = null;

	private MenuVO menuVO = null;

	private IPageList<PlayList> playLists = null;
	
	private IPageList<UserInfo> users = null;

	@Override
	public String process() {

		if (getSession().getAttribute(Constants.MENU_STAT) == null) {
			menuVO = new MenuVO();
			menuVO.setChannels(systemService.findParentChannels());
		} else {
			menuVO = (MenuVO) getSession().getAttribute(Constants.MENU_STAT);
		}
		menuVO.setMenuStat(Constants.VOD_SCOPE_NAME);
		getSession().setAttribute(Constants.MENU_STAT, menuVO);

		PlayListSearchCondition condition = new PlayListSearchCondition();
		condition.setOrderBy("All");
		playLists = playListService.findPlayListByCondition(new Hints(getStartRow(), 20), condition);
		
		users = userService.findPopularUser(new Hints(getStartRow(), 20));
		
		return Action.SUCCESS;
	}

	public MenuVO getMenuVO() {
		return menuVO;
	}

	public IPageList<PlayList> getPlayLists() {
		return playLists;
	}

	public IPageList<UserInfo> getUsers() {
		return users;
	}

}