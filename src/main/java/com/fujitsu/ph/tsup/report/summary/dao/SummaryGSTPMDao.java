//Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
package com.fujitsu.ph.tsup.report.summary.dao;

import java.time.ZonedDateTime;
import java.util.List;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Summary of JDU Standardization Training for PM
//Class Name   : SummaryGSTPMDao.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2021/04/20 | WS) d.escala          | Initial Version
//0.02    | 2021/04/23 | WS) d.escala          | Updated
//0.03    | 2021/04/27 | WS) d.escala          | Updated
//0.04    | 2021/04/28 | WS) d.escala          | Updated
//==================================================================================================
/**
* <pre>
* The controller for the SummaryGSTPMDao
* <pre>
* 
* @version 0.04
* @author d.escala
*/

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
	public int countTotalNumberOfJDUPM(int deptId,List<Integer> roleID);
	
	//get Total count of JDU PM Finished
	public int countTotalNumberJDUPMFinished(List<Integer> gstCourses, int deptId,List<Integer> roleId, ZonedDateTime EndDate);
	
	//get Total count of JDU PM Finished Last Week
	public int countTotalNumberJDUPMFinishedLW(ZonedDateTime startDate, ZonedDateTime EndDate, List<Integer> gstCourses,int deptId,List<Integer> roleId);

	
	
}
