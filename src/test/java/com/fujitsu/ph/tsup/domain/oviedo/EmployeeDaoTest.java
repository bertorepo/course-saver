package com.fujitsu.ph.tsup.domain.oviedo;

import static org.junit.jupiter.api.Assertions.*;

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
@ActiveProfiles({"postgres-test-oviedo"})
@AutoConfigureTestDatabase(replace = Replace.NONE)
class EmployeeDaoTest {
	@Autowired
	private EmployeeDao edao;

	 @TestConfiguration
	    static class TestContextConfiguration {

	        @Bean
	        public EmployeeDao employeeDao() {
	            return new EmployeeDaoImpl();
	        }
	    }
	@Test
	void test() {
		 	Employee emp1 = new Employee.Builder("1001", "Oviedo", "Thomas", "t.oviedo@gmail.com", "thom").build();
	        Long eId1 = edao.saveEmployee(emp1);

	        Employee emp2 = new Employee.Builder("1002", "Osamu", "Dazai", "d.osamu@gmail.com", "daz").build();
	        Long eId2 = edao.saveEmployee(emp2);


	        Employee dc1 = edao.findById(21L);
	        assertEquals("1001", emp1.getEmpNumber());

	        Employee dc2 = edao.findById(22L);
	        assertEquals("1002", emp1.getEmpNumber());
	}

	@Test
    void test_NotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
        	edao.findById(1L);
        });

    }
}
