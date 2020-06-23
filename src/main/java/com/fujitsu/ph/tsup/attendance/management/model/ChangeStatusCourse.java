package com.fujitsu.ph.tsup.attendance.management.model;

/**
 * <pre>
 * It is a JavaBean for Change Status Course
 * In this class, Instances of the List Data required for the Initial setting of the database
 * </pre>
 * 
 * @version 0.01
 * @author h.francisco
 *
 */

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign up
//System Name  :Attendance process 
//Class Name   :ChangeStatusCourse.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/22/2020 | WS) h.francisco       | New Creation
public class ChangeStatusCourse {
    
    /**
     * Course Schedule Detail Id
     */
    private Long id;
    
    /**
     * Course Name
     */
    private String courseName;

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
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    @Override
    public String toString() {
        return "ChangeStatusCourse [id = " + id + ", courseName = " + courseName + "]";
    }

}
