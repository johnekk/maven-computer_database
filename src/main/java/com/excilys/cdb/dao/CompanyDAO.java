package com.excilys.cdb.dao;

import java.util.List;

//import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.exceptions.DAOException;
import com.excilys.cdb.mapper.CompanyMapper;
import com.excilys.cdb.model.Company;

@Repository
public class CompanyDAO {

	JdbcTemplate jdbcTemplate;
	
	
	/*private final String FIND_NUMBER_OF_COMPANY 	= "	SELECT count(company.id)"
													+ "	FROM company";*/
	
	//private static final String DELETE_COMPANY_BY_COMPANYID   = "DELETE FROM company where id = ? ;";
	
	//private static final String DELETE_COMPUTER_BY_COMPANYID  = "DELETE FROM computer where company_id = ? ;";
	
	
	private static final String FIND_ALL_COMPANIES	= "SELECT ca.id, ca.name"
											+ "FROM company ca";
	
	private static final String FIND_COMPANY_BY_ID	= "SELECT ca.id, ca.name"
											+ "FROM company ca"
											+ "WHERE id = ?";

	
	public CompanyDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Company> findAllCompanies() throws DAOException {
		return jdbcTemplate.query(FIND_ALL_COMPANIES, new CompanyMapper());
	}
	
	public Company findById(int id) throws DAOException {
		return jdbcTemplate.queryForObject(FIND_COMPANY_BY_ID, new Object[] { id }, new CompanyMapper());
	}
}
