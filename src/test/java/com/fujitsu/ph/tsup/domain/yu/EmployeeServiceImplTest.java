package com.fujitsu.ph.tsup.domain.yu;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

import com.fujitsu.ph.tsup.domain.yu.EmployeeService;
import com.fujitsu.ph.tsup.domain.yu.EmployeeDao;
import com.fujitsu.ph.tsup.domain.yu.Employee;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

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
    public void testSave() {
        Employee c = new Employee.Builder((long) 1, "220053487", "Yu", "Jhaisy",
                "j.yu@fujitsu.com", "j.yu").build();
        service.save(c);
        assertEquals(c.getNumber(), "220053487");
        assertEquals(c.getId(), new Long(1));
        assertEquals(c.getLastName(), "Yu");
        assertEquals(c.getFirstName(), "Jhaisy");
        assertEquals(c.getEmailAddress(), "j.yu@fujitsu.com");
        assertEquals(c.getUserName(), "j.yu");
    }

    @Test
    public void testSaveError() {
        Employee c = new Employee.Builder((long) 2, "784350022", "Yu", "Jhaisy",
                "j.yu@fujitsu.com", "j.yu").build();
        service.save(c);
        assertEquals(c.getNumber(), "784350022");
        assertEquals(c.getId(), new Long(2));
        assertEquals(c.getLastName(), "Yu");
        assertEquals(c.getFirstName(), "Jhaisy");
        assertEquals(c.getEmailAddress(), "j.yu@fujitsu.com");
        assertEquals(c.getUserName(), "j.yu");
    }

    @Test
    public void testFindAll() {
        Set<Employee> c = new HashSet<Employee>();
        c.add(new Employee.Builder((long) 1, "220053487", "Yu", "Jhaisy Jade",
                "j.yu@fujitsu.com", "j.yu").build());
        when(employeeDao.findAll()).thenReturn(c);
        assertEquals(service.findAll().size(), c.size());
    }

    @Test
    public void testFindAllError() {
        Set<Employee> c = new HashSet<Employee>();
        assertEquals(service.findAll().size(), c.size());
    }

    @Test
    public void testFindById() {
        when(employeeDao.findById(anyLong()))
                .thenReturn(createEmployeeFindById());
        Employee c = service.findById((long) 1);
        assertEquals(c.getNumber(), "220053487");
    }

    @Test
    public void testFindByIdError() {
        when(employeeDao.findById(anyLong()))
                .thenReturn(createEmployeeFindByIdError());
        Employee c = service.findById((long) 2);
        assertEquals(c.getNumber(), "784350022");
    }

    private Employee createEmployeeFindById() {
        return new Employee.Builder((long) 1, "220053487", "Yu", "Jhaisy Jade",
                "j.yu@fujitsu.com", "j.yu").build();
    }
    private Employee createEmployeeFindByIdError() {
        return new Employee.Builder((long) 2, "784350022", "Yu", "Jhaisy Jade",
                "j.yu@fujitsu.com", "j.yu").build();
    }
}
