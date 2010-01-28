package com.vc.presentatioin.listener;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vc.service.user.ISecurityManager;

public class ServletContextLoaderListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		servletContextEvent.getServletContext().removeAttribute("urlAuthorities");
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		ISecurityManager securityManager = this.getSecurityManager(servletContext);

		Map<String, String> urlAuthorities = securityManager.loadUrlAuthorities();
		servletContext.setAttribute("urlAuthorities", urlAuthorities);

	}

	protected ISecurityManager getSecurityManager(ServletContext servletContext) {
		return (ISecurityManager) WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean("userService");
	}

}
