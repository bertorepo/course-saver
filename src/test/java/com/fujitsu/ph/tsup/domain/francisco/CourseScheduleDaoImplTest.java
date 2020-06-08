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
public class CourseScheduleDaoImplTest {
    
    @Autowired
    private CourseScheduleDao courseSchedDao;

    @TestConfiguration
    static class TestContextConfiguration {
        
        @Bean
        public CourseScheduleDao courseSchedDao() {
            return new CourseScheduleDaoImpl();
        }
    }
    
    @Test
    void test() {
        CourseSchedule courseSched = new CourseSchedule.Builder(new Long(1), new Long(1), new Long(1), new Long(1), 1, 5, "a").build();
        courseSchedDao.save(courseSched);
        
        CourseSchedule dbCourse = courseSchedDao.findById(courseSched.getId());
        assertEquals(new Long(1), dbCourse.getCourseId());
        assertEquals(new Long(1), dbCourse.getInstructorId());
        assertEquals(new Long(1), dbCourse.getVenueId());
        assertEquals(1, dbCourse.getMinRequired());
        assertEquals(5, dbCourse.getMaxAllowed());
        assertEquals("a", dbCourse.getStatus());
    }
    
    @Test
    void test_NotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            courseSchedDao.findById(6L);
        });
        
    }

}
