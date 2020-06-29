package com.fujitsu.ph.tsup.dashboard.dao;

import java.util.Set;

import com.fujitsu.ph.tsup.dashboard.domain.DashboardMemberForm;
//==================================================================================================
//$Id:$
//Project Name :Training Sign Up
//System Name  :Dashboard
//Class Name   :DashboardMemberDao.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/23/2020 |  WS) Jm.Deguzman   | New Creation
//==================================================================================================
/**
* <pre>
* The data access interface for dashboard related database access
* <pre>
* 
* @version 0.01
* @author Jm.Deguzman
*/
public interface DashboardMemberDao {
    /**
     * Gets the count of courses/trainings for the day
     * @param employeeId
     * @return integer
     */
    int getTrainingsToday(Long employeeId);
    /**
     * Gets the count of active courses/trainings that the user confirmed their attendance
     * @param employeeId
     * @return integer
     */
    int getActiveCourses(Long employeeId);
    /**
     * Finds the courses that the users haven't enrolled to
     * @param employeeId
     * @return Set<dashboardMemberForm>
     */
    Set<DashboardMemberForm> findCourses(Long employeeId);
}
