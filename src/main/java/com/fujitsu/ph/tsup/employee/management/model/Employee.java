package com.fujitsu.ph.tsup.employee.management.model;

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
	private Long id;
	private String number;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String username;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * The builder class of the Employee. 
	 * @param builder
	 */
	private Employee(Builder builder) {
		this.id = builder.id;
		this.number = builder.number;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.emailAddress = builder.emailAddress;
		this.username = builder.username;
	}
	
	public static class Builder {
		private Long id;
		private String number;
		private String firstName;
		private String lastName;
		private String emailAddress;
		private String username;
		
		/**
		 * The builder is a public static member class of Employee
		 * @param id
		 * @param number
		 * @param firstName
		 * @param lastName
		 * @param emailAddress
		 * @param username
		 */
		public Builder(Long id, String number, String firstName, String lastName, 
						String emailAddress, String username) {
			validateId(id);
			validateNumber(number);
			validateFirstName(firstName);
			validateLastName(lastName);
			validateEmailAddress(emailAddress);
			validateUsername(username);
			
			this.id = id;
			this.number = number;
			this.firstName = firstName;
			this.lastName = lastName;
			this.emailAddress = emailAddress;
			this.username = username;
		}
		
		/**
		 * Validates id
		 * @param id
		 * @throws IllegalArgumentException
		 */
		private void validateId(Long id) {
			if(id == null || id.toString().isEmpty() || id == 0) {
				throw new IllegalArgumentException("Id shoud not be empty");
			}
		}
		
		/**
		 * Validates number
		 * @param number
		 * @throws IllegalArgumentException
		 */
		private void validateNumber(String number) {
			if(number == null || number.isEmpty()) {
				throw new IllegalArgumentException("Number shoud not be empty");
			}
		}
		
		/**
		 * Validates First Name
		 * @param firstName
		 * @throws IllegalArgumentException
		 */
		private void validateFirstName(String firstName) {
			if(firstName == null || firstName.isEmpty()) {
				throw new IllegalArgumentException("First Name shoud not be empty");
			}
		}
		
		/**
		 * Validates Last Name
		 * @param lastName
		 * @throws IllegalArgumentException
		 */
		private void validateLastName(String lastName) {
			if(lastName == null || lastName.isEmpty()) {
				throw new IllegalArgumentException("Last Name shoud not be empty");
			}
		}
		
		/**
		 * Validates Email Address
		 * @param emailAddress
		 * @throws IllegalArgumentException
		 */
		private void validateEmailAddress(String emailAddress) {
			if(emailAddress == null || emailAddress.isEmpty()) {
				throw new IllegalArgumentException("Email Address shoud not be empty");
			}
		}
		
		/**
		 * Validates Username
		 * @param username
		 * @throws IllegalArgumentException
		 */
		private void validateUsername(String username) {
			if(username == null || username.isEmpty()) {
				throw new IllegalArgumentException("Username shoud not be empty");
			}
		}
	}
	
	@Override
	public String toString() {
		return "EmployeeForm [firstName=" + firstName + ", lastName=" + lastName + ", employeeNumber=" + number
				+ ", emailAddress=" + emailAddress + ", username=" + username + "]";
	}
}
