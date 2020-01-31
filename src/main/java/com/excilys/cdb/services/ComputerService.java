package com.excilys.cdb.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.dtos.ComputerDTO;
import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Computer;

@Service
public class ComputerService {
	/**START Constructor Spring*/
	private ComputerDAO computerDAO;

	private ComputerService(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}
	/**END Constructor Spring*/
	
	public boolean create(Computer computer) {
		return computerDAO.createComputer(computer);
	}
	
	
	public List<Computer> getListComputer() {
		return computerDAO.findAllComputers();
	}
	public List<ComputerDTO> findAll() { 
		return ComputerMapper.listComputerToComputerDTO(getListComputer()); 
	}

	
	public int nbComputer() { 
		return computerDAO.findNumberOfComputers();
	}
	
	public Computer findByID(int id) {
		return computerDAO.findComputerById(id);
	}
	
	public boolean update(Computer computer) { 
		return computerDAO.updateComputer(computer);
	}
	
	public boolean delete(int id) {
		return computerDAO.deleteComputer(id);
	}
}
