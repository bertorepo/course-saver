package com.fujitsu.ph.tsup.domain.abad;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @TestConfiguration
    static class EmployeeServiceImplestContextConfiguration {
        
        @Bean
        EmployeeService employeeService() {
            return new EmployeeServiceImpl();
        }
    }
    
    @Autowired
    private EmployeeService service;
    
    @MockBean
    private EmployeeDao employeeDao;
    
    @Test
    public void testFindById() {
        when(employeeDao.findById(anyLong()))
            .thenReturn(createEmployeeId());
        Employee employee = service.findById(1000L);
        assertEquals(employee.getId(), new Long(1000));
    }
    
    @Test
    public void testFindById_Unmatched() {
        when(employeeDao.findById(any(Long.class)))
        .thenThrow(new ServiceException("Employee not found"));
    
        Exception exception = assertThrows(ServiceException.class, () -> {
        service.findById(1000L);
        });
    
        String expectedMessage = "Employee not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    private Employee createEmployeeId() {
        return new Employee.Builder(1000L, "12345", "abad", "kenneth", "k.abad@fujitsu.com", "k.abad").build();
    }
    
    @Test
    public void testSave(){
        Employee employee = new Employee.Builder(1000L, "12345", "abad", "kenneth", "k.abad@fujitsu.com", "k.abad").build();
        service.save(employee);
        assertEquals(employee.getId(), new Long(1000));
        assertEquals(employee.getEmployeeNumber(), "12345");
        assertEquals(employee.getLastName(), "abad");
        assertEquals(employee.getFirstName(), "kenneth");
        assertEquals(employee.getEmailAddress(), "k.abad@fujitsu.com");
        assertEquals(employee.getUserName(), "k.abad");
    }
    
    @Test
    public void testSaveUnmatched(){
        Employee employee = createEmployeeId();
        doThrow(new ServiceException("Employee Id not saved")).
            when(employeeDao).save(any(Employee.class));
            
        Exception exception = assertThrows(ServiceException.class, () -> {
            service.save(employee);
                
        });

        String expectedMessage = "Employee Id not saved";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
 
    
    @Test
    public void testFindAll() {
        Set<Employee> employee = new HashSet<Employee>();
        employee.add(new Employee.Builder(1000L, "12345", "abad", "kenneth", "k.abad@fujitsu.com", "k.abad").build());
        when(employeeDao.findAll()).thenReturn(employee);
        assertEquals(service.findAll().size(), employee.size());
    }
    
    @Test
    public void testFindAllUnmatched() {
        doThrow(new ServiceException("Record not found")).
        when(employeeDao).findAll();
    
        Exception exception = assertThrows(ServiceException.class, () -> {
        service.findAll();    
        });

        String expectedMessage = "Record not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
}