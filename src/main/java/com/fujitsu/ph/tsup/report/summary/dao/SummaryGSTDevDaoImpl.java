//==================================================================================================                                                                                                                                                                            
// Project Name : Training Sign Up
// System Name  : Summary of G3CC Standardization Training for Dev                                                                                                                                                             
// Class Name   : SummaryGSTDevDaoImpl.java                                                                                                                                                                            
//                                                                                                                                                                          
// <<Modification History>>                                                                                                                                                                             
// Version | Date       | Updated By            | Content                                                                                                                                                                           
//---------+------------+-----------------------+---------------------------------------------------                                                                                                                                                                            
// 0.01   | 2021/04/21 | WS)R.Rivero            | New Creation             
//==================================================================================================

package com.fujitsu.ph.tsup.report.summary.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.tsup.report.summary.model.SummaryGSTDevForm;

/**
 * <pre>
 * The implementation of G3CC standardization training for dev dao
 * </pre>
 * 
 * @version 0.01
 * @author r.rivero
 *
 */


@Repository
public class SummaryGSTDevDaoImpl implements SummaryGSTDevDao {

	 // Call NamedParameterJdbcTemplate
    @Autowired
    private NamedParameterJdbcTemplate template;
    
	@Override
	public SummaryGSTDevForm getSummary() {
		
		String query = "SELECT * FROM tsup.summary_gst_dev()";
		
		List<SummaryGSTDevForm> summary = template.query(query, new SummaryGSTDevFormRowMapper());
		
		if (!summary.isEmpty()) {
            return summary.get(0);
        } else {
            return null;
        }
	}
	
	

}
