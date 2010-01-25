package com.vc.presentation.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.vc.util.photo.PhotoType;
import com.vc.util.photo.PicUtil;

public class PhotoTag extends TagSupport {

	private static final long serialVersionUID = -3882926658844492250L;

	private Long index = null;

	private String photoType = null;

	public int doStartTag() throws JspException {
		
		PhotoType type = null;
		if(photoType.endsWith(PhotoType.FilmScreenShot.toString())){
			type = PhotoType.FilmScreenShot;
		}else{
			type = PhotoType.UserPhoto;
		}
		
		StringBuffer path = new StringBuffer(PicUtil.getPhotoAddress(index, type));

		try {
			pageContext.getOut().write(path.toString());
		} catch (IOException e) {
		}
		return EVAL_PAGE;
	}

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public String getPhotoType() {
		return photoType;
	}

	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}

}