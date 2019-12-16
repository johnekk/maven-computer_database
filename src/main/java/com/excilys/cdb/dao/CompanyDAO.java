package com.excilys.cdb.dao.impl;

import com.excilys.cdb.model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.cdb.dao.MySQLConnection;
import com.excilys.cdb.exceptions.DAOException;;


public class CompanyDAO {

	private ResultSet res;
	
	private final static String FIND_ALL_COMPANIES = "SELECT ca.id, ca.name FROM company ca";
	
	/** START Singleton.CompanyDAO -- Lazy-Loading */

	// Private Constructor
	private CompanyDAO() {};
	
	private static CompanyDAO companyDAO = null;
	
	// Point d'accès pour l'instance unique du singleton 
	public static CompanyDAO getCompanyDAOInstance() {
		if (companyDAO == null) {
			companyDAO = new CompanyDAO();
		}
		return companyDAO;
	}
	
	/** END Singleton.CompanyDAO */
	
	public ArrayList<Company> findAllCompanies() throws DAOException {
		ArrayList<Company> c = new ArrayList<>();
		try(Connection connect = MySQLConnection.getConnectionInstance(); PreparedStatement statement = connect.prepareStatement(FIND_ALL_COMPANIES);) {
			/** On se connecte, on prépare la requete, on l'éxécute et on récupère le resultat*/
			res = statement.executeQuery(FIND_ALL_COMPANIES);
			
			while (res.next()) {
				c.add( new Company.CompanyBuilder().setName(res.getString("name")).build());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException( e );
		}
		return c;
	}
}
