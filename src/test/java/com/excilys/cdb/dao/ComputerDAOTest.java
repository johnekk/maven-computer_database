package com.excilys.cdb.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

class ComputerDAOTest {

	private ComputerDAO computerDao;
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

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void testCreateComputer() {
		Computer computer = new Computer.ComputerBuilder().setId(16).setName("test").setIntroduced(null).setdiscontinued(null).setCompany(new Company.CompanyBuilder().setId(1).build()).build();
		//computerDao.createComputer(computer);
		
		equals(computer.equals(new Computer.ComputerBuilder().build()));
		
	}

	@Test
	void testFindAllComputers() {
		int limit = 0;
		int offset = 0;
		List<Computer> computers = computerDao.findAllComputers();
		assertEquals(computers.size(), 15);
	}


	@Test
	void testFindComputerById() {
	//	int computer = computerDao.findComputerById(1).getId();
	//	assertEquals(computer.getId(), 1);
	}

	@Test
	void testUpdateComputer() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteComputer() {
	//	computerDao.deleteComputer(16);
	}
	
	@AfterEach
	void afterEach() {
		LOGGER.info("@AfterEach "); 
		System.setProperty("test","false");
	}

}
