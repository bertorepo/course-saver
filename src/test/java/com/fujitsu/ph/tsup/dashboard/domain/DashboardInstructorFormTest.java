package com.fujitsu.ph.tsup.dashboard.domain;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import com.fujitsu.ph.tsup.dashboard.domain.DashboardInstructorForm;

class DashboardInstructorFormTest {

    @Test
    void testCourseName_Valid() {
        assertEquals("Goal Setting", dashboardInstructor().getCourseName());
    }

    @Test
    void testCourseName_isNull() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardInstructorForm.Builder(null, startDateTime, endDateTime, "Online", 1L, "A").build();
        });
        assertTrue(error.getMessage().equals("Course Name should not be empty"));
    }

    @Test
    void testCourseName_isEmpty() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardInstructorForm.Builder("", startDateTime, endDateTime, "Online", 1L, "A").build();
        });
        assertTrue(error.getMessage().equals("Course Name should not be empty"));
    }

    @Test
    void testStartDateTime_Valid() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        assertEquals(startDateTime, dashboardInstructor().getStartDateTime());
    }

    @Test
    void testStartDateTime_isNull() {
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardInstructorForm.Builder("Goal Setting", null, endDateTime, "Online", 1L, "A").build();
        });
        assertTrue(error.getMessage().equals("Start Date Time should not be empty"));
    }

    @Test
    void testEndDateTime_Valid() {
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        assertEquals(endDateTime, dashboardInstructor().getEndDateTime());
    }

    @Test
    void testEndDateTime_isNull() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardInstructorForm.Builder("Goal Setting", startDateTime, null, "Online", 1L, "A").build();
        });
        assertTrue(error.getMessage().equals("End Date Time should not be empty"));
    }

    @Test
    void testVenueName_Valid() {
        assertEquals("Online", dashboardInstructor().getVenueName());
    }

    @Test
    void testVenueName_isNull() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardInstructorForm.Builder("Goal Setting", startDateTime, endDateTime, null, 1L, "A").build();
        });
        assertTrue(error.getMessage().equals("Venue Name should not be empty"));
    }

    @Test
    void testVenueName_isEmpty() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardInstructorForm.Builder("Goal Setting", startDateTime, endDateTime, "", 1L, "A").build();
        });
        assertTrue(error.getMessage().equals("Venue Name should not be empty"));
    }

    @Test
    void testEmployeeId_Valid() {
        assertEquals(1L, dashboardInstructor().getEmployeeId());
    }

    @Test
    void testEmployeeId_isNull() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardInstructorForm.Builder("Goal Setting", startDateTime, endDateTime, "Online", null, "A")
                    .build();
        });
        assertTrue(error.getMessage().equals("Employee ID should not be empty"));
    }

    @Test
    void testEmployeeId_isZero() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardInstructorForm.Builder("Goal Setting", startDateTime, endDateTime, "Online", 0L, "A").build();
        });
        assertTrue(error.getMessage().equals("Employee ID should not be empty"));
    }

    @Test
    void testStatus_Valid() {
        assertEquals("A", dashboardInstructor().getStatus());
    }

    @Test
    void testStatus_isNull() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardInstructorForm.Builder("Goal Setting", startDateTime, endDateTime, "Online", 1L, null).build();
        });
        assertTrue(error.getMessage().equals("Status should not be empty"));
    }

    @Test
    void testStatus_isEmpty() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardInstructorForm.Builder("Goal Setting", startDateTime, endDateTime, "Online", 1L, "").build();
        });
        assertTrue(error.getMessage().equals("Status should not be empty"));
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
