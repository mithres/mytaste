package com.vc.core.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public abstract class BaseAction extends ActionSupport implements Preparable, ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 2554551704059634634L;

	protected final Logger log = Red5LoggerFactory.getLogger(getClass(), "VideoConference");

	protected static final String NEXT = "next";

	public static final String ERROR_PAGE = "error";
	public static final String RUNTIME_ERROR_PAGE = "runtime";
	public static final String NEED_LOGIN = "needlogin";

	protected HttpServletRequest request = null;
	protected HttpServletResponse response = null;

	private String backToUrl = null;

	public abstract String process();

	public final String execute() {

		try {
			return process();
		} catch (RuntimeException e) {
			log.error("BaseAction error", e);
			addActionError(e.getMessage());
			return RUNTIME_ERROR_PAGE;
		}
	}

	public ActionContext getActionContext() {
		return ActionContext.getContext();
	}

	public HttpSession getSession() {
		return request.getSession();
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void write(String message) throws IOException {
		response.getWriter().write(message);
	}

	public String getWebAppPath() {
		StringBuffer sb = new StringBuffer(request.getScheme() + ":" + "//" + request.getServerName() + ":" + request.getServerPort());
		sb.append(request.getContextPath());
		return sb.toString();
	}

	public String getBackToUrl() {
		return backToUrl;
	}

	public void setBackToUrl(String backToUrl) {
		this.backToUrl = backToUrl;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void prepare() throws Exception {

	}

}
