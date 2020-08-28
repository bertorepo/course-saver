package com.fujitsu.ph.tsup.dashboard.dao;

import java.util.Set;

import com.fujitsu.ph.tsup.dashboard.domain.DashboardPmo;
//==================================================================================================
//$Id:PR06$
//Project Name :Training Sign Up
//System Name  :Dashboard
//Class Name   :DashboardPmoDao.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/25/2020 |  WS) Jm.Deguzman   | New Creation
//0.02 | 08/24/2020 |  WS) Jm.Deguzman   | Updated
//==================================================================================================
/**
* <pre>
* The data access interface for dashboard related database access
* <pre>
* 
* @version 0.01
* @author Jm.Deguzman
*/
public interface DashboardPmoDao {
    /**
     * Finds the courses that may be cancelled
     * @return Set<dashboardPmoForm>
     */
    Set<DashboardPmo> findCourses();
}
