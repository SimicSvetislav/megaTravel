package com.project.megatravel.users.request;



import javax.validation.constraints.*;

public class SignUpForm {
    @NotBlank
    @Size(min = 3, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 50)
    private String lastName;
    

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;
    
    
	private String phoneNumber;
	

	private String address;
	
    @NotBlank
    @Size(min = 3, max = 40)
    private String password;

    
    private String lokacija;
    
    private Double geoSirina;
    
    private Double geoDuzina;
    

    
    
    public String getLokacija() {
		return lokacija;
	}

	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}

	public Double getGeoSirina() {
		return geoSirina;
	}

	public void setGeoSirina(Double geoSirina) {
		this.geoSirina = geoSirina;
	}

	public Double getGeoDuzina() {
		return geoDuzina;
	}

	public void setGeoDuzina(Double geoDuzina) {
		this.geoDuzina = geoDuzina;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
   
}