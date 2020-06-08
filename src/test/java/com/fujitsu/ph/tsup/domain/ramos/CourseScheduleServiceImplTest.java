package com.fujitsu.ph.tsup.domain.ramos;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
public class CourseScheduleServiceImplTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@TestConfiguration
	static class CourseScheduleServiceImplTestContextConfiguration {

		@Bean
		CourseScheduleService courseSchedule() {
			return new CourseScheduleServiceImpl();
		}

	}

	@Autowired
	private CourseScheduleService service;

	@MockBean
	private CourseScheduleDao courseScheduleDao;
	
	@Test
	public void testFindById() {
		when(courseScheduleDao.findById(anyLong())).thenReturn(createCourseSchedule());
		CourseSchedule courseSchedule = service.findById(100L);
		assertEquals(courseSchedule.getId(), new Long(100));
	}
	
    @Test
    public void testFindAll() {
        Set<CourseSchedule> courseSchedule = new HashSet<CourseSchedule>();
        courseSchedule.add(new CourseSchedule.Builder(1000L, 2000L, 3000L, 5, 5, "R").build());
        when(courseScheduleDao.findAll()).thenReturn(courseSchedule);
        assertEquals(service.findAll().size(), courseSchedule.size());
    }
    
    @Test
    public void testSave() {
        CourseSchedule courseSchedule = new CourseSchedule.Builder(1000L, 2000L, 3000L, 5, 5, "R").build();
        service.save(courseSchedule);
        assertEquals(courseSchedule.getCourseId(), new Long(1000));
        assertEquals(courseSchedule.getInstructorId(), new Long(2000));
        assertEquals(courseSchedule.getVenueId(), new Long(3000));
        assertEquals(courseSchedule.getMinRequired(), 5);
        assertEquals(courseSchedule.getMaxAllowed(), 5);
        assertEquals(courseSchedule.getStatus(), "R");
    }
    
	private CourseSchedule createCourseSchedule() {
		return new CourseSchedule.Builder(1000L, 2000L, 3000L, 5, 5, "R").build();

	}
}
