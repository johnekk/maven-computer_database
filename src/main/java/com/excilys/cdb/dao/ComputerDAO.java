package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.excilys.cdb.dao.MyConnectionToDB;
import com.excilys.cdb.exceptions.DAOException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

@Component
public class ComputerDAO {
	
	private ResultSet res;
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class);
	
	private final static String CREATE_COMPUTER 	= "	INSERT INTO computer (name, introduced, discontinued, company_id)"
													+ "	VALUES (?,?,?,?)";

	
	private final static String FIND_ALL_COMPUTERS 	= "	SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name"
													+ "	FROM computer, company"
													+ " WHERE company.id = computer.company_id";
	
	private final static String FIND_COMPUTER_BY_ID = "	SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name"
													+ "	FROM computer, company"
													+ "	WHERE company.id = computer.company_id"
													+ "	AND computer.id = ?";
	
	private final static String FIND_COMPUTERS_BY_COMPANY	= "	SELECT computer.id, computer.name, compter.introduced, computer.discontinued"
															+ "	FROM computer"
															+ "	WHERE computer.company_id = ?";
		
	private final static String FIND_NUMBER_OF_COMPUTER	= "	SELECT count(computer.id) AS 'nbComputer'"
														+ "	FROM computer";
	
	
	private final static String UPDATE_COMPUTER = "	UPDATE computer"
												+ "	SET name = ?, introduce = ?, discontinued = ?, company_id = ?"
												+ "	WHERE id = ?";

	
	private final static String DELETE_COMPUTER = "	DELETE "
												+ "	FROM computer"
												+ "	WHERE id = ?";
	private MyConnectionToDB connection;
	
	private ComputerDAO(MyConnectionToDB connection) {
		this.connection = connection;
	}
	
	public Computer createComputer(Computer computer) throws DAOException {
		try{
			Connection connect = connection.getConnectionInstance(); 
			PreparedStatement statement = connect.prepareStatement(CREATE_COMPUTER);
			statement.setString(1, computer.getName());
			statement.setObject(2, computer.getIntroduced());
			statement.setObject(3, computer.getDiscontinued());
			statement.setInt(4, computer.getCompany().getId());
			statement.executeUpdate();
		} catch (SQLException error) {
			throw new DAOException( error );
		}
		return null;
	}

	
	public List<Computer> findAllComputers() {	
		List<Computer> c = new ArrayList<>();
		try(Connection connect = connection.getConnectionInstance(); PreparedStatement statement = connect.prepareStatement(FIND_ALL_COMPUTERS);) {
			res = statement.executeQuery(FIND_ALL_COMPUTERS);
			while (res.next()) {	
				c.add(new Computer.ComputerBuilder().
						setId(res.getInt("id")).
						setName(res.getString("name")).
						setIntroduced(res.getTimestamp("introduced")==null?null:res.getTimestamp("introduced").toLocalDateTime().toLocalDate()).
						setdiscontinued(res.getTimestamp("discontinued")==null?null:res.getTimestamp("discontinued").toLocalDateTime().toLocalDate()).
						setCompany(new Company.CompanyBuilder().setName(res.getString("name")).build()).build());
			}
		} catch (SQLException error) {
			throw new DAOException( error );
		}
		return c;
	}
	
	public int findNumberOfComputers() throws DAOException {
		try(Connection connect = connection.getConnectionInstance(); PreparedStatement statement = connect.prepareStatement(FIND_NUMBER_OF_COMPUTER);) {
			res = statement.executeQuery();
			if (res.first()) {
				return res.getInt("nbComputer");
			}
		} catch (SQLException error) {
			throw new DAOException( error );
		}
		return 0;
	}

	
	public Optional<Computer> findComputerById(int id) throws DAOException {
		Computer computer = null;
		try(Connection connect = connection.getConnectionInstance(); PreparedStatement statement = connect.prepareStatement(FIND_COMPUTER_BY_ID);){	
			statement.setInt(1, id);
			res= statement.executeQuery();
			if (res.first()) {
				computer = new Computer.ComputerBuilder().
				setId(res.getInt("id")).
				setName(res.getString("name")).
				setIntroduced(res.getTimestamp("introduced")==null?null:res.getTimestamp("introduced").toLocalDateTime().toLocalDate()).
				setdiscontinued(res.getTimestamp("discontinued")==null?null:res.getTimestamp("discontinued").toLocalDateTime().toLocalDate()).
				setCompany(new Company.CompanyBuilder().setName(res.getString("name")).build()).build();
			}
		} catch (SQLException error) {
			throw new DAOException( error );
		}
		return Optional.ofNullable(computer);
	}

	
	public Computer updateComputer(Computer computer) throws DAOException {
		try(Connection connect = connection.getConnectionInstance(); PreparedStatement statement = connect.prepareStatement(UPDATE_COMPUTER);) {
			if(res.first()) {
				statement.setString(1, computer.getName());
				statement.setObject(2, computer.getIntroduced());
				statement.setObject(3, computer.getDiscontinued());
				statement.setInt(4, (int) computer.getCompany().getId());
			}
			statement.executeUpdate();
		} catch (SQLException error) {
			throw new DAOException( error);
		}
		return computer;
	}

	
	public void deleteComputer(int id) throws DAOException {
		try(Connection connect = connection.getConnectionInstance(); PreparedStatement statement = connect.prepareStatement(DELETE_COMPUTER);) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException error) {
			throw new DAOException(error);
		}
	}
}
