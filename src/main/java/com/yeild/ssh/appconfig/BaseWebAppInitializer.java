package com.yeild.ssh.appconfig;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.annotation.Order;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.util.IntrospectorCleanupListener;
import org.springframework.web.util.Log4jConfigListener;

@Order(1)
public class BaseWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		// the ram cleanner
		container.addListener(IntrospectorCleanupListener.class);
		container.setInitParameter("log4jConfigLocation", "classpath:config/properties/log4j.properties");
		container.setInitParameter("log4jRefreshInterval", "600000");
		container.addListener(Log4jConfigListener.class);
		
		FilterRegistration.Dynamic etagFilter = container.addFilter("etagFilter", new ShallowEtagHeaderFilter());
		etagFilter.addMappingForUrlPatterns(
				EnumSet.of(DispatcherType.REQUEST,DispatcherType.FORWARD,DispatcherType.INCLUDE), false, "/*");
		
		FilterRegistration.Dynamic openSessionRegistration = container.addFilter("hibernateFilter"
				, new OpenSessionInViewFilter());
		openSessionRegistration.addMappingForUrlPatterns(
				EnumSet.of(DispatcherType.REQUEST,DispatcherType.FORWARD,DispatcherType.INCLUDE), false, "/");
	}

}
