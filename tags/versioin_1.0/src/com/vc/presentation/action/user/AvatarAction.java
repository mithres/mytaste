package com.vc.presentation.action.user;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.UserInfo;
import com.vc.service.user.IUserService;

public class AvatarAction extends BaseAction {

	private static final long serialVersionUID = -7832258542821564002L;

	@Autowired
	private IUserService userService = null;
	
	private File photo = null;

	private String photoContentType = null;
	private String photoFileName = null;
	
	private String type = "Main";
	
	private UserInfo userAccount = null;
	
	public String saveAvatar() {

		if (photo == null || photo.length() > 3145728) {
			this.addActionError("Photo length error.");
			return Action.INPUT;
		}

		if (!photoContentType.equals("image/png") && !photoContentType.equals("image/gif")
				&& !photoContentType.equals("image/jpg") && !photoContentType.equals("image/jpeg")) {
			this.addActionError("Photo type error.");
			return Action.INPUT;
		}
		
		if(!userService.updateProfilePhoto(photo)){
			this.addActionError("Upload profile photo error.");
			return Action.INPUT;
		}
		
		return Action.SUCCESS;
	}

	@Override
	public String process() {
		userAccount = userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
		return Action.SUCCESS;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getPhotoContentType() {
		return photoContentType;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public UserInfo getUserAccount() {
		return userAccount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
