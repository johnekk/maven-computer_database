package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cdb.dtos.CompanyDTO;
import com.excilys.cdb.dtos.ComputerDTO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class ComputerMapper implements RowMapper<Computer>{
	
	public ComputerMapper() {};
	
	public static LocalDateTime StringToLocalDateTime(String introduced) {
		introduced = introduced + " 00:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return LocalDateTime.parse(introduced, formatter);
	}
	
	public static Computer computerDTOToComputer(ComputerDTO computerDTO) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String dateStringInt = computerDTO.getIntroduced();
		LocalDate introduced = dateStringInt.equals("") ? null : LocalDate.parse(dateStringInt, formatter);
		
		String dateStringDis = computerDTO.getDiscontinued();
		LocalDate discontinued = dateStringInt.equals("") ? null : LocalDate.parse(dateStringDis, formatter);
		
		CompanyDTO companyDTO = computerDTO.getCompanyDTO();
		
		Computer c = new Computer.ComputerBuilder().
							setId(computerDTO.getId()).
							setName(computerDTO.getName()).
							setIntroduced(introduced).
							setdiscontinued(discontinued).
							setCompany(new Company.CompanyBuilder().
										setId(companyDTO.getId()).
										setName(companyDTO.getName()).build()).
							build();
		return c;
	}
	
	public static ComputerDTO computerToComputerDTO(Computer computer) {
		String introducedDate = computer.getIntroduced()!= null ? computer.getIntroduced().toString() : null;
		String discontinuedDate = computer.getDiscontinued()!= null ? computer.getDiscontinued().toString() : null;
		
		ComputerDTO computerDTO = 	new ComputerDTO.ComputerDTOBuilder().
									id(computer.getId()).
									name(computer.getName()).
									introduced(introducedDate).
									discontinued(discontinuedDate).
									companyDTO(	new CompanyDTO.CompanyDTOBuilder().
												setId(computer.getCompany().getId()).
												setName(computer.getCompany().getName()).build()).
									build();
		return computerDTO;
	}
	
	public static List<ComputerDTO> listComputerToComputerDTO(List<Computer> list){
		List<ComputerDTO> listDTO = new ArrayList<ComputerDTO> ();
		
		for (Computer computer : list) {
			listDTO.add(computerToComputerDTO(computer));
		}
		
		return listDTO;
		
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
