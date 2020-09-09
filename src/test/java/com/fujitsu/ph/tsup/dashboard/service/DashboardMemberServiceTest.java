package com.fujitsu.ph.tsup.dashboard.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fujitsu.ph.tsup.dashboard.dao.DashboardMemberDao;
import com.fujitsu.ph.tsup.dashboard.domain.DashboardMember;

@ExtendWith(SpringExtension.class)
class DashboardMemberServiceTest {

    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        DashboardMemberService dashboardMemberService() {
            return new DashboardMemberServiceImpl();
        }

    }

    @Autowired
    private DashboardMemberService service;

    @MockBean
    private DashboardMemberDao dao;

    @Test
    void testActiveCourses_Valid() {

        int activeCourses = service.getActiveCourses(any(Long.class));
        assertEquals(0, activeCourses);
    }

    @Test
    void testTrainingsToday_Valid() {

        int trainingsToday = service.getTrainingsToday(any(Long.class));
        assertEquals(0, trainingsToday);
    }

    @Test
    void testFindCourses_Valid() {
        Set<DashboardMember> dashboardSet = new HashSet<DashboardMember>();
        DashboardMember dashboardMember = new DashboardMember.Builder("Understanding SS",
                "de Guzman, Genevieve", ZonedDateTime.now(), ZonedDateTime.now().plus(2, ChronoUnit.HOURS), "Online",
                1L, "A").build();
        dashboardSet.add(dashboardMember);

        when(dao.findCourses(any(Long.class))).thenReturn(dashboardSet);

        assertEquals(dashboardSet, service.findCourses(1L));
    }

    @Test
    void testFindCourses_Invalid() {
        Set<DashboardMember> dashboardSet = new HashSet<DashboardMember>();
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            when(dao.findCourses(any(Long.class))).thenReturn(dashboardSet);
            assertEquals(dashboardSet, service.findCourses(1L));
        });

        assertTrue(error.getMessage().equals("No records found"));

    }
}
