package com.excilys.cdb.services;

import com.excilys.cdb.dao.impl.CompanyDAO;
import com.excilys.cdb.model.Company;

public class CompanyService {
	
	private static CompanyDAO companyDAO = CompanyDAO.getCompanyDAOInstance();
	
	/** START Singleton.ComputerService -- Lazy-Loading */
	
	private CompanyService() {};
	
	private static CompanyService companyService = null;
	
	public static CompanyService getInstance() {
		if (companyService == null) {
			companyService = new CompanyService();
		}
		return companyService;
	}
	
	/** END Singleton.ComputerService*/
	
	public static void findAllCompanies() {
        for(Company company : companyDAO.findAllCompanies()) {
            System.out.println(company.toString());
        }
	}
}
