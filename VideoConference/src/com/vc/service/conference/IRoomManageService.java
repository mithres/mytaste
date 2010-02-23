package com.vc.service.conference;

import com.vc.entity.Room;

public interface IRoomManageService {

	public abstract Room createRoom(Room room);

	public abstract Room updateRoom(Room room);

	public abstract void deleteRoom(String roomId);

}
