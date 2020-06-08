package com.fujitsu.ph.tsup.domain.abad;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ActiveProfiles;

@JdbcTest
@ActiveProfiles({"postgres-test-abad"})
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CourseScheduleDaoImplTest {

    @Autowired
    private CourseScheduleDao courseScheduleDao;
    
    @TestConfiguration
    static class TestContextConfiguration {
        
        @Bean
        public CourseScheduleDao courseScheduleDao() {
            return new CourseScheduleDaoImpl();
        }
    }
    
    @Test
    void test() {
        CourseSchedule courseScheduleOne = new CourseSchedule.Builder(1000L, 1000L, 1000L, 1, 1000, "A").build();
        courseScheduleDao.save(courseScheduleOne);
        Long crsSchdlOne = courseScheduleDao.saveCourseSchedule();
        
        CourseSchedule courseScheduleTwo = new CourseSchedule.Builder(2000L, 2000L, 2000L, 1, 999, "B").build();
        courseScheduleDao.save(courseScheduleTwo);
        Long crsSchdlTwo = courseScheduleDao.saveCourseSchedule();
              
        System.out.println("ID1: " + crsSchdlOne);
        CourseSchedule dbCourseScheduleOne = courseScheduleDao.findById(crsSchdlOne);   
        System.out.println("Course Id: "+ dbCourseScheduleOne.getCourseId());
        assertEquals(1000L, dbCourseScheduleOne.getCourseId());
        System.out.println("Instructor Id: "+ dbCourseScheduleOne.getInstructorId());
        assertEquals(1000L, dbCourseScheduleOne.getInstructorId());
        System.out.println("Venue Id: "+ dbCourseScheduleOne.getVenueId());
        assertEquals(1000L, dbCourseScheduleOne.getVenueId());
        System.out.println("Minimum Required: "+ dbCourseScheduleOne.getMinRequired());
        assertEquals(1, dbCourseScheduleOne.getMinRequired());
        System.out.println("Maximum Required: "+ dbCourseScheduleOne.getMaxAllowed());
        assertEquals(1000, dbCourseScheduleOne.getMaxAllowed());
        System.out.println("Status: "+ dbCourseScheduleOne.getStatus());
        assertEquals("A", dbCourseScheduleOne.getStatus());
        
        System.out.println("ID2: " + crsSchdlTwo);
        CourseSchedule dbCourseScheduleTwo = courseScheduleDao.findById(crsSchdlTwo); 
        System.out.println("Course Id: "+ dbCourseScheduleTwo.getCourseId());
        assertEquals(2000L, dbCourseScheduleTwo.getCourseId());
        System.out.println("Instructor Id: "+ dbCourseScheduleTwo.getInstructorId());
        assertEquals(2000L, dbCourseScheduleTwo.getInstructorId());
        System.out.println("Venue Id: "+ dbCourseScheduleTwo.getVenueId());
        assertEquals(2000L, dbCourseScheduleTwo.getVenueId());
        System.out.println("Minimum Required: "+ dbCourseScheduleTwo.getMinRequired());
        assertEquals(1, dbCourseScheduleTwo.getMinRequired());
        System.out.println("Maximum Required: "+ dbCourseScheduleTwo.getMaxAllowed());
        assertEquals(999, dbCourseScheduleTwo.getMaxAllowed());       
        System.out.println("Status: "+ dbCourseScheduleTwo.getStatus());
        assertEquals("B", dbCourseScheduleTwo.getStatus());
        
        Set<CourseSchedule> courseSchedule = courseScheduleDao.findAll();
        assertNotNull(courseSchedule.size());      
    }
    
    @Test
    void NoTestFound() {
        assertThrows(EmptyResultDataAccessException.class, () ->{
            courseScheduleDao.findById(1L);
        });
    }
}
