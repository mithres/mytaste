package com.vc.service.conference;

import org.springframework.beans.factory.annotation.Autowired;

import com.vc.dao.conference.RoomDao;
import com.vc.entity.Room;

public class RoomManager implements IRoomManageService {
	
	@Autowired
	private RoomDao roomDao = null;
	
	@Override
	public Room createRoom(Room room) {
		roomDao.create(room);
		return room;
	}

	@Override
	public void deleteRoom(String roomId) {
		roomDao.delete(roomId);
	}

	@Override
	public Room updateRoom(Room room) {
		roomDao.update(room);
		return room;
	}

}
