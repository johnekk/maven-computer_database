package com.excilys.cdb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.mapper.CompanyMapper;

@Controller
public class CompanyConroller {
	
	@Autowired
	CompanyDAO dao;
	
	 //It displays a form to input data, here "command" is a reserved request attribute  
     // which is used to display object data into form  
	@RequestMapping("/companyForm")
	public String showform(Model m) {
		m.addAttribute("command",new CompanyMapper());
		return "companyForm";
	}
	

}
