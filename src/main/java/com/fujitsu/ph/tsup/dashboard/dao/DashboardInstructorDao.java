package com.fujitsu.ph.tsup.dashboard.dao;

import java.util.Set;

import com.fujitsu.ph.tsup.dashboard.domain.DashboardInstructorForm;
//==================================================================================================
//$Id:$
//Project Name :Training Sign Up
//System Name  :Dashboard
//Class Name   :DashboardInstructorDao.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/24/2020 |  WS) Jm.Deguzman   | New Creation
//==================================================================================================
/**
* <pre>
* The data access interface for dashboard related database access
* <pre>
* 
* @version 0.01
* @author Jm.Deguzman
*/
public interface DashboardInstructorDao {
    /**
     * Gets the count of courses/trainings for the day
     * @param employeeId
     * @return integer
     */
    int getCoursesToday(Long employeeId);
    /**
     * Finds the courses for the day
     * @param employeeId
     * @return Set<DashboardInstructorForm>
     */
    Set<DashboardInstructorForm> findCourses(Long employeeId);
}
