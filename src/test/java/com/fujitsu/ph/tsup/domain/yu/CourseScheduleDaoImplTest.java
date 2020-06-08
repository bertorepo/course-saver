package com.fujitsu.ph.tsup.domain.yu;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
@ActiveProfiles({ "postgres-test-yu" })
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
        CourseSchedule courseSchedule1 = new CourseSchedule.Builder((long) 1, (long) 1, (long) 1, 1, 1, "A").build();
        courseScheduleDao.save(courseSchedule1);
        Long id1 = courseScheduleDao.generatedKey();
        System.out.println("ID:" + id1);
        
        CourseSchedule courseSchedule2 = new CourseSchedule.Builder((long) 2, (long) 2, (long) 2, 2, 2, "C").build();
        courseScheduleDao.save(courseSchedule2);
        Long id2 = courseScheduleDao.generatedKey();
        System.out.println("ID:" + id2);
        
        CourseSchedule courseScheduleDb1 = courseScheduleDao.findById(id1);
        assertEquals(1, courseScheduleDb1.getCourseId());
        assertEquals(1, courseScheduleDb1.getInstructorId());
        assertEquals(1, courseScheduleDb1.getVenueId());
        assertEquals(1, courseScheduleDb1.getMinRequired());
        assertEquals(1, courseScheduleDb1.getMaxAllowed());
        assertEquals("A", courseScheduleDb1.getStatus());
        
        CourseSchedule courseScheduleDb2 = courseScheduleDao.findById(id2);
        assertEquals(2, courseScheduleDb2.getCourseId());
        assertEquals(2, courseScheduleDb2.getInstructorId());
        assertEquals(2, courseScheduleDb2.getVenueId());
        assertEquals(2, courseScheduleDb2.getMinRequired());
        assertEquals(2, courseScheduleDb2.getMaxAllowed());
        assertEquals("C", courseScheduleDb2.getStatus());
        
        Set<CourseSchedule> setCourseSchedule = courseScheduleDao.findAll();
        assertNotNull(setCourseSchedule.size());
    }
    
    @Test
    void TestNotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            courseScheduleDao.findById(1L);
        });
    }

}
