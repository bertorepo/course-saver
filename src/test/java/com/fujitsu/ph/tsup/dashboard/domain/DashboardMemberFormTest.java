package com.fujitsu.ph.tsup.dashboard.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fujitsu.ph.tsup.dashboard.domain.DashboardMemberForm;

@ExtendWith(SpringExtension.class)
class DashboardMemberFormTest {

    @Test
    void testCourseName() {
        assertEquals("Goal Setting", dashboardMember().getCourseName());
    }

    @Test
    void testInstructorName() {
        assertEquals("de Leon, JC", dashboardMember().getInstructorName());
    }

    @Test
    void testStartDateTime() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));

        assertEquals(startDateTime, dashboardMember().getStartDateTime());
    }

    @Test
    void testEndDateTime() {
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));

        assertEquals(endDateTime, dashboardMember().getEndDateTime());
    }

    @Test
    void testVenueName() {
        assertEquals("Online", dashboardMember().getVenueName());
    }

    @Test
    void testEmployeeId() {
        assertEquals(1L, dashboardMember().getEmployeeId());
    }

    @Test
    void testStatus() {
        assertEquals("A", dashboardMember().getStatus());
    }

    private DashboardMemberForm dashboardMember() {
        ZonedDateTime startDateTime = ZonedDateTime.parse("07/03/20 10:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        ZonedDateTime endDateTime = ZonedDateTime.parse("07/03/20 12:00.00.000 +08:00",
                DateTimeFormatter.ofPattern("dd/MM/yy HH:mm.ss.SSS XXX"));
        return new DashboardMemberForm.Builder("Goal Setting", "de Leon, JC", startDateTime, endDateTime, "Online", 1L,
                "A").build();
    }
}
