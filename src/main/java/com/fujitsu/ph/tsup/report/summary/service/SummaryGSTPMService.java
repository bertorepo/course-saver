//Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
package com.fujitsu.ph.tsup.report.summary.service;

import java.time.ZonedDateTime;
import java.util.List;

import com.fujitsu.ph.tsup.report.summary.model.SummaryGSTForm;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Summary of JDU Standardization Training for PM
//Class Name   : SummaryGSTPMService.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2021/04/20 | WS) d.escala          | Initial Version
//0.02    | 2021/04/23 | WS) m.padaca          | Updated
//0.03    | 2021/04/27 | WS) m.padaca          | Updated
//==================================================================================================
/**
* <pre>
* The controller for the SummaryGSTPMService
* <pre>
* 
* @version 0.03
* @author m.padaca
* @author d.escala
*/


public interface SummaryGSTPMService {
	
	 /**
     * <pre>
     * Show summary based on given date
     * </pre>
     */
	
    //Method for count total number of JDU PM
	long countTotalNumberOfJDUPM(int deptId,List<Integer> roleId);
	
    //Method for count total number JDU PM Last Week
	long countTotalNoJDUPMLastWeek();
    
	//Method for count total number original member
	long countTotalNoOrigMem();
	
	//Method for count total number new member
	long countTotalNoNewMem();
    
	//Method for count total number JDU PM final
	long countTotalNoJDUPMF(List<Integer> gstCourses, int deptId, List<Integer> roleId,ZonedDateTime EndDateTime);
	
    //Method for count total number JDU PM last week final
	long countTotalNoJDUPMLastWkF(ZonedDateTime StartDateTime, ZonedDateTime EndDateTime, List<Integer> gstCourses,
			int deptId, List<Integer> roleId);
    
	//Method for accumulating summary data
	SummaryGSTForm getSummary(ZonedDateTime StartDateTime,ZonedDateTime EndDateTime,SummaryGSTForm summaryGSTForm);

}
