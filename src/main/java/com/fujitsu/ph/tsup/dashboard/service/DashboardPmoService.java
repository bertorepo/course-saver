package com.fujitsu.ph.tsup.dashboard.service;

import java.util.Set;

import com.fujitsu.ph.tsup.dashboard.domain.DashboardPmoForm;
//==================================================================================================
//$Id:$
//Project Name :Training Sign Up
//System Name  :Dashboard
//Class Name   :DashboardPmoService.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/25/2020 |  WS) Jm.Deguzman   | New Creation
//==================================================================================================
/**
* <pre>
* The service interface for dashboard related service
* <pre>
* 
* @version 0.01
* @author Jm.Deguzman
*/
public interface DashboardPmoService {
    /**
     * Finds the courses to be cancelled
     * 
     * @return Set<DashbcoardPmoForm>
     */
    Set<DashboardPmoForm> findCourses();
}
