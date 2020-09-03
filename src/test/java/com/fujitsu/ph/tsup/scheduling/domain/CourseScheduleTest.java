package com.fujitsu.ph.tsup.scheduling.domain;
/**
* <pre>
* The Unit Testing of course schedule builder
* <pre>
* @version 0.01
* @author j.balanon
* @author jc.jimenez
*/

//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: ScheduleServiceTest.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 07/02/2020 | WS) J.Balanon	 | New Creation
//0.01    | 07/03/2020 | WS) JC. Jimenez | Update
//
//=======================================================
import static org.junit.Assert.*;

import java.time.ZonedDateTime;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;

public class CourseScheduleTest {

    /**
     * <pre>
     * testCourseSchedule with valid values
     * <pre>
     */
    @Test
    void testCourseScheduleValid() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A', 1).build();

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

    /**
     * <pre>
     * testId with valid value
     * <pre>
     */
    @Test
    void testValidId() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A', 1).build();
        assertEquals(CourseSched.getId(), 1L);
    }
    
    /**
     * <pre>
     * testId with null value
     * <pre>
     */
    @Test
    void testInvalidId_isNull() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(null, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo", 1, 1, 'A', 1).build();
        });
        assertTrue(error.getMessage().equals("Id should not be empty"));
    }
    
    /**
     * <pre>
     * testId with zero value
     * <pre>
     */
    @Test
    void testInvalidId_isZero() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(0L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo", 1, 1, 'A', 1).build();
        });
        assertTrue(error.getMessage().equals("Id should not be empty"));
    }


    /**
     * <pre>
     * testCourseId with valid value
     * <pre>
     */
    @Test
    void testValidCourseId() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A', 1).build();
        assertEquals(CourseSched.getCourseId(), 1L);
    }
    
    /**
     * <pre>
     * testCourseId with null value
     * <pre>
     */
    @Test
    void testInvalidCourseId_isNull() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, null, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo", 1, 1, 'A', 1).build();
        });
        assertTrue(error.getMessage().equals("Course should not be empty"));
    }

    /**
     * <pre>
     * testCourseName with valid value
     * <pre>
     */
    @Test
    void testValidCourseName() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A', 1).build();
        assertEquals(CourseSched.getCourseName(), "JUnit");
    }
    
    /**
     * <pre>
     * testCourseName with null value
     * <pre>
     */
    @Test
    void testInvalidCourseName_isNull() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, null, 1L, "Lorenzo", "Loyce", 1L, "Two/Neo", 1, 1, 'A', 1).build();
        });
        assertTrue(error.getMessage().equals("Course name should not be empty"));
    }
    
    /**
     * <pre>
     * testCourseName with empty value
     * <pre>
     */
    @Test
    void testInvalidCourseName_isEmpty() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo", 1, 1, 'A', 1).build();
        });
        assertTrue(error.getMessage().equals("Course name should not be empty"));
    }

    /**
     * <pre>
     * testInstructorId with valid value
     * <pre>
     */
    @Test
    void testValidInstructorId() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A', 1).build();
        assertEquals(CourseSched.getInstructorId(), 1L);
    }
    
    /**
     * <pre>
     * testInstructorId with null value
     * <pre>
     */
    @Test
    void testInvalidInstructorId_isNull() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", null, "Lorenzo", "Loyce", 1L, "Two/Neo", 1, 1, 'A', 1).build();
        });
        assertTrue(error.getMessage().equals("Instructor should not be empty"));
    }
    
    /**
     * <pre>
     * testInstructorId with zero value
     * <pre>
     */
    @Test
    void testInvalidInstructorId_isZero() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 0L, "Lorenzo", "Loyce", 1L, "Two/Neo", 1, 1, 'A', 1).build();
        });
        assertTrue(error.getMessage().equals("Instructor should not be empty"));
    }

    /**
     * <pre>
     * testInstructorLastName with valid value
     * <pre>
     */
    @Test
    void testValidInstructorLastName() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A',1).build();
        assertEquals(CourseSched.getInstructorLastName(), "Lorenzo");
    }
    
    /**
     * <pre>
     * testInstructorLastName with null value
     * <pre>
     */
    @Test
    void testInvalidInstructorLastName_isNull() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, null, "Loyce", 1L, "Two/Neo", 1, 1, 'A', 1).build();
        });
        assertTrue(error.getMessage().equals("Instructor Name should not be empty"));
    }
    
    /**
     * <pre>
     * testInstructorLastName with empty value
     * <pre>
     */
    @Test
    void testInstructorLastName_isEmpty() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "", "Loyce", 1L, "Two/Neo", 1, 1, 'A', 1).build();
        });
        assertTrue(error.getMessage().equals("Instructor Name should not be empty"));
    }

    /**
     * <pre>
     * testInstructorFirstName with valid value
     * <pre>
     */
    @Test
    void testValidInstructorFirstName() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A', 1).build();
        assertEquals(CourseSched.getInstructorFirstName(), "Loyce");
    }
    
    /**
     * <pre>
     * testInstructorFirstName with null value
     * <pre>
     */
    @Test
    void testInstructorFirstName_isNull() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", null, 1L, "Two/Neo", 1, 1, 'A', 1).build();
        });
        assertTrue(error.getMessage().equals("Instructor Name should not be empty"));
    }

    /**
     * <pre>
     * testVenueId with valid value
     * <pre>
     */
    @Test
    void testValidVenueId() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A', 1).build();
        assertEquals(CourseSched.getVenueId(), 1L);
    }
    
    /**
     * <pre>
     * testVenueId with null value
     * <pre>
     */
    @Test
    void testInvalidVenueId_isNull() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", null, "Two/Neo", 1, 1, 'A', 1).build();
        });
        assertTrue(error.getMessage().equals("Venue should not be empty"));
    }

    /**
     * <pre>
     * testVenueId with zero value
     * <pre>
     */
    @Test
    void testInvalidVenueId_isZero() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 0L, "Two/Neo", 1, 1, 'A', 1).build();
        });
        assertTrue(error.getMessage().equals("Venue should not be empty"));
    }
    
    /**
     * <pre>
     * testVenueName with valid value
     * <pre>
     */
    @Test
    void testVenueName() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A', 1).build();
        assertEquals(CourseSched.getVenueName(), "Two/Neo");
    }
    
    /**
     * <pre>
     * testVenueName with null value
     * <pre>
     */
    @Test
    void testInvalidVenueName_isNull() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, null, 1, 1, 'A', 1).build();
        });
        assertTrue(error.getMessage().equals("Venue should not be empty"));
    }
    
    /**
     * <pre>
     * testVenueName with empty value
     * <pre>
     */
    @Test
    void testInvalidVenueName_isEmpty() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L,1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "", 1, 1, 'A', 1).build();
        });
        assertTrue(error.getMessage().equals("Venue should not be empty"));
    }


    /**
     * <pre>
     * testMinRequired with valid value
     * <pre>
     */
    @Test
    void testValidMinRequired() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A', 1).build();
        assertEquals(CourseSched.getMinRequired(), 1);
    }

    /**
     * <pre>
     * testMinRequired with zero value
     * <pre>
     */
    @Test
    void testInvalidMinRequired_isZero() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo", 0 , 1, 'A', 1).build();
        });
        assertTrue(error.getMessage().equals("Mininum No. of Participants should be greater than 0"));
    }
    
    /**
     * <pre>
     * testMinRequired with less than zero value
     * <pre>
     */
    @Test
    void testInvalidMinRequired_isLessThanZero() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo", -1, 1, 'A', 1).build();
        });
        assertTrue(error.getMessage().equals("Mininum No. of Participants should be greater than 0"));
    }

    /**
     * <pre>
     * testMaxAllowed with valid value
     * <pre>
     */
    @Test
    void testValidMaxAllowed() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A', 1).build();
        assertEquals(CourseSched.getMaxAllowed(), 1);
    }
    
    /**
     * <pre>
     * testMaxAllowed with less than zero value
     * <pre>
     */
    @Test
    void testInvalidMaxAllowed_isLessThanZero() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L,1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo", 1, -1, 'A', 1).build();
        });
        assertTrue(error.getMessage().equals("Maximum No. of Participants should not be less than 0"));
    }

    /**
     * <pre>
     * testStatus with valid value
     * <pre>
     */
    @Test
    void testValidStatus() {

        CourseSchedule CourseSched = new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo",
                1, 1, 'A', 1).build();
        assertEquals(CourseSched.getStatus(), 'A');
    }
    
    /**
     * <pre>
     * testStatus with invalid values
     * <pre>
     */
    @Test
    void testInvalidStatus_isNotAorP() {

        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo", 1, 1, 'H', 1).build();
        });
        assertTrue(error.getMessage().equals("Status should be A or D only"));
    }

}
