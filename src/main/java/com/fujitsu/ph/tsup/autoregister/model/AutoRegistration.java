package com.fujitsu.ph.tsup.autoregister.model;

public class AutoRegistration {
	private Long id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private Long departmentid;
	private String userName;
	private String employmentDate;
	
	private AutoRegistration(Builder builder) {

        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.emailAddress = builder.emailAddress;
        this.departmentid = builder.departmentid;
        this.userName = builder.userName;
        this.employmentDate = builder.employmentDate;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Long getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(Long departmentid) {
		this.departmentid = departmentid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmploymentDate() {
		return employmentDate;
	}

	public void setEmploymentDate(String employmentDate) {
		this.employmentDate = employmentDate;
	}
	
	@Override
    public String toString() {
        return "AutoRegistration [ID=" +id +", FirstName=" + firstName +", LastName=" + lastName + ", EmailAddress=" + emailAddress
        		+ ", DepartmentID=" + departmentid + ", UserName=" + userName + ", EmploymentDate=" + employmentDate;
    }
	
	public static class Builder {
		private Long id;
		private String firstName;
		private String lastName;
		private String emailAddress;
		private Long departmentid;
		private String userName;
		private String employmentDate;
		
		public Builder(Long id, String firstName, String lastName, String emailAddress, Long departmentid, String userName, String employmentDate) {
			validateID(id);
			validateFirstName(firstName);
			validateLastName(lastName);
			validateEmailAddress(emailAddress);
			validateDepartmentID(departmentid);
			validateUserName(userName);
			validateEmploymentDate(employmentDate);
		    	
			this.id = id;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.emailAddress = emailAddress;
	        this.departmentid = departmentid;
	        this.userName = userName;
	        this.employmentDate = employmentDate;
		    	
		}
		
		public AutoRegistration build() {

            return new AutoRegistration(this);

        }
		
		private void validateID(Long id) {

            if (id == null || id == 0) {
                throw new IllegalArgumentException("ID should not be empty");
            }

        }
		
		private void validateFirstName(String firstName) {

            if (firstName.equals(null) || firstName.isEmpty()) {
                throw new IllegalArgumentException("First Name should not be empty");
            }

        }
		
		private void validateLastName(String lastName) {

            if (lastName.equals(null) || lastName.isEmpty()) {
                throw new IllegalArgumentException("Last Name should not be empty");
            }

        }
		
		private void validateEmailAddress(String emailAddress) {

            if (emailAddress.equals(null) || emailAddress.isEmpty()) {
                throw new IllegalArgumentException("Email Address should not be empty");
            }

        }
		
		private void validateDepartmentID(Long departmentid) {

            if (departmentid == null || departmentid == 0) {
                throw new IllegalArgumentException("Department ID should not be empty");
            }

        }
		
		private void validateUserName(String userName) {

            if (userName.equals(null) || userName.isEmpty()) {
                throw new IllegalArgumentException("User Name should not be empty");
            }

        }
		
		private void validateEmploymentDate(String employmentDate) {

            if (employmentDate.equals(null) || employmentDate.isEmpty()) {
                throw new IllegalArgumentException("EmploymentDate should not be empty");
            }

        }
	}
	
}
