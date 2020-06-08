package com.fujitsu.ph.tsup.domain.angara;

public class Employee {
    private Long id;
    private Long employeeNumber;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String userName;

    protected Employee() {
    }

    private Employee(Builder builder) {
        this.id = builder.id;
        this.emailAddress = builder.emailAddress;
        this.employeeNumber = builder.employeeNumber;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.userName = builder.userName;
    }

    public Long getId() {
        return id;
    }

    public Long getEmployeeNumber() {
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
        public String userName;
        public String lastName;
        public String firstName;
        public String emailAddress;
        public Long id;
        private Long employeeNumber;

        public Builder(Long employeeNumber, String firstName, String lastName, String emailAddress, String userName, String string) {
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

        public Builder(Long id2, Long employeeNumber2, String firstName2, String lastName2, String emailAddress2,
                String userName2) {
            // TODO Auto-generated constructor stub
        }

        public Builder(String string, String firstName2, String lastName2, String emailAddress2, String userName2,
                String string2) {
            // TODO Auto-generated constructor stub
        }

        private void validateUserName(String userName) {
            if (userName.isEmpty() || userName == null || userName.length() > 50 || userName.length() < 3) {
                throw new IllegalArgumentException("Provide appropriate username.");
            }
        }

        private void validateEmailAddress(String emailAddress) {
            if (emailAddress.isEmpty() || emailAddress == null || emailAddress.length() > 50
                    || emailAddress.length() < 3) {
                throw new IllegalArgumentException("Provide appropriate email address.");
            }
        }

        private void validateLastName(String lastName) {
            if (lastName.isEmpty() || lastName == null || lastName.length() > 50 || lastName.length() < 1) {
                throw new IllegalArgumentException("Provide appropriate last name.");
            }

        }

        private void validateEmployeeNumber(Long employeeNumber) {
            if (employeeNumber == null || employeeNumber == 0 || employeeNumber > 50) {
                throw new IllegalArgumentException("Provide appropriate first name.");
            }
        }

        private void validateFirstName(String firstName) {
            if (firstName.isEmpty() || firstName == null || firstName.length() > 50 || firstName.length() < 1) {
                throw new IllegalArgumentException("Provide appropriate first name.");
            }
        }

        public Employee build() {
            return null;
        }
    }
}