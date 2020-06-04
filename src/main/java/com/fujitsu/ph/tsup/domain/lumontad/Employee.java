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
            if (employeeNumber == null || employeeNumber.isEmpty()) {
                throw new IllegalArgumentException("Employee Number cannot be Empty");
            }else if (employeeNumber.length() < 10) {
                throw new IllegalArgumentException("Employee Number is less than the Required 10 Characters!");
                
            }else if (employeeNumber.length() > 10) {
                throw new IllegalArgumentException("Employee Number is greater than the Required 10 Characters!");
            }
        }
            
        private void validateLastName(String lastName) {
            if (lastName == null || lastName.isEmpty()) {
                throw new IllegalArgumentException("Last Name cannot be Empty");
            }else if (lastName.length() < 2) {
                throw new IllegalArgumentException("Last Name is less than the Required 2 Characters!");
            }else if (  lastName.length() > 50) {
                throw new IllegalArgumentException("Last Name is greater than the Required 50 Characters!");
            }
        }
                
        private void validateFirstName(String firstName) {
            if (firstName == null || firstName.isEmpty()) {
                throw new IllegalArgumentException("First Name cannot be Empty");
            }else if(firstName.length() < 2) {
                throw new IllegalArgumentException("First Name is less than the Required 2 Characters!");
            }else if(firstName.length() > 20) {
                throw new IllegalArgumentException("First Name is greater than the Required 50 Characters!");
            }
        }
                    
        private void validateEmailAddress(String emailAddress) {
            if (emailAddress == null || emailAddress.isEmpty()) {
                 throw new IllegalArgumentException("Email Address cannot be Empty");
            }else if(emailAddress.length() < 10) {
                throw new IllegalArgumentException("Email Address is less than the Required 10 Characters!");
            }else if(emailAddress.length() > 50) {
                throw new IllegalArgumentException("Email Address is greater than the Required 50 Characters!");
            }
        }
                        
        private void validateUserName(String userName) {
            if (userName == null || userName.isEmpty()) {
                 throw new IllegalArgumentException("Username cannot be Empty");
            }else if (userName.length() < 4) {
                throw new IllegalArgumentException("Username is less than the Required 4 Characters!");
            }else if (userName.length() > 50){
                throw new IllegalArgumentException("Username is greater than the Required 50 Characters!");
            }
       }       
    }
}