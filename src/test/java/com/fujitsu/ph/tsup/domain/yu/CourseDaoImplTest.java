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
        Course course1 = new Course.Builder("SpringBootMVC").build();
        courseDao.save(course1);
        Long id1 = courseDao.generatedKey();
        System.out.println("ID1:" + id1);

        Course course2 = new Course.Builder("SpringBoot").build();
        courseDao.save(course2);
        Long id2 = courseDao.generatedKey();
        System.out.println("ID1:" + id2);

        Course courseDb1 = courseDao.findById(id1);
        assertEquals("SpringBootMVC", courseDb1.getName());

        Course courseDb2 = courseDao.findById(id2);
        assertEquals("SpringBoot", courseDb2.getName());

        Set<Course> setCourse = courseDao.findAll();
        assertNotNull(setCourse.size());
    }
    
    @Test
    void TestNotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            courseDao.findById(1L);
        });
    }

}
