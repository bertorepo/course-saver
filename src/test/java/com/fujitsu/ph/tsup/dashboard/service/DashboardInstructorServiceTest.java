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

import com.fujitsu.ph.tsup.dashboard.dao.DashboardInstructorDao;
import com.fujitsu.ph.tsup.dashboard.domain.DashboardInstructor;

@ExtendWith(SpringExtension.class)
class DashboardInstructorServiceTest {
    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        DashboardInstructorService dashboardInstructorService() {
            return new DashboardInstructorServiceImpl();
        }

    }

    @Autowired
    private DashboardInstructorService service;

    @MockBean
    private DashboardInstructorDao dao;

    @Test
    void testCoursesToday_Valid() {
        int coursesToday = service.getCoursesToday(any(Long.class));
        assertEquals(0, coursesToday);
    }

    @Test
    void testFindCourses_Valid() {
        Set<DashboardInstructor> dashboardSet = new HashSet<DashboardInstructor>();
        DashboardInstructor dashboardInstructor = new DashboardInstructor.Builder("Understanding UI",
                ZonedDateTime.now(), ZonedDateTime.now().plus(2, ChronoUnit.HOURS), "Online", 1L, "A").build();
        dashboardSet.add(dashboardInstructor);

        when(dao.findCourses(any(Long.class))).thenReturn(dashboardSet);

        assertEquals(dashboardSet, service.findCourses(1L));

    }

    @Test
    void testFindCourses_Invalid() {
        Set<DashboardInstructor> dashboardSet = new HashSet<DashboardInstructor>();
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            when(dao.findCourses(any(Long.class))).thenReturn(dashboardSet);
            assertEquals(dashboardSet, service.findCourses(1L));
        });

        assertTrue(error.getMessage().equals("No records found"));

    }
}
