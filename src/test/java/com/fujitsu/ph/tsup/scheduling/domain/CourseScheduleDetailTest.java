package com.fujitsu.ph.tsup.scheduling.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

public class CourseScheduleDetailTest {
    
    @Test
    void testValid_CourseScheduleDetail() {
        CourseScheduleDetail courseScheduleDetail = 
                new CourseScheduleDetail.Builder(1L, 1L, ZonedDateTime.parse("2016-11-16T17:21:00Z"),
                        ZonedDateTime.parse("2016-11-16T17:21:00Z").plusHours(5)).build();
        
        assertNotNull(courseScheduleDetail.getId());
        assertEquals(1L, courseScheduleDetail.getId());
        assertNotNull(courseScheduleDetail.getCourseScheduleId());
        assertEquals(1L, courseScheduleDetail.getCourseScheduleId());
        assertNotNull(courseScheduleDetail.getScheduledStartDateTime());
        assertEquals(ZonedDateTime.parse("2016-11-16T17:21:00Z"), courseScheduleDetail.getScheduledStartDateTime());
        assertNotNull(courseScheduleDetail.getScheduledEndDateTime());
        assertEquals(ZonedDateTime.parse("2016-11-16T17:21:00Z").plusHours(5), courseScheduleDetail.getScheduledEndDateTime());
    }
    
    @Test
    void testInvalidId_isNull() {
        
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseScheduleDetail.Builder(null, 1L, ZonedDateTime.now(), ZonedDateTime.now().plusHours(5)).build();
        });
        assertTrue(error.getMessage().equals("Id should not be empty"));
    }
    
    @Test
    void testInvalidId_isZero() {
        
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseScheduleDetail.Builder(0L, 1L, ZonedDateTime.now(), ZonedDateTime.now().plusHours(5)).build();
        });
        assertTrue(error.getMessage().equals("Id should not be empty"));
    }
    
    @Test
    void testInvalidCourseScheduleId_isNull() {
        
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseScheduleDetail.Builder(1L, null, ZonedDateTime.now(), ZonedDateTime.now().plusHours(5)).build();
        });
        assertTrue(error.getMessage().equals("Course Schedule Id should not be empty"));
    }
    
    @Test
    void testInvalidCourseScheduleId_isZero() {
        
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseScheduleDetail.Builder(1L, 0L, ZonedDateTime.now(), ZonedDateTime.now().plusHours(5)).build();
        });
        assertTrue(error.getMessage().equals("Course Schedule Id should not be empty"));
    }
    
    @Test
    void testInvalidScheduledStartDateTime_isNull() {
        
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseScheduleDetail.Builder(1L, 1L, null, ZonedDateTime.now().plusHours(5)).build();
        });
        assertTrue(error.getMessage().equals("Scheduled Start Date and Time should not be empty"));
    }
    
    @Test
    void testInvalidScheduledEndDateTime_isNull() {
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseScheduleDetail.Builder(1L, 1L, ZonedDateTime.now(), null).build();
        });
        assertTrue(error.getMessage().equals("Scheduled End Date and Time should not be empty"));
    }
    
    @Test
    void testInvalidScheduledEndDateTime_isBefore() {
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseScheduleDetail.Builder(1L, 1L, ZonedDateTime.now(), ZonedDateTime.now().minusDays(5)).build();
        });
        assertTrue(error.getMessage().equals("Scheduled end date and time should be greater than or " + 
                                       "equal to the the scheduled start date and time"));
    }
}
