package com.fujitsu.ph.tsup.domain.jimenez;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ActiveProfiles;


@JdbcTest
@ActiveProfiles({"postgres-test-jimenez"})
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class CourseScheduleDaoImplTest {
        
    @Autowired
    private CourseScheduleDao CourseScheduleDao;
    
    @TestConfiguration
    static class TestContextConfiguration{
        
        @Bean
        public CourseScheduleDao CourseScheduleDao() {
            return new CourseScheduleDaoImpl();
        }            
    }
    
    @Test
    void test() {
        CourseSchedule crseSched1 = new CourseSchedule.Builder(123434L, 123434L, 123434L, 5, 5, 'A').builder();
        CourseScheduleDao.save(crseSched1);
        Long csId1 = CourseScheduleDao.returnGeneratedKey();
        System.out.println("ID: "+ csId1);
        
        CourseSchedule crseSchedId1 = CourseScheduleDao.findById(csId1);
        System.out.println("Course ID: "+ crseSchedId1.getCourseId());
        assertEquals(123434L, crseSchedId1.getCourseId());
        
        System.out.println("Instructor ID: "+ crseSchedId1.getInstructorId());
        assertEquals(123434L, crseSchedId1.getInstructorId());
        
        System.out.println("Venue ID: "+ crseSchedId1.getVenueId());
        assertEquals(123434L, crseSchedId1.getVenueId());
        
        System.out.println("Minimum Required: "+ crseSchedId1.getMinRequired());
        assertEquals(5, crseSchedId1.getMinRequired());
        
        System.out.println("Max Allowed: "+ crseSchedId1.getMaxAllowed());
        assertEquals(5, crseSchedId1.getMaxAllowed());
        
        System.out.println("Status: "+ crseSchedId1.getStatus());
        assertEquals('A', crseSchedId1.getStatus());
        
        
        CourseSchedule crseSched2 = new CourseSchedule.Builder(123435L, 123435L, 123435L, 5, 5, 'P').builder();
        CourseScheduleDao.save(crseSched2);
        Long csId2 = CourseScheduleDao.returnGeneratedKey();
        System.out.println("ID: "+ csId2);
        
        CourseSchedule crseSchedId2 = CourseScheduleDao.findById(csId2);
        System.out.println("Course ID: "+ crseSchedId2.getCourseId());
        assertEquals(123435L, crseSchedId2.getCourseId());
        
        System.out.println("Instructor ID: "+ crseSchedId2.getInstructorId());
        assertEquals(123435L, crseSchedId2.getInstructorId());
        
        System.out.println("Venue ID: "+ crseSchedId2.getVenueId());
        assertEquals(123435L, crseSchedId2.getVenueId());
        
        System.out.println("Minimum Required: "+ crseSchedId2.getMinRequired());
        assertEquals(5, crseSchedId2.getMinRequired());
        
        System.out.println("Max Allowed: "+ crseSchedId2.getMaxAllowed());
        assertEquals(5, crseSchedId2.getMaxAllowed());
        
        System.out.println("Status: "+ crseSchedId2.getStatus());
        assertEquals('P', crseSchedId2.getStatus());
        
        Set<CourseSchedule> crseSchedSet = CourseScheduleDao.findAll();
        assertNotNull(crseSchedSet.size());
        
    }
    
    @Test
    void Test_NotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            CourseScheduleDao.findById(1L);
        });
    }
}


