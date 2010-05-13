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
import com.vc.entity.RoomPrivacy;
import com.vc.entity.RoomType;
import com.vc.presentation.exception.RoomNotFoundException;
import com.vc.presentation.exception.RoomPeopleFullException;
import com.vc.service.cluster.ILoadBalancer;
import com.vc.vo.LBNode;

@Service
public class RoomManager implements IRoomManageService {

	@Autowired
	private ILoadBalancer loadBalancer = null;

	@Autowired
	private RoomDao roomDao = null;

	@Override
	public IPageList<Room> findRoomsByType(Hints hnts, RoomType type, RoomPrivacy privacy) {
		IPageList<Room> list = new PageListImpl<Room>();
		list.setRecordTotal(roomDao.findRoomCountByType(type, privacy));
		list.setRecords(roomDao.findRoomByType(hnts, type, privacy));
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Room createRoom(Room room) {
		LBNode node = loadBalancer.getLBNode();
		room.setStreamUrl(node.getConferenceServiceUrl());
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

	@Override
	public void joinRoom(String roomId) throws RoomNotFoundException, RoomPeopleFullException {
		Room room = roomDao.findById(roomId);
		if (room == null) {
			throw new RoomNotFoundException("Room " + roomId + " not found.", "RoomNotFound");
		}
		if (room.getCurrentPeopleCount() == room.getMaxPeopleCount()) {
			throw new RoomPeopleFullException("Room " + roomId + " is full.", "RoomIsFull");
		}
		room.setCurrentPeopleCount(room.getCurrentPeopleCount() + 1);
		roomDao.update(room);
	}

	@Override
	public void leaveRoom(String roomId) throws RoomNotFoundException {
		Room room = roomDao.findById(roomId);
		if (room == null) {
			throw new RoomNotFoundException("Room " + roomId + " not found.", "RoomNotFound");
		}
		if (room.getCurrentPeopleCount() > 0) {
			room.setCurrentPeopleCount(room.getCurrentPeopleCount() - 1);
		}
		roomDao.update(room);
	}

	

}
