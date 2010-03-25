package com.vc.service.system;

import java.util.List;

import com.vc.entity.Category;
import com.vc.entity.Channels;
import com.vc.entity.Configuration;
import com.vc.entity.Role;

public interface ISystemService {

	public abstract Configuration install(Configuration conf);

	public abstract Configuration checkStatus();

	public abstract List<Role> finaAllRole();

	// Category manage
	public abstract Category findCategoryById(String categoryId);

	public abstract Category createCategory(Category category);

	public abstract Category updateCategory(Category category);

	public abstract void deleteCategory(String categoryId);

	public abstract List<Category> findAllCategories();

	// Channel manage
	public abstract Channels findChannelById(String channelId);

	public abstract Channels createChannel(Channels channel);

	public abstract Channels updateChannel(Channels channel);

	public abstract void deleteChannel(String channelId);

	public abstract List<Channels> findParentChannels();

}