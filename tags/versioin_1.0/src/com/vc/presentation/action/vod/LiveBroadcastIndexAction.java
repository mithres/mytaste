package com.vc.presentation.action.vod;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.Room;
import com.vc.entity.RoomPrivacy;
import com.vc.entity.RoomType;
import com.vc.service.conference.IRoomManageService;

public class LiveBroadcastIndexAction extends BaseAction {

	private static final long serialVersionUID = -8895346314863873057L;

	@Autowired
	private IRoomManageService roomManager = null;

	private IPageList<Room> rooms = null;

	@Override
	public String process() {
		rooms = roomManager.findRoomsByType(new Hints(getStartRow(), getPageCount()), RoomType.Live, RoomPrivacy.Public);
		return Action.SUCCESS;
	}

	public IPageList<Room> getRooms() {
		return rooms;
	}

}