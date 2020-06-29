package com.fujitsu.ph.tsup.dashboard.service;

import java.util.Set;

import com.fujitsu.ph.tsup.dashboard.domain.DashboardMemberForm;
//==================================================================================================
//$Id:$
//Project Name :Training Sign Up
//System Name  :Dashboard
//Class Name   :DashboardMemberService.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/23/2020 |  WS) Jm.Deguzman   | New Creation
//==================================================================================================
/**
* <pre>
* The service interface for dashboard related service
* <pre>
* 
* @version 0.01
* @author Jm.Deguzman
*/
public interface DashboardMemberService {
    /**
     * Gets the count of courses/trainings for the day
     * @param employeeId
     * @return integer
     */
    int getTrainingsToday(Long employeeId);
    /**
     * Gets the count of active courses/trainings that the user confirmed attendance to
     * @param employeeId
     * @return integer
     */
    int getActiveCourses(Long employeeId);
    /**
     * Find the list of courses that the user haven't enrolled to
     * @param employeeId
     * @return Set<DashboardMemberForm>
     */
    Set<DashboardMemberForm> findCourses(Long employeeId);
}