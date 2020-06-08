package com.fujitsu.ph.tsup.domain.ramos;
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
@ActiveProfiles({ "postgres-test-ramos" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CourseDaoImplTest {

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

        Course course1 = new Course.Builder("AAAAA").build();
        Long id1 = courseDao.saveLong(course1);
        System.out.println("ID1:" + id1);
        
        Course course2 = new Course.Builder("BBBBB").build();
        Long id2 = courseDao.saveLong(course2);
        System.out.println("ID2:" + id2);

        Course dbCourse1 = courseDao.findById(id1);
        assertEquals("AAAAAAAAAA", dbCourse1.getCourseName());

        Course dbCourse2 = courseDao.findById(id2);
        assertEquals("BBBBBBBBBB", dbCourse2.getCourseName());

        Set<Course> c = courseDao.findAll();
        assertNotNull(c.size());

    }

    @Test
    void test_NotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            courseDao.findById(1L);
        });

    }

}
