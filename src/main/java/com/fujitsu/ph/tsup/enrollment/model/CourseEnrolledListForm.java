package com.fujitsu.ph.tsup.enrollment.model;

//==================================================================================================
//$Id:PR01$
//Project Name :  Training Sign Up
//System Name  : Enrollment process
//Class Name   : CourseEnrolledListForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/22/2020 | WS) G.Cabiling        | New Creation
//0.02    | 07/30/2020 | WS) M.Lumontad        | Updated
//0.03    | 06/14/2021 | WS) L.Celoso          | Update
//0.03    | 06/16/2021 | WS) M.Taboada         | Update
//==================================================================================================

/** 
*<pre>  
* It is a JavaBean for CourseEnrolledListForm       
*</pre> 
*   
* @version 0.01
* @author m.lumontad 
* 
*/
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

public class CourseEnrolledListForm {

    /* From Date and Time */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
    private ZonedDateTime fromDateTime;

    /* To Date and Time */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
    private ZonedDateTime toDateTime;

    /* Set of Course Enrollment Form */
    private List<CourseEnrollmentForm> courseSchedules;

    /* Filter course category ID */
    private String courseCategoryId;

    /* Filter course name ID */
    private String courseNameId;
    
    /* Filter instructor ID */
    private String instructorId;
    
    /* Filter venue ID */
    private String venueId;

    /* Filter mandatory */
    private String mandatory;
    
    /* Filter deadline */
    private String deadline;
    
    /** From Date and Time Getter */
    public ZonedDateTime getFromDateTime() {
        return fromDateTime;
    }

    /** From Date and Time Setter */
    public void setFromDateTime(ZonedDateTime fromDateTime) {
        this.fromDateTime = fromDateTime;
    }

    /** To Date and Time Getter */
    public ZonedDateTime getToDateTime() {
        return toDateTime;
    }

    /** To Date and Time Setter */
    public void setToDateTime(ZonedDateTime toDateTime) {
        this.toDateTime = toDateTime;
    }

    /** Set of Course Enrollment Form Getter */
    public List<CourseEnrollmentForm> getCourseSchedules() {
        return courseSchedules;
    }

    /** Set of Course Enrollment Form Setter */
    public void setCourseScheduleDetailForm(List<CourseEnrollmentForm> courseSchedules) {
        this.courseSchedules = courseSchedules;
    }
    
    /** Filter course category ID Getter */
    public String getCourseCategoryId() {
        return courseCategoryId;
    }

    /** Filter course category ID Setter */
    public void setCourseCategoryId(String courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
    }
    
    /** Filter course name ID Getter */
    public String getCourseNameId() {
        return courseNameId;
    }

    /** Filter course name ID Setter */
    public void setCourseNameId(String courseNameId) {
        this.courseNameId = courseNameId;
    }

    /** Filter instructor ID Getter */
    public String getInstructorId() {
        return instructorId;
    }

    /** Filter instructor ID Setter */
    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }
    
    /** Filter venue ID Getter */
    public String getVenueId() {
        return venueId;
    }

    /** Filter venue ID Setter */
    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }
    
    /** Filter Mandatory Getter */
    public String getMandatory() {
        return mandatory;
    }

    /** Filter Mandatory Setter */
    public void setMandatory(String mandatory) {
        this.mandatory = mandatory;
    }
    
    /** Filter Deadline Getter */
    public String getDeadline() {
        return deadline;
    }

    /** Filter Deadline Setter */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    
    
    @Override
	public String toString() {
		return "CourseEnrolledListForm [FromDateTime = " + fromDateTime + ", ToDateTime = " + toDateTime + ","
				+ " CourseSchedules =" + courseSchedules
				+ ", CourseCategoryId =" + courseCategoryId 
				+ ", courseNameId=" + courseNameId
                + ", instructorId=" + instructorId
                + ", venueId=" + venueId
                + ", mandatory=" + mandatory
                + ", deadline=" + deadline
				+ " ]";
	}
}
