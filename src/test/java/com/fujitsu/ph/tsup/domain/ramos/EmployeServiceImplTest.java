package com.fujitsu.ph.tsup.domain.ramos;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
public class EmployeServiceImplTest {
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
		when(employeeDao.findById(anyLong())).thenReturn(createEmployeeId());
		Employee e = service.findById(100L);
		assertEquals(e.getId(), new Long(100));
	}
	
    @Test
    public void testFindAll() {
        Set<Employee> employee = new HashSet<Employee>();
        employee.add(new Employee.Builder("0123456", "ramos", "ramon", "ramonparadaramos@gmail.com", "r.ramos").build());
        when(employeeDao.findAll()).thenReturn(employee);
        assertEquals(service.findAll().size(), employee.size());
    }
    
    @Test
    public void testSave(){
        Employee employee = new Employee.Builder("0123456", "ramos", "ramon", "ramonparadaramos@gmail.com", "r.ramos").build();
        service.save(employee);
        assertEquals(employee.getEmployeeNumber(), "0123456");
        assertEquals(employee.getLastName(), "ramos");
        assertEquals(employee.getFirstName(), "ramon");
        assertEquals(employee.getEmailAddress(), "ramonparadaramos@gmail.com");
        assertEquals(employee.getUserName(), "r.ramos");
    }
	
	private Employee createEmployeeId() {
		return new Employee.Builder("0123456", "ramos", "ramon", "ramonparadaramos@gmail.com", "r.ramos").build();

	}
}
