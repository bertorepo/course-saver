package com.fujitsu.ph.tsup.employee.management.model;

public class EmployeeView {


		private int id;
		private int empNumber;
		private String firstName;
		private String lastName;
		private String emailAddress;
		private String userName;
		

		public EmployeeView() {
			
		}
		
		public EmployeeView(int id, int empNumber, String firstName, String lastName, String emailAddress, String userName) {
			this.id = id;
			this.empNumber = empNumber;
			this.firstName = firstName;
			this.lastName = lastName;
			this.emailAddress = emailAddress;
			this.userName = userName;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getEmpNumber() {
			return empNumber;
		}

		public void setEmpNumber(int empNumber) {
			this.empNumber = empNumber;
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

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		@Override
		public String toString() {
			return "Employee [id=" + id + ", empNumber=" + empNumber + ", firstName=" + firstName + ", lastName=" + lastName
					+ ", emailAddress=" + emailAddress + ", userName=" + userName + "]";
		}
		
		
		
	}
