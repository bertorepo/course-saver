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
public class EmployeeDaoImplTest {
        
    @Autowired
    private EmployeeDao employeeDao;
    
    @TestConfiguration
    static class TestContextConfiguration{
        
        @Bean
        public EmployeeDao employeeDao() {
            return new EmployeeDaoImpl();
        }            
    }
    
    @Test
    void test() {
        Employee emp1 = new Employee.Builder("1234", "HELLLLLLLOOO", "HIIIIIIIIIIII", "HIIIIIIIIIIII@example.com", "HIIIIIIIIIIII").builder();
        employeeDao.save(emp1);
        Long eId1 = employeeDao.returnGeneratedKey();
        System.out.println("ID: "+ eId1);
        
        Employee empId1 = employeeDao.findById(eId1);
        System.out.println("Employee Number: "+ empId1.getEmpNum());
        assertEquals("1234", empId1.getEmpNum());
        
        System.out.println("First Name: "+ empId1.getFirstName());
        assertEquals("HELLLLLLLOOO", empId1.getFirstName());
        
        System.out.println("Last Name: "+ empId1.getLastName());
        assertEquals("HIIIIIIIIIIII", empId1.getLastName());
        
        System.out.println("Email Address: "+ empId1.getEmailAddress());
        assertEquals("HIIIIIIIIIIII@example.com", empId1.getEmailAddress());
        
        System.out.println("Username: "+ empId1.getUserName());
        assertEquals("HIIIIIIIIIIII", empId1.getUserName());
        
        
        Employee emp2 = new Employee.Builder("1235", "HELLO", "HI", "HI@example.com", "HI").builder();
        employeeDao.save(emp2);
        Long eId2 = employeeDao.returnGeneratedKey();
        System.out.println("ID: "+ eId2);
        
        Employee empId2 = employeeDao.findById(eId2);
        System.out.println("Employee Number: "+ empId2.getEmpNum());
        assertEquals("1235", empId2.getEmpNum());
        
        System.out.println("First Name: "+ empId2.getFirstName());
        assertEquals("HELLO", empId2.getFirstName());
        
        System.out.println("Last Name: "+ empId2.getLastName());
        assertEquals("HI", empId2.getLastName());
        
        System.out.println("Email Address: "+ empId2.getEmailAddress());
        assertEquals("HI@example.com", empId2.getEmailAddress());
        
        System.out.println("Username: "+ empId2.getUserName());
        assertEquals("HI", empId2.getUserName());
        
        Set<Employee> empSet = employeeDao.findAll();
        assertNotNull(empSet.size());
        
    }
    
    @Test
    void Test_NotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            employeeDao.findById(1L);
        });
    }
}



