package com.fujitsu.ph.tsup.domain.abad;

public class Employee {
    private Long id;
    private String employeeNumber;
    private String lastName;
    private String firstName;
    private String emailAddress;
    private String userName;
    
    protected Employee() {    
    }
    
    private Employee(Builder builder) {
        this.id = builder.id;
        this.employeeNumber = builder.employeeNumber;
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.emailAddress = builder.emailAddress;
        this.userName = builder.userName;
    }
    

    public Long getId() {
        return id;
    }

    public String getEmployeeNumber() {
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
        private Long id;
        private String employeeNumber;
        private String lastName;
        private String firstName;
        private String emailAddress;
        private String userName;

        public Builder(Long id, String employeeNumber, String lastName, String firstName, String emailAddress, String userName) {
            validateEmployeeNumber(employeeNumber);
            validateLastName(lastName);
            validateFirstName(firstName);
            validateEmailAddress(emailAddress);
            validateUserName(userName);
            
            this.id = id;
            this.employeeNumber = employeeNumber;
            this.lastName = lastName;
            this.firstName = firstName;
            this.emailAddress = emailAddress;
            this.userName = userName;
        }
        
        public Employee build() {
            return new Employee(this);
        }
           
        private void validateEmployeeNumber(String employeeNumber) {
            if (employeeNumber == null || employeeNumber.isEmpty()) {  
                throw new IllegalArgumentException("employee number should not be empty");
            }
            else if (employeeNumber.length() < 5) {
                throw new IllegalArgumentException("employee number should not be less than 5 characters"); 
            }
            else if (employeeNumber.length() > 20) {
                throw new IllegalArgumentException("employee number should not exceeds by 20 characters");        
            }
        }
            
        private void validateLastName(String lastName) {
            if (lastName == null || lastName.isEmpty()) {
                throw new IllegalArgumentException("last name should not be empty");
            }
            else if (lastName.length() < 1) {
                throw new IllegalArgumentException("last name should not be less than 1 character");
            }
            else if (lastName.length() > 20) {
                throw new IllegalArgumentException("last name should not exceeds by 20 characters");       
            }
        }
                
        private void validateFirstName(String firstName) {
            if (firstName == null || firstName.isEmpty()) {
                throw new IllegalArgumentException("first name should not be empty");
            }
            else if (firstName.length() < 1) {
                throw new IllegalArgumentException("first name should not be less than 1 character");
            }
            else if (firstName.length() > 20) {
                throw new IllegalArgumentException("first name should not exceeds by 20 characters");   
            }
        }
                    
        private void validateEmailAddress(String emailAddress) {
            if (emailAddress == null || emailAddress.isEmpty()) {
                 throw new IllegalArgumentException("email address should not be empty");
            }
            else if (emailAddress.length() < 10) {
                throw new IllegalArgumentException("email address should not be less than 10 characters");
            }
            else if (emailAddress.length() > 100) {
                throw new IllegalArgumentException("email address should not exceeds by 100 characters");      
            }
        }
                        
        private void validateUserName(String userName) {
            if (userName == null || userName.isEmpty()) {
                 throw new IllegalArgumentException("username should not be empty");
            } 
            else if (userName.length() < 1) {
                throw new IllegalArgumentException("username should not be less than 1 character");
            }
            else if (userName.length() > 20) {
                throw new IllegalArgumentException("username should not exceeds by 20 characters");  
            }
       }       
    }
}