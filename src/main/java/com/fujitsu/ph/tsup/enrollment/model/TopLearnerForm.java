package com.fujitsu.ph.tsup.enrollment.model;

//=================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enrollment
//Class Name   :TopLearnerForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+--------------------------------------------------
//0.01    | 08/24/2020 | WS) J.Yu              | New Creation
//=================================================================================================
/**
* <pre>
* JavaBean for TopLearnerForm.java
* <pre>
* 
* @version 0.01
* @author j.yu                       
*/

public class TopLearnerForm {
    
    /* Participant id */
    private long id;
    
    /* Ranking */
    private int place;
    
    /* Participant Name */
    private String participantName;
    
    /** get id */
    public long getId() {
        return id;
    }
    
    /** set id */
    public void setId(long id) {
        this.id = id;
    }
    
    /** get Ranking */
    public int getPlace() {
        return place;
    }
    
    /** set Ranking */
    public void setPlace(int place) {
        this.place = place;
    }
    
    /** get Participant Name */
    public String getParticipantName() {
        return participantName;
    }
    
    /** set Participant Name */
    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    @Override
    public String toString() {
        return "TopLearnersForm [id=" + id + ", place=" + place + ", participantName=" + participantName + "]";
    }
    
}