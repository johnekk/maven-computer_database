package com.excilys.cdb.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.services.CompanyService;
import com.excilys.cdb.services.ComputerService;

@Controller
public class ComputerController {
	
	private ComputerService computerService;
	private CompanyService companyService;
	
	private ComputerMapper computerMapper;
	
	public ComputerController(ComputerService computerService, CompanyService companyService, ComputerMapper computerMapper) {
		this.computerService = computerService;
		this.companyService = companyService;
		this.computerMapper = computerMapper;
	}
	
	int pageID = 1, nbComputer = 0, limit = 0, offset = 0, nbPage = 0;
	
	   
    
    // It provides list of employees in model object    
    @GetMapping("/")
    //@RequestMapping(value="/dashboard/{pageID}")
    public String findAllComputers(@PathVariable int pageID, Model model){
    	
    	List<Computer> listComputer = computerService.findAll();
        nbComputer = computerService.nbComputer();
        
        model.addAttribute("listComputer", listComputer);  
        model.addAttribute("nbComputer", nbComputer);
        model.addAttribute("nbPage", nbPage);
		model.addAttribute("pageID", pageID);
        return "dashboard";    
    }
    
    // It deletes record for the given id in URL and redirects to /viewemp
    @GetMapping(value="/deleteComputer/{computer}")    
    public String delete(@PathVariable int id){    
    	computerService.delete(id);    
        return "redirect:/dashboard";    
    }
    
 // It displays a form to input data, here "command" is a reserved request attribute  
    // which is used to display object data into form  
	@GetMapping("/addComputer")
	public String showform(Model model) {
		model.addAttribute("command",new ComputerMapper());
		return "companyForm";
	}
	
	// It saves object into database. The @ModelAttribute puts request data  
    // into model object. You need to mention RequestMethod.POST method   
    // because default request is GET*/    
    @PostMapping(value="/addComputer")    
    public String save(@ModelAttribute("computer") Computer computer){    
    	computerService.create(computer);    
        return "redirect:/addComputer";//will redirect to viewemp request mapping    
    } 
}
