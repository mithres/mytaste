package com.vc.presentation.action.common;

import java.util.List;

import org.mcavallo.opencloud.Cloud;
import org.mcavallo.opencloud.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.constants.Constants;
import com.vc.core.dao.Hints;
import com.vc.entity.Tags;
import com.vc.service.system.ISystemService;

public class TagCloudAction extends BaseAction {

	private static final long serialVersionUID = -8849349453225183123L;

	@Autowired
	private ISystemService systemService = null;

	private Cloud cloud = null;

	@Override
	public String process() {

		cloud = new Cloud();
		cloud.setMinWeight(8);
		cloud.setMaxTagsToDisplay(100);
		cloud.setMaxWeight(30.0);
		cloud.setDefaultLink(this.getWebAppPath() + "/vod/tag");

		List<Tags> tags = systemService.findTags(new Hints(0, Constants.DEFAULT_TAG_NUM));
		for (Tags tag : tags) {
			cloud.addTag(new Tag(tag.getTag(),tag.getCount()));
		}

		return Action.SUCCESS;
	}

	public Cloud getCloud() {
		return cloud;
	}

}
