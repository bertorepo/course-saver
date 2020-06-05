package com.fujitsu.ph.tsup.domain.deguzman;

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

        public Builder(Long id, String number, String lastName, String firstName, String emailAddress, String userName) {
            validateNumber(number);
            validateLastName(lastName);
            validateFirstName(firstName);
            validateEmailAddress(emailAddress);
            validateUserName(userName);

            this.id = id;
            this.number = number;
            this.lastName = lastName;
            this.firstName = firstName;
            this.emailAddress = emailAddress;
            this.userName = userName;
        }
        public Builder(String number, String lastName, String firstName, String emailAddress, String userName) {
            validateNumber(number);
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

        private void validateNumber(String number) {
            if (number == null || number.isEmpty()) {
                throw new IllegalArgumentException("Employee Number should not be empty");
            } else if(number.length() < 10) {
                throw new IllegalArgumentException("Employee Number should not be less than 10 characters");
            } else if(number.length() > 10) {
                throw new IllegalArgumentException("Employee Number should not be more than 10 characters");
            }

        }

        private void validateLastName(String lastName) {
            if (lastName == null || lastName.isEmpty()) {
                throw new IllegalArgumentException("Last name should not be empty");
            } else if(lastName.length() < 1) {
                throw new IllegalArgumentException("Last name should not be less than 1 character");
            } else if(lastName.length() > 50) {
                throw new IllegalArgumentException("Last name should not be more than 50 characters");
            }

        }

        private void validateFirstName(String firstName) {
            if (firstName == null || firstName.isEmpty()) {
                throw new IllegalArgumentException("First name should not be empty");
            } else if(firstName.length() < 1) {
                throw new IllegalArgumentException("First name should not be less than 1 character");
            } else if(firstName.length() > 50) {
                throw new IllegalArgumentException("First name should not be more than 50 characters");
            }
        }

        private void validateEmailAddress(String emailAddress) {
            if (emailAddress == null || emailAddress.isEmpty()) {
                throw new IllegalArgumentException("Email Address should not be empty");
            } else if(emailAddress.length() > 50) {
                throw new IllegalArgumentException("Email Address should not be more than 50 characters");
            } else if(emailAddress.length() < 5) {
                throw new IllegalArgumentException("Email Address should not be less than 5 characters");
            }
        }

        private void validateUserName(String userName) {
            if (userName == null || userName.isEmpty()) {
                throw new IllegalArgumentException("Username should not be empty");
            } else if(userName.length() > 50) {
                throw new IllegalArgumentException("Username should not be more than 50 characters");
            } else if(userName.length() < 1) {
                throw new IllegalArgumentException("Username should not be less than 1 character");
            }
        }
    }

}
