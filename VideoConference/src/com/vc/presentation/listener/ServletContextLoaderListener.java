package com.vc.presentation.listener;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vc.core.constants.Constants;
import com.vc.service.cluster.ILoadBalancer;
import com.vc.service.user.ISecurityManager;
import com.vc.util.configuration.ServerConfiguration;
import com.vc.vo.LBNode;

public class ServletContextLoaderListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		servletContextEvent.getServletContext().removeAttribute(Constants.URL_AUTHORITIES);
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		ServletContext servletContext = servletContextEvent.getServletContext();
		ISecurityManager securityManager = this.getSecurityManager(servletContext);

		// Load url authorities
		Map<String, String> urlAuthorities = securityManager.loadUrlAuthorities();
		servletContext.setAttribute(Constants.URL_AUTHORITIES, urlAuthorities);

		// Regist current node as a loadbalancer
		LBNode node = new LBNode(ServerConfiguration.getRTMPIp(), ServerConfiguration.getRTMPPort(), "rtmp");
		getLoadBalacer(servletContext).registerLBNode(node);		
		
	}

	protected ISecurityManager getSecurityManager(ServletContext servletContext) {
		return (ISecurityManager) WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean(
				ISecurityManager.SECURITY_MANAGER_NAME);
	}

	protected ILoadBalancer getLoadBalacer(ServletContext servletContext) {
		return (ILoadBalancer) WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean(
				ILoadBalancer.LOAD_BALACENER_NAME);
	}

}
