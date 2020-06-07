package com.fujitsu.ph.tsup.domain.francisco;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

public class EmployeeServiceImplTest {

    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        EmployeeService service() {
            return new EmployeeServiceImpl();
        }
    }

    @Autowired
    private EmployeeService service;

    @MockBean
    private EmployeeDao dao;

    @Test
    void testSave() {
        Employee employee = new Employee.Builder(new Long(1), "123","dela cruz", "juan","abc@gmail.com","juandc").build();
        assertEquals(employee.getId(), new Long(1));
        assertEquals(employee.getNumber(), "123");
        assertEquals(employee.getLastName(), "dela cruz");
        assertEquals(employee.getFirstName(), "juan");
        assertEquals(employee.getEmailAddress(), "abc@gmail.com");
        assertEquals(employee.getUsername(), "juandc");
    }

    @Test
    void testSaveError() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee.Builder(new Long(1), "123","dela cruz", "juan","abc@gmail.com","").build();
        });

        String expectedMessage = "username should not be empty!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testFindAll() {
        Set<Employee> employee = new HashSet<Employee>();
        employee.add(new Employee.Builder(new Long(1), "123","delacruz", "juan","abc@gmail.com","juandc").build());
        employee.add(new Employee.Builder(new Long(2), "456","garcia", "jose","def@gmail.com","joseg").build());
        when(dao.findAll()).thenReturn(employee);
        assertEquals(service.findAll().size(), employee.size());
    }

    @Test
    void testFindAllError() {
        when(dao.findAll()).thenThrow(new IllegalArgumentException("Application Error!"));
        Exception exception = assertThrows(ApplicationException.class, () -> {
            service.findAll().size();
        });

        String expectedMessage = "Application Error!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testFindById() {
        Employee c = new Employee.Builder(new Long(5), "123123123","delacruz", "juan","abc@gmail.com","juandc").build();
        when(dao.findById(c.getId())).thenReturn(c);
        Employee e = service.findById(c.getId());
        assertEquals(c.getId(), e.getId());
        assertEquals(c.getNumber(), e.getNumber());
        assertEquals(c.getLastName(), e.getLastName());
        assertEquals(c.getFirstName(), e.getFirstName());
        assertEquals(c.getEmailAddress(), e.getEmailAddress());
        assertEquals(c.getUsername(), e.getUsername());
    }

    @Test
    void testFindByIdError() {
        when(dao.findById(any(Long.class))).thenThrow(new IllegalArgumentException("Application Error!"));
        Exception exception = assertThrows(ApplicationException.class, () -> {
            service.findById(any(Long.class));
        });

        String expectedMessage = "Application Error!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
