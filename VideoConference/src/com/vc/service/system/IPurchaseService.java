package com.vc.service.system;

import java.io.File;

import org.springframework.security.annotation.Secured;

import com.vc.entity.PointCard;
import com.vc.entity.UserInfo;

public interface IPurchaseService {

	@Secured( { "ROLE_ADMIN" })
	public abstract boolean importPointCardInfo(File file);

	public abstract PointCard findCardInfoByPassword(String password);

	@Secured( { "ROLE_USER", "ROLE_ADMIN" })
	public abstract UserInfo purchase(String userName, String password);
}
