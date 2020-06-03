package com.fujitsu.ph.tsup.domain.ramos;

import java.util.regex.Pattern;
import ch.qos.logback.core.boolex.Matcher;

public class Employee {
	private Long id;
	private String number;
	private String lastName;
	private String firstName;
	private String emailAddress;
	private String userName;

	protected Employee() {
	}

	private Employee(Builder builder) {
		this.id = builder.id;
		this.number = builder.number;
		this.lastName = builder.lastName;
		this.firstName = builder.firstName;
		this.emailAddress = builder.emailAddress;
		this.userName = builder.userName;
	}

	public Long getId() {
		return id;
	}

	public String getEmployeeNumber() {
		return number;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getUserName() {
		return userName;
	}

	public static class Builder {
		private Long id;
		private String number;
		private String lastName;
		private String firstName;
		private String emailAddress;
		private String userName;
		private Pattern pattern;

		public Builder(String number, String lastName, String firstName, String emailAddress, String userName) {
			validateEmployeeNumber(number);
			validateLastName(lastName);
			validateFirstName(firstName);
			validateEmailAddress(emailAddress);
			validateUserName(userName);

			this.number = number;
			this.lastName = lastName;
			this.firstName = firstName;
			this.emailAddress = emailAddress;
			this.userName = userName;
		}

		public Employee build() {
			return new Employee(this);
		}

		private void validateEmployeeNumber(String number) {
			Pattern.compile("[0-9]*");
			java.util.regex.Matcher matcher = pattern.matcher(number);

			if (number == null || number.isEmpty()) {
				throw new IllegalArgumentException("Employee Number is empty");
			} else if (!matcher.matches()) {
				throw new IllegalArgumentException("Employee Number must contain numbers only");
			}
		}

		private void validateLastName(String lastName) {
			Pattern.compile("[a-zA-Z]*");
			java.util.regex.Matcher matcher = pattern.matcher(lastName);

			if (lastName == null || lastName.isEmpty()) {
				throw new IllegalArgumentException("Last Name is empty");
			} else if (!matcher.matches()) {
				throw new IllegalArgumentException("Last Name must contain letters only");
			}
		}

		private void validateFirstName(String firstName) {
			Pattern.compile("[a-zA-Z]*");
			java.util.regex.Matcher matcher = pattern.matcher(firstName);

			if (firstName == null || firstName.isEmpty()) {
				throw new IllegalArgumentException("First Name is empty");
			} else if (!matcher.matches()) {
				throw new IllegalArgumentException("First Name must contain letters only");
			}
		}

		private void validateEmailAddress(String emailAddress) {
			if (emailAddress == null || emailAddress.isEmpty()) {
				throw new IllegalArgumentException("email address should not be empty");
			}
		}

		private void validateUserName(String userName) {
			if (userName == null || userName.isEmpty() || userName.length() < 1 || userName.length() > 20) {
				throw new IllegalArgumentException("username should not be empty");
			}
		}
	}
}
