//==================================================================================================                                                                                                                                                                            
// Project Name : Training Sign Up
// System Name  : Summary of G3CC Standardization Training for Dev                                                                                                                                                
// Class Name   : SummaryGSTDevFormRowMapper.java                                                                                                                                                                            
//                                                                                                                                                                          
// <<Modification History>>                                                                                                                                                                             
// Version | Date       | Updated By            | Content                                                                                                                                                                           
//---------+------------+-----------------------+---------------------------------------------------                                                                                                                                                                            
// 0.01   | 2021/04/21 | WS)N.DeJesus       | New Creation             
//==================================================================================================
/**
* <pre>
* The controller for CoursesConductedRowMapper
* </pre>
* 
* @version 0.01
* @author n.dejesus
*
*/
package com.fujitsu.ph.tsup.report.summary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.report.summary.model.SummaryGSTDevForm;

public class SummaryGSTDevFormRowMapper implements RowMapper<SummaryGSTDevForm> {
	/**
	    * <pre>
	    * Maps the Rows returned by ResultSet
	    * </pre>
	    * @param ResultSet rs
	    * @param int rowNum
	    * @throws SQLException
	    */
		@Override
			public SummaryGSTDevForm mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				long totalNoJDUDev = rs.getInt("totalNoJDUDev");
				long totalNoJDUDevLastWeek = rs.getInt("totalNoJDUDevLastWeek");
				long totalNoOrigMem = rs.getInt("totalNoOrigMem");
				long totalNoNewMem = rs.getInt("totalNoNewMem");
				long totalNoJDUDevFin = rs.getInt("totalNoJDUDevFin");
				long totalNoJDUDevLastWkFin = rs.getInt("totalNoJDUDevLastWkFin");
				long percentageFinToday = rs.getInt("percentageFinToday");
				long percentageFinLastWk = rs.getInt("percentageFinLastWk");
				
				SummaryGSTDevForm summary = new SummaryGSTDevForm();
				summary.setTotalNoJDUDevValue(totalNoJDUDev);
				summary.setTotalNoJDUDevLastWeekValue(totalNoJDUDevLastWeek);
				summary.setTotalNoOrigMemValue(totalNoOrigMem);
				summary.setTotalNoNewMemValue(totalNoNewMem);
				summary.setTotalNoJDUDevFinValue(totalNoJDUDevFin);
				summary.setTotalNoJDUDevLastWkFinValue(totalNoJDUDevLastWkFin);
				summary.setPercentageFinTodayValue(percentageFinToday);
				summary.setPercentageFinLastWkValue(percentageFinLastWk);
				
				return summary;
			}

}
