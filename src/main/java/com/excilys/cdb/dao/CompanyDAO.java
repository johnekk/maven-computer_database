package com.excilys.cdb.dao;

import com.excilys.cdb.model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.dao.MySQLConnection;
import com.excilys.cdb.exceptions.DAOException;
import com.excilys.cdb.mapper.CompanyMapper;


public class CompanyDAO {

	private ResultSet res;
	private final static String FIND_ALL_COMPANIES = "SELECT ca.id, ca.name FROM company ca";
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class); 
	/** START Singleton.CompanyDAO -- Lazy-Loading */
	// Private Constructor
	private CompanyDAO() {};
	private static CompanyDAO companyDAO = null;
	private static CompanyMapper companyMapper = CompanyMapper.getCompanyMapper();
	
	// Point d'accès pour l'instance unique du singleton 
	public static CompanyDAO getCompanyDAOInstance() {
		if (companyDAO == null) {
			companyDAO = new CompanyDAO();
		}
		return companyDAO;
	}
	/** END Singleton.CompanyDAO */
	
	public ArrayList<Company> findAllCompanies() throws DAOException {
		ArrayList<Company> cList = new ArrayList<>();
		try(Connection connect = MySQLConnection.getConnectionInstance(); PreparedStatement statement = connect.prepareStatement(FIND_ALL_COMPANIES);) {
			res = statement.executeQuery(FIND_ALL_COMPANIES);
			while (res.next()) {
				cList.add(companyMapper.ResultSetToCompany(res)); 
			}
		} catch (SQLException error) {
			LOGGER.error(error.getMessage());
			throw new DAOException( error );
		}
		return cList;
	}
}
