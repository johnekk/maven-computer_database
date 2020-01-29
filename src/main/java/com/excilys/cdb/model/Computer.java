package com.excilys.cdb.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.excilys.cdb.model.Company;

@Entity
@Table(name="computer")
public class Computer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name = "";
	private LocalDate introduced;	
	private LocalDate discontinued;
	@OneToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	public int getId() 					{ return this.id; }
	public String getName() 			{ return name; }
	public LocalDate getIntroduced() 	{ return introduced; }
	public LocalDate getDiscontinued() 	{ return discontinued; }
	public Company getCompany() 		{ return company; }
	
	private Computer(ComputerBuilder builder) {
		this.id 			= builder.id;
		this.name 			= builder.name;
		this.introduced 	= builder.introduced;
		this.discontinued 	= builder.discontinued;
		this.company 		= builder.company;
	}
	
	public static class ComputerBuilder {
		private int id;
		private String name;
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
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Computer other = (Computer) obj;
		
		if (id != other.id)
			return false;
		
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		
		return true;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int res = 1;
		res = prime * res + id;
		res = prime * res + ((name == null) ? 0 : name.hashCode());
		res = prime * res + ((introduced == null) ? 0 : introduced.hashCode());
		res = prime * res + ((discontinued == null) ? 0 : discontinued.hashCode());
		res = prime * res + ((company == null) ? 0 : company.hashCode());
		return res;
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
}