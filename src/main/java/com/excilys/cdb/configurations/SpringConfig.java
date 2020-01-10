package com.excilys.cdb.configurations;


import javax.servlet.ServletContext;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc //Adding this annotation to an @Configuration class imports the Spring MVC configuration from WebMvcConfigurationSupport
@ComponentScan(basePackages = {	"com.excilys.cdb.configuration",
								"com.excilys.cdb.controllers",
								"com.excilys.cdb.dao",
								"com.excilys.cdb.services",
								"com.excilys.cdb.servlets"})
@PropertySource("classpath:datasource.properties")
public class SpringConfig implements WebApplicationInitializer {
	
	@Value("${jdbcUrl}")
	private String url;
	@Value("${dataSource.user}")
	private String username;
	@Value("${dataSource.password}")
	private String password;
	@Value("${driverClassName}")
	private String jdbcDriver;
	

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(jdbcDriver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
		return dataSource;
	}
	
	@Override
    public void onStartup(ServletContext servletCxt) throws ServletException {

        // Load Spring web application configuration
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(SpringConfig.class, SpringMvcConfig.class);
        rootContext.setServletContext(servletCxt);

        // Create and register the DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(rootContext);
        ServletRegistration.Dynamic registration = servletCxt.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
		
	@Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
        //return new JdbcTemplate(dataSource());
    }
}
