package com.fujitsu.ph.tsup.domain.deguzman;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.sql.DataSource;

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
@ActiveProfiles({"postgres-test-deguzman"})
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CourseScheduleDaoImplTest {

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
        CourseSchedule courseSchedule1 = new CourseSchedule.Builder((long) 1, (long) 1, (long) 1, 1, 100, "A").build();
        Long c1 = courseScheduleDao.saveLong(courseSchedule1);
        System.out.println("ID1:" + c1);

        CourseSchedule courseSchedule2 = new CourseSchedule.Builder((long) 2, (long) 2, (long) 2, 2, 200, "C").build();
        Long c2 = courseScheduleDao.saveLong(courseSchedule2);
        System.out.println("ID2:" + c2);

        CourseSchedule dbCourseSchedule1 = courseScheduleDao.findById(c1);
        assertEquals((long) 1, dbCourseSchedule1.getCourseId());
        assertEquals((long) 1, dbCourseSchedule1.getInstructorId());
        assertEquals((long) 1, dbCourseSchedule1.getVenueId());
        assertEquals(1, dbCourseSchedule1.getMinRequired());
        assertEquals(100, dbCourseSchedule1.getMaxAllowed());
        assertEquals("A", dbCourseSchedule1.getStatus());
        
        CourseSchedule dbCourseSchedule2 = courseScheduleDao.findById(c2);
        assertEquals((long) 2, dbCourseSchedule2.getCourseId());
        assertEquals((long) 2, dbCourseSchedule2.getInstructorId());
        assertEquals((long) 2, dbCourseSchedule2.getVenueId());
        assertEquals(2, dbCourseSchedule2.getMinRequired());
        assertEquals(200, dbCourseSchedule2.getMaxAllowed());
        assertEquals("C", dbCourseSchedule2.getStatus());
        
        Set<CourseSchedule> cs = courseScheduleDao.findAll();
        assertNotNull(cs.size());
    }

    @Test
    void test_NotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            courseScheduleDao.findById(1L);
        });

    }

}
