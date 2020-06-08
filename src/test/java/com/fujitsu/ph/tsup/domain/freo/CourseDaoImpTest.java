package com.fujitsu.ph.tsup.domain.freo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
@ActiveProfiles({"postgres-test-freo"})
@AutoConfigureTestDatabase(replace=Replace.NONE)

public class CourseDaoImpTest {
	 @Autowired
	    private CourseDao cdao;
	    
	    @TestConfiguration
	    static class TestContextConfiguration{
	        
	        @Bean
	        public CourseDao CourseDao() {
	            return new CourseDaoImp();
	        }            
	    }
	    
	    @Test
	    void test() {
	        Course cs = new Course.Builder("SDEM").builder();
	        cdao.save(cs);
	        Long cId = cdao.returnGeneratedKey();
	        System.out.println("ID: "+ cId);
	        
	        Course csId = cdao.findById(cId);
	        System.out.println("Course Name: "+ csId.getCourseName());
	        assertEquals("SDEM", csId.getCourseName());
	        
	        Course cs1 = new Course.Builder("SDEM1").builder();
	        cdao.save(cs1);
	        Long cId1 = cdao.returnGeneratedKey();
	        System.out.println("ID: "+ cId1);
	        
	        Course courseId2 = cdao.findById(cId1);
	        System.out.println("Course Name: "+ courseId2.getCourseName());
	        assertEquals("SDEM1", cs1.getCourseName());
	        
	        Set<Course> csSet = cdao.findAll();
	        assertNotNull(csSet.size());
	        
	    }    
	    @Test
	    void Test_NotFound() {
	        assertThrows(EmptyResultDataAccessException.class, () -> {
	            cdao.findById(1L);
	        });
	    }
}
