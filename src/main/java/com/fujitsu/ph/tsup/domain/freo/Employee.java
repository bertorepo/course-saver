package com.fujitsu.ph.tsup.domain.freo;


public class Employee {
	private Long Id;
	private String employeeNumber;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String userName;
	
	protected Employee() {
		
	}
	
	private Employee(Builder builder) {
		this.Id = builder.Id;
		this.employeeNumber = builder.employeeNumber;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.emailAddress = builder.emailAddress;
		this.userName = builder.userName;
	}

	public Long getId() {
		return Id;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}


	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getUserName() {
		return userName;
	}
	
	public static class Builder {
		private Long Id;
		private String employeeNumber;
		private String firstName;
		private String lastName;
		private String emailAddress;
		private String userName;	
		
		public Builder(Long Id, String employeeNumber,  String firstName, String lastName, String emailAddress,String userName) {
			validateId(Id);
			validateemployeeNumber(employeeNumber);
			validatefirstName(firstName);
			validatelastName(lastName);
			validateemailAddress(emailAddress);
			validateuserName(userName);
			
			this.Id = Id;
			this.employeeNumber = employeeNumber;
			this.firstName = firstName;
			this.lastName = lastName;
			this.emailAddress = emailAddress;
			this.userName = userName;
			
		}	
		 public Employee builder() {
			 return new Employee(this);
		 }	 
		 
		private void validateId(Long Id) {
			if (Id == null) {
                throw new IllegalArgumentException("ID should not be null");
            }
		}	
		private void validateemployeeNumber(String employeeNumber) {
			if (employeeNumber == null) {
                throw new IllegalArgumentException("The Employee Number must not null");
            }else if (employeeNumber.length() > 10) {
                throw new IllegalArgumentException(" The Employee Number Length exceeds the Limit");
            }
		}
		private void validatefirstName(String firstName) {
			if (firstName== null) {
				throw new IllegalArgumentException("The First Name must not null");	
			}else if (firstName.isEmpty()) {
                throw new IllegalArgumentException("The First Name must not null");
            }else if (firstName.length() > 50) {
                throw new IllegalArgumentException("The First Name Length exceeds the Limit");
            }
		}
		private void validatelastName(String lastName) {
			if ( lastName== null ) {
				throw new IllegalArgumentException("Last Name Should not  null");	
			} else if (lastName.isEmpty()) {
                throw new IllegalArgumentException("Last Name should not Empty");
            }else if (lastName.length() > 50) {
                throw new IllegalArgumentException("Last Name Length exceeds the Limit");
            }
		}
		private void validateemailAddress(String emailAddress) {
			if (emailAddress== null) {
				throw new IllegalArgumentException("Email Address must notnull");	
			}else if (emailAddress.isEmpty()) {
                throw new IllegalArgumentException("Email Address must not Empty");
            }else if (emailAddress.length() > 50) {
                throw new IllegalArgumentException("Email Address Length exceeds the Limit");
           }
		}   
		private void validateuserName(String userName) {
			if (userName == null) {
				throw new IllegalArgumentException("Username must not null");	
			}  else if (userName.isEmpty()) {
                throw new IllegalArgumentException("Username must not Empty");
            }else if (userName.length() > 50) {
                throw new IllegalArgumentException("Username Length exceeds the Limit");
           }
		}
	}
}