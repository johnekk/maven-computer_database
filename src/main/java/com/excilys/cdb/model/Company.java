package com.excilys.cdb.model;

public class Company {
	
	private int id;
	private String name;
	
	
	private Company(CompanyBuilder builder) {
		this.id 	= builder.id;
		this.name 	= builder.name;
	}
	
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
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
	public String toString() {
		return "Name of company : " + this.getName() + "\n";
	}

	
}
