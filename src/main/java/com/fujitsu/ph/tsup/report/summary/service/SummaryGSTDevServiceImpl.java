//==================================================================================================                                                                                                                                                                            
// Project Name : Training Sign Up
// System Name  : Summary of G3CC Standardization Training for Dev                                                                                                                                                             
// Class Name   : SummaryGSTDevServiceImpl.java                                                                                                                                                                            
//                                                                                                                                                                          
// <<Modification History>>                                                                                                                                                                             
// Version | Date       | Updated By            | Content                                                                                                                                                                           
//---------+------------+-----------------------+---------------------------------------------------                                                                                                                                                                            
// 0.01   | 2021/04/21 | WS)N.DeJesus       | New Creation                                                                                                                                                                                       
// 0.02   | 2021/04/29 | WS)A.Batongbacal   | Update             
//==================================================================================================
package com.fujitsu.ph.tsup.report.summary.service;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fujitsu.ph.tsup.report.summary.dao.SummaryGSTDevDao;
import com.fujitsu.ph.tsup.report.summary.model.SummaryGSTDevForm;


//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Summary of JDU Standardization Training for Dev
//Class Name   : SummaryGSTDevServiceImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | ----/--/-- | WS) n.dejesus         | Initial Version
//0.02    | ----/--/-- | WS) a.batongbacal     | Updated
//0.03    | 2021/06/14 | WS) m.padaca          | Updated
//==================================================================================================


/**
 * <pre>
 * The implementation of G3CC standardization training for dev service
 * </pre>
 * 
 * @version 0.03
 * @author n.dejesus
 * @author a.batongbacal
 * @author m.padaca
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
	public SummaryGSTDevForm getSummary(ZonedDateTime StartDateTime,ZonedDateTime EndDateTime, 
		   SummaryGSTDevForm summaryGSTDev) {
//		return reportSummaryGSTDevDao.getSummary();
	    Set<Long> employeeList = reportSummaryGSTDevDao.findAllJDUDev();
        Set<Long> courseList = reportSummaryGSTDevDao.findAllCoursesByCategoryId();
        HashMap<Integer, Integer> totalCoursePerEmployee = new HashMap<>();
        
        //for JDU Dev who Finished 
        for (long employee_id : employeeList) {
            int total_course_completed = reportSummaryGSTDevDao.findTotalCoursePerEmployee
            (courseList, employee_id, EndDateTime);
            totalCoursePerEmployee.put((int) employee_id, total_course_completed);
        }
        int totalDevFin = (int) totalCoursePerEmployee.entrySet().stream().filter(v -> 
        v.getValue().equals(courseList.size())).count();

        //for JDU Dev who Finished Last Week
        totalCoursePerEmployee = new HashMap<>();
        for (long employee_id : employeeList) {
            int total_course_completed = reportSummaryGSTDevDao.findTotalCoursePerEmployeeLastWeek
            (StartDateTime, EndDateTime, courseList, employee_id);
            totalCoursePerEmployee.put((int) employee_id, total_course_completed);
        }
        int totalDevFinLastWk = (int) totalCoursePerEmployee.entrySet().stream().filter(v -> 
        v.getValue().equals(courseList.size())).count();
        
        double percentageFinToday = (totalDevFin * 100) / employeeList.size();
        double percentageFinLastWk = (totalDevFinLastWk * 100) / employeeList.size();
		double df = Math.pow(10, 2);
		
        summaryGSTDev.setTotalNoJDUDevValue(employeeList.size());
        summaryGSTDev.setTotalNoJDUDevLastWeekValue(reportSummaryGSTDevDao.findAllJDUDevLastWeek());
        summaryGSTDev.setTotalNoExistingMemValue(reportSummaryGSTDevDao.findAllJDUExisitingMembers());
        summaryGSTDev.setTotalNoNewMemValue(reportSummaryGSTDevDao.findAllJDUNewMembers());
        summaryGSTDev.setTotalNoJDUDevFinValue(totalDevFin);
        summaryGSTDev.setTotalNoJDUDevLastWkFinValue(totalDevFinLastWk);
        summaryGSTDev.setPercentageFinTodayValue(Math.round((double)(percentageFinToday)*100*df)/df);
        summaryGSTDev.setPercentageFinLastWkValue(Math.round((double)(percentageFinLastWk)*100*df)/df);
        return summaryGSTDev;
	}
	
}
