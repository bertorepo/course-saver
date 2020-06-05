package com.fujitsu.ph.tsup.domain.macabugao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {

	@TestConfiguration
	static class TestContextConfiguration {

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
	void testSaveWithError() {
		Employee employee = createEmployee();

		doThrow(new DataRetrievalFailureException("error")).when(employeeDao).save(any(Employee.class));

		Exception exception = assertThrows(IllegalApplicationException.class, () -> {
			employeeService.save(employee);
		});

		String expectedMessage = "Employee not found";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testFindAll() {
		
		Employee expectedResult = createEmployee();
		when(employeeDao.findAll()).thenReturn((Set<Employee>) expectedResult);

		Set<Employee> employee = employeeService.findAll();
		
		assertEquals(expectedResult.getFirstName(), employee.getClass());

	}

	@Test
	void testFindAllWithError() {
		
		doThrow(new DataRetrievalFailureException("error")).when(employeeDao).findAll();

		Exception exception = assertThrows(IllegalApplicationException.class, () -> {
			employeeService.findAll();
	    });
		
		String expectedMessage = "Cannot find Employee";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testFindById() {
		Employee expectedResult = createEmployee();
		when(employeeDao.findById(any(Long.class))).thenReturn(expectedResult);

		Employee employee = employeeService.findById(1L);

		assertEquals(expectedResult.getId(), employee.getId());
	}

	@Test
	void testFindByIdWithError() {
		when(employeeDao.findById(any(Long.class))).thenThrow(new DataRetrievalFailureException("error"));

		Exception exception = assertThrows(IllegalApplicationException.class, () -> {
			employeeService.findById(1L);
		});

		String expectedMessage = "Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	private Employee createEmployee() {
		return new Employee.Builder(1L, "12345", "Janella", "Macabugao", "j.macabugao@fujitsu.com", "Janella").build();

	}

	private Long createId() {

		return 1L;
	}

}
