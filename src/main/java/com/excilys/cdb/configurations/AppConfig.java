package com.excilys.cdb.configurations;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
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
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/computer-database-db?autoReconnect=true&useSSL=false");
        dataSource.setUsername("admincdb");
        dataSource.setPassword("qwerty1234");
		return dataSource;
	}
	
	@Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }
}
