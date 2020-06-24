package com.fujitsu.ph.tsup.attendance.model;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :AttendanceParticipantDetail.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/19/2020 |  WS) J. Iwarat   | New Creation
//==================================================================================================
/**
 * <pre>
 * JavaBean for GenerateAttendanceParticipant
 * In this Class,Instances or fields of the List of the data for the initial setting of the data base 
 * <pre>
 * 
 * @version 0.01
 * @author j.iwarat                           
 */
import java.time.ZonedDateTime;

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
    private ZonedDateTime loginDateTime;

    /**
     * Email
     */
    private String email;

    /**
     * Employee Number
     */
    private String employeeNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public ZonedDateTime getLoginDateTime() {
        return loginDateTime;
    }

    public void setLoginDateTime(ZonedDateTime loginDateTime) {
        this.loginDateTime = loginDateTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

}
