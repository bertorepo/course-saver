package com.fujitsu.ph.tsup.domain.francisco;

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
        
        public Builder(Long id, String number, String lastName, String firstName, String emailAddress, String username) {
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
        
        private void validateNumber(String number) {
            if (number == null || number.isEmpty()) {
                throw new IllegalArgumentException("employee number should not be empty!");
            } else if (number.length() > 10) {
                throw new IllegalArgumentException("employee number should not exceed 10 digits!");
            }
        }

        private void validateLastName(String lastName) {
            if (lastName == null || lastName.isEmpty()) {
                throw new IllegalArgumentException("last name should not be empty!");
            } else if (lastName.length() > 50) {
                throw new IllegalArgumentException("last name should not exceed 50 characters!");
            }
        }

        private void validateFirstName(String firstName) {
            if (firstName == null || firstName.isEmpty()) {
                throw new IllegalArgumentException("first name should not be empty!");
            } else if (firstName.length() > 50) {
                throw new IllegalArgumentException("first name should not exceed 50 characters!");
            }
        }

        private void validateEmailAddress(String emailAddress) {
            if (emailAddress == null || emailAddress.isEmpty()) {
                throw new IllegalArgumentException("email address should not be empty!");
            } else if (emailAddress.length() > 50) {
                throw new IllegalArgumentException("email address should not exceed 50 characters!");
            }
        }

        private void validateUsername(String username) {
            if (username == null || username.isEmpty()) {
                throw new IllegalArgumentException("username should not be empty!");
            } else if (username.length() > 50) {
                throw new IllegalArgumentException("username should not exceed 50 characters!");
            }
        }
    }
}
