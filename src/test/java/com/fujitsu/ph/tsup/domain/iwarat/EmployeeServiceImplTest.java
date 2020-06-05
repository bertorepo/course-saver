package com.fujitsu.ph.tsup.domain.iwarat;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceImplTest {
    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        EmployeeService employeeService() {
            return new EmployeeServiceImpl();
        }
    }

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeDao employeeDao;

    @Test
    void testSave() {

        doNothing().when(employeeDao).save(any(Employee.class));

    }

    @Test
    void testSaveError() {
        Employee saveE = createEmployee();

        doThrow(new IllegalArgumentException("error")).when(employeeDao).save(any(Employee.class));
        Exception exception = assertThrows(MyException.class, () -> {
            employeeService.save(saveE);
        });

        String expectedMessage = "Error found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    void testFindById() {
        Employee expected = createEmployee();
        when(employeeDao.findById(any(Long.class))).thenReturn(expected);

        Employee employees = employeeService.findById(7L);

        assertEquals(expected.getId(), employees.getId());
        assertEquals(expected.getNumber(), employees.getNumber());
        assertEquals(expected.getLastName(), employees.getLastName());
        assertEquals(expected.getFirstName(), employees.getFirstName());
        assertEquals(expected.getEmailAddress(), employees.getEmailAddress());
        assertEquals(expected.getUsername(), employees.getUsername());

    }

    @Test
    void testFindByIdError() {
        when(employeeDao.findById(any(Long.class))).thenThrow(new IllegalArgumentException("error"));
        Exception exception = assertThrows(MyException.class, () -> {
            employeeService.findById(7L);
        });

        String expectedMessage = "Error found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testFindAll() {
        Set<Employee> Option = new HashSet<Employee>();
        Option.add(
                new Employee.Builder(7L, "10131219", "Iwarat", "Harvey", "j.iwarat@fujitus.com", "j.iwarat").build());
        Option.add(
                new Employee.Builder(8L, "12191013", "Iwarat", "Jhon", "jh.iwarat@fujitus.com", "jh.iwarat").build());
        Option.add(new Employee.Builder(9L, "19131012", "Iwarat", "Vey", "v.iwarat@fujitus.com", "v.iwarat").build());
        Option.add(
                new Employee.Builder(10L, "13191210", "Iwarat", "Veytz", "vz.iwarat@fujitus.com", "vz.iwarat").build());

        when(employeeDao.findAll()).thenReturn(Option);
        assertEquals(4, Option.size());

    }

    @Test
    void testFindAllError() {
        when(employeeDao.findAll()).thenThrow(new IllegalArgumentException("error"));
        Exception exception = assertThrows(MyException.class, () -> {
            employeeService.findAll().size();
        });

        String expectedMessage = "Error found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    private Employee createEmployee() {
        return new Employee.Builder(7L, "10131219", "Iwarat", "Harvey", "j.iwarat@fujitus.com", "j.iwarat").build();
    }

}
