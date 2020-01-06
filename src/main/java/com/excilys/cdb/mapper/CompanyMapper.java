package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.dtos.CompanyDTO;
import com.excilys.cdb.model.Company;

public class CompanyMapper {
	
	private CompanyMapper() {};

	public static Company CompanyDTOtoCompany(CompanyDTO companyDTO) {
		Company company = new Company.CompanyBuilder().setName(companyDTO.getName()).build();
		return company;
	}
	
	public static CompanyDTO CompanyDTOToCompany(Company company) {
		CompanyDTO companyDTO = new CompanyDTO.CompanyDTOBuilder().setName(company.getName()).build();
		return companyDTO;
	}
	
	public static Company ResultSetToCompany(ResultSet res) throws SQLException {
		
		Company company = 	new Company.CompanyBuilder().
							setId(res.getInt("id")).
							setName(res.getString("name")).
							build();
		return company;
	}
}
