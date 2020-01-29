package com.excilys.cdb.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="company")
public class Company {
	
	@Id
	private int id;
	private String name;
	
	public int getId() { return id; }
	public String getName() { return name; }
	
	private Company(CompanyBuilder builder) {
		this.id 	= builder.id;
		this.name 	= builder.name;
	}
	
	public static class CompanyBuilder {
		private int id;
		private String name = "";
		
		public CompanyBuilder() {};
		
		public CompanyBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public CompanyBuilder setName(String name) {
            this.name = name;
            return this;
        }
        
        public Company build() {
        	return new Company(this);
        }
	}

	@Override
    public boolean equals(Object obj) {
		// checking if both the object references are referring to the same object.
        if (this == obj) return true;
        
        // it checks if the argument is of the type Geek by comparing the classes 
        // of the passed argument and this object.
        if (obj == null || getClass() != obj.getClass()) return false;
        
        // type casting of the argument. 
        Company company = (Company) obj;
        
        // comparing the state of argument with the state of 'this' Object. 
        return (company.id == this.id
        		&& company.name == this.name);
        /**return name == 	company.name 
        				&& Objects.equals(id, company.id)
        				&& Objects.equals(name, company.name);*/
    }

    @Override
    public int hashCode() {
    	// We are returning the Company_id as a hashcode value. we can also return some  
        // other calculated value or may be memory address of the Object on which it is invoked.  
        // it depends on how you implement hashCode() method. 
        return Objects.hash(id, name);
    }
	
	@Override
	public String toString() {
		return "Name of company : " + this.getName() + "\n";
	}	
}