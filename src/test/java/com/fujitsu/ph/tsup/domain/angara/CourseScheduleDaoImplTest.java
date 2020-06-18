package com.fujitsu.ph.tsup.domain.angara;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.AccessException;
import org.springframework.test.context.ActiveProfiles;

@JdbcTest
@ActiveProfiles({ "postgres-test-angara" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CourseScheduleDaoImplTest {

    @Autowired
    private CourseScheduleDao courseScheduleDao;

    @TestConfiguration
    static class TestingConfiguration {

        @Bean
        public CourseScheduleDao courseScheduleDao() {
            return new CourseScheduleDaoImpl();
        }
    }

    @Test
    public void test() {
        CourseSchedule cs1 = new CourseSchedule.Builder((long) 1, (long) 1, (long) 1, 100, 1, "OK").build();
         Long c = courseScheduleDao.save(cs1);
        System.out.println("ID1:" + c);

        CourseSchedule dbCourseSchedule = courseScheduleDao.findById(c);
        // assertEquals((long) 1, dbCourseSchedule.getCourseId());
        assertEquals((long) 1, dbCourseSchedule.getInstructorId());
         // assertEquals((long) 1, dbCourseSchedule.getVenueId());
        assertEquals(100, dbCourseSchedule.getMaxRequired());
        assertEquals(1, dbCourseSchedule.getMinRequired());
        assertEquals("OK", dbCourseSchedule.getStatus());

        Set<CourseSchedule> cs = courseScheduleDao.findAll();
        assertNotNull(cs.size());
    }

    @Test
    void notFound_Test() {
        assertThrows(AccessException.class, () -> {
            courseScheduleDao.findById(1L);
        });

    }
}
