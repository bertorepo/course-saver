package com.fujitsu.ph.tsup.domain.macabugao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;

@JdbcTest
@ActiveProfiles({ "postgres-test-macabugao" })
@AutoConfigureTestDatabase(replace = Replace.NONE)

class CourseScheduleDaoImplTest {

	@Autowired
	private CourseScheduleDao courseScheduleDao;
	
	@TestConfiguration
    static class TestContextConfiguration {
        
        @Bean
        public CourseScheduleDao courseScheduleDao() {
        	return new CourseScheduleDaoImpl();
        }
    }
    

	@Test
	void test() {
		CourseSchedule courseSchedule1 = new CourseSchedule.Builder(1111L, 2222L, 3333L, 5, 5, "B").build();
		courseScheduleDao.save(courseSchedule1);
		Long id1 = courseScheduleDao.generatedKey();
		System.out.println("ID:" + id1);

		CourseSchedule courseSchedule2 = new CourseSchedule.Builder(1111L, 2222L, 3333L, 5, 5, "B").build();
		courseScheduleDao.save(courseSchedule2);
		Long id2 = courseScheduleDao.generatedKey();
		System.out.println("ID:" + id2);

		CourseSchedule dbCourseSched1 = courseScheduleDao.findById(id1);
		assertEquals(1111L, dbCourseSched1.getCourseId());
		assertEquals(2222L, dbCourseSched1.getInstructorId());
		assertEquals(3333L, dbCourseSched1.getVenueId());
		assertEquals(5, dbCourseSched1.getMinRequired());
		assertEquals(5, dbCourseSched1.getMaxAllowed());
		assertEquals("B", dbCourseSched1.getStatus());

		CourseSchedule dbCourseSched2 = courseScheduleDao.findById(id2);
		assertEquals(1111L, dbCourseSched2.getCourseId());
		assertEquals(2222L, dbCourseSched2.getInstructorId());
		assertEquals(3333L, dbCourseSched2.getVenueId());
		assertEquals(5, dbCourseSched2.getMinRequired());
		assertEquals(5, dbCourseSched2.getMaxAllowed());
		assertEquals("B", dbCourseSched2.getStatus());

		Set<CourseSchedule> courseScheduleSet = courseScheduleDao.findAll();
		assertNotNull(courseScheduleSet.size());
	}

	@Test
	void Test_NotFound() {
		assertThrows(EmptyResultDataAccessException.class, () -> {
			courseScheduleDao.findById(1L);
		});
	}

}
