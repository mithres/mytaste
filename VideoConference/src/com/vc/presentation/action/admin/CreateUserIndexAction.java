package com.vc.presentation.action.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.Role;
import com.vc.service.system.ISystemService;

public class CreateUserIndexAction extends BaseAction {

	private static final long serialVersionUID = -9175629983207926514L;

	@Autowired
	private ISystemService systemService = null;

	private List<Role> roles = null;

	@Override
	public String process() {
		roles = systemService.finaAllRole();
		return Action.SUCCESS;
	}

	public List<Role> getRoles() {
		return roles;
	}

}
