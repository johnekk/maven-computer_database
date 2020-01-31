package com.excilys.cdb.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.exceptions.DAOException;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.mapper.ComputerMapper;

@Repository
public class ComputerDAO {
	
	JdbcTemplate jdbcTemplate;
	
	private static final String CREATE_COMPUTER 	= "	INSERT INTO computer (name, introduced, discontinued, company_id)"
											+ "	VALUES (?,?,?,?)";

	
	private static final String FIND_ALL_COMPUTERS 	= "	SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name"
												+ "	FROM computer, company"
												+ " WHERE company.id = computer.company_id";
	
	/*private static final String FIND_ALL_COMPUTER_PAGINATION 	= "SELECT  computer.id, computer.name, computer.introduced, computer.discontinued, "
														+ "computer.company_id, company.name "
														+ "FROM computer "
														+ "LEFT JOIN company ON computer.company_id = company.id "														
														+ "LIMIT ? OFFSET ? ;";*/
	
	private static final String FIND_COMPUTER_BY_ID	=	" SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name"
												+ 	" FROM computer, company"
												+ 	" WHERE company.id = computer.company_id"
												+ 	" AND computer.id = ?";
	
		
	private static final String FIND_NUMBER_OF_COMPUTER	= "	SELECT count(computer.id) AS 'nbComputer'"
													+ "	FROM computer";
	
	
	private static final String UPDATE_COMPUTER 	= "	UPDATE computer"
											+ "	SET name = ?, introduce = ?, discontinued = ?, company_id = ?"
											+ "	WHERE id = ?";

	
	private static final String DELETE_COMPUTER 	= "	DELETE "
											+ "	FROM computer"
											+ "	WHERE id = ?";

	
	public ComputerDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	

	public boolean createComputer(Computer computer) throws DAOException {
		return jdbcTemplate.update(CREATE_COMPUTER, computer.getId(),
													computer.getName(), 
													computer.getIntroduced(),
													computer.getDiscontinued(),
													computer.getCompany()) > 0;
	}

	public List<Computer> findAllComputers() {
		return jdbcTemplate.query(FIND_ALL_COMPUTERS, new ComputerMapper());
	}
	
	public Computer findComputerById(int id) throws DAOException {
		return jdbcTemplate.queryForObject(FIND_COMPUTER_BY_ID, new Object[] { id }, new ComputerMapper());
	}
	
	public int findNumberOfComputers() throws DAOException {
		return jdbcTemplate.queryForObject(FIND_NUMBER_OF_COMPUTER, Integer.class);
	}


	public boolean updateComputer(Computer computer) {	
		return jdbcTemplate.update(UPDATE_COMPUTER, computer.getName(), 
													computer.getIntroduced(), 
													computer.getDiscontinued(),
													computer.getCompany().getId(), 
													computer.getId()) > 0;	
	}


	public boolean deleteComputer(int id) throws DAOException {
		return jdbcTemplate.update(DELETE_COMPUTER, id) > 0;
	}
}
