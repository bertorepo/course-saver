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
public class CourseServiceImplTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@TestConfiguration
	static class CourseServiceImplTestContextConfiguration {

		@Bean
		CourseService courseService() {
			return new CourseServiceImpl();
		}

	}

	@Autowired
	private CourseService service;

	@MockBean
	private CourseDao courseDao;

	@Test
	public void testFindById() {
		when(courseDao.findById(anyLong())).thenReturn(createCourseId());
		Course course = service.findById(100L);
		assertEquals(course.getId(), new Long(100));
	}

	@Test
	public void testFindAll() {
		Set<Course> course = new HashSet<Course>();
		course.add(new Course.Builder(100L, "Java Programming").build());
		when(courseDao.findAll()).thenReturn(course);
		assertEquals(service.findAll().size(), course.size());
	}

	@Test
	public void testSave() {
		Course course = new Course.Builder(100L, "Java Programming").build();
		service.save(course);
		assertEquals(course.getId(), new Long(100));
		assertEquals(course.getCourseName(), "Java Programming");
	}

	private Course createCourseId() {
		return new Course.Builder(new Long(100), "Java Programming").build();

	}

}