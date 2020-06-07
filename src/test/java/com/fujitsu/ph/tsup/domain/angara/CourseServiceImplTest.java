package com.fujitsu.ph.tsup.domain.angara;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CourseServiceImplTest {

    // two test each to create
    // (1) successful retrieval (2) error/dao throws exceptions

    @TestConfiguration
    static class TestingConfiguration {

        @Bean
        CourseService courseService() {
            return new CourseServiceImpl();
        }
    }

    @Autowired
    private CourseService courseService;

    @MockBean
    private CourseDao courseDao;

    // testing save
    @Test
    public void TestSave() {
        Course c = new Course.Builder("Computer Engineering", (long) 1).build();
        courseService.save(c);
        assertEquals(c.getId(), new Long(1));
        assertEquals(c.getCourseName(), "Computer Engineering");
    }

    @Test
    public void TestSaveError() {
        Course c = new Course.Builder("Computer Engineering", (long) 2).build();
        courseService.save(c);
        assertEquals(c.getId(), new Long(1));
        assertEquals(c.getCourseName(), "IT Digital Arts");
    }

    // testing findAll
    @Test
    public void TestFindAll() {
        Set<Course> c = new HashSet<Course>();
        c.add(new Course.Builder("Computer Engineering", (long) 1).build());
        when(courseDao.findAll()).thenReturn(c);
        assertEquals(courseService.findAll().size(), c.size());
    }

    @Test
    public void TestFindAllError() {
        Set<Course> c = new HashSet<Course>();
        c.add(new Course.Builder("IT Digital Arts", (long) 0).build());
        assertEquals(courseService.findAll().size(), c.size());
    }

    // testing findById

}
