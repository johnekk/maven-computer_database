package com.excilys.cdb.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Company;

class CompanyDAOTest {

	private CompanyDAO companyDao ;
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOTest.class);	
	
	@BeforeAll
	public static void beforeAll() {  
		BasicConfigurator.configure();
		LOGGER.info("@BeforeAll");
	}
	
	@BeforeEach
	public void beforeEach() {
		System.setProperty("test","true");
		LOGGER.info("@beforeEach");	
	}

	@Test
	void testFindAllCompanies() {
		List<Company> companies = companyDao.findAllCompanies();
		assertThat(companies).isNotEmpty().hasSize(15);
	}
	

	@AfterEach
	void afterEach() {
		LOGGER.info("@AfterEach "); 
		System.setProperty("test","false");
	}

}
