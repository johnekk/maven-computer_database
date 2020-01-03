package com.excilys.cdb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.model.Computer;

@Service
public class ComputerService {
	
	private static ComputerDAO computerDAO;
	
	public static void create(Computer computer) { computerDAO.createComputer(computer); }
	
	public static List<Computer> findAll() { return computerDAO.findAllComputers(); }
	
	public static void findByID(int id) {
		Optional<Computer> computer = computerDAO.findComputerById(id);
		if (computer.isPresent())
			System.out.println(computer.get().toString());
	}
	
	public static void update(Computer computer) { 
		System.out.println(computerDAO.updateComputer(computer));
	}
	
	public static void delete(int id) {
		computerDAO.deleteComputer(id);
	}
	
	public static int nbComputer() { return computerDAO.findNumberOfComputers();}
}
