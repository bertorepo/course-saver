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
public class CourseDaoImplTest {

    @Autowired
    private CourseDao courseDao;
    
    @TestConfiguration
    static class TestContextConfiguration {
        
        @Bean
        public CourseDao courseDao() {
            return new CourseDaoImpl();
        }
    }
    
    @Test
    void test() {
        Course courseOne = new Course.Builder("DIFFCALC").build();
        courseDao.save(courseOne);
        Long crsOne = courseDao.saveCourse();
        System.out.println("ID1: " + crsOne);
        
        Course dbCourseOne = courseDao.findById(crsOne);
        System.out.println("Course Name: " +dbCourseOne.getCourseName());
        assertEquals("DIFFCALC", dbCourseOne.getCourseName());
        
        Course courseTwo = new Course.Builder("INTEGCALC").build();
        courseDao.save(courseTwo);
        Long crsTwo = courseDao.saveCourse();
        System.out.println("ID2: " + crsTwo);
          
        Course dbCourseTwo = courseDao.findById(crsTwo);
        System.out.println("Course Name: "+dbCourseTwo.getCourseName());
        assertEquals("INTEGCALC", dbCourseTwo.getCourseName());
        
        Set<Course> course = courseDao.findAll();
        assertNotNull(course.size());
        
    }
    
    @Test
    void NoTestFound() {
        assertThrows(EmptyResultDataAccessException.class, () ->{
            courseDao.findById(1L);
        });
    }
}

