package com.fujitsu.ph.tsup.domain.oviedo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {

	@TestConfiguration
	static class EmployeeServiceContextConfiguration {
		@Bean
		EmployeeService employeeService() {
			return new EmployeeServiceImpl();
		}
	}

	@Autowired
	EmployeeService service;

	@MockBean
	EmployeeDao cDao;


	@Test
	void testFindByID() {
		Employee expected = createEmployee();
		when(cDao.findById(anyLong())).thenReturn(expected);
		Employee employee = service.findById(12L);
		assertEquals(employee.getId(), 12L);


	}

	@Test
	void testFindByID_invalid() {
		when(cDao.findById(anyLong())).thenThrow(new ApplicationException("error"));
		Exception exception = assertThrows(ApplicationException.class, () -> {
			service.findById(1L);
		});

		String expectedMessage = "Employee not found";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testFindAll() {
		Employee expected = createEmployee();
		when(cDao.findById(anyLong())).thenReturn(expected);
		Employee employee = service.findById(12L);
		assertEquals(expected, employee);
	}

	private Employee createEmployee() {
		return new Employee.Builder(10L, "1001", "Oviedo", "Thomas", "t.oviedo@gmail.com", "thom").build();
	}


}
