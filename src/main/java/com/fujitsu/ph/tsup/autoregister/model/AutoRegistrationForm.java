/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.autoregister.model;

/**
 * AutoRegistrationForm class
 * 
 * @author k.sala (New Creation by: k.sala)
 * @version 0.01
 */
public class AutoRegistrationForm {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Long departmentid;
    private String userName;
    private String employmentDate;

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
        return "AutoRegistration [ID + " + id + "FirstName " + firstName + " LastName " + lastName
                + " EmailAddress " + emailAddress + " DepartmentID " + departmentid + " UserName " + userName
                + " EmploymentDate " + employmentDate;
    }

}
