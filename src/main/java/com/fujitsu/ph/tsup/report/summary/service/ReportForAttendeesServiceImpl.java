//==================================================================================================                                                                                                                                                                            
// Project Name : Training Sign Up
// System Name  : Reports for members who have not yet finished the mandatory courses                                                                                                                                                          
// Class Name   : ReportsForAttendeesServiceImpl.java                                                                                                                                                                            
//                                                                                                                                                                          
// <<Modification History>>                                                                                                                                                                             
// Version | Date       | Updated By            | Content                                                                                                                                                                           
//---------+------------+-----------------------+---------------------------------------------------                                                                                                                                                                            
// 0.01    | 2021/06/25  | WS)L.Celoso         	| New Creation        
//==================================================================================================
package com.fujitsu.ph.tsup.report.summary.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.report.summary.dao.ReportForAttendeesDao;
import com.fujitsu.ph.tsup.report.summary.model.Attendee;
import com.fujitsu.ph.tsup.report.summary.model.AttendeeForm;

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
public class ReportForAttendeesServiceImpl implements ReportForAttendeesService {

     //Reports for members who have not yet finished the mandatory courses                                                                                                                                                 
	@Autowired
	private ReportForAttendeesDao reportForNonAttendees;
	/**
     * <pre>
     * Get Summary
     * </pre> 
     * @return SummaryGSTDevForm
     */
	@Override
	public Set<AttendeeForm> getSummary(String mandatoryType) {

		Long jduId = reportForNonAttendees.getJDUType();
		Set<Attendee> attendanceList = reportForNonAttendees.findAllNonAttendees(mandatoryType, jduId);
	    Set<Attendee> employeeList = reportForNonAttendees.findEmployees(mandatoryType, jduId);
	    Set<Attendee> mandatoryCourses = reportForNonAttendees.findMandatoryAllCourses(mandatoryType, jduId);
	    
	    Set<AttendeeForm> nonAttendeeForm = new LinkedHashSet<AttendeeForm>();
	    AttendeeForm nonAttendeeTemp = new AttendeeForm();

	    Set<Attendee> filtered = new HashSet<Attendee>();
	    
		List<String> courseName = new ArrayList<String>();
		List<String> courseAttendance = new ArrayList<String>();
	    
	    for (Attendee employee : employeeList) {	    		

		    nonAttendeeTemp.setId(employee.getId());
    		nonAttendeeTemp.setEmployeeName(employee.getEmployeeName());
    		
    		courseName = new ArrayList<String>();
    		courseAttendance = new ArrayList<String>();
    		
		    for (Attendee course : mandatoryCourses) {
		    	
		    	courseName.add(course.getCourseName());
		    	
		    	filtered = attendanceList.stream()
                        .filter(mc -> mc.getEmployeeId().equals(employee.getEmployeeId()))
                        .filter(mc -> mc.getEmployeeName().equals(employee.getEmployeeName()))
                        .filter(mc -> mc.getId().equals(course.getId()))
                        .filter(mc -> mc.getCourseName().equals(course.getCourseName()))
                        .collect(Collectors.toSet());
		    	
	            if(filtered.isEmpty() || filtered.equals(null)){
	            	courseAttendance.add("O");
	            } else {
	            	courseAttendance.add("X");
	            }
	        }
		    
		    nonAttendeeTemp.setCourseName(courseName);
		    nonAttendeeTemp.setAttendanceStatus(courseAttendance);
		    
		    nonAttendeeForm.add(nonAttendeeTemp);
    		nonAttendeeTemp = new AttendeeForm();
	    }
	    
        return nonAttendeeForm;
	}
	
}
