package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.exceptions.DAOException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class ComputerMapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class); 
	private ComputerMapper() {};
	private static ComputerMapper computerMapper;

	public static ComputerMapper getComputerMapper() {
		if(computerMapper == null) {
			computerMapper = new ComputerMapper();
		}
		return computerMapper;
	}
	
	public static Computer ResultSetToComputer(ResultSet resultat) throws DAOException  {
		
		int id = 0;
		String name = null;
		LocalDate introduced;
		LocalDate discontinued;
		Company company;
		
		try {
			id 		= (resultat.getInt("id") != 0) ? resultat.getInt("id"): null;
			name 	= (resultat.getString("name") != null) ? resultat.getString("name"): null;
			introduced = (resultat.getTimestamp("introduced")==null?null:resultat.getTimestamp("introduced").toLocalDateTime().toLocalDate());
			discontinued = (resultat.getTimestamp("discontinued")==null?null:resultat.getTimestamp("discontinued").toLocalDateTime().toLocalDate());
			company = new Company.CompanyBuilder().setName(resultat.getString("name")).build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}	
		return new Computer.ComputerBuilder().build();
	}

}
