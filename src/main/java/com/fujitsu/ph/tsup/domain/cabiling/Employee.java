package com.fujitsu.ph.tsup.domain.cabiling;

public class Employee {

	private Long id;
	private String empNum;
	private String firstName;
	private String lastName;
	private String emailAdd;
	private String userName;

	protected Employee() {

	}

	private Employee(Builder emp) {

		this.id = emp.id;
		this.empNum = emp.empNum;
		this.firstName = emp.firstName;
		this.lastName = emp.lastName;
		this.emailAdd = emp.emailAdd;
		this.userName = emp.userName;

	}

	public Long getId() {
		return id;
	}

	public String getEmpNum() {
		return empNum;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailAdd() {
		return emailAdd;
	}

	public String getUserName() {
		return userName;
	}

	public static class Builder {
		private Long id;
		private String empNum;
		private String firstName;
		private String lastName;
		private String emailAdd;
		private String userName;

		public Builder(Long id, String empNum, String firstName, String lastName, String emailAdd, String userName) {
			validateId(id);
			validateEmpNum(empNum);
			validateFirstName(firstName);
			validateLastName(lastName);
			validateEmailAdd(emailAdd);
			validateUserName(userName);

			this.id = id;
			this.empNum = empNum;
			this.firstName = firstName;
			this.lastName = lastName;
			this.emailAdd = emailAdd;
			this.userName = userName;
		}

		public Employee emp() {
			return new Employee(this);
		}

		public void validateId(Long id) {
			if (id == null) {
				throw new IllegalArgumentException("ID should not be null");
			}
		}

		public void validateEmpNum(String empNum) {
			if (empNum == null) {
				throw new IllegalArgumentException("Employee number should not be null");
			}
		}

		public void validateFirstName(String firstName) {
			if ((firstName == null) || (firstName.isEmpty())) {
				throw new IllegalArgumentException("First name should not be null or empty");
			}
		}

		public void validateLastName(String lasttName) {
			if ((lasttName == null) || (lasttName.isEmpty())) {
				throw new IllegalArgumentException("Last name should not be null or empty");
			}
		}

		public void validateEmailAdd(String emailAdd) {
			if ((emailAdd == null) || (emailAdd.isEmpty())) {
				throw new IllegalArgumentException("Email address should not be null or empty");
			}
		}

		public void validateUserName(String userName) {
			if ((userName == null) || (userName.isEmpty())) {
				throw new IllegalArgumentException("Username should not be null or empty");
			}
		}
	}

}
