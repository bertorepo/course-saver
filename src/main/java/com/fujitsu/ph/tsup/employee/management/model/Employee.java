/**
 * <pre>
 * It is a JavaBean for Employee
 * In this class, Instances of the List Data required for the Initial setting of the database
 * </pre>
 * 
 * @version 0.01
 * @author j.macabudbud
 *
 */
package com.fujitsu.ph.tsup.employee.management.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign up
//Class Name   :Employee.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/24/2020 | WS) J.Macabudbud            | New Creation
public class Employee {
	@NotNull 
	private long id;
	private String number;
	@NotBlank(message = "First Name cannot be null")
	private String firstName;
	@NotBlank(message = "Last Name cannot be null")
	private String lastName;
	@NotBlank(message = "Employee Number cannot be null")
	@NotBlank(message = "Email Address cannot be null")
	@Email(message = "Email should be valid")
	private String emailAddress;
	@NotBlank(message = "Username cannot be null")
	private String username;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String toString() {
		return "EmployeeForm [firstName=" + firstName + ", lastName="
				+ lastName + ", employeeNumber=" + number + ", emailAddress=" + emailAddress + 
				", username=" + username + "]";
	}
}
