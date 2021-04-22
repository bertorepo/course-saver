package com.fujitsu.ph.tsup.report.summary.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.tsup.report.summary.model.SummaryGSTDevForm;


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
