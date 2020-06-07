package com.fujitsu.ph.tsup.domain.oviedo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.ActiveProfiles;

@JdbcTest
@ActiveProfiles({"postgres-test-oviedo"})
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CourseDaoImplTest {

	@Autowired
	private CourseDao cdao;

	 @TestConfiguration
	    static class TestContextConfiguration {

	        @Bean
	        public CourseDao courseDao() {
	            return new CourseDaoImpl();
	        }
	    }

	 @Test
	    void test() {

	        Course course1 = new Course.Builder("ITS 021").build();
	        Long cId1 = cdao.saveCourse(course1);
	        System.out.println("ID1:" + cId1);
	        Course course2 = new Course.Builder("ITS 053").build();
	        Long cId2 = cdao.saveCourse(course2);
	        System.out.println("ID1:" + cId2);

	        Course dc1 = cdao.findById(cId1);
	        assertEquals("ITS 021", dc1.getCourse());

	        Course dc2 = cdao.findById(cId2);
	        assertEquals("ITS 053", dc2.getCourse());

	    }

	    @Test
	    void test_NotFound() {
	        assertThrows(EmptyResultDataAccessException.class, () -> {
	        	cdao.findById(1L);
	        });

	    }

}
