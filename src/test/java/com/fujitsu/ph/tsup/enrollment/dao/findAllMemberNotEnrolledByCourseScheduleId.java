package com.fujitsu.ph.tsup.enrollment.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;
import com.fujitsu.ph.tsup.enrollment.model.SearchForm;
@JdbcTest
@ActiveProfiles({ "postgres" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
class findAllMemberNotEnrolledByCourseScheduleId {

	 @Autowired
	    private EnrollmentDao enrollmentDao;
	    
	    @TestConfiguration
	    static class TestContextConfiguration {
	        
	        @Bean
	        public EnrollmentDao enrollmentDao() {
	            return new EnrollmentDaoImpl();
	        }
	    }
	    
	    /**
	     * Test case for findAllScheduledCourses method
	     * 
	     * @author J.Yu
	     */
	    @Test
	    void testfindAllMemberENotEnrolledByCourseScheduleId() {
	    	CourseParticipant courseParticipant = new CourseParticipant.Builder().addCourseScheduleIdAndEmployeeNumber(1L, "220053694").build();
	        Set<CourseParticipant> courseParticipantSet = enrollmentDao.findAllMemberNotEnrolledByCourseScheduleId(courseParticipant);
	        for(CourseParticipant courseParticipants : courseParticipantSet) {
	        	System.out.println("\n(TEST)Employee Number: " + courseParticipants.getEmployeeNumber());
	        	System.out.println("(TEST)Employee Name: " + courseParticipants.getParticipantName());
	        	System.out.println("(TEST)Employee Email: " + courseParticipants.getEmail() +"\n");
	        	assertNotNull(courseParticipants); 
	        }
	    }
	    
	    @Test
	    void testFindMemberNotEnrolledByCourseScheduleId() {
	    	SearchForm searchForm = new SearchForm();
	    	searchForm.setCourseScheduleId(1L);
	    	searchForm.setEmployeeNumber("220053694");
	    	searchForm.setSearch("t");
	    	Set<CourseParticipant> courseParticipantSet = enrollmentDao.findMemberNotEnrolledByCourseScheduleId(searchForm);
	        System.out.println("CourseParticipantSize:" + courseParticipantSet.size());
	    	for(CourseParticipant courseParticipant: courseParticipantSet) {
	    		System.out.println("\nEmployeeNumber:" +courseParticipant.getEmployeeNumber());
	    		System.out.println("Employee Name: " + courseParticipant.getParticipantName());
	    		System.out.println("Employee Email Address: " + courseParticipant.getEmail() + "\n");
	    	}
	    }
}
