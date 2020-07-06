package com.fujitsu.ph.tsup.scheduling.domain;

import static org.junit.Assert.*;

import java.time.ZonedDateTime;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;

public class courseScheduleTest {

    @Test
    void testCourseScheduleValid() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A').build();

        assertNotNull(CourseSched.getId());
        assertNotNull(CourseSched.getCourseId());
        assertNotNull(CourseSched.getCourseName());
        assertNotNull(CourseSched.getInstructorId());
        assertNotNull(CourseSched.getInstructorLastName());
        assertNotNull(CourseSched.getInstructorFirstName());
        assertNotNull(CourseSched.getVenueId());
        assertNotNull(CourseSched.getVenueName());
        assertNotNull(CourseSched.getMinRequired());
        assertNotNull(CourseSched.getMaxAllowed());
        assertNotNull(CourseSched.getStatus());

    }

    @Test
    void testValidId() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A').build();
        assertEquals(CourseSched.getId(), 1L);
    }

    @Test
    void testValidCourseId() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A').build();
        assertEquals(CourseSched.getCourseId(), 1L);
    }

    @Test
    void testValidCourseName() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A').build();
        assertEquals(CourseSched.getCourseName(), "JUnit");
    }

    @Test
    void testValidInstructorId() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A').build();
        assertEquals(CourseSched.getInstructorId(), 1L);
    }

    @Test
    void testValidInstructorLastName() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A').build();
        assertEquals(CourseSched.getInstructorLastName(), "Lorenzo");
    }

    @Test
    void testValidInstructorFirstName() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A').build();
        assertEquals(CourseSched.getInstructorFirstName(), "Loyce");
    }

    @Test
    void testValidVenueId() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A').build();
        assertEquals(CourseSched.getVenueId(), 1L);
    }

    @Test
    void testVenueName() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A').build();
        assertEquals(CourseSched.getVenueName(), "Two/Neo");
    }

    @Test
    void testValidMinRequired() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A').build();
        assertEquals(CourseSched.getMinRequired(), 1);
    }

    @Test
    void testValidMaxAllowed() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A').build();
        assertEquals(CourseSched.getMaxAllowed(), 1);
    }

    @Test
    void testValidStatus() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A').build();
        assertEquals(CourseSched.getStatus(), 'A');
    }

    @Test
    void testInvalidId_isNull() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(null, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo", 1, 1, 'A').build();
        });
        assertTrue(error.getMessage().equals("Id should not be empty"));
    }

    @Test
    void testInvalidCourseId_isNull() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, null, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo", 1, 1, 'A').build();
        });
        assertTrue(error.getMessage().equals("Course ID should not be empty"));
    }
    
    @Test
    void testInvalidCourseName_isNull() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, null, 1L, "Lorenzo", "Loyce", 1L, "Two/Neo", 1, 1, 'A').build();
        });
        assertTrue(error.getMessage().equals("Course ID should not be empty"));
    }

    @Test
    void testInvalidInstructorId_isNull() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", null, "Lorenzo", "Loyce", 1L, "Two/Neo", 1, 1, 'A').build();
        });
        assertTrue(error.getMessage().equals("Instructor Id should not be empty"));
    }

    @Test
    void testInvalidInstructorLastName_isNull() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, null, "Loyce", 1L, "Two/Neo", 1, 1, 'A').build();
        });
        assertTrue(error.getMessage().equals("Instructor First Name should not be empty"));
    }

    @Test
    void testInstructorFirstName_isNull() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", null, 1L, "Two/Neo", 1, 1, 'A').build();
        });
        assertTrue(error.getMessage().equals("Instructor Last Name should not be empty"));
    }

    @Test
    void testInvalidVenueId_isNull() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", null, "Two/Neo", 1, 1, 'A').build();
        });
        assertTrue(error.getMessage().equals("Venue Id should not be empty"));
    }

    @Test
    void testInvalidVenueName_isNull() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, null, 1, 1, 'A').build();
        });
        assertTrue(error.getMessage().equals("Venue Name should not be empty"));
    }

    @Test
    void testInvalidMinRequired_isNull() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo", 0, 1, 'A').build();
        });
        assertTrue(error.getMessage().equals("Minimum required participants should not be empty"));
    }

    @Test
    void testInvalidMaxAllowed_isNull() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L,1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo", 1, 0, 'A').build();
        });
        assertTrue(error.getMessage().equals("Minimum required participants should not be empty"));
    }

    @Test
    void testInvalidStatus_isNull() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo", 1, 1, '\0').build();
        });
        assertTrue(error.getMessage().equals("Status should not be empty"));
    }
    
    @Test
    void testInvalidId_isZero() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(0L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo", 1, 1, 'A').build();
        });
        assertTrue(error.getMessage().equals("Id should not be empty"));
    }

    @Test
    void testInvalidCourseId_isZero() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 0L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo", 1, 1, 'A').build();
        });
        assertTrue(error.getMessage().equals("Course ID should not be empty"));
    }
    
    @Test
    void testInvalidCourseName_isZero() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 0L, "0", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo", 1, 1, 'A').build();
        });
        assertTrue(error.getMessage().equals("Course ID should not be empty"));
    }

    @Test
    void testInvalidInstructorId_isZero() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 0L, "Lorenzo", "Loyce", 1L, "Two/Neo", 1, 1, 'A').build();
        });
        assertTrue(error.getMessage().equals("Instructor Id should not be empty"));
    }

    @Test
    void testInstructorLastName_isZero() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "0", "Loyce", 1L, "Two/Neo", 1, 1, 'A').build();
        });
        assertTrue(error.getMessage().equals("Instructor Last Name should not be empty"));
    }

    @Test
    void testInstructorFirstName_isZero() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "0", 1L, "Two/Neo", 1, 1, 'A').build();
        });
        assertTrue(error.getMessage().equals("Instructor Last Name should not be empty"));
    }

    @Test
    void testInvalidVenueId_isZero() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 0L, "Two/Neo", 1, 1, 'A').build();
        });
        assertTrue(error.getMessage().equals("Venue Id should not be empty"));
    }

    @Test
    void testInvalidVenueName_isZero() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L,1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "0", 1, 1, 'A').build();
        });
        assertTrue(error.getMessage().equals("Venue Name should not be empty"));
    }

    @Test
    void testInvalidMinRequired_isZero() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo", 0 , 1, 'A').build();
        });
        assertTrue(error.getMessage().equals("Minimum required participants should not be empty"));
    }

    @Test
    void testInvalidMaxRequired_isZero() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo", 1 , 0, 'A').build();
        });
        assertTrue(error.getMessage().equals("Maximum required participants should not be empty"));
    }

    @Test
    void testInvalidStatus_isZero() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo", 1, 1, '0').build();
        });
        assertTrue(error.getMessage().equals("Status should not be empty"));
    }

}
