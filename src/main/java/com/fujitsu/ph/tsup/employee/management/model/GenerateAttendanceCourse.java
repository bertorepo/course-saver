package com.fujitsu.ph.tsup.employee.management.model;

/**
 * <pre>
 * It is a JavaBean for GenerateAttendanceCourse
 * In this class, Instances of the List Data required for the Initial setting of the database
 * </pre>
 * 
 * @version 0.01
 * @author k.abad
 *
 */

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign up
//System Name  :Attendance process 
//Class Name   :GenerateAttendanceCourse.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/22/2020 | WS) K.Abad            | New Creation

public class GenerateAttendanceCourse {
    /**
     * Course Schedule Detail Id
     */
    private Long id;
    
    /**
     * Course Name
     */
    private String courseName;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    
}
