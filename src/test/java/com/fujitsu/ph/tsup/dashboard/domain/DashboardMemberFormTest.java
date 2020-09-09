package com.fujitsu.ph.tsup.dashboard.domain;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
class DashboardMemberFormTest {

    @Test
    void testCourseName_Valid() {
        assertEquals("Goal Setting", dashboardMember().getCourseName());
    }

    @Test
    void testCourseName_isNull() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardMember.Builder(null, "de Leon, JC", startDateTime, endDateTime, "Online", 1L, "A").build();
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
            new DashboardMember.Builder("", "de Leon, JC", startDateTime, endDateTime, "Online", 1L, "A").build();
        });
        assertTrue(error.getMessage().equals("Course Name should not be empty"));
    }

    @Test
    void testInstructorName_Valid() {
        assertEquals("de Leon, JC", dashboardMember().getInstructorName());
    }

    @Test
    void testInstructorName_isNull() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardMember.Builder("Goal Setting", null, startDateTime, endDateTime, "Online", 1L, "A")
                    .build();
        });
        assertTrue(error.getMessage().equals("Instructor Name should not be empty"));
    }

    @Test
    void testInstructorName_isEmpty() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardMember.Builder("Goal Setting", "", startDateTime, endDateTime, "Online", 1L, "A").build();
        });
        assertTrue(error.getMessage().equals("Instructor Name should not be empty"));
    }

    @Test
    void testStartDateTime_Valid() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));

        assertEquals(startDateTime, dashboardMember().getStartDateTime());
    }

    @Test
    void testStartDateTime_isNull() {
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardMember.Builder("Goal Setting", "de Leon, JC", null, endDateTime, "Online", 1L, "A")
                    .build();
        });
        assertTrue(error.getMessage().equals("Start Date Time should not be empty"));
    }

    @Test
    void testEndDateTime_Valid() {
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));

        assertEquals(endDateTime, dashboardMember().getEndDateTime());
    }

    @Test
    void testEndDateTime_isNull() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardMember.Builder("Goal Setting", "de Leon, JC", startDateTime, null, "Online", 1L, "A")
                    .build();
        });
        assertTrue(error.getMessage().equals("End Date Time should not be empty"));
    }

    @Test
    void testVenueName_Valid() {
        assertEquals("Online", dashboardMember().getVenueName());
    }

    @Test
    void testVenueName_isNull() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardMember.Builder("Goal Setting", "de Leon. JC", startDateTime, endDateTime, null, 1L, "A")
                    .build();
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
            new DashboardMember.Builder("Goal Setting", "de Leon, JC", startDateTime, endDateTime, "", 1L, "A")
                    .build();
        });
        assertTrue(error.getMessage().equals("Venue Name should not be empty"));
    }

    @Test
    void testEmployeeId_Valid() {
        assertEquals(1L, dashboardMember().getEmployeeId());
    }

    @Test
    void testEmployeeId_isNull() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardMember.Builder("Goal Setting", "de Leon, JC", startDateTime, endDateTime, "Online", null,
                    "A").build();
        });
        assertTrue(error.getMessage().equals("Employee ID should not be empty"));
    }

    @Test
    void testEmployeeId_isEmpty() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardMember.Builder("Goal Setting", "de Leon, JC", startDateTime, endDateTime, "Online", 0L,
                    "A").build();
        });
        assertTrue(error.getMessage().equals("Employee ID should not be empty"));
    }

    @Test
    void testStatus_Valid() {
        assertEquals("A", dashboardMember().getStatus());
    }

    @Test
    void testStatus_isNull() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardMember.Builder("Goal Setting", "de Leon JC", startDateTime, endDateTime, "Online", 1L,
                    null).build();
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
            new DashboardMember.Builder("Goal Setting", "de Leon, JC", startDateTime, endDateTime, "Online", 1L, "")
                    .build();
        });
        assertTrue(error.getMessage().equals("Status should not be empty"));
    }

    private DashboardMember dashboardMember() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        return new DashboardMember.Builder("Goal Setting", "de Leon, JC", startDateTime, endDateTime, "Online", 1L,
                "A").build();
    }
}
