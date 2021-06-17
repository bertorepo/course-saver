package com.fujitsu.ph.tsup.enrollment.model;

//==================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enrollment
//Class Name   :CourseScheduleListForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/23/2020 | WS) M. Lumontad       | New Creation
//0.02    | 09/09/2020 | WS) J.Yu              | Update
//0.03    | 06/14/2021 | WS) L.Celoso          | Update
//0.03    | 06/16/2021 | WS) K.Sevilla         | Update
//==================================================================================================
/**
* <pre>
* JavaBean for CourseScheduleListForm.java
* <pre>
* 
* @version 0.01
* @author m.lumontad                          
*/
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;

public class CourseScheduleListForm {
    /* From Date and Time */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime fromDateTime;

    /* To Date and Time */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime toDateTime;

    /* Set of Course Schedule Form */
    private Set<CourseScheduleForm> courseSchedules;
    
    /* List of Top Learner Form by Month */
    private List<TopLearnerForm> topLearnerByMonth;
    
    /* List of Top Learner Form by Quarter */
    private List<TopLearnerForm> topLearnerByQuarter;

    /** Course From Date Getter */
    public ZonedDateTime getFromDateTime() {
        return fromDateTime;
    }

    /** Course From Date Setter */
    public void setFromDateTime(ZonedDateTime fromDateTime) {
        this.fromDateTime = fromDateTime;
    }

    /** Course To Date Getter */
    public ZonedDateTime getToDateTime() {
        return toDateTime;
    }

    /** Course To Date Setter */
    public void setToDateTime(ZonedDateTime toDateTime) {
        this.toDateTime = toDateTime;
    }

    /** Course Schedule Set Getter */
    public Set<CourseScheduleForm> getCourseSchedules() {
        return courseSchedules;
    }

    /** Course Schedule Set Setter */
    public void setCourseSchedules(Set<CourseScheduleForm> courseSchedules) {
        this.courseSchedules = courseSchedules;
    }
    
    /** List of Top Learner Form by Month Getter */
    public List<TopLearnerForm> getTopLearnerByMonth() {
        return topLearnerByMonth;
    }
    
    /** List of Top Learner Form by Month Setter */
    public void setTopLearnerByMonth(List<TopLearnerForm> topLearnerByMonth) {
        this.topLearnerByMonth = topLearnerByMonth;
    }
    
    /** List of Top Learner Form by Quarter Getter */
    public List<TopLearnerForm> getTopLearnerByQuarter() {
        return topLearnerByQuarter;
    }

    /** List of Top Learner Form by Quarter Setter */
    public void setTopLearnerByQuarter(List<TopLearnerForm> topLearnerByQuarter) {
        this.topLearnerByQuarter = topLearnerByQuarter;
    }

    @Override
    public String toString() {
        return "CourseScheduleListForm [fromDateTime=" + fromDateTime + ", toDateTime=" + toDateTime
                + ", courseSchedules=" + courseSchedules + ", topLearnerByMonth=" + topLearnerByMonth
                + ", topLearnerByQuarter=" + topLearnerByQuarter + "]";
    }   
}