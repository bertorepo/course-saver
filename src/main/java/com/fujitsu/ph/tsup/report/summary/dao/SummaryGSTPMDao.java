package com.fujitsu.ph.tsup.report.summary.dao;

import java.time.ZonedDateTime;
import java.util.List;

public interface SummaryGSTPMDao {

	//get Department ID of FDC-G3CC
	public int getDeptId();
	
	//get G3CC category ID();
	public int getCatId();
	
	//get Employee Role ID
	public List<Integer> getEmployeeRoleId();
	
	//get List of ID of courses under GST
	public List<Integer> gstCourses(int catId);
	
	//get Total count of JDU PM
	public int countTotalNumberOfJDUPM(int deptId);
	
	//get Total count of JDU PM Finished
	public int countTotalNumberJDUPMFinished(List<Integer> gstCourses, int deptId,List<Integer> roleId, ZonedDateTime EndDate);
	
	//get Total count of JDU PM Finished Last Week
	public int countTotalNumberJDUPMFinishedLW(ZonedDateTime startDate, ZonedDateTime EndDate, List<Integer> gstCourses,int deptId,List<Integer> roleId);

	
	
}
