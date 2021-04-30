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

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
//		return reportSummaryGSTDevDao.getSummary();
	    Set<Long> employeeList = reportSummaryGSTDevDao.findAllJDUDev();
        Set<Long> courseList = reportSummaryGSTDevDao.findAllCourseByCategoryId();
        HashMap<Integer, Integer> totalCoursePerEmployee = new HashMap<>();
        
        //for JDU Dev who Finished 
        for (long employee_id : employeeList) {
            int total_course_completed = reportSummaryGSTDevDao.findTotalCoursePerEmployee(courseList, employee_id);
            totalCoursePerEmployee.put((int) employee_id, total_course_completed);
        }
        int totalDevFin = (int) totalCoursePerEmployee.entrySet().stream().filter(v -> v.getValue().equals(courseList.size())).count();

        //for JDU Dev who Finished Last Week
        totalCoursePerEmployee = new HashMap<>();
        for (long employee_id : employeeList) {
            int total_course_completed = reportSummaryGSTDevDao.findTotalCoursePerEmployeeLastWeek(courseList, employee_id);
            totalCoursePerEmployee.put((int) employee_id, total_course_completed);
        }
        int totalDevFinLastWk = (int) totalCoursePerEmployee.entrySet().stream().filter(v -> v.getValue().equals(courseList.size())).count();

        int percentageFinToday = (totalDevFin * 100) / employeeList.size();
        int percentageFinLastWk = (totalDevFinLastWk * 100) / employeeList.size();
        
        SummaryGSTDevForm summary = new SummaryGSTDevForm();
        summary.setTotalNoJDUDevValue(employeeList.size());
        summary.setTotalNoJDUDevLastWeekValue(reportSummaryGSTDevDao.findAllJDUDevLastWeek());
        summary.setTotalNoOrigMemValue(reportSummaryGSTDevDao.findAllJDUOriginalMembers());
        summary.setTotalNoNewMemValue(reportSummaryGSTDevDao.findAllJDUNewMembers());
        summary.setTotalNoJDUDevFinValue(totalDevFin);
        summary.setTotalNoJDUDevLastWkFinValue(totalDevFinLastWk);
        summary.setPercentageFinTodayValue(percentageFinToday);
        summary.setPercentageFinLastWkValue(percentageFinLastWk);
        
        
        return summary;
	}
	
}
