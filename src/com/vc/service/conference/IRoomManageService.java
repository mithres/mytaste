package com.vc.service.conference;

import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.Room;
import com.vc.presentation.exception.RoomNotFoundException;
import com.vc.presentation.exception.RoomPeopleFullException;

public interface IRoomManageService {

	public abstract Room createRoom(Room room);

	public abstract Room updateRoom(Room room);

	public abstract void deleteRoom(String roomId);

	public abstract IPageList<Room> findAllRooms(Hints hnts);

	public abstract void joinRoom(String roomId) throws RoomNotFoundException, RoomPeopleFullException;

	public abstract void leaveRoom(String roomId) throws RoomNotFoundException;

}
