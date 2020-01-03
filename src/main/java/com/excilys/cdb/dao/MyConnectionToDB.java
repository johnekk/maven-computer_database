package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.excilys.cdb.exceptions.DAOException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class MyConnectionToDB {
	
	//initialiser HikariConfig avec un fichier de propriétés placé dans le répertoire resources :
	private HikariConfig config = new HikariConfig("datasource.properties");
	//creation d'une instance unique d'une source de donnée
	private HikariDataSource ds = new HikariDataSource( config );
	
	private static Connection myConnection;
    
	private MyConnectionToDB() {};
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(MyConnectionToDB.class);
   
	/** Méthode qui retourne notre instance
	 * @throws SQLException */
	public Connection getConnectionInstance() throws  SQLException {
		
		if(myConnection == null) {
			try {
				myConnection = ds.getConnection();
			} catch (SQLException e) {
				throw new DAOException( e );
			}
		}
		return myConnection;
	}
	
}