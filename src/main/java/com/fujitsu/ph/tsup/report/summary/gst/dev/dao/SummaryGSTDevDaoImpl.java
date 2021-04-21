package com.fujitsu.ph.tsup.report.summary.gst.dev.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.tsup.course.category.dao.CourseCategoryRowMapper;
import com.fujitsu.ph.tsup.course.category.model.CourseCategory;
import com.fujitsu.ph.tsup.report.summary.gst.dev.model.SummaryGSTDevForm;

@Repository
public class SummaryGSTDevDaoImpl implements SummaryGSTDevDao {

	 // Call NamedParameterJdbcTemplate
    @Autowired
    private NamedParameterJdbcTemplate template;
    
	@Override
	public SummaryGSTDevForm getSummary() {
		
		String query = "SELECT * FROM tsup.summary_gst_dev()";
		
		List<SummaryGSTDevForm> summary = template.query(query, new SummaryGSTDevFormRowMapper());
		
		return summary.get(0);
	}
	
	

}
