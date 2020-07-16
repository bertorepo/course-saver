package com.fujitsu.ph.tsup.attendance.model;

/**
 * <pre>
 * It is a JavaBean for GenerateAttendanceCourse
 * In this class, Instances of the List Data required for the Initial setting of the database
 * </pre>
 * 
 * @version 0.02
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
//0.02    | 06/25/2020 | WS) K.Abad            | New Creation

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
    
    @Override
    public String toString() {
        return "GenerateAttendanceCourse [id=" + id + ", courseName=" + courseName + "]";
    }
}
