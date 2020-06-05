package com.fujitsu.ph.tsup.domain.iwarat;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
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
    static class CourseServiceImplTestContextConfiguration {

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

        doNothing().when(courseDao).save(any(Course.class));

    }

    @Test
    void testSaveError() {
        Course saveC = createCourse();

        doThrow(new IllegalArgumentException("error")).when(courseDao).save(any(Course.class));
        Exception exception = assertThrows(MyException.class, () -> {
            courseService.save(saveC);
        });

        String expectedMessage = "Error found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    void testFindById() {
        Course expected = createCourse();
        when(courseDao.findById(any(Long.class))).thenReturn(expected);

        Course courses = courseService.findById(7L);

        assertEquals(expected.getId(), courses.getId());
        assertEquals(expected.getName(), courses.getName());
    }

    @Test
    void testFindByIdError() {
        when(courseDao.findById(any(Long.class))).thenThrow(new IllegalArgumentException("error"));
        Exception exception = assertThrows(MyException.class, () -> {
            courseService.findById(7L);
        });

        String expectedMessage = "Error found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testFindAll() {
        Set<Course> Option = new HashSet<Course>();
        Option.add(new Course.Builder(7L, "IT").build());
        Option.add(new Course.Builder(6L, "ComSci").build());

        when(courseDao.findAll()).thenReturn(Option);
        assertEquals(2, Option.size());

    }

    @Test
    void testFindAllError() {
        when(courseDao.findAll()).thenThrow(new IllegalArgumentException("error"));
        Exception exception = assertThrows(MyException.class, () -> {
            courseService.findAll().size();
        });

        String expectedMessage = "Error found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    private Course createCourse() {
        return new Course.Builder(7L, "IT").build();
    }

}
