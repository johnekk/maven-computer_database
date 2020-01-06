package com.excilys.cdb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.model.Computer;

@Service
public class ComputerService {
	/**START Constructor Spring*/
	private ComputerDAO computerDAO;

	private ComputerService(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}
	/**END Constructor Spring*/
	
	public void create(Computer computer) {
		computerDAO.createComputer(computer);
	}
	
	public List<Computer> findAll() { 
		return computerDAO.findAllComputers(); 
	}
	
	public int nbComputer() { 
		return computerDAO.findNumberOfComputers();
	}
	
	public void findByID(int id) {
		Optional<Computer> computer = computerDAO.findComputerById(id);
		if (computer.isPresent())
			System.out.println(computer.get().toString());
	}
	
	public void update(Computer computer) { 
		computerDAO.updateComputer(computer);
	}
	
	public void delete(int id) {
		computerDAO.deleteComputer(id);
	}
}
