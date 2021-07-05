package com.fujitsu.ph.tsup.course.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fujitsu.ph.tsup.course.dao.CourseManagementDao;
import com.fujitsu.ph.tsup.course.model.Course;
import com.fujitsu.ph.tsup.exception.TsupException;


//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Course Management
//Class Name   : CourseManagementServiceTest.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2021/04/27 | WS) i.fajardo         | Initial Version
//0.02    | 05/10/2021 | WS) D.Escala          | Updated
//0.03	  | 06/09/2021 | WS) M.Aguinaldo       | Fix and update the test
//==================================================================================================
@ExtendWith(SpringExtension.class)
public class CreateCourseTest {

	/**
     * CourseManagementDao as mock object
     */
    @MockBean
    private CourseManagementDao courseManagementDao;
    
    /**
     * CourseManagementService as dependency
     */
    @Autowired
    private CourseManagementService courseManagementService;
	
	/**
     * Test Configuration
     */
	@TestConfiguration
    static class TestContextConfiguration {
        
		/**
    	 * CourseManagementService
    	 * @return CourseManagementServiceImpl
    	 */
        @Bean
        public CourseManagementService courseManagementDao() {
        	return new CourseManagementServiceImpl();
        }
    }
	
    /**
     * <pre>
     * testCreateCourse with Null and Valid Value
     * Call courseManagementDao.createCourse and test if values are save and return error message if null values are save
     * <pre>
     */
    @Test
    @Rollback(false)
    void testCreateCourse() {
        doThrow(new DataRetrievalFailureException("error")).when(courseManagementDao).createCourse(null);
        
        Course course = createCourse();
        courseManagementService.createCourse(course);
        
        assertEquals(course.getName(), "New Data");
        assertEquals(course.getDetail(), "New Data Detail");
        assertEquals(course.getIsMandatory(), "Yes");
        assertEquals(course.getDeadline(), "Immediate");
		assertEquals(course.getCourseCategoryId(), 3);
    }
    
    /**
     * <pre>
     * testCreateCourse with Error Message
     * Call courseManagementDao.createCourse and test if error message is return
     * <pre>
     */
    @Test
    @Rollback(false)
    void testCreateCourseSchedule_Error() {
        doThrow(new DataRetrievalFailureException("error")).when(courseManagementDao).createCourse(any(Course.class));
        
        Course course = createCourse();
        
        Exception courseException = assertThrows(TsupException.class, () 
                -> courseManagementService.createCourse(course));
        
        String expectedMessage = "Can't create new course";
        String actualMessage = courseException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
	
    /**
     * <pre>
     * Build data for Create Course
     * <pre>
     */
    private Course createCourse() {
	return Course.builder()
		     .withName("New Data")
		     .withDetail("New Data Detail")
		     .withIsMandatory("Yes")
		     .withDeadline("Immediate")
		     .withCourseCategoryId(3L)
		     .build();
    }
}