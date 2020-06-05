package com.fujitsu.ph.tsup.domain.deguzman;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
public class CourseScheduleServiceImplTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none(); 
    @TestConfiguration
    static class CourseScheduleServiceImplestContextConfiguration {
        
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
    public void testSave(){
        CourseSchedule c = new CourseSchedule.Builder((long) 1, (long) 1, (long) 1, (long) 1, 5, 100, "A").build();
        service.save(c);
        assertEquals(c.getStatus(), "A");
        assertEquals(c.getCourseId(), new Long(1));
        assertEquals(c.getInstructorId(), new Long(1));
        assertEquals(c.getVenueId(), new Long(1));
        assertEquals(c.getId(), new Long(1));
        assertEquals(c.getMinRequired(), 5);
        assertEquals(c.getMaxAllowed(), 100);
    }
    @Test
    public void testSaveErr() {
        CourseSchedule c = new CourseSchedule.Builder((long) 0, (long) 0, (long)0, (long) 0, 5, 100, "C").build();
        service.save(c);
        assertEquals(c.getStatus(), "C");
        assertEquals(c.getCourseId(), new Long(0));
        assertEquals(c.getInstructorId(), new Long(0));
        assertEquals(c.getVenueId(), new Long(0));
        assertEquals(c.getId(), new Long(0));
        assertEquals(c.getMinRequired(), 5);
        assertEquals(c.getMaxAllowed(), 100);
    }
    
    @Test
    public void testFindAll(){
        Set<CourseSchedule> c = new HashSet<CourseSchedule>();
        c.add(new CourseSchedule.Builder((long) 1, (long) 1, (long) 1, (long) 1, 5, 100, "A").build());
        when(courseScheduleDao.findAll()).thenReturn(c);
        assertEquals(service.findAll().size(), c.size());
    }
    @Test
    public void testFindAllErr() {
        Set<CourseSchedule> c = new HashSet<CourseSchedule>();
        assertEquals(service.findAll().size(), c.size());
    }
    
    @Test
    public void testFindById(){
        when(courseScheduleDao.findById(anyLong()))
        .thenReturn(createCourseScheduleFindById());
        CourseSchedule c = service.findById((long) 1);
        assertEquals(c.getStatus(), "A");
    }
    @Test
    public void testFindByIdErr() {
        when(courseScheduleDao.findById(anyLong()))
        .thenReturn(createCourseScheduleFindByIdErr());
        CourseSchedule c = service.findById((long) 1);
        assertEquals(c.getStatus(), "C");
    }
    
    private CourseSchedule createCourseScheduleFindById() {
        return new CourseSchedule.Builder((long) 1, (long) 1, (long) 1, (long) 1, 5, 100, "A").build();
    }
    private CourseSchedule createCourseScheduleFindByIdErr() {
        return new CourseSchedule.Builder((long) 0, (long) 1, (long) 1, (long) 1, 5, 100, "C").build();
    }
}