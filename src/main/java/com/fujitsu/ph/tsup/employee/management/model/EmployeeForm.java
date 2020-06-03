package com.fujitsu.ph.tsup.employee.management.model;

import javax.validation.constraints.*;

public class EmployeeForm {
	@NotBlank(message = "First Name cannot be null")
	private String firstName;
	@NotBlank(message = "Last Name cannot be null")
	private String lastName;
	@NotBlank(message = "Employee Number cannot be null")
	@Size(min = 6, max = 6, message ="Employee number should be 6 characters")
	private String employeeNumber;
	@NotBlank(message = "Email Address cannot be null")
	@Email(message = "Email should be valid")
	private String emailAddress;
	@NotBlank(message = "Username cannot be null")
	@Size(min = 6, max = 50)
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
