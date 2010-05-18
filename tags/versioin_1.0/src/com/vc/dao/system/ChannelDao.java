package com.vc.dao.system;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.Channels;

@Repository
public class ChannelDao extends GenericDAO<Channels, String> {

	private static final String FIND_PARENT_CHANNELS = " from Channels where parentId = null ";

	private static final String FIND_SUB_CHANNELS = " from Channels where parentId = ? order by channelName ";

	private static final String FIND_ALL_CHANNELS = " select distinct(c) from Channels c left join fetch c.childChannels where c.parentId is null order by c.channelName ";
	
	public static final String FIND_CHANNEL_WITH_SUBCHANNES = " from Channels c left join fetch c.childChannels where c.id = ? ";

	public Channels findChannelWithSubChannel(String channelId){
		return this.findPaged(FIND_CHANNEL_WITH_SUBCHANNES, new Hints(0), channelId).iterator().next();
	}
	
	@SuppressWarnings("unchecked")
	public List<Channels> findSubChannels(String channelId) {
		Hints hints = new Hints(0);
		return this.find(FIND_SUB_CHANNELS, hints, channelId);
	}

	@SuppressWarnings("unchecked")
	public List<Channels> findParentChannels() {
		Hints hints = new Hints(0);
		return this.find(FIND_PARENT_CHANNELS, hints);
	}

	public List<Channels> findAllChannels() {
		return this.findPaged(FIND_ALL_CHANNELS, new Hints(0));
	}

}