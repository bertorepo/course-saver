package com.fujitsu.ph.tsup.domain.francisco;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
@ActiveProfiles({"postgres-test-francisco"})
@AutoConfigureTestDatabase(replace=Replace.NONE) 
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
        Course course = new Course.Builder(new Long(1), "courseA").build();
        courseDao.save(course);
        
        Course dbCourse = courseDao.findById(course.getId());
        assertEquals("courseA", dbCourse.getCourseName());
    }
    
    @Test
    void test_NotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            courseDao.findById(6L);
        });
        
    }

}
