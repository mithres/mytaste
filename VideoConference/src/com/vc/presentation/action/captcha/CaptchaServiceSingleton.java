package com.vc.presentation.action.captcha;

import com.octo.captcha.service.image.ImageCaptchaService;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;
import com.vc.core.spring.ApplicationContextUtil;

public class CaptchaServiceSingleton {

	private static ImageCaptchaService instance = null;

	public static ImageCaptchaService getInstance() {
		
		if(instance == null){
			CaptchaGmailEngine cgm = (CaptchaGmailEngine)ApplicationContextUtil.getApplicationContext().getBean("imageEngine");
			instance = new GenericManageableCaptchaService(cgm,180,100000,75000);
		}
		
		return instance;
	}

}
