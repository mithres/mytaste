package com.vc.presentation.action.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.PointCard;
import com.vc.presentation.exception.DepositException;
import com.vc.presentation.exception.PointCardException;
import com.vc.service.system.IPurchaseService;
import com.vc.vo.PurchaseVO;

public class DepositServiceAction extends BaseAction {

	private static final long serialVersionUID = 1470350362804180491L;

	@Autowired
	private IPurchaseService purchaseService = null;

	private String cardPassword = null;

	private String account = null;

	private PointCard pointCard = null;

	public String index() {
		return Action.SUCCESS;
	}

	public String summary() {
		try {
			pointCard = purchaseService.findCardInfoByPassword(cardPassword);
		} catch (PointCardException e) {
			this.addActionError(e.getMessage());
			return Action.INPUT;
		}
		return Action.SUCCESS;
	}

	@Override
	public String process() {
		PurchaseVO vo = new PurchaseVO();
		vo.setAccount(account);
		vo.setCardPassword(cardPassword);
		vo.setRemoteIp(request.getRemoteAddr());
		try {
			purchaseService.purchase(vo);
		} catch (DepositException e) {
			this.addActionError(e.getMessage());
			return Action.INPUT;
		} catch (PointCardException e) {
			this.addActionError(e.getMessage());
			return Action.INPUT;
		}
		return Action.SUCCESS;
	}

	public String getCardPassword() {
		return cardPassword;
	}

	public void setCardPassword(String cardPassword) {
		this.cardPassword = cardPassword;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public PointCard getPointCard() {
		return pointCard;
	}

}
