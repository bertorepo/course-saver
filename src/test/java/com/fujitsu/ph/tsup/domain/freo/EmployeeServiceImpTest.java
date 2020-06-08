package com.fujitsu.ph.tsup.domain.freo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
class EmployeeServiceImpTest {

	 @TestConfiguration
	    static class TestContextConfiguration {
	        
	        @Bean
	        EmployeeService EmployeeService() {
	         
	            return new EmployeeServiceImp();
	        }
 	    }

	    @Autowired
	    private EmployeeService employeeService;
	  
	    
	    @MockBean
	    private EmployeeDao empdao;
	    
	    
	    @Test
	    void testSave() {
	        doThrow(new DataRetrievalFailureException("error occurs")).when(empdao).save(null);
	        
	        Employee Employee = createEmployee();        
	        employeeService.save(Employee);     
	        
	        assertEquals(Employee.getId(), 202006L);
	        assertEquals(Employee.getEmployeeNumber(), "202007");
	        assertEquals(Employee.getFirstName(), "faith");
	        assertEquals(Employee.getLastName(), "freo");
	        assertEquals(Employee.getEmailAddress(), "faithfreo@yy.com");
	        assertEquals(Employee.getUserName(), "faithfreo");
	    }
	    
	    @Test
	    void testSaveEx() {
	        doThrow(new DataRetrievalFailureException("error occurs")).when(empdao).save(null);
	        
	        Employee em = createEmployee();
	        
	        Exception eeException = assertThrows(EmployeeException.class, () -> {
	            employeeService.save(em);
	        });
	        
	        String expectedMessage = "Employee Id should not be zero or less than zero.";
	        String actualMessage = eeException.getMessage();
	        assertTrue(actualMessage.contains(expectedMessage));
	        
	    }



	    @Test
	    void testFindById() {
	        Employee createemploy = createEmployee();
	        when(empdao.findById(any(Long.class)))
	            .thenReturn(createemploy);
	        
	        Employee Employee = employeeService.findById(202006L);
	        
	        assertEquals(createemploy.getId(), Employee.getId());

	    }

	    @Test
	    void testFindById_NotFound() {
	        when(empdao.findById(any(Long.class)))
	            .thenThrow(new DataRetrievalFailureException("error occurs"));
	        
	        Exception eeException = assertThrows(EmployeeException.class, () -> {
	            employeeService.findById(20200L);
	        });
	        
	        String expectedMessage = "Employee ID not found!";
	        String actualMessage = eeException.getMessage();
	        assertTrue(actualMessage.contains(expectedMessage));
	    }   
	    
	    
	    @Test
	    void testFindAll() {
	        Set<Employee> emp = new HashSet<Employee>();
	        emp.add(createcsEmployee());
	        when(empdao.findAll()).thenReturn(emp);
	        assertEquals(employeeService.findAll().size(), emp.size()); 
	    }
	    
	    @Test
	    void testFindAll_NotFound() {
	        Exception eeException = assertThrows(EmployeeException.class, () -> {
	            employeeService.findAll();
	        });
	        
	        String expectedMessage = "Can't find any Employee details on List";
	        String actualMessage = eeException.getMessage();
	        assertTrue(actualMessage.contains(expectedMessage));
	    }

	    private Employee createEmployee() {
	        return new Employee.Builder(202006L, "202007", "faith", "freo", "faithfreo@yy.com", "faithfreo").builder(); 
	    }
	    
	    private Employee createcsEmployee() {
	       return new Employee.Builder(0L, "202007", "faith","freo", "faithfreo@yy.com", "faithfreo").builder();
	    }
	    
	}
