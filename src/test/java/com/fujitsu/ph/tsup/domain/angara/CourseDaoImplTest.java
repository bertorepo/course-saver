package com.fujitsu.ph.tsup.domain.angara;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.rmi.AccessException;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

@JdbcTest
@ActiveProfiles({ "postgres-test-angara" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CourseDaoImplTest {

    @Autowired
    private CourseDao courseDao;

    @TestConfiguration
    static class TestingConfiguration {

        @Bean
        public CourseDao courseDao() {
            return new CourseDaoImpl();
        }
    }

    @Test
    public void test() {
        Course course1 = new Course.Builder("Computer Engineering").build();
        Long c1 = courseDao.save(course1);
        System.out.println("ID1: " + c1);

        Course dbCourse = courseDao.findById(c1);
        assertEquals("Computer Engineering", dbCourse.getCourseName());

        Set<Course> c = courseDao.findAll();
        assertNotNull(c.size());
    }

    @Test
    public void notFound_Test() {
        assertThrows(AccessException.class, () -> {
            courseDao.findById(1L);
        });

    }

}
