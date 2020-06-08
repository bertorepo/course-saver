package com.fujitsu.ph.tsup.domain.ramos;
import static org.junit.Assert.assertEquals;

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
@ActiveProfiles({"postgres-test-ramos"})
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
        CourseSchedule courseSchedule1 = new CourseSchedule.Builder(1L, 2L, 3L, 15, 1000, "A").build();
        courseScheduleDao.save(courseSchedule1);
        Long csched1 = courseScheduleDao.save(courseSchedule1);
        
        CourseSchedule courseSchedule2 = new CourseSchedule.Builder(2L, 3L, 4L, 25, 500, "B").build();
        courseScheduleDao.save(courseSchedule2);
        Long csched2 = courseScheduleDao.save(courseSchedule2);
        
        CourseSchedule dbCourseSchedule1 = courseScheduleDao.findById(csched1);       

    }
}
