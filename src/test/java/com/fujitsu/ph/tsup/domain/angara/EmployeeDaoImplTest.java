package com.fujitsu.ph.tsup.domain.angara;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.AccessException;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.ActiveProfiles;

@JdbcTest
@ActiveProfiles({ "postgres-test-angara" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EmployeeDaoImplTest {

    @Autowired
    private EmployeeDao employeeDao;
    
    @TestConfiguration
    static class TestingConfiguration {
        
        @Bean
        public EmployeeDao employeeDao() {
            return new EmployeeDaoImpl();
        }
    }
    
    @Test
    public void test() {
        Employee employee1 = new Employee.Builder("12345678", "00001111", "Mary Rose", "Angara", "m.angara", "mangara").build();
        Long e = employeeDao.save(employee1);
        System.out.println("ID1: " + e);
        
        Employee dbEmployee = employeeDao.findById(e);
        assertEquals("12345678", dbEmployee.getId());
        assertEquals("00001111", dbEmployee.getEmployeeNumber());
        assertEquals("Mary Rose", dbEmployee.getFirstName());
        assertEquals("Angara", dbEmployee.getLastName());
        assertEquals("m_angara", dbEmployee.getEmailAddress());
        assertEquals("mangara", dbEmployee.getUserName());
        
        Set<Employee> emp = employeeDao.findAll();
        assertNotNull(emp.size());
    }
    
    @Test
    void notFound_Test() {
        assertThrows(AccessException.class, () -> {
            employeeDao.findById(1L);
        });
        
        
    }

}
