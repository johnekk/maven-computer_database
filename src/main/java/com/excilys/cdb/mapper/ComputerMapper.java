package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cdb.dtos.CompanyDTO;
import com.excilys.cdb.dtos.ComputerDTO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class ComputerMapper implements RowMapper<Computer>{
	
	public ComputerMapper() {};
	
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

	@Override
	public Computer mapRow(ResultSet res, int rowNum) throws SQLException {
		Computer computer = new Computer.ComputerBuilder().
								setId(res.getInt("id")).
								setName(res.getString("name")).
								setIntroduced(res.getTimestamp("introduced")==null?null:res.getTimestamp("introduced").toLocalDateTime().toLocalDate()).
								setdiscontinued(res.getTimestamp("discontinued")==null?null:res.getTimestamp("discontinued").toLocalDateTime().toLocalDate()).
								setCompany(new 	Company.CompanyBuilder().
												setName(res.getString("name")).build()).build();
		return computer;
	}
	
}
