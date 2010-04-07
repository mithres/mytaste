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
import com.vc.vo.MenuVO;

public class PlayListIndexAction extends BaseAction {

	private static final long serialVersionUID = 6373087375653567380L;
	
	@Autowired
	private ISystemService systemService = null;
	
	@Autowired
	private IPlayListService playListService = null;
	
	private IPageList<PlayList> playList = null;
	
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
		
		playList = playListService.findPlayList(new Hints(getStartRow(),getPageCount()));
		return Action.SUCCESS;
	}
	
	public IPlayListService getPlayListService() {
		return playListService;
	}

	public IPageList<PlayList> getPlayList() {
		return playList;
	}

	public MenuVO getMenuVO() {
		return menuVO;
	}

}