package com.fujitsu.ph.tsup.dashboard.service;

import java.util.Set;

import com.fujitsu.ph.tsup.dashboard.domain.DashboardInstructor;
//==================================================================================================
//$Id:PR06$
//Project Name :Training Sign Up
//System Name  :Dashboard
//Class Name   :DashboardInstructorService.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/24/2020 |  WS) Jm.Deguzman   | New Creation
//0.02 | 08/24/2020 |  WS) Jm.Deguzman   | Updated
//==================================================================================================
/**
* <pre>
* The service interface for dashboard related service
* <pre>
* 
* @version 0.01
* @author Jm.Deguzman
*/
public interface DashboardInstructorService {
    /**
     * Gets the count of courses/trainings for the day
     * @param employeeId
     * @return integer
     */
    int getCoursesToday(Long employeeId);
    /**
     * Finds the courses assigned to instructor
     * @param employeeId
     * @return Set<DashboardInstructorForm>
     */
    Set<DashboardInstructor> findCourses(Long employeeId);
}
