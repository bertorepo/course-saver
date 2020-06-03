package com.fujitsu.ph.tsup.domain.deguzman;

public class Employee {
    private Long id;
    private String number;
    private String lastName;
    private String firstName;
    private String emailAddress;
    private String userName;;

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
            if (number == null || number.isEmpty() || number.length() < 10 || number.length() > 10) {
                throw new IllegalArgumentException("Employee Number should not be empty");
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
            if (emailAddress == null || emailAddress.isEmpty() || emailAddress.length() > 50
                    || emailAddress.length() < 5) {
                throw new IllegalArgumentException("Email Address should not be empty");
            }
        }

        private void validateUserName(String userName) {
            if (userName == null || userName.isEmpty() || userName.length() > 50 || userName.length() < 1) {
                throw new IllegalArgumentException("Username should not be empty");
            }
        }
    }

}
