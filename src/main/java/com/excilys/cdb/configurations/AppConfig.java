package com.excilys.cdb.configurations;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.services.CompanyService;
import com.excilys.cdb.servlets.DashboardServlet;


@Configurable
@ComponentScan(basePackageClasses = {AppConfig.class, CompanyService.class, CompanyDAO.class, DashboardServlet.class})
public class AppConfig extends AbstractContextLoaderInitializer{
	
	@Override
	protected WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppConfig.class);
		return rootContext;
	}

}
