package com.fujitsu.ph.tsup.domain.jimenez;

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
public class EmployeeServiceImplTest {
    @TestConfiguration
    static class TestContextConfiguration {
        
        @Bean
        EmployeeService EmployeeService() {
            /*
             * returns the implementation to be injected
             */
            return new EmployeeServiceImpl();
        }
    }

    @Autowired
    private EmployeeService employeeService;
  
    
    @MockBean
    private EmployeeDao employeeDao;
    
    
    @Test
    void testSave() {
        doThrow(new DataRetrievalFailureException("error")).when(employeeDao).save(null);
        
        Employee Employee = createEmployee();        
        employeeService.save(Employee);     
        
        assertEquals(Employee.getId(), 123434L);
        assertEquals(Employee.getEmpNum(), 1234L);
        assertEquals(Employee.getFirstName(), "HELLLLLLLOOO");
        assertEquals(Employee.getLastName(), "HIIIIIIIIIIII");
        assertEquals(Employee.getEmailAddress(), "HIIIIIIIIIIII@example.com");
        assertEquals(Employee.getUserName(), "HIIIIIIIIIIII");
    }
    
    @Test
    void testSaveEx() {
        doThrow(new DataRetrievalFailureException("error")).when(employeeDao).save(null);
        
        Employee employ = createErrEmployee();
        
        Exception eException = assertThrows(EmployeeException.class, () -> {
            employeeService.save(employ);
        });
        
        String expectedMessage = "Employee Id should not be zero or less than zero.";
        String actualMessage = eException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        
    }



    @Test
    void testFindById() {
        Employee createemploy = createEmployee();
        when(employeeDao.findById(any(Long.class)))
            .thenReturn(createemploy);
        
        Employee Employee = employeeService.findById(123434L);
        
        assertEquals(createemploy.getId(), Employee.getId());
;
    }

    @Test
    void testFindById_NotFound() {
        when(employeeDao.findById(any(Long.class)))
            .thenThrow(new DataRetrievalFailureException("error"));
        
        Exception eException = assertThrows(EmployeeException.class, () -> {
            employeeService.findById(1L);
        });
        
        String expectedMessage = "Employee not found!";
        String actualMessage = eException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }   
    
    
    @Test
    void testFindAll() {
        Set<Employee> employ = new HashSet<Employee>();
        employ.add(createEmployee());
        when(employeeDao.findAll()).thenReturn(employ);
        assertEquals(employeeService.findAll().size(), employ.size()); 
    }
    
    @Test
    void testFindAll_NotFound() {
        Exception eException = assertThrows(EmployeeException.class, () -> {
            employeeService.findAll();
        });
        
        String expectedMessage = "Can't find Employee Details";
        String actualMessage = eException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    private Employee createEmployee() {
        return new Employee.Builder(123434L, 1234L, "HELLLLLLLOOO", "HIIIIIIIIIIII", "HIIIIIIIIIIII@example.com", "HIIIIIIIIIIII").builder(); 
    }
    
    private Employee createErrEmployee() {
       return new Employee.Builder(0L, 1234L, "HELLLLLLLOOO", "HIIIIIIIIIIII", "HIIIIIIIIIIII@example.com", "HIIIIIIIIIIII").builder();
    }
    
}


