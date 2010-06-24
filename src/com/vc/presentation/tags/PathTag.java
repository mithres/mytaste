package com.vc.presentation.tags;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author ammen
 * 
 */
public class PathTag extends TagSupport {

	private static final long serialVersionUID = 2101380568989199665L;

	/**
	 * Protocol(schema) name which may differ from used by default
	 */
	private String protocol;

	public int doStartTag() throws JspException {

		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		String contextPath = request.getContextPath();
		int port = request.getServerPort();

		StringBuffer path = new StringBuffer(protocol == null ? request.getScheme() : protocol);
		path.append("://").append(request.getServerName()).append(port != 80 ? ":" + port : "");
		path.append("/".equals(contextPath) ? "" : contextPath);
		
		try {
			pageContext.getOut().write(path.toString());
		} catch (IOException e) {
		}
		return EVAL_PAGE;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

}
