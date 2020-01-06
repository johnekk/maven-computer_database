package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.dtos.CompanyDTO;
import com.excilys.cdb.dtos.ComputerDTO;
import com.excilys.cdb.exceptions.DAOException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class ComputerMapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class); 
	
	private ComputerMapper() {};
	
	private ComputerMapper computerMapper;
	
	private  static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
	
	
	public static Computer ComputerDTOToComputer(ComputerDTO computerDTO) {
		String dateStringInt = computerDTO.getIntroduced();
		LocalDate introduced = dateStringInt.equals("") ? null : LocalDate.parse(dateStringInt, formatter);
		
		String dateStringDis = computerDTO.getDiscontinued();
		LocalDate discontinued = dateStringInt.equals("") ? null : LocalDate.parse(dateStringDis, formatter);
		
		CompanyDTO companyDTO = computerDTO.getCompanyDTO();
		
		Computer computer = new Computer.ComputerBuilder().
							setId(computerDTO.getId()).
							setName(computerDTO.getName()).
							setIntroduced(introduced).
							setdiscontinued(discontinued).
							setCompany(new Company.CompanyBuilder().
										setId(companyDTO.getId()).
										setName(companyDTO.getName()).build()).
							build();
		return computer;
	}
	
	public static ComputerDTO ComputerToComputerDTO(Computer computer) {
		ComputerDTO computerDTO = 	new ComputerDTO.ComputerDTOBuilder().
									id(computer.getId()).
									name(computer.getName()).
									introduced(computer.getIntroduced().toString()).
									discontinued(computer.getDiscontinued().toString()).
									companyDTO(	new CompanyDTO.CompanyDTOBuilder().
												setId(computer.getCompany().getId()).
												setName(computer.getCompany().getName()).build()).
									build();
		return computerDTO;
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
