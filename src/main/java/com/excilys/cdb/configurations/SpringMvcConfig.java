package com.excilys.cdb.configurations;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

public class SpringMvcConfig implements WebMvcConfigurer{
	
	@Bean
    public ViewResolver internalResourceViewResolver() {
      InternalResourceViewResolver bean = new InternalResourceViewResolver();
      bean.setViewClass(JstlView.class);
      bean.setPrefix("/WEB-INF/views/");
      bean.setSuffix(".jsp");

      return bean;
    }
	
	 @Bean
	 public ReloadableResourceBundleMessageSource messageSource() {
		 ReloadableResourceBundleMessageSource ret = new ReloadableResourceBundleMessageSource();
		 ret.setBasename("classpath:messages");
		 ret.setDefaultEncoding("utf-8");
		 return ret;
	 }
	 
	 @Bean
	 public CookieLocaleResolver localeResolver(){
	    CookieLocaleResolver resolver = new CookieLocaleResolver();
	    resolver.setDefaultLocale(Locale.ENGLISH);
	    resolver.setCookieName("myCookie");
	    resolver.setCookieMaxAge(3600);
	    return resolver;
	 }
	 
	 @Bean
	 public LocaleChangeInterceptor localeChangeInterceptor() {
		 LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	     localeChangeInterceptor.setParamName("dashboard");
	     return localeChangeInterceptor;
	 }
	 
	 public void addInterceptors(InterceptorRegistry registry) {
		 registry.addInterceptor(localeChangeInterceptor());
	 }

}
