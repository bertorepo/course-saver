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
public class CourseScheduleServiceImplTest {

    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        CourseScheduleService courseScheduleService() {
            return new CourseScheduleServiceImpl();
        }
    }

    @Autowired
    private CourseScheduleService service;

    @MockBean
    private CourseScheduleDao dao;

    @Test
    void testSave() {
        CourseSchedule cSched = new CourseSchedule.Builder(new Long(1), new Long(1), new Long(1), 1, 5, "a").build();
        assertEquals(cSched.getCourseId(), new Long(1));
        assertEquals(cSched.getStatus(), "a");
    }

    @Test
    void testSaveError() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(new Long(1), new Long(1), new Long(1), 1, 5, "").build();
        });

        String expectedMessage = "status should not be null or empty!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testFindAll() {
        Set<CourseSchedule> cSched = new HashSet<CourseSchedule>();
        cSched.add(new CourseSchedule.Builder(new Long(1),new Long(1),new Long(1),1,5, "a").build());
        cSched.add(new CourseSchedule.Builder(new Long(2),new Long(2),new Long(2),1,5, "a").build());
        when(dao.findAll()).thenReturn(cSched);
        assertEquals(service.findAll().size(), cSched.size());
    }

    @Test
    void testFindAllError() {
        when(dao.findAll()).thenThrow(new IllegalArgumentException("Application Error!"));
        Exception exception = assertThrows(ApplicationException.class, () -> {
            service.findAll().size();
        });

        String expectedMessage = "Application Error!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testFindById() {
        CourseSchedule c = new CourseSchedule.Builder(new Long(1),new Long(1),new Long(1),1,5, "a").build();
        when(dao.findById(c.getId())).thenReturn(c);
        CourseSchedule cSched = service.findById(c.getId());
        assertEquals(c.getId(), cSched.getId());
        assertEquals(c.getCourseId(), cSched.getCourseId());
        assertEquals(c.getInstructorId(), cSched.getInstructorId());
        assertEquals(c.getMinRequired(), cSched.getMinRequired());
        assertEquals(c.getMaxAllowed(), cSched.getMaxAllowed());
        assertEquals(c.getStatus(), cSched.getStatus());
    }

    @Test
    void testFindByIdError() {
        when(dao.findById(any(Long.class))).thenThrow(new IllegalArgumentException("Application Error!"));
        Exception exception = assertThrows(ApplicationException.class, () -> {
            service.findById(any(Long.class));
        });

        String expectedMessage = "Application Error!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
