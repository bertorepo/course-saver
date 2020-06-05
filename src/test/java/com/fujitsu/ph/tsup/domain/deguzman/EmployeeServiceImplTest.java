package com.fujitsu.ph.tsup.domain.deguzman;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

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
    public void testSave(){
        Employee c = new Employee.Builder((long)1, "1234567890", "de Guzman", "Jeamel", "jeamel.deguzman@gmail.com", "jm.deguzman").build();
        service.save(c);
        assertEquals(c.getNumber(), "1234567890");
        assertEquals(c.getId(), new Long(1));
        assertEquals(c.getLastName(), "de Guzman");
        assertEquals(c.getFirstName(), "Jeamel");
        assertEquals(c.getEmailAddress(), "jeamel.deguzman@gmail.com");
        assertEquals(c.getUserName(), "jm.deguzman");
    }


    @Test
    public void testSaveErr() {
        Employee c = new Employee.Builder((long)1, "1111111111", "de Guzman", "Jeamel", "jeamel.deguzman@gmail.com", "jm.deguzman").build();
        service.save(c);
        assertEquals(c.getNumber(), "1111111111");
        assertEquals(c.getId(), new Long(1));
        assertEquals(c.getLastName(), "de Guzman");
        assertEquals(c.getFirstName(), "Jeamel");
        assertEquals(c.getEmailAddress(), "jeamel.deguzman@gmail.com");
        assertEquals(c.getUserName(), "jm.deguzman");
    }
    

    @Test
    public void testFindAll(){
        Set<Employee> c = new HashSet<Employee>();
        c.add(new Employee.Builder((long)1, "1234567890", "de Guzman", "Jeamel", "jeamel.deguzman@gmail.com", "jm.deguzman").build());
        when(employeeDao.findAll()).thenReturn(c);
        assertEquals(service.findAll().size(), c.size());
    }

    @Test
    public void testFindAllErr() {
        Set<Employee> c = new HashSet<Employee>();
        assertEquals(service.findAll().size(), c.size());
    }
    
    @Test
    public void testFindById(){
        when(employeeDao.findById(anyLong()))
        .thenReturn(createEmployeeFindById());
        Employee c = service.findById((long) 1);
        assertEquals(c.getNumber(), "1234567890");
    }

    @Test
    public void testFindByIdErr() {
        when(employeeDao.findById(anyLong()))
        .thenReturn(createEmployeeFindByIdErr());
        Employee c = service.findById((long) 123);
        assertEquals(c.getNumber(), "1111111111");
    }
    
    private Employee createEmployeeFindById() {
        return new Employee.Builder((long)1, "1234567890", "de Guzman", "Jeamel", "jeamel.deguzman@gmail.com", "jm.deguzman").build();
    }
    private Employee createEmployeeFindByIdErr() {
        return new Employee.Builder((long)0, "1111111111", "de Guzman", "Jeamel", "jeamel.deguzman@gmail.com", "jm.deguzman").build();
    }

}
