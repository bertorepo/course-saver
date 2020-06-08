package com.fujitsu.ph.tsup.domain.rivera;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

@JdbcTest
@ActiveProfiles({"postgres-test-rivera"})
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
        Employee employeeOne = new Employee.Builder("2151736", "rivera", "michael", "m.rivera@fujitsu.com", "m.rivera").build();
        employeeDao.save(employeeOne);
        Long empOne = employeeDao.saveEmployee();
        
        Employee employeeTwo = new Employee.Builder("2157345", "employee", "two", "e.two@fujitsu.com", "e.two").build();
        employeeDao.save(employeeTwo);
        Long empTwo = employeeDao.saveEmployee();
        
        System.out.println("ID1: " + empOne);
        Employee dbEmployeeOne = employeeDao.findById(empOne);   
        
        assertEquals("2151736", dbEmployeeOne.getEmployeeNumber());        
        assertEquals("rivera", dbEmployeeOne.getLastName());        
        assertEquals("michael", dbEmployeeOne.getFirstName());       
        assertEquals("m.rivera@fujitsu.com", dbEmployeeOne.getEmailAddress());       
        assertEquals("m.rivera", dbEmployeeOne.getUserName());
        
        System.out.println("ID2: " + empTwo);      
        Employee dbEmployeeTwo = employeeDao.findById(empTwo);
        
        assertEquals("2157345", dbEmployeeTwo.getEmployeeNumber());       
        assertEquals("employee", dbEmployeeTwo.getLastName());        
        assertEquals("two", dbEmployeeTwo.getFirstName());        
        assertEquals("e.two@fujitsu.com", dbEmployeeTwo.getEmailAddress());        
        assertEquals("e.two", dbEmployeeTwo.getUserName());
         
        Set<Employee> employeeAll = employeeDao.findAll();
        assertNotNull(employeeAll.size());       
    }
    
    @Test
    void NoTestFound() {
        assertThrows(EmptyResultDataAccessException.class, () ->{
            employeeDao.findById(1L);
        });
    }
}
