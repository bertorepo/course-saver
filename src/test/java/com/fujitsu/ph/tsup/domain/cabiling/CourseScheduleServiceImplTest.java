package com.fujitsu.ph.tsup.domain.cabiling;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CourseScheduleServiceImplTest {

	@TestConfiguration
	static class TestContextConfiguration {

		@Bean
		CourseScheduleService courseScheduleService() {

			return new CourseScheduleServiceImpl();

		}
	}

	@Autowired
	private CourseScheduleService cSchedService;

	@MockBean
	private CourseScheduleDao courseSchedDao;

	@Test
	public void testSave1() {
		CourseSchedule sched = new CourseSchedule.Builder((long) 100, (long) 1000, (long) 10000, (long) 100, 3, 50, 'A')
				.cSched();
		cSchedService.save(sched);
		assertEquals(sched.getSts(), 'A');
		assertEquals(sched.getId(), new Long(100));
		assertEquals(sched.getCourseId(), new Long(1000));
		assertEquals(sched.getInstructorId(), new Long(10000));
		assertEquals(sched.getVenueId(), new Long(100));
		assertEquals(sched.getMinRequired(), new Integer(3));
		assertEquals(sched.getMaxAllowed(), new Integer(50));

	}

	@Test
	public void testSave2() {
		CourseSchedule sched = new CourseSchedule.Builder((long) 100, (long) 1000, (long) 10000, (long) 100, 3, 50, 'A')
				.cSched();
		cSchedService.save(sched);
		assertEquals(sched.getSts(), 'D');
		assertEquals(sched.getId(), new Long(1000));
		assertEquals(sched.getCourseId(), new Long(10000));
		assertEquals(sched.getInstructorId(), new Long(100));
		assertEquals(sched.getVenueId(), new Long(1000));
		assertEquals(sched.getMinRequired(), new Integer(6));
		assertEquals(sched.getMaxAllowed(), new Integer(500));

	}

	@Test
	public void testFindAll1() {
		Set<CourseSchedule> sched = new HashSet<CourseSchedule>();
		sched.add(new CourseSchedule.Builder((long) 100, (long) 1000, (long) 10000, (long) 100, 3, 50, 'A').cSched());
		when(courseSchedDao.findAll()).thenReturn(sched);
		assertEquals(cSchedService.findAll().size(), sched.size());
	}

	@Test
	public void testFindAll2() {
		Set<CourseSchedule> sched = new HashSet<CourseSchedule>();
		sched.add(new CourseSchedule.Builder((long) 1000, (long) 100, (long) 100, (long) 1000, 3, 50, 'A').cSched());
		assertEquals(cSchedService.findAll().size(), sched.size());
	}

	@Test
	public void testFindById1() {
		when(courseSchedDao.findById(anyLong())).thenReturn(createCourseScheduleFindById1());
		CourseSchedule sched = cSchedService.findById((long) 100);
		assertEquals(sched.getId(), new Long (100));
	}

	@Test
	public void testFindById2() {
		when(courseSchedDao.findById(anyLong())).thenReturn(createCourseScheduleFindById2());
		CourseSchedule sched = cSchedService.findById((long) 1000);
		assertEquals(sched.getId(), new Long (100));
	}

	private CourseSchedule createCourseScheduleFindById1() {
		return new CourseSchedule.Builder((long) 100, (long) 1000, (long) 10000, (long) 100, 3, 50, 'A').cSched();
	}

	private CourseSchedule createCourseScheduleFindById2() {
		return new CourseSchedule.Builder((long) 1000, (long) 100, (long) 1000, (long) 1000, 5, 60, 'B').cSched();
	}

}
