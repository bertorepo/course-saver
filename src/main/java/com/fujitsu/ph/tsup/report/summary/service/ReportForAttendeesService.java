//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Reports for members who have not yet finished the mandatory courses
//Class Name   : ReportsForAttendeesService.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2021/06/25 | WS) l.celoso          | Initial Version
//==================================================================================================

package com.fujitsu.ph.tsup.report.summary.service;

import java.util.Set;

import com.fujitsu.ph.tsup.report.summary.model.AttendeeForm;

/**
* <pre>
* The controller for the SummaryGSTPMService
* <pre>
* 
* @version 0.03
* @author m.padaca
* @author d.escala
*/


public interface ReportForAttendeesService {
	
	 /**
     * <pre>
     * Get summary
     * </pre>
     */
	Set<AttendeeForm> getSummary(String jduType);

}
