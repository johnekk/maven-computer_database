package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.excilys.cdb.dao.MyConnectionToDB;
import com.excilys.cdb.exceptions.DAOException;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.mapper.ComputerMapper;

@Component
public class ComputerDAO {
	
	private ResultSet res;
	
	JdbcTemplate jdbcTemplate;
	
	private final String CREATE_COMPUTER 	= "	INSERT INTO computer (name, introduced, discontinued, company_id)"
											+ "	VALUES (?,?,?,?)";

	
	/*private final String FIND_ALL_COMPUTERS 	= "	SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name"
												+ "	FROM computer, company"
												+ " WHERE company.id = computer.company_id";*/
	
	private final String FIND_ALL_COMPUTER_PAGINATION 	= "SELECT  computer.id, computer.name, computer.introduced, computer.discontinued, "
														+ "computer.company_id, company.name "
														+ "FROM computer "
														+ "LEFT JOIN company ON computer.company_id = company.id "														
														+ "LIMIT ? OFFSET ? ;";
	
	private final String FIND_COMPUTER_BY_ID	=	" SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name"
												+ 	" FROM computer, company"
												+ 	" WHERE company.id = computer.company_id"
												+ 	" AND computer.id = ?";
	
	private final String FIND_COMPUTERS_BY_COMPANY	= "	SELECT computer.id, computer.name, compter.introduced, computer.discontinued"
													+ "	FROM computer"
													+ "	WHERE computer.company_id = ?";
		
	private final String FIND_NUMBER_OF_COMPUTER	= "	SELECT count(computer.id) AS 'nbComputer'"
													+ "	FROM computer";
	
	
	private final String UPDATE_COMPUTER 	= "	UPDATE computer"
											+ "	SET name = ?, introduce = ?, discontinued = ?, company_id = ?"
											+ "	WHERE id = ?";

	
	private final String DELETE_COMPUTER 	= "	DELETE "
											+ "	FROM computer"
											+ "	WHERE id = ?";
	private MyConnectionToDB connection;

	
	public ComputerDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public boolean createComputer(Computer computer) throws DAOException {
		return jdbcTemplate.update(CREATE_COMPUTER, computer.getId(), computer.getName(), 
													computer.getIntroduced(), computer.getDiscontinued(),
													computer.getCompany()) > 0;
	}

	public int findNumberOfComputers() throws DAOException {
		return jdbcTemplate.queryForObject(FIND_NUMBER_OF_COMPUTER, Integer.class);
	}

	
	public List<Computer> findAllComputers(int limit, int offset) {	
		return (List<Computer>) jdbcTemplate.query(FIND_ALL_COMPUTER_PAGINATION, new ComputerMapper(), limit, offset);
	}
	
	public Computer findComputerById(int id) throws DAOException {
		return jdbcTemplate.queryForObject(FIND_COMPUTER_BY_ID, new Object[] { id }, new ComputerMapper());
	}

	
	public Computer updateComputer(Computer computer) throws DAOException {
		try(Connection connect = connection.getConnectionInstance();
			PreparedStatement statement = connect.prepareStatement(UPDATE_COMPUTER);) {
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

	
	public boolean deleteComputer(Computer computer) throws DAOException {
		return jdbcTemplate.update(DELETE_COMPUTER, computer.getId()) > 0;
	}
}
