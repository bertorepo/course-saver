package com.fujitsu.ph.tsup.attendance.domain;

import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :CourseScheduleDetailTest.java
//
//<<Modification History>>
//Version | Date       | Updated By                                    | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 07/08/2020 |   WS) K.Abad, WS) J.Iwarat, WS) R.Ramos       | New Creation
//==================================================================================================
/**
* <pre>
* It is the test of method of CourseScheduleDetail builder class
* In this class, test the CourseScheduleDetail builder class of all its validation
* </pre>
* 
* @version 0.01
* @author k.abad
* @author j.iwarat
* @author r.ramos
*/

public class CourseScheduleDetailTest {

    /**
     * Test case for Course Schedule Detail Builder
     */
    @Test
    void testBuilder1() {
        CourseScheduleDetail expected = createCourseScheduleDetail();

        assertEquals(1L, expected.getId());
        assertEquals(2L, expected.getCourseScheduleId());
        assertEquals(ZonedDateTime.parse("2016-11-16T17:21:00Z"), expected.getScheduledStartDateTime());
        assertEquals(ZonedDateTime.parse("2016-11-16T17:21:00Z"), expected.getScheduledEndDateTime());

    }

    /**
     * Test case for Course Schedule Detail Builder
     */
    @Test
    void testBuilder2() {
        CourseScheduleDetail expected1 = createCourseScheduleDetail1();

        assertEquals(3L, expected1.getCourseScheduleId());
        assertEquals(ZonedDateTime.parse("2016-11-16T17:21:00Z"), expected1.getScheduledStartDateTime());
        assertEquals(ZonedDateTime.parse("2016-11-16T17:21:00Z"), expected1.getScheduledEndDateTime());

    }

    /**
     * Test case for when Id is null
     */
    @Test
    void testId_IsNull() {
        IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
            new CourseScheduleDetail.Builder(null, 2L, ZonedDateTime.parse("2016-11-16T17:21:00Z"),
                    ZonedDateTime.parse("2016-11-16T17:21:00Z")).build();
        });
        assertThat(t.getMessage(), equalTo("Id should not be empty"));
    }

    /**
     * Test case for when Id is zero
     */
    @Test
    void testId_IsZero() {

        IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
            new CourseScheduleDetail.Builder(0L, 2L, ZonedDateTime.parse("2016-11-16T17:21:00Z"),
                    ZonedDateTime.parse("2016-11-16T17:21:00Z")).build();
        });
        assertThat(t.getMessage(), equalTo("Id should not be empty"));
    }

    /**
     * Test case for when Id is valid
     */
    @Test
    void testId_IsValid() {
        CourseScheduleDetail expected = createCourseScheduleDetail();
        assertEquals(1L, expected.getId());
    }

    /**
     * Test case for when CourseScheduleDetailId is valid
     */
    @Test
    void testCourseScheduleDetailId_IsValid() {
        CourseScheduleDetail expected = createCourseScheduleDetail();
        assertEquals(2L,expected.getCourseScheduleId());
    }
    
    /**
     * Test case for when CourseScheduleDetailId is null
     */
    @Test
    void testCourseScheduleDetailId_IsNull() {
        IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
            new CourseScheduleDetail.Builder(1L, null, ZonedDateTime.parse("2016-11-16T17:21:00Z"),
                    ZonedDateTime.parse("2016-11-16T17:21:00Z")).build();
        });
        assertThat(t.getMessage(), equalTo("Course Schedule Id should not be empty"));
    }

    /**
     * Test case for when CourseScheduleDetailId is zero
     */
    @Test
    void testCourseScheduleDetailId_IsZero() {
        IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
            new CourseScheduleDetail.Builder(1L, 0L, ZonedDateTime.parse("2016-11-16T17:21:00Z"),
                    ZonedDateTime.parse("2016-11-16T17:21:00Z")).build();
        });
        assertThat(t.getMessage(), equalTo("Course Schedule Id should not be empty"));
    }

    /**
     * Test case for when ScheduledStartDateTime is null
     */
    @Test
    void testScheduledStartDateTime_IsNull() {
        IllegalArgumentException r = assertThrows(IllegalArgumentException.class, () -> {
            new CourseScheduleDetail.Builder(1L, 2L,null,
                    ZonedDateTime.parse("2016-11-16T17:21:00Z")).build();
        });
        assertThat(r.getMessage(), equalTo("Scheduled Start Date and Time should not be empty"));
    }

    /**
     * Test case for when ScheduledStartDateTime is valid
     */
    @Test
    void testScheduledStartDateTime_IsValid() {
        CourseScheduleDetail expected = createCourseScheduleDetail();

        assertNotNull(expected);
        assertEquals(expected.getScheduledStartDateTime(), ZonedDateTime.parse("2016-11-16T17:21:00Z"));
    }

    /**
     * Test case for when ScheduledEndDateTime is valid
     */
    @Test
    void testScheduledEndDateTime_IsValid() {
        CourseScheduleDetail expected = createCourseScheduleDetail();
        assertEquals(ZonedDateTime.parse("2016-11-16T17:21:00Z"), expected.getScheduledEndDateTime());
    }
    /**
     * Test case for when ScheduledEndDateTime is null
     */
    @Test
    void testScheduledEndDateTime_IsNull() {
        IllegalArgumentException w = assertThrows(IllegalArgumentException.class, () -> {
            new CourseScheduleDetail.Builder(1L, 2L, ZonedDateTime.parse("2016-11-16T17:21:00Z"),
                    null).build();
        });
        assertThat(w.getMessage(), equalTo("Scheduled end date and time should not be empty"));
    }

    /**
     * Test case for when ScheduledEndDateTime is less than ScheduledStartDateTime
     */
    @Test
    void testScheduledEndDateTime_IsAfter() {
        IllegalArgumentException s = assertThrows(IllegalArgumentException.class, () -> {
            new CourseScheduleDetail.Builder(1L, 2L, ZonedDateTime.parse("2016-11-16T17:21:00Z"),
                    ZonedDateTime.parse("2015-11-16T20:21:00Z")).build();
        });
        assertThat(s.getMessage(), equalTo(
                "Scheduled end date and time should be greater than or "
                        + "equal to the the scheduled start date and time"));
    }

    private CourseScheduleDetail createCourseScheduleDetail() {
        return new CourseScheduleDetail.Builder(1L, 2L, ZonedDateTime.parse("2016-11-16T17:21:00Z"),
                ZonedDateTime.parse("2016-11-16T17:21:00Z")).build();
    }

    private CourseScheduleDetail createCourseScheduleDetail1() {
        return new CourseScheduleDetail.Builder(3L, ZonedDateTime.parse("2016-11-16T17:21:00Z"),
                ZonedDateTime.parse("2016-11-16T17:21:00Z")).build();
    }
}
