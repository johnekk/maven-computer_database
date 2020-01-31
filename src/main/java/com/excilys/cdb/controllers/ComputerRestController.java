package com.excilys.cdb.controllers;

import java.util.List;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.dtos.ComputerDTO;
import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.services.ComputerService;

import io.swagger.annotations.ApiOperation;

@RestController
public class ComputerRestController {
	
	private ComputerService computerService;
	
		
	public ComputerRestController(ComputerService computerService) {
		this.computerService = computerService;
	}
	
	@CrossOrigin
	@GetMapping("/")
	@ApiOperation(value = "${swagger.computers}", notes = "${swagger.computers.desc}")
	public List<ComputerDTO> getAll() {	
		return computerService.findAll();
	}
	
	@CrossOrigin
	@PostMapping
	public boolean create(ComputerDTO computer) {	
		return computerService.create( ComputerMapper.computerDTOToComputer(computer));
	}
	
	@CrossOrigin
	@GetMapping("/{id}")
	public boolean delete(int id) {	
		return computerService.delete(id);
	}
	
	@CrossOrigin
	@PutMapping
	public boolean edit(ComputerDTO computer) {	
		return computerService.update( ComputerMapper.computerDTOToComputer(computer) );
	}
	
}
