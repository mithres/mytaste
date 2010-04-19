package com.vc.presentation.action.vod;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.constants.Constants;
import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.PlayList;
import com.vc.service.system.ISystemService;
import com.vc.service.vod.IPlayListService;
import com.vc.service.vod.PlayListSearchCondition;
import com.vc.vo.MenuVO;

public class PlayListIndexAction extends BaseAction {

	private static final long serialVersionUID = 6373087375653567380L;
	
	@Autowired
	private ISystemService systemService = null;
	
	@Autowired
	private IPlayListService playListService = null;
	
	private IPageList<PlayList> playLists = null;
	
	private IPageList<PlayList> recentlyAddedPlayLists = null;
	
	private MenuVO menuVO = null;
	
	@Override
	public String process() {
		
		if(getSession().getAttribute(Constants.MENU_STAT) == null){
			menuVO = new MenuVO();
			menuVO.setChannels(systemService.findParentChannels());
		}else{
			menuVO = (MenuVO)getSession().getAttribute(Constants.MENU_STAT);
		}
		menuVO.setMenuStat(Constants.VOD_SCOPE_NAME);
		getSession().setAttribute(Constants.MENU_STAT, menuVO);
		
		PlayListSearchCondition condition = new PlayListSearchCondition();
		condition.setOrderBy("AddedTime");
		playLists = playListService.findPlayListByCondition(new Hints(Constants.DEFAULT_START,Constants.DEFAULT_COUNT),condition);
		
		condition.setOrderBy("All");
		recentlyAddedPlayLists = playListService.findPlayListByCondition(new Hints(Constants.DEFAULT_START,Constants.DEFAULT_COUNT),condition);
		
		return Action.SUCCESS;
	}
	
	public IPlayListService getPlayListService() {
		return playListService;
	}


	public MenuVO getMenuVO() {
		return menuVO;
	}

	public IPageList<PlayList> getPlayLists() {
		return playLists;
	}

	public IPageList<PlayList> getRecentlyAddedPlayLists() {
		return recentlyAddedPlayLists;
	}

}