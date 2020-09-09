package com.fujitsu.ph.tsup.dashboard.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.fujitsu.ph.tsup.dashboard.domain.DashboardPmo;

@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class DashboardPmoDaoTest {
    @TestConfiguration
    static class TestContextConfiguration {
        
        @Bean
        DashboardPmoDao dashboardPmoDao() {
            return new DashboardPmoDaoImpl();
        }
        
    }
    @Autowired
    private DashboardPmoDao dao;
 
    
    @Test
    void findCourses() {
        Set<DashboardPmo> dashboardPmo = dao.findCourses();
        assertEquals(3, dashboardPmo.size());
    }
}
