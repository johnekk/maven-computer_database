package com.excilys.cdb.services;

import org.springframework.stereotype.Service;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.model.Company;

@Service
public class CompanyService {
	
	private final CompanyDAO companyDAO;

	public CompanyService(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}
	
	public void findAllCompanies() {
        for(Company company : companyDAO.findAllCompanies()) {
            System.out.println(company.toString());
        }
	}
}
