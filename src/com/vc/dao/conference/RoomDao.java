package com.vc.dao.conference;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.Room;

@Repository
public class RoomDao extends GenericDAO<Room, String> {

	private static final String FIND_ROOMCOUNT_BY_TYPE = " select count(roomId) from Room where roomPrivacy = ? and roomType = ? ";

	private static final String FIND_ROOM_BY_TYPE = " from Room where roomPrivacy = ? and roomType = ? order by createdTime desc ";

	private static final String FIND_ROOMCOUNT = " select count(roomId) from Room ";

	private static final String FIND_ROOM = " from Room order by createdTime desc ";
	
	public Long findRoomCount(){
		return this.findRowCount(FIND_ROOMCOUNT);
	}
	
	public List<Room> findAllRooms(Hints hnts){
		return this.findPaged(FIND_ROOM, hnts);
	}

}
