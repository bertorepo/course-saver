package com.fujitsu.ph.tsup.dashboard.domain;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class DashboardPmoFormTest {

    @Test
    void testCourseName_Valid() {
        assertEquals("Goal Setting", dashboardPmo().getCourseName());
    }
    @Test
    void testCourseName_isNull() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardPmo.Builder(null, "de Leon, JC", startDateTime, endDateTime, 10, 
                    30, 3, "A").build();
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
            new DashboardPmo.Builder("", "de Leon, JC", startDateTime, endDateTime, 10, 
                    30, 3, "A").build();
        });
        assertTrue(error.getMessage().equals("Course Name should not be empty"));
    }

    @Test
    void testInstructorName_Valid() {
        assertEquals("de Leon, JC", dashboardPmo().getInstructorName());
    }
    @Test
    void testInstructorName_isNull() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardPmo.Builder("Goal Setting", null, startDateTime, endDateTime, 10, 
                    30, 3, "A").build();
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
            new DashboardPmo.Builder("Goal Setting", "", startDateTime, endDateTime, 10, 
                    30, 3, "A").build();
        });
        assertTrue(error.getMessage().equals("Instructor Name should not be empty"));
    }
    @Test
    void testStartDateTime_Valid() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));

        assertEquals(startDateTime, dashboardPmo().getStartDateTime());
    }
    @Test
    void testStartDateTime_isNull() {
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardPmo.Builder("Goal Setting", "de Leon, JC", null, endDateTime, 10, 
                    30, 3, "A").build();
        });
        assertTrue(error.getMessage().equals("Start Date Time should not be empty"));
    }
    @Test
    void testEndDateTime_Valid() {
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));

        assertEquals(endDateTime, dashboardPmo().getEndDateTime());
    }
    @Test
    void testEndDateTime_isNull() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardPmo.Builder("Goal Setting", "de Leon, JC", startDateTime, null, 10, 
                    30, 3, "A").build();
        });
        assertTrue(error.getMessage().equals("End Date Time should not be empty"));
    }
    @Test
    void testMinRequired_Valid() {
        assertEquals(10, dashboardPmo().getMinRequired());
    }
    @Test
    void testMinRequired_isZero() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardPmo.Builder("Goal Setting", "de Leon, JC", startDateTime, endDateTime, 0, 
                    30, 3, "A").build();
        });
        assertTrue(error.getMessage().equals("Minimum required should not be zero"));
    }
    @Test
    void testMaxAllowed_Valid() {
        assertEquals(30, dashboardPmo().getMaxAllowed());
    }
    @Test
    void testMaxAllowed_isZero() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardPmo.Builder("Goal Setting", "de Leon, JC", startDateTime, endDateTime, 10, 
                    0, 3, "A").build();
        });
        assertTrue(error.getMessage().equals("Maximum allowed should not be zero"));
    }
    @Test
    void testEnrolled() {
        assertEquals(3, dashboardPmo().getEnrolled());
    }

    @Test
    void testStatus_Valid() {
        assertEquals("A", dashboardPmo().getStatus());
    }
    @Test
    void testStatus_isNull() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            new DashboardPmo.Builder("Goal Setting", "de Leon, JC", startDateTime, endDateTime, 10, 
                    30, 3, null).build();
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
            new DashboardPmo.Builder("Goal Setting", "de Leon, JC", startDateTime, endDateTime, 10, 
                    30, 3, "").build();
        });
        assertTrue(error.getMessage().equals("Status should not be empty"));
    }
    private DashboardPmo dashboardPmo() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        return new DashboardPmo.Builder("Goal Setting", "de Leon, JC", startDateTime, endDateTime, 10, 30, 3, "A")
                .build();
    }
}
