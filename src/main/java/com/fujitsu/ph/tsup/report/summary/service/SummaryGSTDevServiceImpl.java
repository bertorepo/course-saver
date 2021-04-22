//==================================================================================================                                                                                                                                                                            
// Project Name : Training Sign Up
// System Name  : Summary of G3CC Standardization Training for Dev                                                                                                                                                             
// Class Name   : SummaryGSTDevServiceImpl.java                                                                                                                                                                            
//                                                                                                                                                                          
// <<Modification History>>                                                                                                                                                                             
// Version | Date       | Updated By            | Content                                                                                                                                                                           
//---------+------------+-----------------------+---------------------------------------------------                                                                                                                                                                            
// 0.01   | 2021/04/21 | WS)N.DeJesus       | New Creation             
//==================================================================================================
package com.fujitsu.ph.tsup.report.summary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.report.summary.dao.SummaryGSTDevDao;
import com.fujitsu.ph.tsup.report.summary.model.SummaryGSTDevForm;
/**
 * <pre>
 * The implementation of G3CC standardization training for dev service
 * </pre>
 * 
 * @version 0.01
 * @author n.dejesus
 *
 */
@Service
public class SummaryGSTDevServiceImpl implements SummaryGSTDevService {

     //Report Summary G3CC Standardization Dev Dao
	@Autowired
	private SummaryGSTDevDao reportSummaryGSTDevDao;
	/**
     * <pre>
     * Find total number of JDU members
     * </pre> 
     * @return SummaryGSTDevForm
     */
	@Override
	public SummaryGSTDevForm getSummary() {
		return reportSummaryGSTDevDao.getSummary();
	}
	
}
