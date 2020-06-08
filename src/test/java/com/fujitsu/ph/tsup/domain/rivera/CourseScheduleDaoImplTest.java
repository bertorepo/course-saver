package com.fujitsu.ph.tsup.domain.rivera;

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
@ActiveProfiles({"postgres-test-rivera"})
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
        CourseSchedule courseScheduleOne = new CourseSchedule.Builder(1000L, 1000L, 1000L, 1, 1000, "One").build();
        courseScheduleDao.save(courseScheduleOne);
        Long courseSchedOne = courseScheduleDao.saveCourseSchedule();
        
        CourseSchedule courseScheduleTwo = new CourseSchedule.Builder(2000L, 2000L, 2000L, 1, 500, "Two").build();
        courseScheduleDao.save(courseScheduleTwo);
        Long courseSchedTwo = courseScheduleDao.saveCourseSchedule();
              
        System.out.println("ID1: " + courseSchedOne);
        CourseSchedule dbCourseScheduleOne = courseScheduleDao.findById(courseSchedOne);   
        assertEquals(1000L, dbCourseScheduleOne.getCourseId());
        assertEquals(1000L, dbCourseScheduleOne.getInstructorId());
        assertEquals(1000L, dbCourseScheduleOne.getVenueId());
        assertEquals(1, dbCourseScheduleOne.getMinRequired());
        assertEquals(1000, dbCourseScheduleOne.getMaxAllowed());
        assertEquals("One", dbCourseScheduleOne.getStatus());
        
        System.out.println("ID2: " + courseSchedTwo);
        CourseSchedule dbCourseScheduleTwo = courseScheduleDao.findById(courseSchedTwo); 
        assertEquals(2000L, dbCourseScheduleTwo.getCourseId());
        assertEquals(2000L, dbCourseScheduleTwo.getInstructorId());
        assertEquals(2000L, dbCourseScheduleTwo.getVenueId());
        assertEquals(1, dbCourseScheduleTwo.getMinRequired());
        assertEquals(500, dbCourseScheduleTwo.getMaxAllowed());       
        assertEquals("Two", dbCourseScheduleTwo.getStatus());
        
        Set<CourseSchedule> courseScheduleAll = courseScheduleDao.findAll();
        assertNotNull(courseScheduleAll.size());      
    }
    
    @Test
    void NoTestFound() {
        assertThrows(EmptyResultDataAccessException.class, () ->{
            courseScheduleDao.findById(1L);
        });
    }
}
