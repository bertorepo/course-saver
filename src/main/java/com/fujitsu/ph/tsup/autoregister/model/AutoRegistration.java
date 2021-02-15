/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.autoregister.model;

/**
 * AutoRegistration class
 * 
 * @author k.sala (New Creation by: k.sala)
 * @version 0.01
 */
public class AutoRegistration {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Long departmentid;
    private String userName;
    private String employmentDate;

    /**
     * AutoRegistration Constructor
     * @param Builder builder
     */
    private AutoRegistration(Builder builder) {

        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.emailAddress = builder.emailAddress;
        this.departmentid = builder.departmentid;
        this.userName = builder.userName;
        this.employmentDate = builder.employmentDate;
    }

    /**
     * Getter method for AutoRegistration Id
     * 
     * @return AutoRegistration id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for AutoRegistration Id
     * 
     * @param AutoRegistration id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for AutoRegistration First Name
     * 
     * @return AutoRegistration firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter method for AutoRegistration First Name
     * 
     * @param AutoRegistration firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter method for AutoRegistration Last Name
     * 
     * @return AutoRegistration lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter method for AutoRegistration Last Name
     * 
     * @param AutoRegistration lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter method for AutoRegistration Email Address
     * 
     * @return AutoRegistration emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Setter method for AutoRegistration Email Address
     * 
     * @param AutoRegistration emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Getter method for AutoRegistration Department Id
     * 
     * @return AutoRegistration departmentid
     */
    public Long getDepartmentid() {
        return departmentid;
    }

    /**
     * Setter method for AutoRegistration Department Id
     * 
     * @param AutoRegistration departmentid
     */
    public void setDepartmentid(Long departmentid) {
        this.departmentid = departmentid;
    }

    /**
     * Getter method for AutoRegistration User Name
     * 
     * @return AutoRegistration userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter method for AutoRegistration User Name
     * 
     * @param AutoRegistration userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter method for AutoRegistration Employment Date
     * 
     * @return AutoRegistration employmentDate
     */
    public String getEmploymentDate() {
        return employmentDate;
    }

    /**
     * Setter method for AutoRegistration Employment Date
     * 
     * @param AutoRegistration employmentDate
     */
    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = employmentDate;
    }

    @Override
    public String toString() {
        return "AutoRegistration [ID=" + id + ", FirstName=" + firstName + ", LastName=" + lastName
                + ", EmailAddress=" + emailAddress + ", DepartmentID=" + departmentid + ", UserName="
                + userName + ", EmploymentDate=" + employmentDate;
    }

    /**
     * Builder Class
     * @author k.sala
     *
     */
    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String emailAddress;
        private Long departmentid;
        private String userName;
        private String employmentDate;

        /**
         * Builder Constructor
         * @param id
         * @param firstName
         * @param lastName
         * @param emailAddress
         * @param departmentid
         * @param userName
         * @param employmentDate
         */
        public Builder(Long id, String firstName, String lastName, String emailAddress, Long departmentid,
                String userName, String employmentDate) {
            validateID(id);
            validateFirstName(firstName);
            validateLastName(lastName);
            validateEmailAddress(emailAddress);
            validateDepartmentID(departmentid);
            validateUserName(userName);
            validateEmploymentDate(employmentDate);

            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.emailAddress = emailAddress;
            this.departmentid = departmentid;
            this.userName = userName;
            this.employmentDate = employmentDate;

        }

        public AutoRegistration build() {

            return new AutoRegistration(this);

        }

        /**
         * Validate AutoRegistration id if null or empty
         * @param id
         */
        private void validateID(Long id) {

            if (id == null || id == 0) {
                throw new IllegalArgumentException("ID should not be empty");
            }

        }

        /**
         * Validate AutoRegistration firstName if null or empty
         * @param firstName
         */
        private void validateFirstName(String firstName) {

            if (firstName.equals(null) || firstName.isEmpty()) {
                throw new IllegalArgumentException("First Name should not be empty");
            }

        }

        /**
         * Validate AutoRegistration lastName if null or empty
         * @param lastName
         */
        private void validateLastName(String lastName) {

            if (lastName.equals(null) || lastName.isEmpty()) {
                throw new IllegalArgumentException("Last Name should not be empty");
            }

        }

        /**
         * Validate AutoRegistration emailAddress if null or empty
         * @param emailAddress
         */
        private void validateEmailAddress(String emailAddress) {

            if (emailAddress.equals(null) || emailAddress.isEmpty()) {
                throw new IllegalArgumentException("Email Address should not be empty");
            }

        }

        /**
         * Validate AutoRegistration departmentid if null or empty
         * @param departmentid
         */
        private void validateDepartmentID(Long departmentid) {

            if (departmentid == null || departmentid == 0) {
                throw new IllegalArgumentException("Department ID should not be empty");
            }

        }

        /**
         * Validate AutoRegistration userName if null or empty
         * @param userName
         */
        private void validateUserName(String userName) {

            if (userName.equals(null) || userName.isEmpty()) {
                throw new IllegalArgumentException("User Name should not be empty");
            }

        }

        /**
         * Validate AutoRegistration employmentDate if null or empty
         * @param employmentDate
         */
        private void validateEmploymentDate(String employmentDate) {

            if (employmentDate.equals(null) || employmentDate.isEmpty()) {
                throw new IllegalArgumentException("Employment Date should not be empty");
            }

        }
    }

}
