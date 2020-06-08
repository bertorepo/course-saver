package com.fujitsu.ph.tsup.domain.francisco;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
@ActiveProfiles({"postgres-test-francisco"})
@AutoConfigureTestDatabase(replace=Replace.NONE) 
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
        Employee employee = new Employee.Builder(new Long(1), "12345", "delacruz", "juan", "abc@fujitsu.com", "jdcruz").build();
        employeeDao.save(employee);
        
        Employee dbEmployee = employeeDao.findById(employee.getId());
        assertEquals("12345", dbEmployee.getNumber());
        assertEquals("delacruz", dbEmployee.getLastName());
        assertEquals("juan", dbEmployee.getFirstName());
        assertEquals("jdcruz", dbEmployee.getUsername());
    }
    
    @Test
    void test_NotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            employeeDao.findById(6L);
        });
        
    }

}
