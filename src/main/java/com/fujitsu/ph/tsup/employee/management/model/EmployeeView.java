/* Author : Macabugao, Janella Marie */
package com.fujitsu.ph.tsup.employee.management.model;

import javax.validation.constraints.NotEmpty;


public class EmployeeView {

	    @NotEmpty(message="Input value for id, it must not be empty")
		private String id;
		private int empNumber;
		private String firstName;
		private String lastName;
		private String emailAddress;
		private String userName;
		
	
		public String getId() {
			return id;
		}

		public void setId(String id) {
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