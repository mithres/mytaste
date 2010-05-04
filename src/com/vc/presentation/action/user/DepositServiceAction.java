package com.vc.presentation.action.user;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.PointCard;
import com.vc.entity.UserInfo;
import com.vc.presentation.exception.DepositException;
import com.vc.presentation.exception.PointCardException;
import com.vc.service.system.IPurchaseService;
import com.vc.service.user.IUserService;
import com.vc.util.security.ItemChecker;
import com.vc.vo.PurchaseVO;

public class DepositServiceAction extends BaseAction {

	private static final long serialVersionUID = 1470350362804180491L;

	@Autowired
	private IPurchaseService purchaseService = null;
	@Autowired
	private IUserService userService = null;

	private String cardPassword = null;
	
	private String type = "Balance";

	private PointCard pointCard = null;
	private UserInfo userAccount = null;

	public String index() {
		userAccount = userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
		return Action.SUCCESS;
	}

	private final void preValidate() {

		if (ItemChecker.checkNull(cardPassword)) {
			this.addActionError(this.getText("vc.accountdeposits.card.password.empty"));
		}
		if (ItemChecker.checkNumber(cardPassword)) {
			this.addActionError(this.getText("vc.accountdeposits.card.password.error"));
		}
	}

	public String summary() {
		try {

			preValidate();
			if (this.getActionErrors().size() > 0) {
				return Action.INPUT;
			}

			pointCard = purchaseService.findCardInfoByPassword(cardPassword);
			if (pointCard.getUsed()) {
				this.addActionError(getText("vc.accountdeposits.cardused"));
				return Action.INPUT;
			} else if (pointCard.getExpireTime().before(new Timestamp(System.currentTimeMillis()))) {
				this.addActionError(getText("vc.accountdeposits.card.expired"));
				return Action.INPUT;
			}

			userAccount = userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
		} catch (PointCardException e) {
			this.addActionError(getText(e.getMessageKey()));
			return Action.INPUT;
		}
		return Action.SUCCESS;
	}

	@Override
	public String process() {

		preValidate();
		if (this.getActionErrors().size() > 0) {
			String json = "{\"status\":\"error\",\"messages\",\""+this.getActionErrors().iterator().next()+"\"}";
			this.write(json);
			return Action.NONE;
		}

		PurchaseVO vo = new PurchaseVO();
		vo.setAccount(SecurityContextHolder.getContext().getAuthentication().getName());
		vo.setCardPassword(cardPassword);
		vo.setRemoteIp(request.getRemoteAddr());
		try {
			purchaseService.purchase(vo);
			String json = "{\"status\":\"success\"}";
			this.write(json);
		} catch (DepositException e) {
//			this.addActionError(e.getMessage());
			String json = "{\"status\":\"error\",\"messages\",\""+e.getMessage()+"\"}";
			this.write(json);
			return Action.NONE;
		} catch (PointCardException e) {
			String json = "{\"status\":\"error\",\"messages\",\""+e.getMessage()+"\"}";
			this.write(json);
			return Action.NONE;
		}
		return Action.NONE;
	}

	public String getCardPassword() {
		return cardPassword;
	}

	public void setCardPassword(String cardPassword) {
		this.cardPassword = cardPassword;
	}

	public PointCard getPointCard() {
		return pointCard;
	}

	public UserInfo getUserAccount() {
		return userAccount;
	}

	public String getType() {
		return type;
	}

}
