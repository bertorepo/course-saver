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

import com.fujitsu.ph.tsup.report.summary.model.SummaryGSTDevForm;

/**
 * <pre>
 * The interface of G3CC standardization training for dev service
 * </pre>
 * 
 * @version 0.01
 * @author n.dejesus
 *
 */
public interface SummaryGSTDevService {
	/**
     * <pre>
     * Get Summary of members who completed G3CC Standardization Training
     * </pre>
     * @return SummaryGSTDevForm
     */
	SummaryGSTDevForm getSummary();
}
