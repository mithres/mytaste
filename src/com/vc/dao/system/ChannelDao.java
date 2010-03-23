package com.vc.dao.system;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vc.core.constants.Constants;
import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.Channels;

@Repository
public class ChannelDao extends GenericDAO<Channels, String> {

	private static final String FIND_PARENT_CHANNEL = " from Channels where parentChannel = null ";

	@SuppressWarnings("unchecked")
	public List<Channels> findParentChannels() {
		Hints hints = new Hints(0);
		hints.setHintParameters(Constants.ENABLE_QUERY_CACHE, Boolean.TRUE);
		return this.find(FIND_PARENT_CHANNEL, hints);
	}

}