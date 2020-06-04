package com.fujitsu.ph.tsup.employee.management.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EMPLOYEE_CREATE {

    /* Employee ID */
    private Long employeeID;

    /* Employee Number */
    @NotBlank(message = "Employee Number cannot be null")
    @Size(min = 10, max = 10, message = "Employee number should be 10 characters")
    private String employeeNumber;

    /* Employee's Lastname */
    @NotBlank(message = "Last Name annot be Null")
    private String lastName;

    /* Employee's Firstname */
    @NotBlank(message = "First Name cannot be Null")
    private String firstName;

    /* Employee's Email Address */
    @NotBlank(message = "Email Address cannot be Null")
    @Email(message = "Invalid Email Address")
    private String emailAddress;

    /* Employee's Username */
    @NotBlank(message = "Username cannot be Null")
    @Size(min = 6, max = 50)
    private String username;

    /* Submit Button */
    private String submit;

    /* Getter and Setters */

    /* Getter of Employee ID */
    public Long getEmployeeID() {
        return employeeID;
    }

    /* Setter of Employee ID */
    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    /* Getter of Employee Number */
    public String getEmployeeNumber() {
        return employeeNumber;
    }

    /* Setter of Employee Number */
    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    /* Getter of Employee's Lastname */
    public String getLastName() {
        return lastName;
    }

    /* Setter of Employee's Lastname */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /* Getter of Employee's Firstname */
    public String getFirstName() {
        return firstName;
    }

    /* Setter of Employee's Firstname */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /* Getter of Employee's Email Address */
    public String getEmailAddress() {
        return emailAddress;
    }

    /* Setter of Employee's Email Address */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /* Getter of Employee's Username */
    public String getUsername() {
        return username;
    }

    /* Setter of Employee's Username */
    public void setUsername(String username) {
        this.username = username;
    }

    /* Getter of Submit Button */
    public String getSubmit() {
        return submit;
    }

    /* Setter of Submit Button */
    public void setSubmit(String submit) {
        this.submit = submit;
    }

    @Override
    public String toString() {
        return "EMPLOYEE_CREATE [employeeID=" + employeeID + ", employeeNumber=" + employeeNumber + ", lastName="
                + lastName + ", firstName=" + firstName + ",  emailAddress=" + emailAddress + ", username=" + username
                + ", submit=" + submit + "]";
    }

}
//Mark Lumontad