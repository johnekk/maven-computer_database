package com.excilys.cdb.dtos;

public class ComputerDTO {

	private int id;
	private String name;
	private String introduced;
	private String discontinued;
	private CompanyDTO companyDTO;

	
	private ComputerDTO(ComputerDTOBuilder builder) {
		this.id 			= builder.id;
		this.name 			= builder.name;
		this.introduced 	= builder.introduced;
		this.discontinued 	= builder.discontinued;
		this.companyDTO 	= builder.companyDTO;
	}
	
	
	public static class ComputerDTOBuilder {
		
		private int id;
		private String name;
		private String introduced;
		private String discontinued;
		private CompanyDTO companyDTO;
		
		public ComputerDTOBuilder() {};
		
    	public ComputerDTOBuilder id(int id) {
    		this.id = id;
    		return this;
    	}
    	public ComputerDTOBuilder name(String name) {
    		this.name = name;
    		return this;
    	}
    	public ComputerDTOBuilder introduced(String introduced) {
    		this.introduced = introduced;
    		return this;
    	}
    	public ComputerDTOBuilder discontinuedDate(String discontinued) {
    		this.discontinued = discontinued;
    		return this;
    	}
    	public ComputerDTOBuilder companyDTO(CompanyDTO companyDTO) {
    		this.companyDTO = companyDTO;
    		return this;
    	}
    	
    	public ComputerDTO build() {
    		return new ComputerDTO(this);
    	}
	}


	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public CompanyDTO getCompanyDTO() {
		return companyDTO;
	}

	
	@Override
	public String toString() {
		String str =	"ID : " + this.getId() + "\n";
		str +=			"Name : " + this.getName() + "\n";
		str += 			"Date of birth : " + this.getIntroduced() + "\n";
		str += 			"Date of death : " + this.getDiscontinued() + "\n";
		str +=			this.companyDTO.toString();
		str +=			"\n.....................................\n";
		
		return str;
	}
}
