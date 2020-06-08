package com.fujitsu.ph.tsup.domain.macabugao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

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
@ActiveProfiles({"postgres-test-macabugao"})
@AutoConfigureTestDatabase(replace=Replace.NONE)

class CourseDaoImplTest {

	@Autowired
	private CourseDao courseDao;
	
	@TestConfiguration
    static class TestContextConfiguration {
        
        @Bean
        public CourseDao courseDao() {
        	return new CourseDaoImpl();
        }
    }
    
	
	@Test
	void test() {
		Course course1 = new Course.Builder("Programming").build();
		courseDao.save(course1);
		Long id1 = courseDao.generatedKey();
	    System.out.println("ID1:" + id1);

		
		Course course2 = new Course.Builder("Programming2").build();
		courseDao.save(course2);
		Long id2 = courseDao.generatedKey();
	    System.out.println("ID1:" + id2);

		
		Course dbCourse1 = courseDao.findById(id1);
		assertEquals("Programming", dbCourse1.getCourseName());
		
		Course dbCourse2 = courseDao.findById(id2);
		assertEquals("Programming2", dbCourse2.getCourseName());
		
		Set<Course> courseSet = courseDao.findAll();
		assertNotNull(courseSet.size());
		
	}
	@Test
	void Test_NotFound() {
		assertThrows(EmptyResultDataAccessException.class, () -> {
			courseDao.findById(1L);
	    });
		
	}

}
