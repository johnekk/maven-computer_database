package com.excilys.cdb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.services.ComputerService;

@Controller
@RequestMapping("/Computer")
public class ComputerController {

	
	private ComputerService computerService;
	
	@Autowired
	public ComputerController(ComputerService computerService) {
		this.computerService = computerService;
	}
	
	// It displays a form to input data, here "command" is a reserved request attribute  
    // which is used to display object data into form  
	@RequestMapping("/computerForm")
	public String showform(Model model) {
		model.addAttribute("command",new ComputerMapper());
		return "companyForm";
	}
	
	// It saves object into database. The @ModelAttribute puts request data  
    // into model object. You need to mention RequestMethod.POST method   
    // because default request is GET*/    
    @RequestMapping(value="/addComputer",method = RequestMethod.POST)    
    public String save(@ModelAttribute("computer") Computer computer){    
    	computerService.create(computer);    
        return "redirect:/addComputer";//will redirect to viewemp request mapping    
    }    
    
    /* It provides list of employees in model object */    
    @RequestMapping(value="/dashboard")    
    public String viewemp(Model model){    
        List<Computer> list = computerService.findAll();    
        model.addAttribute("list",list);  
        return "dashboard";    
    }
    
    /* It deletes record for the given id in URL and redirects to /viewemp */    
    @RequestMapping(value="/deleteComputer/{computer}",method = RequestMethod.GET)    
    public String delete(@PathVariable Computer computer){    
    	computerService.delete(computer);    
        return "redirect:/dashboard";    
    }     
	
	
}
