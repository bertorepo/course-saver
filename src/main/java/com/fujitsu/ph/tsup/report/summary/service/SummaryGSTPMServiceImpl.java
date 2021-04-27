package com.fujitsu.ph.tsup.report.summary.service;

import java.time.ZonedDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.report.summary.dao.SummaryGSTPMDao;



@Service
public class SummaryGSTPMServiceImpl implements SummaryGSTPMService   {

	@Autowired
	private SummaryGSTPMDao summaryGSTPMDao;

	@Override
	public long percentageFinishedToday() {
		
		return (countTotalNoJDUPMF()/countTotalNumberOfJDUPM())*100;
	}

	@Override
	public long percentageFinishedLastWeek(ZonedDateTime StartDateTime, 
			ZonedDateTime EndDateTime ) {
		
		return (countTotalNoJDUPMLastWkF(StartDateTime, EndDateTime)/countTotalNumberOfJDUPM())*100;
	}

	@Override
	public long countTotalNumberOfJDUPM() {
		int deptId= summaryGSTPMDao.getDeptId();
		return summaryGSTPMDao.countTotalNumberOfJDUPM(deptId);
		
	}

	@Override
	public long countTotalNoJDUPMLastWeek() {
		return 0;
	}

	@Override
	public long countTotalNoOrigMem() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long countTotalNoNewMem() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long countTotalNoJDUPMF() {
		int catId= summaryGSTPMDao.getCatId();
		List<Integer> gstCourse= summaryGSTPMDao.gstCourses(catId);
		int deptId= summaryGSTPMDao.getDeptId();
		List<Integer> roleId= summaryGSTPMDao.getEmployeeRoleId();
		return summaryGSTPMDao.countTotalNumberJDUPMFinished(gstCourse, deptId, roleId);
	}

	@Override
	public long countTotalNoJDUPMLastWkF(ZonedDateTime StartDateTime, 
			ZonedDateTime EndDateTime ) {
		
		
		int catId= summaryGSTPMDao.getCatId();
		List<Integer> gstCourses= summaryGSTPMDao.gstCourses(catId);
		int deptId= summaryGSTPMDao.getDeptId();
		List<Integer> roleId= summaryGSTPMDao.getEmployeeRoleId();
		return summaryGSTPMDao.countTotalNumberJDUPMFinishedLW(StartDateTime, EndDateTime, gstCourses, deptId, roleId);
	}




}
