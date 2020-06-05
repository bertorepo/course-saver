package com.fujitsu.tsup.domain.macabudbud;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fujitsu.ph.tsup.domain.macabudbud.*;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceImplTest {
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
	void testFindAll() {
		Set<Employee> employees = new HashSet<Employee>();
		employees.add(new Employee.Builder("A102110", "MACABUDBUD", "JAY IAN", "j.macabudbud@fujitsu.com", "j.macabudbud").build());

		when(employeeDao.findAll()).thenReturn(employees);
		assertEquals(1, employees.size());
	}

	@Test
	void testFindAllWithError() {
		when(employeeDao.findAll()).thenThrow(new DataRetrievalFailureException("error"));
		Exception exception = assertThrows(ApplicationException.class, () -> {
			service.findAll();
		});

		String expectedMessage = "Employee Schedule could not be save";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testSave() {
		doNothing().when(employeeDao).save(any(Employee.class));
	}

	@Test
	void testSaveWithEror() {
		doThrow(new IllegalArgumentException("error")).when(employeeDao).save(any(Employee.class));

		Exception exception = assertThrows(ApplicationException.class, () -> {
			service.save(this.createEmployee());
		});

		String expectedMessage = "Employee Schedule could not be save";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testFindById() {
		Employee result = createEmployee();
		when(employeeDao.findById(any(Long.class))).thenReturn(result);

		Employee employee = service.findById(1L);

		assertEquals(result.getId(), employee.getId());
	}

	@Test
	void testFindByIdWithError() {
		when(employeeDao.findById(any(Long.class))).thenThrow(new DataRetrievalFailureException("error"));

		Exception exception = assertThrows(ApplicationException.class, () -> {
			service.findById(1L);
		});

		String expectedMessage = "Employee Schedule not found";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	private Employee createEmployee() {
		return new Employee.Builder("A102110", "MACABUDBUD", "JAY IAN", "j.macabudbud@fujitsu.com", "j.macabudbud").build();

	}
}
