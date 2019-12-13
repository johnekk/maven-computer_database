package com.excilys.cdb.services;

import com.excilys.cdb.dao.impl.ComputerDAO;
import com.excilys.cdb.model.Computer;

public class ComputerService {
	
	private static ComputerDAO computerDAO = ComputerDAO.getComputerDAOInstance();
	
	/** START Singleton.ComputerService -- Lazy-Loading */
	private static ComputerService computerService;
	
	public static ComputerService getInstance() {
		if (computerService == null) {
			computerService = new ComputerService();
		}
		return computerService;
	}
	
	/** END Singleton.ComputerService*/

	
	public static void create(Computer computer) {
		computerDAO.createComputer(computer);
	}
	
	public static void findAll() {
		for(Computer computer : computerDAO.findAllComputers()) {
			System.out.println(computer.toString());
		}
	}
	
	public static void findByID(int id) {
		System.out.println(computerDAO.findComputerById(id).toString());
		
		
	}
	
	public static void update(Computer computer) { 
		System.out.println(computerDAO.updateComputer(computer));
	}
	
	public static void delete(int id) {
		computerDAO.deleteComputer(id);
	}
}
