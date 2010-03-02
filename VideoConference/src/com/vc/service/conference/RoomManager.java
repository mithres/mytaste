package com.vc.service.conference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.core.entity.PageListImpl;
import com.vc.dao.conference.RoomDao;
import com.vc.entity.Room;

@Service
public class RoomManager implements IRoomManageService {

	@Autowired
	private RoomDao roomDao = null;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Room createRoom(Room room) {
		roomDao.create(room);
		return room;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteRoom(String roomId) {
		roomDao.delete(roomId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Room updateRoom(Room room) {
		roomDao.update(room);
		return room;
	}

	@Override
	public IPageList<Room> findAllRooms(Hints hnts) {
		IPageList<Room> list = new PageListImpl<Room>();
		list.setRecordTotal(roomDao.findRoomCount());
		list.setRecords(roomDao.findAllRooms(hnts));
		return list;
	}

}
