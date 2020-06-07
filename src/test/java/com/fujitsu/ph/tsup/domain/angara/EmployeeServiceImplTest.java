package com.fujitsu.ph.tsup.domain.angara;

import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {

    // two test each to create
    // (1) successful retrieval (2) error/dao throws exceptions

    @TestConfiguration
    static class TestingConfiguration {

        @Bean
        EmployeeService employeeService() {
            return new EmployeeServiceImpl();
        }
    }

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeDao employeeDao;

    // testing save
    @Test
    public void TestSave() {
        Employee e = new Employee.Builder((long) 1, "20202020", "Angara", "Mary Rose", "m.angara@fujitsu.com",
                "mangara").build();
        employeeService.save(e);
        assertEquals(e.getEmployeeNumber(), "20202020");
        assertEquals(e.getLastName(), "Angara");
        assertEquals(e.getFirstName(), "Mary Rose");
        assertEquals(e.getEmailAddress(), "m.angara@fujitsu.com");
        assertEquals(e.getUserName(), "mangara");
        assertEquals(e.getId(), new Long(1));
    }

    @Test
    public void TestSaveError() {
        Employee e = new Employee.Builder((long) 2, "20202020", "Angara", "Mary Rose", "m.angara@fujitsu.com",
                "mangara").build();
        employeeService.save(e);
        assertEquals(e.getEmployeeNumber(), "12345678");
        assertEquals(e.getLastName(), "Angara");
        assertEquals(e.getFirstName(), "Mary Rose");
        assertEquals(e.getEmailAddress(), "m.angara@fujitsu.com");
        assertEquals(e.getUserName(), "mangara");
        assertEquals(e.getId(), new Long(2));
    }

    // testing findAll
    @Test
    public void TestFindAll() {
        Set<Employee> e = new HashSet<Employee>();
        e.add(new Employee.Builder((long) 1, "20202020", "Angara", "Mary Rose", "m.angara@fujitsu.com", "mangara")
                .build());
        when(employeeDao.findAll()).thenReturn(e);
        assertEquals(employeeService.findAll().size(), e.size());
    }

    @Test
    public void TestFindAllError() {
        Set<Employee> e = new HashSet<Employee>();
        assertEquals(employeeService.findAll().size(), e.size());
    }

    // testing findById

}
