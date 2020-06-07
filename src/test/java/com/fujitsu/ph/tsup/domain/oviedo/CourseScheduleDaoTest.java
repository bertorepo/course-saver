package com.fujitsu.ph.tsup.domain.oviedo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
@JdbcTest
@ActiveProfiles({"postgres-test-oviedo"})
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CourseScheduleDaoTest {
	@Autowired
	private CourseScheduleDao chdao;

	 @TestConfiguration
	    static class TestContextConfiguration {

	        @Bean
	        public CourseScheduleDao courseScheduleDao() {
	            return new CourseScheduleDaoImpl();
	        }
	    }
	@Test
	void test() {
		CourseSchedule cs1 = new CourseSchedule.Builder(1001L, 10001L, 100001L, 30, 40, 'o').build();
	    Long chId1 = chdao.saveCourseSchedule(cs1);

	    CourseSchedule cs2 = new CourseSchedule.Builder(1002L, 10002L, 100002L, 30, 40, 'o').build();
	    Long chId2 = chdao.saveCourseSchedule(cs1);


	    CourseSchedule dch1 = chdao.findById(101L);
	    assertEquals(101L, dch1.getId());

	    CourseSchedule dch2 = chdao.findById(102L);
	    assertEquals(102L, dch2.getId());

	}

	@Test
	void test_NotFound() {
	    assertThrows(EmptyResultDataAccessException.class, () -> {
    	chdao.findById(1L);
        });

    }
}
