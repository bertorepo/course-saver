package com.fujitsu.ph.tsup.dashboard.service;

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
import com.fujitsu.ph.tsup.dashboard.domain.DashboardMemberForm;

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
    void testActiveCourses() {

        int activeCourses = service.getActiveCourses(any(Long.class));
        assertEquals(0, activeCourses);
    }

    @Test
    void testTrainingsToday() {

        int trainingsToday = service.getTrainingsToday(any(Long.class));
        assertEquals(0, trainingsToday);
    }

    @Test
    void testFindCourses() {
        Set<DashboardMemberForm> dashboardSet = new HashSet<DashboardMemberForm>();
        DashboardMemberForm dashboardMember = new DashboardMemberForm.Builder("Understanding SS",
                "de Guzman, Genevieve", ZonedDateTime.now(), ZonedDateTime.now().plus(2, ChronoUnit.HOURS), "Online",
                1L, "A").build();
        dashboardSet.add(dashboardMember);

        when(dao.findCourses(any(Long.class))).thenReturn(dashboardSet);
        
        assertEquals(dashboardSet, service.findCourses(1L));
    }

}
