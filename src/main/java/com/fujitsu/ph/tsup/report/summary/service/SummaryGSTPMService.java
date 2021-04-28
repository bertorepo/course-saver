package com.fujitsu.ph.tsup.report.summary.service;

import java.time.ZonedDateTime;
import java.util.List;

import com.fujitsu.ph.tsup.report.summary.model.SummaryGSTForm;


public interface SummaryGSTPMService {
	
	long countTotalNumberOfJDUPM(int deptId);

	long countTotalNoJDUPMLastWeek();

	long countTotalNoOrigMem();

	long countTotalNoNewMem();

	long countTotalNoJDUPMF(List<Integer> gstCourses, int deptId, List<Integer> roleId,ZonedDateTime EndDateTime);
	
	long countTotalNoJDUPMLastWkF(ZonedDateTime StartDateTime, ZonedDateTime EndDateTime, List<Integer> gstCourses,
			int deptId, List<Integer> roleId);

	SummaryGSTForm getSummary(ZonedDateTime StartDateTime,ZonedDateTime EndDateTime,SummaryGSTForm summaryGSTForm);

}
