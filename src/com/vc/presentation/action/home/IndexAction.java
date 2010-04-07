package com.vc.presentation.action.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.constants.Constants;
import com.vc.entity.Channels;
import com.vc.service.system.ISystemService;
import com.vc.vo.MenuVO;

public class IndexAction extends BaseAction {

	private static final long serialVersionUID = -8814106229563837658L;

	@Autowired
	private ISystemService systemService = null;
//	@Autowired
//	private IPlayListService playListService = null;
	
	private MenuVO menuVO = null;
	
//	private List<Category> categories = null;
//	private List<PlayList> mostViewedPlayLists = null;
//	private List<PlayList> playListsOfWeek = null;

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

		
//		categories = systemService.findAllCategories();
//		mostViewedPlayLists = playListService.findPlayListByViewCount(new Hints(Constants.DEFAULT_START, 5));
//		playListsOfWeek = playListService.findPlayListByWeekView(new Hints(0, 1), TimeUtil.getCurrentWeek(0), 0);
		return Action.SUCCESS;
	}

	public MenuVO getMenuVO() {
		return menuVO;
	}


//	public List<Category> getCategories() {
//		return categories;
//	}
//
//	public List<PlayList> getMostViewedPlayLists() {
//		return mostViewedPlayLists;
//	}
//
//	public List<PlayList> getPlayListsOfWeek() {
//		return playListsOfWeek;
//	}

}