package com.fujitsu.ph.tsup.autoregister.model;

public class AutoRegistrationForm {
	private Long id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private Long departmentid;
	private String userName;
	private String employmentDate;
	
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
        return "AutoRegistration [ID + " +id +"FirstName " + firstName +" LastName " + lastName + " EmailAddress " + emailAddress
        		+ " DepartmentID " + departmentid + " UserName " + userName + " EmploymentDate " + employmentDate;
    }
	
}
