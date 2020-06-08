package com.fujitsu.ph.tsup.domain.freo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;


@JdbcTest
@ActiveProfiles({"postgres-test-freo"})
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class CourseScheduleDaoImpTest {
	 @Autowired
	    private CourseScheduleDao CourseScheduleDao;
	    
	    @TestConfiguration
	    static class TestContextConfiguration{
	        
	        @Bean
	        public CourseScheduleDao CourseScheduleDao() {
	            return new CourseScheduleDaoImp();
	        }            
	    }
	    
	    @Test
	    void test() {
	        CourseSchedule crsId = new CourseSchedule.Builder(20202L, "0000L", "5555L", 1, 2, 'O').builder();
	        CourseScheduleDao.save(crsId);
	        Long csId = CourseScheduleDao.returnGeneratedKey();
	        System.out.println("ID: "+ csId);
	        
	        CourseSchedule crsId1 = CourseScheduleDao.findById(csId);
	        System.out.println("Course ID: "+ crsId1.getCourseId());
	        assertEquals(20202L, crsId1.getCourseId());
	        System.out.println("Instructor ID: "+ crsId1.getInstructorId());
	        assertEquals(0000L, crsId1.getInstructorId());
	        System.out.println("Venue ID: "+ crsId1.getVenueId());
	        assertEquals(5555L, crsId1.getVenueId());
	        System.out.println("Minimum Required: "+ crsId1.getMinRequired());
	        assertEquals(1, crsId1.getMinRequired());
	        System.out.println("Max Allowed: "+ crsId1.getMaxAllowed());
	        assertEquals(2, crsId1.getMaxAllowed());
	        System.out.println("Status: "+ crsId1.getStatus());
	        assertEquals('O', crsId1.getStatus());
	        
	        
	        CourseSchedule crs2 = new CourseSchedule.Builder(1L, "20202L", "0000L", "5555L", 1, 2, 'O').builder();
	        CourseScheduleDao.save(crs2);
	        
	        Long csId2 = CourseScheduleDao.returnGeneratedKey();
	        System.out.println("ID: "+ csId2);
	        CourseSchedule crsId2 = CourseScheduleDao.findById(csId2);
	        System.out.println("Course ID: "+ crsId2.getCourseId());
	        assertEquals(20202L, crsId2.getCourseId());
	        System.out.println("Instructor ID: "+ crsId2.getInstructorId());
	        assertEquals(0000L, crsId2.getInstructorId());
	        System.out.println("Venue ID: "+ crsId2.getVenueId());
	        assertEquals(5555L, crsId2.getVenueId());
	        System.out.println("Minimum Required: "+ crsId2.getMinRequired());
	        assertEquals(1, crsId2.getMinRequired());
	        System.out.println("Max Allowed: "+ crsId2.getMaxAllowed());
	        assertEquals(2, crsId2.getMaxAllowed());
	        System.out.println("Status: "+ crsId2.getStatus());
	        assertEquals('O', crsId2.getStatus());
	        Set<CourseSchedule> crseSchedSet = CourseScheduleDao.findAll();
	        assertNotNull(crseSchedSet.size());
	       
	    }
	    
	    @Test
	    void Test_NotFound() {
	        assertThrows(EmptyResultDataAccessException.class, () -> {
	            CourseScheduleDao.findById(1L);
	        });
	    }
}
