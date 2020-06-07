package com.fujitsu.ph.tsup.domain.yu;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

import com.fujitsu.ph.tsup.domain.yu.CourseScheduleService;
import com.fujitsu.ph.tsup.domain.yu.CourseScheduleDao;
import com.fujitsu.ph.tsup.domain.yu.CourseSchedule;

@RunWith(SpringRunner.class)
public class CourseScheduleServiceImplTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @TestConfiguration
    static class CourseScheduleServiceImplTestContextConfiguration {

        @Bean
        CourseScheduleService courseScheduleService() {
            return new CourseScheduleServiceImpl();
        }

    }

    @Autowired
    private CourseScheduleService service;

        @MockBean
        private CourseScheduleDao courseScheduleDao;

    @Test
    public void testSave() {
        CourseSchedule c = new CourseSchedule.Builder((long) 1, (long) 1,
                (long) 1, (long) 1, 1, 1, "A").build();
        service.save(c);
        assertEquals(c.getCourseId(), new Long(1));
        assertEquals(c.getInstructorId(), new Long(1));
        assertEquals(c.getVenueId(), new Long(1));
        assertEquals(c.getId(), new Long(1));
        assertEquals(c.getMinRequired(), 1);
        assertEquals(c.getMaxAllowed(), 1);
        assertEquals(c.getStatus(), "A");
    }

    @Test
    public void testSaveError() {
        CourseSchedule c = new CourseSchedule.Builder((long) 2, (long) 2,
                (long) 2, (long) 2, 2, 2, "C").build();
        service.save(c);
        assertEquals(c.getCourseId(), new Long(2));
        assertEquals(c.getInstructorId(), new Long(2));
        assertEquals(c.getVenueId(), new Long(2));
        assertEquals(c.getId(), new Long(2));
        assertEquals(c.getMinRequired(), 2);
        assertEquals(c.getMaxAllowed(), 2);
        assertEquals(c.getStatus(), "C");
    }

    @Test
    public void testFindById() {
        when(courseScheduleDao.findById(anyLong()))
                .thenReturn(createCourseScheduleFindById());
        CourseSchedule c = service.findById((long) 1);
        assertEquals(c.getStatus(), "A");
    }
    
    private CourseSchedule createCourseScheduleFindById() {
        return new CourseSchedule.Builder((long) 1, (long) 1, (long) 1,
                (long) 1, 1, 1, "A").build();
    }
  
    @Test
    public void testFindByIdError() {
        when(courseScheduleDao.findById(anyLong()))
                .thenReturn(createCourseScheduleFindByIdError());
        CourseSchedule c = service.findById((long) 2);
        assertEquals(c.getStatus(), "C");
    }

    private CourseSchedule createCourseScheduleFindByIdError() {
        return new CourseSchedule.Builder((long) 2, (long) 2, (long) 2,
                (long) 2, 2, 2, "C").build();
    }
}