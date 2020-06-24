package com.fujitsu.ph.tsup.attendance.model;

import java.util.Set;

/**
 * <pre>
 * It is a JavaBean for Change Status Form
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
//Class Name   :ChangeStatusForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/22/2020 | WS) h.francisco       | New Creation
public class ChangeStatusForm {
    /**
     * 
     */
    private Long id;
    /**
     * 
     */
    private Set<ChangeStatusCourse> courses;
    /**
     * 
     */
    private Set<ChangeStatusParticipant> participants;

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
    public Set<ChangeStatusCourse> getCourses() {
        return courses;
    }

    /**
     * @param courses
     */
    public void setCourses(Set<ChangeStatusCourse> courses) {
        this.courses = courses;
    }

    /**
     * @return
     */
    public Set<ChangeStatusParticipant> getParticipants() {
        return participants;
    }

    /**
     * @param participants
     */
    public void setParticipants(Set<ChangeStatusParticipant> participants) {
        this.participants = participants;
    }
    
    @Override
    public String toString() {
        return "ChangeStatusForm [id = " + id + ", courses = " + courses + ", participants = " + participants + "]";
    }
}
