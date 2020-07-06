package com.fujitsu.ph.tsup.dashboard.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import com.fujitsu.ph.tsup.dashboard.domain.DashboardInstructorForm;

class DashboardInstructorFormTest {

    @Test
    void testCourseName() {
        assertEquals("Goal Setting", dashboardInstructor().getCourseName());
    }

    @Test
    void testStartDateTime() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        assertEquals(startDateTime, dashboardInstructor().getStartDateTime());
    }

    @Test
    void testEndDateTime() {
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        assertEquals(endDateTime, dashboardInstructor().getEndDateTime());
    }

    @Test
    void testVenueName() {
        assertEquals("Online", dashboardInstructor().getVenueName());
    }

    @Test
    void testEmployeeId() {
        assertEquals(1L, dashboardInstructor().getEmployeeId());
    }

    @Test
    void testStatus() {
        assertEquals("A", dashboardInstructor().getStatus());
    }

    private DashboardInstructorForm dashboardInstructor() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        return new DashboardInstructorForm.Builder("Goal Setting", startDateTime, endDateTime, "Online", 1L, "A")
                .build();
    }
}
