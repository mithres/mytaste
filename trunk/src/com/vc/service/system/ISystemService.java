package com.vc.service.system;

import java.util.List;

import com.vc.core.dao.Hints;
import com.vc.entity.Channels;
import com.vc.entity.Configuration;
import com.vc.entity.Role;
import com.vc.entity.Tags;

public interface ISystemService {

	public abstract Configuration install(Configuration conf);

	public abstract Configuration checkStatus();

	public abstract List<Role> finaAllRole();

	// Channel manage
	public abstract Channels findChannelById(String channelId);

	public abstract Channels createChannel(Channels channel);

	public abstract Channels updateChannel(Channels channel);

	public abstract void deleteChannel(String channelId);

	public abstract List<Channels> findParentChannels();

	public abstract List<Channels> findAllSubChannels(String channelId);

	public abstract List<Tags> findTags(Hints hnts);

}