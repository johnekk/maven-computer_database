package com.excilys.cdb.dao;

import java.sql.*;
import com.excilys.cdb.dao.exceptions.DAOConfigurationException;

public class MySQLConnection {
	
	private static String url = "jdbc:mysql://localhost:3306/computer-database-db";
	private static String user = "admincdb";
	private static String password = "qwerty1234";
	
	/** Declaration de l'objet Connection */
	private static Connection connect;
	
	/** Méthode qui retourne notre instance*/
	public static Connection getConnectionInstance() throws DAOConfigurationException {
		if(connect == null) {
			try {
				connect = DriverManager.getConnection(url, user, password);
	 		} catch (SQLException sqle) {
				throw new DAOConfigurationException("Erreur lors de la connexion: " + sqle);
			}	
		}
		return connect;
	}

	
	public static void myDeconnection() {
		if (connect != null) {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
