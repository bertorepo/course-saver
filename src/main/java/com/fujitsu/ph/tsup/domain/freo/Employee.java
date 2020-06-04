package com.fujitsu.ph.tsup.domain.freo;

public class Employee {
	private Long Id;
	private Long employeeNumber;
	private String lastName;
	private String firstName;
	private String emailAddress;
	private String userName;
	
	protected Employee() {
		
	}
	
	private Employee(Builder builder) {
		this.Id = builder.Id;
		this.employeeNumber = builder.employeeNumber;
		this.lastName = builder.lastName;
		this.firstName = builder.lastName;
		this.emailAddress = builder.emailAddress;
		this.userName = builder.userName;
	}

	public Long getId() {
		return Id;
	}

	public Long getEmployeeNumber() {
		return employeeNumber;
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
		private Long Id;
		private String lastName;
		private String firstName;
		private String emailAddress;
		private String userName;
		private Long employeeNumber;
		
		public Builder(Long Id1, Long employeeNumber1, String lastName1, String firstName1, String emailAddress1,String userName1) {
			validateId(Id1);
			validateemployeeNumber(employeeNumber1);
			validatefirstName(firstName1);
			validatelastName(lastName1);
			validateemailAddress(emailAddress1);
			validateuserName(userName1);
			
			this.Id = Id1;
			this.employeeNumber = employeeNumber1;
			this.lastName = lastName1;
			this.firstName = firstName1;
			this.emailAddress = emailAddress1;
			this.userName = userName1;
		}
		
		 public Employee builder() {
			 return new Employee(this);
		 }
		 
		private void validateId(Long Id) {
			if (Id == null) {
                throw new IllegalArgumentException("ID should not be null");
            }
		}
		
		private void validateemployeeNumber(Long employeeNumber) {
			if (employeeNumber == null) {
                throw new IllegalArgumentException("Employee Number should not be null");
            }
		}
			
		private void validatefirstName(String firstName) {
			if (firstName.isEmpty()  || firstName== null) {
				throw new IllegalArgumentException("First Name Should not be empty");	
			}
		}
		
		private void validatelastName(String lastName) {
			if (lastName.isEmpty()  || lastName== null) {
				throw new IllegalArgumentException("Last Name Should not be empty");	
			}
		}
		private void validateemailAddress(String emailAddress) {
			if (emailAddress.isEmpty()  || emailAddress== null) {
				throw new IllegalArgumentException("Email Address Should not be empty");	
			}
		}
		private void validateuserName(String userName) {
			if (userName.isEmpty()  || userName== null) {
				throw new IllegalArgumentException("Username Should not be empty");	
			}
		}
	}
}

