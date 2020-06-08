package com.fujitsu.ph.tsup.domain.iwarat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

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
@ActiveProfiles({ "postgres-test-iwarat" })
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
        Course course1st = new Course.Builder(7L, "IT").build();
        courseDao.save(course1st);
        Long id1 = courseDao.GeneratedKeyHolderId();
        System.out.println("ID1:" + id1);

        Course course2nd = new Course.Builder(8L, "ComSci").build();
        courseDao.save(course2nd);
        Long id2 = courseDao.GeneratedKeyHolderId();
        System.out.println("ID1:" + id2);

        Course datababseCourse1st = courseDao.findById(id1);
        assertEquals("IT", datababseCourse1st.getName());

        Course datababseCourse2nd = courseDao.findById(id2);
        assertEquals("ComSci", datababseCourse2nd.getName());

        Set<Course> setCourse = courseDao.findAll();
        assertNotNull(setCourse.size());

    }

    @Test
    void Test_NotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            courseDao.findById(7L);
        });

    }

}
