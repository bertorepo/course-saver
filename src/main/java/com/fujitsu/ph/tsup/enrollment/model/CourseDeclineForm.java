package com.fujitsu.ph.tsup.enrollment.model;

//==================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enrollment Course
//Class Name   :CourseDeclineForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01   | 06/25/2020 | WS) K.Freo       | Modified
//==================================================================================================
/**
* <pre>
* JavaBean for CourseDeclineForm
* <pre>
* 
* @version 0.01
* @author k.freo                   
*/
import java.time.ZonedDateTime;
import java.util.Set;

public class CourseDeclineForm {
	 /* COURSE_PARTICIPANT.Id */
    private Long id;

    /* Course Name */
    private String courseName;

    /* Instructor Name (LASTNAME, FIRSTNAME) */
    private String instructorName;

    /* Venue Name */
    private String venueName;

    /* Participant Name (LASTNAME, FIRSTNAME) */
    private String participantName;

    /* Set of course schedule details */
    private Set<CourseScheduleDetailForm> courseScheduleDetailsForm;

    /* Registration Date **/
    private ZonedDateTime registrationDate;

    /* Reason for non-participation */
    private String reason;

    /** COURSE_PARTICIPANT.Id Getter */
    public Long getId() {
        return id;
    }

    /** COURSE_PARTICIPANT.Id Setter */
    public void setId(Long id) {
        this.id = id;
    }

    /** Course Name Getter */
    public String getCourseName() {
        return courseName;
    }

    /** Course Name Setter */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /** Instructor Name (LASTNAME, FIRSTNAME) Getter */
    public String getInstructorName() {
        return instructorName;
    }

    /** Instructor Name (LASTNAME, FIRSTNAME) Setter */
    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    /** Venue Name Getter */
    public String getVenueName() {
        return venueName;
    }

    /** Venue Name Setter */
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    /** Participant Name (LASTNAME, FIRSTNAME) Getter */
    public String getParticipantName() {
        return participantName;
    }

    /** Participant Name (LASTNAME, FIRSTNAME) Setter */
    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    /** Set of course schedule details Getter */
    public Set<CourseScheduleDetailForm> getCourseScheduleDetailsForm() {
        return courseScheduleDetailsForm;
    }

    /** Set of course schedule details Setter */
    public void setCourseScheduleDetailsForm(Set<CourseScheduleDetailForm> courseScheduleDetailsForm) {
        this.courseScheduleDetailsForm = courseScheduleDetailsForm;
    }

    /** Registration Date Getter */
    public ZonedDateTime getRegistrationDate() {
        return registrationDate;
    }

    /** Registration Date Setter */
    public void setRegistrationDate(ZonedDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    /** Reason for non-participation Getter */
    public String getReason() {
        return reason;
    }

    /** Reason for non-participation Setter */
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public String toString() {
        return "CourseScheduleDetailForm [id=" + id + ", courseName=" + courseName + ", instructorName=" + instructorName
        		+ ", venueName=" + venueName + ", participantName=" + participantName + ", courseScheduleDetailsForm=" 
        		+ courseScheduleDetailsForm + ", registrationDate=" + registrationDate + ", reason=" + reason + "]";
    }
}
