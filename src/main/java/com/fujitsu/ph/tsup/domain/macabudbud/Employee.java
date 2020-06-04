package com.fujitsu.ph.tsup.domain.macabudbud;

public class Employee {
	private Long id;
	private String number;
	private String lastName;
	private String firstName;
	private String emailAddress;
	private String username;
	
	protected Employee() {
	}

	private Employee(Builder builder) {
		this.id = builder.id;
		this.number = builder.number;
		this.lastName = builder.lastName;
		this.firstName = builder.firstName;
		this.emailAddress = builder.emailAddress;
		this.username = builder.username;
	}
	
	public static class Builder {
		private Long id;
		private String number;
		private String lastName;
		private String firstName;
		private String emailAddress;
		private String username;
		
		public Builder(String number, String lastName, String firstName, String emailAddress, String username) {
            validateNumber(number);
            validateLastName(lastName);
            validateFirstName(firstName);
            validateEmailAddress(emailAddress);
            validateUsername(username);		
			
			this.number = number;
			this.lastName = lastName;
			this.firstName = firstName;
			this.emailAddress = emailAddress;
			this.username = username;
		}
		
		public Employee build() {
			return new Employee(this);
		}
		
		public void validateNumber(String number) {
			if(number == null || number.isEmpty()) {
				throw new IllegalArgumentException("Employee number should not be empty.");
			}else if(number.length() > 10) {
				throw new IllegalArgumentException("Invalid input. Employee number exceeds 10 characters.");
			}
		}
		public void validateLastName(String lastName) {
			if(lastName == null || lastName.isEmpty()) {
				throw new IllegalArgumentException("Employee last name should not be empty.");
			}else if(lastName.length() > 50) {
				throw new IllegalArgumentException("Invalid input. Employee last name exceeds 50 characters.");
			}
		}
		public void validateFirstName(String firstName) {
			if(firstName == null || firstName.isEmpty()) {
				throw new IllegalArgumentException("Employee first name should not be empty.");
			}else if(firstName.length() > 50) {
				throw new IllegalArgumentException("Invalid input. Employee first name exceeds 50 characters.");
			}
		}
		public void validateEmailAddress(String emailAddress) {
			if(emailAddress == null || emailAddress.isEmpty()) {
				throw new IllegalArgumentException("Employee email address should not be empty.");
			}else if(emailAddress.length() > 50) {
				throw new IllegalArgumentException("Invalid input. Email address exceeds 50 characters.");
			}
		}
		public void validateUsername(String username) {
			if(username == null || username.isEmpty()) {
				throw new IllegalArgumentException("Employee username should not be empty.");
			}else if(username.length() > 50) {
				throw new IllegalArgumentException("Invalid input. Username exceeds 50 characters.");
			}
		}
	}
	
	public Long getId() {
		return id;
	}
	public String getNumber() {
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
	public String getUsername() {
		return username;
	}
}
