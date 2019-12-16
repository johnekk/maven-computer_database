package com.excilys.cdb.dao;

import java.sql.*;
import com.excilys.cdb.exceptions.DAOConfigurationException;

public class MySQLConnection {
	
	private static String url = "jdbc:mysql://localhost:3306/computer-database-db?useSSL=false";
	private static String user = "admincdb";
	private static String password = "qwerty1234";
	
	/** Declaration de l'objet Connection */
	private static Connection connection;
	
	/** Méthode qui retourne notre instance*/
	public static Connection getConnectionInstance() throws DAOConfigurationException {
		if(connection == null) {
			try {
				connection = DriverManager.getConnection(url, user, password);
	 		} catch (SQLException sqle) {
				throw new DAOConfigurationException("Erreur lors de la connexion: " + sqle);
			}	
		}
		return connection;
	}

	
	public static void myDeconnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
