package com.fujitsu.ph.tsup.domain.oviedo;

public class Employee {
	private Long id;
	private String empNumber, lname, fname, email, username;

	protected Employee() {

	}

	private Employee(Builder builder) {
		this.id = builder.id;
		this.empNumber = builder.empNumber;
		this.lname = builder.lname;
		this.fname = builder.fname;
		this.email = builder.email;
		this.username = builder.username;
	}

	public Long getId() {
		return id;
	}

	public String getEmpNumber() {
		return empNumber;
	}

	public String getLname() {
		return lname;
	}

	public String getFname() {
		return fname;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public static class Builder {
		private Long id;
		private String empNumber, lname, fname, email, username;
		public Builder(String empNumber, String lname, String fname, String email, String username) {
			// insert validation
			validateEmpNumber(empNumber);
			validateLname(lname);
			validateFname(fname);
			validateEmail(email);
			validateUsername(username);

			this.empNumber = empNumber;
			this.lname = lname;
			this.fname = fname;
			this.email = email;
			this.username = username;
		}
		public Builder(Long id, String empNumber, String lname, String fname, String email, String username) {
			// insert validation
			validateId(id);
			validateEmpNumber(empNumber);
			validateLname(lname);
			validateFname(fname);
			validateEmail(email);
			validateUsername(username);

			this.id = id;
			this.empNumber = empNumber;
			this.lname = lname;
			this.fname = fname;
			this.email = email;
			this.username = username;
		}

		public Employee build() {
			return new Employee(this);
		}

		private void validateId(Long id) {
			if (id == null || id == 0) {
				throw new IllegalArgumentException("id should not be 0 or empty");
			}
		}

		private void validateEmpNumber(String empNumber) {
			if (empNumber == null || empNumber == "" || empNumber.isEmpty() || empNumber.length() < 10) {
				throw new IllegalArgumentException("Employee Number should not be empty");
			}
		}

		private void validateLname(String lname) {
			if (lname == null || lname.isEmpty() || lname == "" || lname.length() < 50) {

			}
		}

		private void validateFname(String fname) {
			if (fname == null || fname.isEmpty() || fname == "" || fname.length() < 50) {

			}
		}

		private void validateEmail(String email) {
			if (email == null || email.isEmpty() || email == "" || email.length() < 50) {

			}
		}

		private void validateUsername(String username) {
			if (username == null || username.isEmpty() || username == "" || username.length() < 50) {

			}
		}
	}
}
