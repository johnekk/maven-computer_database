package com.excilys.cdb.services;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.NotNull;
import org.junit.jupiter.api.Test;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.mapper.CompanyMapper;
import com.excilys.cdb.model.Company;


class CompanyServiceTest {
	
	//Création de mon object
	@Mock
	private CompanyDAO daoMock;
	
	@InjectMocks
	private CompanyService service;
	
	//Méthode pour intégrer Mockito dans les tests Junit
	@Before
	public void setUp() throws Exception { 
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFindAllCompanies(){
		List<Company> c = new ArrayList<Company>();
		Mockito.when(daoMock.findAllCompanies()).thenReturn(c);
		
		List<Company> company = service.findAllCompanies();
		
		assertEquals(c, company);
		
	}
}
