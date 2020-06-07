package com.fujitsu.ph.tsup.domain.francisco;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CourseServiceImplTest {

    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        CourseService courseService() {
            return new CourseServiceImpl();
        }
    }

    @Autowired
    private CourseService courseService;

    @MockBean
    private CourseDao courseDao;

    @Test
    void testSave() {
        Course course = new Course.Builder(new Long(1), "course1").build();
        assertEquals(course.getId(), new Long(1));
        assertEquals(course.getCourseName(), "course1");
    }

    @Test
    void testSaveError() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Course.Builder(new Long(1), "").build();
        });

        String expectedMessage = "course name should not be null or empty!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testFindAll() {
        Set<Course> course = new HashSet<Course>();
        course.add(new Course.Builder(new Long(1), "course1").build());
        course.add(new Course.Builder(new Long(2), "course2").build());
        when(courseDao.findAll()).thenReturn(course);
        assertEquals(courseService.findAll().size(), course.size());
    }

    @Test
    void testFindAllError() {
        when(courseDao.findAll()).thenThrow(new IllegalArgumentException("Application Error!"));
        Exception exception = assertThrows(ApplicationException.class, () -> {
            courseService.findAll().size();
        });

        String expectedMessage = "Application Error!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testFindById() {
        Course c = new Course.Builder(new Long(1), "course1").build();
        when(courseDao.findById(c.getId())).thenReturn(c);
        Course course = courseService.findById(c.getId());
        assertEquals(c.getId(), course.getId());
        assertEquals(c.getCourseName(), course.getCourseName());
    }
    
    @Test
    void testFindByIdError () {
        when(courseDao.findById(any(Long.class))).thenThrow(new IllegalArgumentException("Application Error!"));
        Exception exception = assertThrows(ApplicationException.class, () -> {
            courseService.findById(any(Long.class));
        });
        
        String expectedMessage = "Application Error!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
