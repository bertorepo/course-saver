//==================================================================================================                                                                                                                                                                            
// Project Name : Training Sign Up
// System Name  : Summary of G3CC Standardization Training for Dev                                                                                                                                                             
// Class Name   : SummaryGSTDevService.java                                                                                                                                                                            
//                                                                                                                                                                          
// <<Modification History>>                                                                                                                                                                             
// Version | Date       | Updated By            | Content                                                                                                                                                                           
//---------+------------+-----------------------+---------------------------------------------------                                                                                                                                                                            
// 0.01   | 2021/04/21 | WS)N.DeJesus       | New Creation             
//==================================================================================================
package com.fujitsu.ph.tsup.report.summary.service;

import java.time.ZonedDateTime;

import com.fujitsu.ph.tsup.report.summary.model.SummaryGSTDevForm;


//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Summary of JDU Standardization Training for Dev
//Class Name   : SummaryGSTDevService.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | ----/--/-- | WS) n.dejesus         | Initial Version
//0.02    | 2021/06/14 | WS) m.padaca          | Updated
//==================================================================================================


/**
 * <pre>
 * The interface of G3CC standardization training for dev service
 * </pre>
 * 
 * @version 0.01
 * @author n.dejesus
 * @version 0.02
 * @author m.padaca
 *
 */
public interface SummaryGSTDevService {
	/**
     * <pre>
     * Get Summary of members who completed G3CC Standardization Training
     * </pre>
	 * @param summaryGSTDev 
	 * @param zonedDateTime2 
	 * @param zonedDateTime 
     * @return SummaryGSTDevForm
     */
	
	//Method for accumulating summary data
	SummaryGSTDevForm getSummary(ZonedDateTime StartDateTime,ZonedDateTime EndDateTime, SummaryGSTDevForm summaryGSTDev);
}
