package com.excilys.cdb.dao;

import com.excilys.cdb.model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.dao.MyConnectionToDB;
import com.excilys.cdb.exceptions.DAOException;
import com.excilys.cdb.mapper.CompanyMapper;

@Repository
public class CompanyDAO {

	private ResultSet res;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class); 
	
	private final static String FIND_NUMBER_OF_COMPANY 	= "	SELECT count(company.id)"
														+ "	FROM company";
	
	private final static String FIND_ALL_COMPANIES	= "SELECT ca.id, ca.name"
													+ "FROM company ca";
	
	private final static String UPDATE_COMPANY 	= "	UPDATE company"
												+ "	SET name = ?"
												+ "	WHERE id = ?";
	
	private final static String DELETE_COMPANY 	= "	DELETE "
												+ " FROM Company"
												+ "	WHERE id = ?";	
	
	private MyConnectionToDB connection;
	
	private CompanyDAO(MyConnectionToDB connection) {
		this.connection = connection;
	}
	
	public List<Company> findAllCompanies() throws DAOException {
		List<Company> cList = new ArrayList<>();
		try(Connection connect = connection.getConnectionInstance(); PreparedStatement statement = connect.prepareStatement(FIND_ALL_COMPANIES);) {
			res = statement.executeQuery();
			while (res.next()) {
				cList.add(CompanyMapper.ResultSetToCompany(res)); 
			}
		} catch (SQLException error) {
			LOGGER.error(error.getMessage());
			throw new DAOException( error );
		}
		return cList;
	}
}
