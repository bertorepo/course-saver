//Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
package com.fujitsu.ph.tsup.report.summary.service;

import java.time.ZonedDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.report.summary.dao.SummaryGSTPMDao;
import com.fujitsu.ph.tsup.report.summary.model.SummaryGSTForm;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Summary of JDU Standardization Training for PM
//Class Name   : SummaryGSTPMServiceImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2021/04/20 | WS) d.escala          | Initial Version
//0.02    | 2021/04/23 | WS) m.padaca          | Updated
//0.03    | 2021/04/27 | WS) m.padaca          | Updated
//0.04    | 2021/04/28 | WS) d.escala          | Updated
//==================================================================================================
/**
* <pre>
* The controller for the SummaryGSTPMServiceImpl
* <pre>
* 
* @version 0.04
* @author m.padaca
* @author d.escala
*/

@Service
public class SummaryGSTPMServiceImpl implements SummaryGSTPMService   {

	@Autowired
	private SummaryGSTPMDao summaryGSTPMDao;

	@Override
	public long countTotalNumberOfJDUPM(int deptId, List<Integer> roleId) {
		return summaryGSTPMDao.countTotalNumberOfJDUPM(deptId,roleId);
		
	}

	@Override
	public long countTotalNoJDUPMLastWeek() {
		return 0;
	}

	@Override
	public long countTotalNoOrigMem() {
		return 0;
	}

	@Override
	public long countTotalNoNewMem() {
		return 0;
	}

	@Override
	public long countTotalNoJDUPMF(List<Integer> gstCourses,int deptId,List<Integer> roleId,ZonedDateTime EndDateTime) {
		return summaryGSTPMDao.countTotalNumberJDUPMFinished(gstCourses, deptId, roleId,EndDateTime);
	}

	@Override
	public long countTotalNoJDUPMLastWkF(ZonedDateTime StartDateTime, 
			ZonedDateTime EndDateTime ,List<Integer> gstCourses,int deptId,List<Integer> roleId ) {
		return summaryGSTPMDao.countTotalNumberJDUPMFinishedLW(StartDateTime, EndDateTime, gstCourses, deptId, roleId);
	}

	@Override
	public SummaryGSTForm getSummary(ZonedDateTime StartDateTime,ZonedDateTime EndDateTime,SummaryGSTForm summaryGSTForm) {
		
		double df = Math.pow(10, 2);
		List<Integer> gstCourses= summaryGSTPMDao.gstCourses(summaryGSTPMDao.getCatId());
	    List<Integer> roleId= summaryGSTPMDao.getEmployeeRoleId();
	    int deptId= summaryGSTPMDao.getDeptId();
		
	    summaryGSTForm.setTotalNoJDUPMValue(countTotalNumberOfJDUPM(deptId,roleId));
	    summaryGSTForm.setTotalNoJDUPMLastWeekValue(countTotalNoJDUPMLastWeek());
	    summaryGSTForm.setTotalNoOrigMemValue(countTotalNoOrigMem());
	    summaryGSTForm.setTotalNoNewMemValue(countTotalNoNewMem());
		summaryGSTForm.setTotalNoJDUPMLastWkFinValue(countTotalNoJDUPMLastWkF(StartDateTime,EndDateTime,gstCourses,deptId,roleId));
		summaryGSTForm.setTotalNoJDUPMFinValue(countTotalNoJDUPMF(gstCourses,deptId,roleId,EndDateTime));
		summaryGSTForm.setPercentageFinTodayValue(Math.round((double)countTotalNoJDUPMF(gstCourses,deptId,roleId,EndDateTime)/countTotalNumberOfJDUPM(deptId,roleId)*100*df)/df);
		summaryGSTForm.setPercentageFinLastWkValue(Math.round((double)countTotalNoJDUPMLastWkF(StartDateTime,EndDateTime,gstCourses,deptId,roleId)/countTotalNumberOfJDUPM(deptId,roleId)*100*df)/df);
		
		return summaryGSTForm;
	}




}
