package com.fujitsu.ph.tsup.dashboard.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.fujitsu.ph.tsup.dashboard.dao.DashboardPmoDao;
import com.fujitsu.ph.tsup.dashboard.domain.DashboardPmoForm;

@ExtendWith(SpringExtension.class)
class DashboardPmoServiceTest {

    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        DashboardPmoService dashboardPmoService() {
            return new DashboardPmoServiceImpl();
        }

    }

    @Autowired
    private DashboardPmoService service;

    @MockBean
    private DashboardPmoDao dao;

    @Test
    void testFindCourses_Valid() {
        Set<DashboardPmoForm> dashboardSet = new HashSet<DashboardPmoForm>();
        DashboardPmoForm dashboardPmo = new DashboardPmoForm.Builder("Goal Setting", "de Leon, JC", ZonedDateTime.now(),
                ZonedDateTime.now().plus(2, ChronoUnit.HOURS), 10, 20, 1, "A").build();
        dashboardSet.add(dashboardPmo);

        when(dao.findCourses()).thenReturn(dashboardSet);

        assertEquals(dashboardSet, service.findCourses());
    }

    @Test
    void testFindCourses_Invalid() {
        Set<DashboardPmoForm> dashboardSet = new HashSet<DashboardPmoForm>();
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            when(dao.findCourses()).thenReturn(dashboardSet);
            assertEquals(dashboardSet, service.findCourses());
        });

        assertTrue(error.getMessage().equals("No records found"));

    }

}
