package com.fujitsu.ph.tsup.domain.cabiling;

import static org.junit.jupiter.api.Assertions.*;
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

import com.fujitsu.ph.tsup.domain.iwarat.MyException;

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
		Exception exception = assertThrows(EmployeeException.class, () -> {
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

		Employee emp = employeeService.findById(7L);

		assertEquals(expected.getId(), emp.getId());
		assertEquals(expected.getEmpNum(), emp.getEmpNum());
		assertEquals(expected.getLastName(), emp.getLastName());
		assertEquals(expected.getFirstName(), emp.getFirstName());
		assertEquals(expected.getEmailAddress(), emp.getEmailAddress());
		assertEquals(expected.getUserName(), emp.getUserName());

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
				new Employee.Builder(9L, "9266591", "first", "last", "mymail@fujitus.com", "myuname").builder());

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
		return new Employee.Builder(9L, "9266591", "first", "last", "mymail@fujitus.com", "myuname").builder();
	}
}