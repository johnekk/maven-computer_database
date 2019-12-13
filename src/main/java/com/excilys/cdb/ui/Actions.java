package com.excilys.cdb.ui;

import java.time.LocalDate;
import java.util.Scanner;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.services.ComputerService;

public class Actions {
	
	static Scanner sc = new Scanner(System.in);
	
	static LocalDate introduced = null;
	static LocalDate discontinued = null;

	static int companyId;
	
	public static void addCLI() {
		
		System.out.println("Enter informations: "  );
		System.out.println( "Name of Computer" );
		String computerName = sc.nextLine();
		
		System.out.println("Date of begin in >> yyyy-MM-dd");
        String dateString= sc.nextLine();
        if (!dateString.equals(""))
        	introduced = LocalDate.parse(dateString);      	

        System.out.println("Date of end in >> yyyy-MM-dd");
        dateString = sc.nextLine();
        if (!dateString.equals(""))
        	introduced = LocalDate.parse(dateString);
        
        System.out.println("ID of company");
        int companyId = sc.nextInt();
        String newId = String.valueOf(companyId);
        if (!newId.equals("")) {
        	Computer computer = new Computer(0, computerName, introduced, discontinued, new Company.CompanyBuilder().build());
        	ComputerService.create(computer);
        }
	}

	public static void findOneCLI() {
		System.out.println("Select ID of company :");
		int computerId = sc.nextInt();
		ComputerService.findByID(computerId);
	}
	
	public static void UpdateCLI() {
	}
/**
	public static void DeleteCLI() {
		
		System.out.println("Enter the ID of the company that you want to delete: ");
		int computerID = sc.nextInt();
		ComputerService.delete(id);
		
	}
*/
}
