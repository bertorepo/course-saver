package com.fujitsu.ph.tsup.domain.abad;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.assertj.core.util.Arrays;
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
@ActiveProfiles({"postgres-test-abad"})
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EmployeeDaoImplTest {

    @Autowired
    private EmployeeDao employeeDao;
    
    @TestConfiguration
    static class TestContextConfiguration {
        
        @Bean
        public EmployeeDao employeeDao() {
            return new EmployeeDaoImpl();
        }
    }
    
    @Test
    void test() {
        Employee employeeOne = new Employee.Builder("202020", "abad", "kenneth", "k.abad@fujitsu.com", "kt.abad").build();
        employeeDao.save(employeeOne);
        Long empOne = employeeDao.saveEmployee();
        System.out.println("ID1: " + empOne);
        
        Employee dbEmployeeOne = employeeDao.findById(empOne);
        
        System.out.println("Employee Number: " + dbEmployeeOne.getEmployeeNumber());
        assertEquals("2020", dbEmployeeOne.getEmployeeNumber());
        
        System.out.println("Employee Number: " + dbEmployeeOne.getLastName());
        assertEquals("abad", dbEmployeeOne.getLastName());
        
        System.out.println("Employee Number: " + dbEmployeeOne.getFirstName());
        assertEquals("kenneth", dbEmployeeOne.getFirstName());
        
        System.out.println("Employee Number: " + dbEmployeeOne.getEmailAddress());
        assertEquals("k.abad@fujitsu.com", dbEmployeeOne.getEmailAddress());
        
        System.out.println("Employee Number: " + dbEmployeeOne.getUserName());
        assertEquals("kt.abad", dbEmployeeOne.getUserName());
        
        Employee employeeTwo = new Employee.Builder("303030", "velasco", "monica", "m.a.velasco@gmail.com", "m.velasco").build();
        employeeDao.save(employeeTwo);
        Long empTwo = employeeDao.saveEmployee();
        System.out.println("ID2: " + empTwo);
        
        Employee dbEmployeeTwo = employeeDao.findById(empTwo);
        
        System.out.println("Employee Number: " + dbEmployeeTwo.getEmployeeNumber());
        assertEquals("3030", dbEmployeeTwo.getEmployeeNumber());
        
        System.out.println("Employee Number: " + dbEmployeeTwo.getLastName());
        assertEquals("velasco", dbEmployeeTwo.getLastName());
        
        System.out.println("Employee Number: " + dbEmployeeTwo.getFirstName());
        assertEquals("monica", dbEmployeeTwo.getFirstName());
        
        System.out.println("Employee Number: " + dbEmployeeTwo.getEmailAddress());
        assertEquals("m.a.velasco@gmail.com", dbEmployeeTwo.getEmailAddress());
        
        System.out.println("Employee Number: " + dbEmployeeTwo.getUserName());
        assertEquals("m.velasco", dbEmployeeTwo.getUserName());

         
        Set<Employee> employee = employeeDao.findAll();
        assertNotNull(employee.size());
        
    }
    
    @Test
    void NoTestFound() {
        assertThrows(EmptyResultDataAccessException.class, () ->{
            employeeDao.findById(1L);
        });
    }
}
