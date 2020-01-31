/*package com.excilys.cdb.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.pagination.Page;
import com.excilys.cdb.services.CompanyService;
import com.excilys.cdb.services.ComputerService;
import com.excilys.cdb.dtos.ComputerDTO;
import com.excilys.cdb.model.Company;

@Controller
public class ComputerController {
	
	private ComputerService computerService;
	private CompanyService companyService;
	
	private ComputerMapper computerMapper;
	
	private Page page;
	
	public ComputerController(ComputerService computerService, CompanyService companyService, ComputerMapper computerMapper, Page page) {
		this.computerService = computerService;
		this.companyService = companyService;
		this.computerMapper = computerMapper;
		this.page = page;
	}
	
	int pageID = 1, nbComputer = 0, limit = 0, offset = 0, nbPage = 0;
	
	   
    
    // It provides list of employees in model object    
    @GetMapping("/")
    public String getComputers(	@RequestParam(required = false, defaultValue = "0") int limit,
    							@RequestParam(required = false, defaultValue = "1") int pageID,
    							@RequestParam(required = false, defaultValue = "0") int search,
    							Model model) {
    	
    	int currentPage = pageID;
		offset = page.calculOffset(currentPage);

		if(limit != 0) {	
			page.setLimite(limit);	
		}	
    	
    	List<com.excilys.cdb.dtos.ComputerDTO> listComputer = computerService.findAll();
        nbComputer = computerService.nbComputer();
        nbPage = page.nbPageTotal(nbComputer);
        
        model.addAttribute("listComputer", listComputer);  
        model.addAttribute("nbComputer", nbComputer);
        model.addAttribute("nbPage", nbPage);
		model.addAttribute("currentPage", currentPage);
        return "dashboard";    
    }
    
    @PostMapping("/addcomputer")
	public String postAddComputer(@ModelAttribute("computer")ComputerDTO computerDTO, Model model ) {

		try {
			Computer computer = computerMapper.computerDTOtoComputer(computerDTO);
			Validation.computerNameIsEmpty(computer.getComputerName());
			Validation.discontinuedDateIsLater(computer.getDiscontinuedDate(), computer.getIntroducedDate());
			serviceComputer.createOneComputer(computer);
			return "redirect:/";
			
		} catch(CheckNameException e) {
			model.addAttribute("nameError", e.getMessage());
		} catch(CheckDateIntervale e) {
			model.addAttribute("dateError", e.getMessage());
		}
			List<Company> listCompany = serviceCompany.findAllCompany();
			model.addAttribute("listCompany", listCompany);
		
			return "addComputer";
			
	}
    
    @GetMapping("/editcomputer")
	public String getEditComputer(@RequestParam(required = true) int computer_id, Model model) {

		Computer computer = computerService.findByID(computer_id);
		List<Company> listCompany = companyService.findAll();
		model.addAttribute("listCompany", listCompany);
		model.addAttribute("computer", ComputerMapper.computerToComputerDTO(computer));

		return "editComputer";
		
	}

	@PostMapping("/editcomputer")
	public String postEditComputer(@ModelAttribute("computer")ComputerDTO computerDTO) {
		
        computerService.update(ComputerMapper.computerDTOToComputer(computerDTO)); 
        
        return "redirect:/";
        
	}
    
    // It deletes record for the given id in URL and redirects to /viewemp
    @GetMapping("/deleteComputer")    
    public String delete(@RequestParam(required = false, defaultValue = "-1") int id){    
    	computerService.delete(id);
        return "redirect:/dashboard";    
    }
    
}*/
