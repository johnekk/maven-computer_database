package com.excilys.cdb.dao;

import java.sql.*;


import com.excilys.cdb.exceptions.DAOConfigurationException;

public class MySQLConnection {
	
	private static String url = "jdbc:mysql://localhost:3306/computer-database-db?useSSL=false";
	private static String user = "admincdb";
	private static String password = "qwerty1234";
	
	/** Declaration de l'objet Connection */
	private static Connection connection;
	
	private static Connection connect = null;
		
	
	/** Méthode qui retourne notre instance
	 * @throws SQLException */
	public static Connection getConnectionInstance() throws DAOConfigurationException, SQLException {
		if(System.getProperty("test") != null && System.getProperty("test").equals("true")) {
			String urlTest ="jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:C&E.sql'";
			String userTest = "";
			String passwdTest = "";
			System.out.println("Connexion database h2 ");
			if(connect == null || connect.isClosed()) {
				connect = DriverManager.getConnection(urlTest, userTest, passwdTest);
			} else {
				System.out.println("Connexion database MySQL ");
				if(connection == null || connect.isClosed() ) {
					try {
						connection = DriverManager.getConnection(url, user, password);
					} catch (SQLException sqle) {
						throw new DAOConfigurationException("Erreur lors de la connexion: " + sqle);
					}	
				}
				return connection;
			}
		}
		return connect;
	}
}