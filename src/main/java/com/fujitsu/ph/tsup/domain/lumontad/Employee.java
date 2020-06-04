package com.fujitsu.ph.tsup.domain.lumontad;


public class Employee {
    private Long employeeID;
    private String employeeNumber;
    private String lastName;
    private String firstName;
    private String emailAddress;
    private String userName;
    
    protected Employee() {    
    }
    
    private Employee(Builder builder) {
        this.employeeID = builder.employeeID;
        this.employeeNumber = builder.employeeNumber;
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.emailAddress = builder.emailAddress;
        this.userName = builder.userName;
    }
    

    public Long getId() {
        return employeeID;
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
        private Long employeeID;
        private String employeeNumber;
        private String lastName;
        private String firstName;
        private String emailAddress;
        private String userName;

        public Builder(String employeeNumber, String lastName, String firstName, String emailAddress, String userName) {
            validateEmployeeNumber(employeeNumber);
            validateLastName(lastName);
            validateFirstName(firstName);
            validateEmailAddress(emailAddress);
            validateUserName(userName);
            
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
            if (employeeNumber == null || employeeNumber.isEmpty() || employeeNumber.length() < 5 || employeeNumber.length() > 20) {
                throw new IllegalArgumentException("Employee Number cannot be Empty");
            } 
        }
            
        private void validateLastName(String lastName) {
            if (lastName == null || lastName.isEmpty() || lastName.length() < 1 || lastName.length() > 20) {
                throw new IllegalArgumentException("Last Name cannot be Empty");
            }
        }
                
        private void validateFirstName(String firstName) {
            if (firstName == null || firstName.isEmpty() || firstName.length() < 1 || firstName.length() > 20) {
                throw new IllegalArgumentException("First Name cannot be Empty");
            }
        }
                    
        private void validateEmailAddress(String emailAddress) {
            if (emailAddress == null || emailAddress.isEmpty() || emailAddress.length() < 10 || emailAddress.length() > 100) {
                 throw new IllegalArgumentException("Email Address cannot be Empty");
            }
        }
                        
        private void validateUserName(String userName) {
            if (userName == null || userName.isEmpty() || userName.length() < 1 || userName.length() > 20) {
                 throw new IllegalArgumentException("Username cannot be Empty");
            } 
       }       
    }
}