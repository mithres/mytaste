package com.vc.presentation.action.room;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.constants.Constants;
import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.Room;
import com.vc.service.conference.IRoomManageService;
import com.vc.service.system.ISystemService;
import com.vc.vo.MenuVO;

public class RoomListAction extends BaseAction {

	private static final long serialVersionUID = -5265461725451697714L;
	
	@Autowired
	private ISystemService systemService = null;
	
	@Autowired
	private IRoomManageService roomManager = null;

	private IPageList<Room> rooms = null;
	
	private MenuVO menuVO = null;
	
	@Override
	public String process() {
		if(getSession().getAttribute(Constants.MENU_STAT) == null){
			menuVO = new MenuVO();	
			menuVO.setChannels(systemService.findParentChannels());
		}else{
			menuVO = (MenuVO)getSession().getAttribute(Constants.MENU_STAT);
		}
		menuVO.setMenuStat(Constants.CONFERENCE_SCOPE_NAME);
		getSession().setAttribute(Constants.MENU_STAT, menuVO);
		
		
		rooms = roomManager.findAllRooms(new Hints(getStartRow(), getPageCount()));
		return Action.SUCCESS;
	}

	public IPageList<Room> getRooms() {
		return rooms;
	}

	public MenuVO getMenuVO() {
		return menuVO;
	}

}