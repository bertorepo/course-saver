//==================================================================================================
// Project Name :  Training Sign Up Project
// System Name  : Training Sign Up Project
// Class Name   : Employee.java
//
// <<Modification History>>
// Version | Date       | Updated By            | Content
// --------+------------+-----------------------+---------------------------------------------------
// 1.0.0   | 06/24/2020 | WS) J.Macabudbud      | Initial Version
//==================================================================================================
package com.fujitsu.ph.tsup.common.domain;

/**
 * <pre>
 * It is a JavaBean for Employee
 * In this class, Instances of the List Data required for the Initial setting of the database
 * </pre>
 * 
 * @version 0.01
 * @author WS) J.Macabudbud
 *
 */
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
	 * <pre>
	 * The builder class of the Employee.
	 * </pre>
	 * 
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
		 * <pre>
		 * The builder is a public static member class of Employee
		 * </pre>
		 * 
		 * @param id
		 * @param number
		 * @param firstName
		 * @param lastName
		 * @param emailAddress
		 * @param username
		 */
		public Builder(Long id, String number, String firstName, String lastName, String emailAddress,
				String username) {
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
		 * <pre>
		 * Validates id
		 * </pre>
		 * 
		 * @param id
		 * @throws IllegalArgumentException
		 */
		private void validateId(Long id) {
			if (id == null || id.toString().isEmpty() || id == 0) {
				throw new IllegalArgumentException("Id shoud not be empty");
			}
		}

		/**
		 * <pre>
		 * Validates number
		 * </pre>
		 * 
		 * @param number
		 * @throws IllegalArgumentException
		 */
		private void validateNumber(String number) {
			if (number == null || number.isEmpty()) {
				throw new IllegalArgumentException("Number shoud not be empty");
			}
		}

		/**
		 * <pre>
		 * Validates First Name
		 * </pre>
		 * 
		 * @param firstName
		 * @throws IllegalArgumentException
		 */
		private void validateFirstName(String firstName) {
			if (firstName == null || firstName.isEmpty()) {
				throw new IllegalArgumentException("First Name shoud not be empty");
			}
		}

		/**
		 * <pre>
		 * Validates Last Name
		 * </pre>
		 * 
		 * @param lastName
		 * @throws IllegalArgumentException
		 */
		private void validateLastName(String lastName) {
			if (lastName == null || lastName.isEmpty()) {
				throw new IllegalArgumentException("Last Name shoud not be empty");
			}
		}

		/**
		 * <pre>
		 * Validates Email Address
		 * </pre>
		 * 
		 * @param emailAddress
		 * @throws IllegalArgumentException
		 */
		private void validateEmailAddress(String emailAddress) {
			if (emailAddress == null || emailAddress.isEmpty()) {
				throw new IllegalArgumentException("Email Address shoud not be empty");
			}
		}

		/**
		 * <pre>
		 * Validates Username
		 * </pre>
		 * 
		 * @param username
		 * @throws IllegalArgumentException
		 */
		private void validateUsername(String username) {
			if (username == null || username.isEmpty()) {
				throw new IllegalArgumentException("Username shoud not be empty");
			}
		}
		
		/**
		 * <pre>
		 * Employee build method
		 * </pre>
		 * 
		 * @return Employee
		 */
		public Employee build() {
			return new Employee(this);
		}
	}

	@Override
	public String toString() {
		return "EmployeeForm [firstName=" + firstName + ", lastName=" + lastName + ", employeeNumber=" + number
				+ ", emailAddress=" + emailAddress + ", username=" + username + "]";
	}
}
