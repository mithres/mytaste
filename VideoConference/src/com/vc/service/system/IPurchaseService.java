package com.vc.service.system;

import java.io.File;

import org.springframework.security.annotation.Secured;

import com.vc.entity.PointCard;
import com.vc.entity.UserInfo;
import com.vc.presentation.exception.DepositException;
import com.vc.presentation.exception.PointCardException;
import com.vc.vo.PurchaseVO;

public interface IPurchaseService {

	@Secured( { "ROLE_ADMIN" })
	public abstract boolean importPointCardInfo(File file);

	public abstract PointCard findCardInfoByPassword(String password) throws PointCardException;

	@Secured( { "ROLE_USER", "ROLE_ADMIN" })
	public abstract UserInfo purchase(PurchaseVO vo) throws DepositException, PointCardException;
}
