package com.fujitsu.ph.tsup.domain.angara;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CourseScheduleServiceImplTest {

    // two test each to create
    // (1) successful retrieval (2) error/dao throws exceptions

    @TestConfiguration
    static class TestingConfiguration {

        @Bean
        CourseScheduleService courseScheduleService() {
            return new CourseScheduleServiceImpl();
        }

    }

    @Autowired
    private CourseScheduleService courseScheduleService;

    @MockBean
    private CourseScheduleDao courseScheduleDao;

    // testing save
    @Test
    public void TestSave() {
        CourseSchedule cs = new CourseSchedule.Builder((long) 1, (long) 1, (long) 1, (long) 1, 1, 1, "A").build();
        courseScheduleService.save(cs);
        assertEquals(cs.getCourseId(), new Long(1));
        assertEquals(cs.getInstructorId(), new Long(1));
        assertEquals(cs.getVenueId(), new Long(1));
        assertEquals(cs.getId(), new Long(1));
        assertEquals(cs.getMinRequired(), 1);
        assertEquals(cs.getMaxRequired(), 1);
        assertEquals(cs.getStatus(), "A");
    }

    @Test
    public void TestSaveError() {
        CourseSchedule cs = new CourseSchedule.Builder((long) 2, (long) 2, (long) 2, (long) 2, 2, 2, "B").build();
        courseScheduleService.save(cs);
        assertEquals(cs.getCourseId(), new Long(2));
        assertEquals(cs.getInstructorId(), new Long(2));
        assertEquals(cs.getVenueId(), new Long(2));
        assertEquals(cs.getId(), new Long(2));
        assertEquals(cs.getMinRequired(), 2);
        assertEquals(cs.getMaxRequired(), 2);
        assertEquals(cs.getStatus(), "B");
    }
    
    // testing findAll
    // testing findById

}