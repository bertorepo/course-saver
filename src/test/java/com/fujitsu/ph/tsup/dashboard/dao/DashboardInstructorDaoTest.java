package com.fujitsu.ph.tsup.dashboard.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.fujitsu.ph.tsup.dashboard.domain.DashboardInstructorForm;

@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class DashboardInstructorDaoTest {
    @TestConfiguration
    static class TestContextConfiguration {
        
        @Bean
        DashboardInstructorDao dashboardInstructorDao() {
            return new DashboardInstructorDaoImpl();
        }
        
    }
   @Autowired
    private DashboardInstructorDao dao;
 
    @Test
    void testCoursesToday() {
        int coursesToday = dao.getCoursesToday(1L);
        assertEquals(0, coursesToday);
    }
    
    @Test
    void testFindCourses() {
        Set<DashboardInstructorForm> dashboardInstructor = dao.findCourses(1L);
        assertEquals(1, dashboardInstructor.size());
    }
    
    

}
