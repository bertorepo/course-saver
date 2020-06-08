package com.fujitsu.ph.tsup.domain.rivera;

import java.util.HashSet;
import java.util.Set;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
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
    
    private Employee createEmployeeId() {
        return new Employee.Builder(1000L, "2151736", "rivera", "michael", "m.rivera@fujitsu.com", "m.rivera").build();
    }
    
    @Test
    public void testSave(){
        Employee employee = new Employee.Builder(1000L, "2151736", "rivera", "michael", "m.rivera@fujitsu.com", "m.rivera").build();
        service.save(employee);
        assertEquals(employee.getId(), new Long(1000));
        assertEquals(employee.getEmployeeNumber(), "2151736");
        assertEquals(employee.getLastName(), "rivera");
        assertEquals(employee.getFirstName(), "michael");
        assertEquals(employee.getEmailAddress(), "m.rivera@fujitsu.com");
        assertEquals(employee.getUserName(), "m.rivera");
    }
    
    
    @Test
    public void testFindAll() {
        Set<Employee> employee = new HashSet<Employee>();
        employee.add(new Employee.Builder(1000L, "2151736", "rivera", "michael", "m.rivera@fujitsu.com", "m.rivera").build());
        when(employeeDao.findAll()).thenReturn(employee);
        assertEquals(service.findAll().size(), employee.size());
    }
    
    
}