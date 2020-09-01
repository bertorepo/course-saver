package com.fujitsu.ph.tsup.attendance.model;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :AttendanceParticipantDetail.java
//
//<<Modification History>>
//Version | Date       | Updated By                             | Content
//--------+------------+----------------------------------------+-----------------------------------
//0.01    | 06/19/2020 |  WS) J.Iwarat                          | New Creation
//0.02    | 06/25/2020 |  WS) J.Iwarat                          | Update
//0.03    | 07/15/2020 |  WS) K.Abad                            | Update
//0.04    | 08/26/2020 |  WS) K.Abad WS) J.Iwarat WS) R.Ramos   | Update
//==================================================================================================
/**
 * <pre>
 * JavaBean for GenerateAttendanceParticipant
 * In this Class,Instances or fields of the List of the data for the initial setting of the data base 
 * <pre>
 * 
 * @version 0.04
 * @author k.abad
 * @author j.iwarat  
 * @author r.ramos                         
 */
import java.time.ZonedDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class AttendanceParticipantDetail {
    /**
     * Course Participant Id
     */
    private Long id;

    /**
     * Participant's Name(LASTNAME + "," + FIRSTNAME)
     */
    private String name;

    /**
     * Participant's Status
     */
    private char status;

    /**
     * Participant's Login Date and Time
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime loginDateTime;

    /**
     * Participant's Logout Date and Time
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime logoutDateTime;

    /**
     * Email
     */
    private String email;

    /**
     * Employee Number
     */
    private String employeeNumber;

    /**
     * Department Name
     */
    private String department;

    /**
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     */
    public char getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(char status) {
        this.status = status;
    }

    /**
     * @return
     */
    public ZonedDateTime getLoginDateTime() {
        return loginDateTime;
    }

    /**
     * @param loginDateTime
     */
    public void setLoginDateTime(ZonedDateTime loginDateTime) {
        this.loginDateTime = loginDateTime;
    }

    /**
     * @return
     */
    public ZonedDateTime getLogoutDateTime() {
        return logoutDateTime;
    }

    /**
     * @param logoutDateTime
     */
    public void setLogoutDateTime(ZonedDateTime logoutDateTime) {
        this.logoutDateTime = logoutDateTime;
    }

    /**
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return
     */
    public String getEmployeeNumber() {
        return employeeNumber;
    }

    /**
     * @param employeeNumber
     */
    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    /**
     * @return
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "AttendanceParticipantDetail [id=" + id + ", name=" + name + ", status=" + status + ", loginDateTime="
                + loginDateTime + ", logoutDateTime=" + logoutDateTime + ", email=" + email + ", employeeNumber="
                + employeeNumber + ", department=" + department + "]";
    }

}
