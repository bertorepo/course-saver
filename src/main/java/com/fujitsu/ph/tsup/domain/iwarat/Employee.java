package com.fujitsu.ph.tsup.domain.iwarat;

public class Employee {
    private Long id;
    private String number;
    private String lastName;
    private String firstName;
    private String emailAddress;
    private String username;

    protected Employee() {

    }

    public Employee(Builder builder) {
        this.id = builder.id;
        this.number = builder.number;
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.emailAddress = builder.emailAddress;
        this.username = builder.username;
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
    
    public static class Builder {
        private Long id;
        private String number;
        private String lastName;
        private String firstName;
        private String emailAddress;
        private String username;
        
        public Builder(Long id, String number, String lastName, String firstName, String emailAddress,
                String username) {
            validateId(id);
            validateNumber(number);
            validateLastName(lastName);
            validateFirstName(firstName);
            validateEmailAddress(emailAddress);
            validateUsername(username);
            
            this.id = id;
            this.number = number;
            this.lastName = lastName;
            this.firstName = firstName;
            this.emailAddress = emailAddress;
            this.username = username;
        }
            public Employee build() {
                return new Employee(this);
            }
            
        private void validateId(Long id) {
            if (id == null) {
                throw new IllegalArgumentException("Id should not be null");
            }
        }
        
        private void validateNumber(String number) {
            if (number == null || number.isEmpty() || number.length() > 10) {
                throw new IllegalArgumentException("Number should not be empty");
            }
        }
        
        private void validateLastName(String lastName) {
            if (lastName == null || lastName.isEmpty() || lastName.length() < 1 || lastName.length() > 50) {
                throw new IllegalArgumentException("Last name should not be empty");
            }
            
        }
        
        private void validateFirstName(String firstName) {
            if (firstName == null || firstName.isEmpty() || firstName.length() < 1 || firstName.length() > 50) {
                throw new IllegalArgumentException("First name should not be empty");
            }
        }
        
        private void validateEmailAddress(String emailAddress) {
            if (emailAddress == null || emailAddress.isEmpty() || emailAddress.length() < 1 || emailAddress.length() > 50) {
                throw new IllegalArgumentException("Email address should not be empty");
            }
        }
        
        private void validateUsername(String username) {
            if (username == null || username.isEmpty() || username.length() < 1 || username.length() > 50) {
                throw new IllegalArgumentException("Username should not be empty");
            }
        }
        
    }
}
