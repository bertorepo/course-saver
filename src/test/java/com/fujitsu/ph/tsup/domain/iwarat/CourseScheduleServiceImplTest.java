package com.fujitsu.ph.tsup.domain.iwarat;

import static org.junit.Assert.assertTrue;
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
public class CourseScheduleServiceImplTest {

    @TestConfiguration
    static class CourseScheduleServiceImplTestContextConfiguration {

        @Bean
        CourseScheduleService courseSchedukeService() {
            return new CourseScheduleServiceImpl();
        }
    }

    @Autowired
    private CourseScheduleService courseScheduleService;

    @MockBean
    private CourseScheduleDao courseScheduleDao;

    @Test
    void testSave() {

        doNothing().when(courseScheduleDao).save(any(CourseSchedule.class));

    }

    @Test
    void testSaveError() {
        CourseSchedule saveSC = createCourseSchedule();

        doThrow(new IllegalArgumentException("error")).when(courseScheduleDao).save(any(CourseSchedule.class));
        Exception exception = assertThrows(MyException.class, () -> {
            courseScheduleService.save(saveSC);
        });

        String expectedMessage = "Error found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testFindById() {
        CourseSchedule expected = createCourseSchedule();
        when(courseScheduleDao.findById(any(Long.class))).thenReturn(expected);

        CourseSchedule courseSchedules = courseScheduleService.findById(7L);

        assertEquals(expected.getId(), courseSchedules.getId());
        assertEquals(expected.getCourseId(), courseSchedules.getCourseId());
        assertEquals(expected.getInstructorId(), courseSchedules.getInstructorId());
        assertEquals(expected.getVenueId(), courseSchedules.getVenueId());
        assertEquals(expected.getMinRecquired(), courseSchedules.getMinRecquired());
        assertEquals(expected.getMaxRecquired(), courseSchedules.getMaxRecquired());
        assertEquals(expected.getStatus(), courseSchedules.getStatus());

    }

    @Test
    void testFindByIdError() {
        when(courseScheduleDao.findById(any(Long.class))).thenThrow(new IllegalArgumentException("error"));
        Exception exception = assertThrows(MyException.class, () -> {
            courseScheduleService.findById(7L);
        });

        String expectedMessage = "Error found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testFindAll() {
        Set<CourseSchedule> Option = new HashSet<CourseSchedule>();
        Option.add(new CourseSchedule.Builder(7L, 8L, 9L, 10L, 5, 9999, "IT").build());
        Option.add(new CourseSchedule.Builder(7L, 12L, 19L, 10L, 13, 28, "ComSci").build());
        Option.add(new CourseSchedule.Builder(7L, 1L, 4L, 3L, 10, 13, "ComSci").build());

        when(courseScheduleDao.findAll()).thenReturn(Option);
        assertEquals(3, Option.size());

    }

    @Test
    void testFindAllError() {
        when(courseScheduleDao.findAll()).thenThrow(new IllegalArgumentException("error"));
        Exception exception = assertThrows(MyException.class, () -> {
            courseScheduleService.findAll().size();
        });

        String expectedMessage = "Error found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    private CourseSchedule createCourseSchedule() {
        return new CourseSchedule.Builder(7L, 8L, 9L, 10L, 5, 9999, "IT").build();
    }

}
