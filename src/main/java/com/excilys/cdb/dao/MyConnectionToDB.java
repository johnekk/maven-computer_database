package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.excilys.cdb.exceptions.DAOException;
@Component
public class MyConnectionToDB {
	
	private static Connection myConnection;
	
	private static DataSource dataSource;
    
	private MyConnectionToDB() {};
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(MyConnectionToDB.class);
   
	/** Méthode qui retourne notre instance
	 * @throws SQLException */
	public Connection getConnectionInstance() throws  SQLException {
		
		if(myConnection == null) {
			try {
				myConnection = dataSource.getConnection();
			} catch (SQLException e) {
				throw new DAOException( e );
			}
		}
		return myConnection;
	}
	
}