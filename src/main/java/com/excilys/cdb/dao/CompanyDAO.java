package com.excilys.cdb.dao;

import com.excilys.cdb.model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.dao.MyConnectionToDB;
import com.excilys.cdb.exceptions.DAOException;
import com.excilys.cdb.mapper.CompanyMapper;

@Repository
public class CompanyDAO {

	private ResultSet res;
	
	JdbcTemplate jdbcTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class); 
	
	private final String FIND_NUMBER_OF_COMPANY 	= "	SELECT count(company.id)"
													+ "	FROM company";
	
	private final String CREATE_COMPANY = "INSERT INTO Company ()"
										+ "";
	
	private final String FIND_ALL_COMPANIES	= "SELECT ca.id, ca.name"
											+ "FROM company ca";
	
	private final String FIND_COMPANY_BY_ID	= "SELECT ca.id, ca.name"
											+ "FROM company ca"
											+ "WHERE id = ?";
	
	private final String UPDATE_COMPANY 	= "	UPDATE company"
												+ "	SET name = ?"
												+ "	WHERE id = ?";
	
	private final String DELETE_COMPANY 	= "	DELETE "
												+ " FROM Company"
												+ "	WHERE id = ?";	
	
	private MyConnectionToDB connection;

	
	public CompanyDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Company> findAllCompanies() throws DAOException {
		return (List<Company>) jdbcTemplate.query(FIND_ALL_COMPANIES, new CompanyMapper());
	}
	
	public Company findById(int id) throws DAOException {
		return jdbcTemplate.queryForObject(FIND_COMPANY_BY_ID, new Object[] { id }, new CompanyMapper());
		
		
	}
}
