package com.excilys.cdb.model;

import java.time.LocalDate;

import com.excilys.cdb.model.Company;

public class Computer {

	private int id;
	private String name = "";
	private LocalDate introduced;	
	private LocalDate discontinued;
	private Company company;

	private Computer(ComputerBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.introduced = builder.introduced;
		this.discontinued = builder.discontinued;
		this.company = builder.company;
	}
	
	public Computer (int id, String name, LocalDate introduced, LocalDate discontinued, Company company) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}
	
	public int getId() 					{ return this.id; }
	public String getName() 			{ return name; }
	public LocalDate getIntroduced() 	{ return introduced; }
	public LocalDate getDiscontinued() 	{ return discontinued; }
	public Company getCompany() 		{ return company; }
	
	public static class ComputerBuilder {
		private int id;
		private String name = "";
		private LocalDate introduced;	
		private LocalDate discontinued;
		private Company company;
		
		public ComputerBuilder() {};
		
		public ComputerBuilder setId(int id) {
            this.id = id;
            return this;
        }
		public ComputerBuilder setName(String name) {
            this.name = name;
            return this;
        }
		public ComputerBuilder setIntroduced(LocalDate introduced) {
            this.introduced = introduced;
            return this;
        }
		public ComputerBuilder setdiscontinued(LocalDate discontinued) {
            this.discontinued = discontinued;
            return this;
        }
		public ComputerBuilder setCompany(Company company) {
            this.company = company;
            return this;
        }
		
		public Computer build() {
			return new Computer(this);
		}
		
	}
	
	@Override
	public String toString() {
		String str =	"ID : " + this.getId() + "\n";
		str +=			"Name : " + this.getName() + "\n";
		str += 			"Date of birth : " + this.getIntroduced() + "\n";
		str += 			"Date of death : " + this.getDiscontinued() + "\n";
		str +=			this.company.toString();
		str +=			"\n.....................................\n";
		
		return str;
	}

	public short isEqualTo(Computer computer) {
		// TODO Auto-generated method stub
		return 0;
	}	
}
