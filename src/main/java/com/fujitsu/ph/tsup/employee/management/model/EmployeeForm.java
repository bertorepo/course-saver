package com.fujitsu.ph.tsup.employee.management.model;

public class EmployeeForm {
	private String firstName;
	private String lastName;
	private String employeeNumber;
	private String emailAddress;
	private String username;
	private String submit;


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
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}

	@Override
	public String toString() {
		return "EmployeeForm [firstName=" + firstName + ", lastName="
				+ lastName + ", employeeNumber=" + employeeNumber + ", emailAddress=" + emailAddress + ", username=" + username 
				+ ", submit="
				+ submit + "]";
	}

}
