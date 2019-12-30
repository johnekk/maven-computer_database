package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.excilys.cdb.exceptions.DAOConfigurationException;
import com.excilys.cdb.exceptions.DAOException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class MySQLConnection {
	
	//initialiser HikariConfig avec un fichier de propriétés placé dans le répertoire resources :
	private static HikariConfig config = new HikariConfig("/datasource.properties" );
	//creation d'une instance unique d'une source de donnée
	private static HikariDataSource ds = new HikariDataSource( config );
	
	private static Connection myConnection;
    private  MySQLConnection() {};

	
	/** Méthode qui retourne notre instance
	 * @throws SQLException */
	public static Connection getConnectionInstance() throws DAOConfigurationException, SQLException {
		
		if(myConnection == null) {
			try {
				myConnection = ds.getConnection();
			} catch (SQLException e) {
				throw new DAOException( e );
			}
		}
		return ds.getConnection();
	}
	
}