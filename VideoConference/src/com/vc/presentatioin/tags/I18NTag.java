package com.vc.presentatioin.tags;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author ammen
 * 
 */
public class I18NTag extends TagSupport {

	private static final long serialVersionUID = -30786921442739394L;

	private String protocol;

	private static Set<String> SUPPORTED_LANGUAGES = new HashSet<String>();
	private static final String DEFAULT_LANGUAGE = "en_US";

	@Override
	public int doStartTag() throws JspException {

		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		String contextPath = request.getContextPath();
		int port = request.getServerPort();
		StringBuffer path = new StringBuffer(protocol == null ? request.getScheme() : protocol);
		path.append("://").append(request.getServerName()).append(port != 80 ? ":" + port : "");
		path.append("/".equals(contextPath) ? "" : contextPath);

		String locale = request.getLocale().toString();

		if (request.getSession().getAttribute("WW_TRANS_I18N_LOCALE") != null) {
			locale = request.getSession().getAttribute("WW_TRANS_I18N_LOCALE").toString();
		}

		if (SUPPORTED_LANGUAGES.contains(locale)) {
			path.append("/resources/").append(locale);
		} else {
			StringBuffer resourcePath = new StringBuffer(request.getSession().getServletContext().getRealPath("/"));
			resourcePath.append("resources/").append(locale);

			File file = new File(resourcePath.toString());
			if (file != null && file.exists()) {
				path.append("/resources/").append(locale);
				SUPPORTED_LANGUAGES.add(locale);
			} else {
				path.append("/resources/").append(DEFAULT_LANGUAGE);
			}
		}

		try {
			pageContext.getOut().write(path.toString());
		} catch (IOException e) {
		}
		return EVAL_PAGE;
	}

}
