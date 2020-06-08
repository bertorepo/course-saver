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
public class EmployeeDaoImpTest {
	   @Autowired
	    private EmployeeDao employeeDao;
	    
	    @TestConfiguration
	    static class TestContextConfiguration{
	        
	        @Bean
	        public EmployeeDao employeeDao() {
	            return new EmployeeDaoImp();
	        }            
	    }
	    
	    @Test
	    void test() {
	        Employee emp = new Employee.Builder(202006L, "202007L", "faith", "freo", "faithfreo@yy.com", "faithfreo").builder();
	        employeeDao.save(emp);
	        Long eId1 = employeeDao.returnGeneratedKey();
	        System.out.println("ID: "+ eId1);
	        
	        Employee empId1 = employeeDao.findById(eId1);
	        System.out.println("Employee Number: "+ empId1.getEmployeeNumber());
	        assertEquals("202007L", empId1.getEmployeeNumber());
	        
	        System.out.println("First Name: "+ empId1.getFirstName());
	        assertEquals("faith", empId1.getFirstName());
	        
	        System.out.println("Last Name: "+ empId1.getLastName());
	        assertEquals("freo", empId1.getLastName());
	        
	        System.out.println("Email Address: "+ empId1.getEmailAddress());
	        assertEquals("faithfreo@yy.com", empId1.getEmailAddress());
	        
	        System.out.println("Username: "+ empId1.getUserName());
	        assertEquals("faithfreo", empId1.getUserName());
	        
	        
	        Employee emp2 = new Employee.Builder(0L, "202007L", "faith","freo", "faithfreo@yy.com", "faithfreo").builder();
	        employeeDao.save(emp2);
	        Long eId2 = employeeDao.returnGeneratedKey();
	        System.out.println("ID: "+ eId2);
	        
	        Employee empId2 = employeeDao.findById(eId2);
	        System.out.println("Employee Number: "+ empId2.getEmployeeNumber());
	        assertEquals("202007L", empId2.getEmployeeNumber());
	        
	        System.out.println("First Name: "+ empId2.getFirstName());
	        assertEquals("faith", empId2.getFirstName());
	        
	        System.out.println("Last Name: "+ empId2.getLastName());
	        assertEquals("freo", empId2.getLastName());
	        
	        System.out.println("Email Address: "+ empId2.getEmailAddress());
	        assertEquals("fathfreo@yy.com", empId2.getEmailAddress());
	        
	        System.out.println("Username: "+ empId2.getUserName());
	        assertEquals("faithfreo", empId2.getUserName());
	        
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
