package com.excilys.cdb.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Computer.ComputerBuilder;

class ComputerDAOTest {

	private ComputerDAO computerDao = ComputerDAO.getComputerDAOInstance();
	private CompanyDAO companyDao = CompanyDAO.getCompanyDAOInstance();
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
		computerDao.createComputer(computer);
		
		equals(computer.isEqualTo(new Computer.ComputerBuilder().build()));
		
	}

	@Test
	void testFindAllComputers() {
		List<Computer> computers = computerDao.findAllComputers();
		assertEquals(computers.size(), 15);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void testFindComputerById() {
		Computer computer = computerDao.findComputerById(1).get();
		equals(computer.isEqualTo(new Computer(1, "Apple Inc.", null, null, null )));
	}

	@Test
	void testUpdateComputer() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteComputer() {
		computerDao.deleteComputer(0);
	}

}
