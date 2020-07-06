package com.fujitsu.ph.tsup.dashboard.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fujitsu.ph.tsup.dashboard.domain.DashboardPmoForm;

@ExtendWith(SpringExtension.class)
class DashboardPmoFormTest {

    @Test
    void testCourseName() {
        assertEquals("Goal Setting", dashboardPmo().getCourseName());
    }

    @Test
    void testInstructorName() {
        assertEquals("de Leon, JC", dashboardPmo().getInstructorName());
    }

    @Test
    void testStartDateTime() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));

        assertEquals(startDateTime, dashboardPmo().getStartDateTime());
    }

    @Test
    void testEndDateTime() {
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));

        assertEquals(endDateTime, dashboardPmo().getEndDateTime());
    }

    @Test
    void testMinRequired() {
        assertEquals(10, dashboardPmo().getMinRequired());
    }

    @Test
    void testMaxAllowed() {
        assertEquals(30, dashboardPmo().getMaxAllowed());
    }

    @Test
    void testEnrolled() {
        assertEquals(3, dashboardPmo().getEnrolled());
    }

    @Test
    void testStatus() {
        assertEquals("A", dashboardPmo().getStatus());
    }

    private DashboardPmoForm dashboardPmo() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        return new DashboardPmoForm.Builder("Goal Setting", "de Leon, JC", startDateTime, endDateTime, 10, 30, 3, "A")
                .build();
    }
}
