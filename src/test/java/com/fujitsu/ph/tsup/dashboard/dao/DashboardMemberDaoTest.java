package com.fujitsu.ph.tsup.dashboard.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.fujitsu.ph.tsup.dashboard.domain.DashboardMember;

@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class DashboardMemberDaoTest {
    @TestConfiguration
    static class TestContextConfiguration {
        
        @Bean
        DashboardMemberDao dashboardMemberDao() {
            return new DashboardMemberDaoImpl();
        }
        
    }
    @Autowired
    private DashboardMemberDao dao;
 
    @Test
    void testActiveCourses() {
        int activeCourses = dao.getActiveCourses(any(Long.class));
        assertEquals(0, activeCourses);
    }
    
    @Test
    void testTrainingsToday() {
       
        int trainingsToday = dao.getTrainingsToday(any(Long.class));
        assertEquals(0, trainingsToday);
    }
    
    @Test
    void testFindCourses() {
        Set<DashboardMember> dashboardMember = dao.findCourses(1L);
        assertEquals(2, dashboardMember.size());
    }
    
    

}
