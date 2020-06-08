package com.fujitsu.ph.tsup.domain.lumontad;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

@JdbcTest
@ActiveProfiles({ "postgres-test-lumontad" })
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

        Course course1 = new Course.Builder(null, "AAAAAAAAAA").build();
        Long c1 = courseDao.saveLong(course1);
        System.out.println("ID1:" + c1);

        Course course2 = new Course.Builder(c1, "BBBBBBBBBB").build();
        Long c2 = courseDao.saveLong(course2);
        System.out.println("ID2:" + c2);

        Course dbCourse1 = courseDao.findById(c1);
        assertEquals("AAAAAAAAAA", dbCourse1.getName());

        Course dbCourse2 = courseDao.findById(c2);
        assertEquals("BBBBBBBBBB", dbCourse2.getName());

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
