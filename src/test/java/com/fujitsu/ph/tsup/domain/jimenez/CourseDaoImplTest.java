package com.fujitsu.ph.tsup.domain.jimenez;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ActiveProfiles;

@JdbcTest
@ActiveProfiles({"postgres-test-jimenez"})
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class CourseDaoImplTest {
        
    @Autowired
    private CourseDao courseDao;
    
    @TestConfiguration
    static class TestContextConfiguration{
        
        @Bean
        public CourseDao CourseDao() {
            return new CourseDaoImpl();
        }            
    }
    
    @Test
    void test() {
        Course crse1 = new Course.Builder("SpringBoot").builder();
        courseDao.save(crse1);
        Long cId1 = courseDao.returnGeneratedKey();
        System.out.println("ID: "+ cId1);
        
        Course crseId1 = courseDao.findById(cId1);
        System.out.println("Course Name: "+ crseId1.getName());
        assertEquals("SpringBoot", crseId1.getName());
        
        Course crse2 = new Course.Builder("Git").builder();
        courseDao.save(crse2);
        Long cId2 = courseDao.returnGeneratedKey();
        System.out.println("ID: "+ cId2);
        
        Course crseId2 = courseDao.findById(cId2);
        System.out.println("Course Name: "+ crseId2.getName());
        assertEquals("Git", crseId2.getName());
        
        Set<Course> crseSet = courseDao.findAll();
        assertNotNull(crseSet.size());
    }
    
    @Test
    void Test_NotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            courseDao.findById(1L);
        });
    }
}

