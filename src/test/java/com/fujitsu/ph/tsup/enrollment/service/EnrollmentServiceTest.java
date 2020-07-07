package com.fujitsu.ph.tsup.enrollment.service;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fujitsu.ph.tsup.domain.oviedo.ApplicationException;
import com.fujitsu.ph.tsup.enrollment.dao.EnrollmentDao;
import com.fujitsu.ph.tsup.enrollment.domain.CourseSchedule;
//@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace=Replace.NONE) 
class EnrollmentServiceTest {
	
	private static final Logger log = LoggerFactory.getLogger(EnrollmentServiceTest.class);
	
	@TestConfiguration
	static class EnrollmentServiceImplestContextConfiguration {
		@Bean
		EnrollmentService enrollmentService() {
			return new EnrollmentServiceImpl();
			
		}
	}

	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@MockBean
	private EnrollmentDao enrollmentDao;

	
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    
    @Test
    void CancelTest() {
    	CourseSchedule courseSched = createCourseSchedule();
		when(enrollmentDao.findCourseScheduleById(any(Long.class))).thenReturn(courseSched);
		enrollmentService.cancel(courseSched.getId());
		CourseSchedule courseSchedule = enrollmentService.findCourseScheduleById(courseSched.getId());
	   	//assertNull(courseSched.getStatus());
	    assertEquals("Test Cancel Status 'C':",'A',courseSchedule.getStatus());//Didn't Cancel
//	    assertEquals("Test Course Name: ",courseSchedule.getCourseName() ,"PeerReview");

    }
    
    @Test
    void findAllScheduledCoursesTest() {
    	Set<CourseSchedule> courseSchedSet = createSetCourseSchedule();
    	when(enrollmentDao.findAllScheduledCourses(any(ZonedDateTime.class), any(ZonedDateTime.class))).thenReturn(courseSchedSet);
    	Set<CourseSchedule>courseScheduleSet = enrollmentService.findAllScheduledCourses(ZonedDateTime.now(), ZonedDateTime.now().plusDays(5));
    	for(CourseSchedule coursesched : courseScheduleSet) {
    		System.out.println();
    		System.out.println();
    		System.out.println();
    		System.out.println();
    		System.out.println();
    		System.out.println();
    	}
    	
    	assertEquals("Course Schedule Set Count: ",courseScheduleSet.size() ,3);
    
    }
    
    @Test
    void invalidFindScheduleCoursesTest() {
    	when(enrollmentDao.findAllScheduledCourses(any(ZonedDateTime.class), any(ZonedDateTime.class))).thenThrow(new IllegalArgumentException("CourseSchedule did found"));
    	Exception exception = assertThrows(IllegalArgumentException.class,() ->{
    		enrollmentService.findAllScheduledCourses(ZonedDateTime.now(), ZonedDateTime.now().plusDays(5));
    	});
    }
    
    private CourseSchedule createCourseSchedule() {
		return new CourseSchedule.Builder(10L, 10L, "PeerReview", 10L, "DeGuzman", "Gene", 10L, "EcoTower", 10, 20, 15, 'A').build();
    	
    }
    private Set<CourseSchedule> createSetCourseSchedule(){
    	Set<CourseSchedule> css = new HashSet<>();
    	
    	CourseSchedule courseSchedule1 = new CourseSchedule.Builder(1L, 1L, "Peer Review", 1L, "De Guzman", "Gene", 1L, "Eco Tower", 10, 20, 15, 'A').build();
    	CourseSchedule courseSchedule2 = new CourseSchedule.Builder(2L, 2L, "Understanding UI", 2L, "De Guzman", "Gene", 2L, "Eco Tower", 30, 50, 45, 'A').build();
    	CourseSchedule courseSchedule3 = new CourseSchedule.Builder(2L, 2L, "Understanding SS", 2L, "De Leon", "John Carlo", 2L, "Eco Tower", 30, 50, 45, 'A').build();
    	css.add(courseSchedule1);
    	css.add(courseSchedule2);
    	css.add(courseSchedule3);
    	return css;
    }
}
