package com.fujitsu.ph.tsup.dashboard.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.dashboard.dao.DashboardPmoDao;
import com.fujitsu.ph.tsup.dashboard.domain.DashboardPmoForm;
//==================================================================================================
//$Id:$
//Project Name :Training Sign Up
//System Name  :Dashboard
//Class Name   :DashboardPmoServiceImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/25/2020 |  WS) Jm.Deguzman   | New Creation
//==================================================================================================
/**
* <pre>
* The service implementation of the dashboard, calls the Dao to get the data
* <pre>
* 
* @version 0.01
* @author Jm.Deguzman
*/
@Service
public class DashboardPmoServiceImpl implements DashboardPmoService{
    /*
     * DashboardPmoDao
     */
    @Autowired
    private DashboardPmoDao dao;
    /**
     * Finds the courses that are to be cancelled
     * @return Set<DashboardPmoForm>
     */
    @Override
    public Set<DashboardPmoForm> findCourses(){
        Set<DashboardPmoForm> dashboardPmo = dao.findCourses();
        if (dashboardPmo.isEmpty() || dashboardPmo == null) {
            throw new IllegalArgumentException("No records found");
        } 
        return dashboardPmo;
    }

}