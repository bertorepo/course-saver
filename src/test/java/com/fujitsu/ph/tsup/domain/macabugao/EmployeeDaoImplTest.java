package com.fujitsu.ph.tsup.domain.macabugao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

@JdbcTest
@ActiveProfiles({ "postgres-test-macabugao" })
@AutoConfigureTestDatabase(replace = Replace.NONE)

class EmployeeDaoImplTest {

	@Autowired
	private EmployeeDao employeeDao;
	
	@TestConfiguration
    static class TestContextConfiguration {
        
        @Bean
        public EmployeeDao employeeDao() {
        	return new EmployeeDaoImpl();
        }
    }
	
	@Test
	void test() {
		Employee employee1 = new Employee.Builder("1234567891", "Janella", "Macabugao", "macabugaoj@fujitsu.com", "ella").build();
		employeeDao.save(employee1);
		Long id1 = employeeDao.generatedKey();
		System.out.println("ID:" + id1);

		Employee employee2 = new Employee.Builder("1234567891", "Janella", "Macabugao", "macabugaoj@fujitsu.com", "ella").build();
		employeeDao.save(employee2);
		Long id2 = employeeDao.generatedKey();
		System.out.println("ID:" + id2);
		
		Employee dbEmployee1 = employeeDao.findById(id1);
		assertEquals("1234567891", dbEmployee1.getNumber());
		assertEquals("Janella", dbEmployee1.getFirstName());
		assertEquals("Macabugao", dbEmployee1.getLastName());
		assertEquals("macabugaoj@fujitsu.com", dbEmployee1.getEmailAddress());
		assertEquals("ella", dbEmployee1.getUserName());
		
		Employee dbEmployee2 = employeeDao.findById(id1);
		assertEquals("1234567891", dbEmployee2.getNumber());
		assertEquals("Janella", dbEmployee2.getFirstName());
		assertEquals("Macabugao", dbEmployee2.getLastName());
		assertEquals("macabugaoj@fujitsu.com", dbEmployee2.getEmailAddress());
		assertEquals("ella", dbEmployee2.getUserName());
		
		Set<Employee> empSet = employeeDao.findAll();
		assertNotNull(empSet.size());
	}
	
	@Test
	void Test_NotFound() {
		assertThrows(EmptyResultDataAccessException.class, () -> {
			employeeDao.findById(1L);
		});
	}

}
