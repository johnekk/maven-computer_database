package com.excilys.cdb.servlets;

import java.util.List;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.services.ComputerService;


/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet(name = "Dashboard", urlPatterns = "/")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//PERMET À spring de reconnaitre tes Servlets 
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nbComputer = ComputerService.nbComputer();
		
		List<Computer> listComputers = ComputerService.findAll();
		
		request.setAttribute("listOfComputer", listComputers);
		request.setAttribute("nbOfComputer", nbComputer);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/dashboard.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
