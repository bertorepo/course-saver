/**
 * 
 */
package com.fujitsu.ph.tsup.authz.service;

import static org.junit.Assert.assertEquals;
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

import com.fujitsu.ph.tsup.authz.dao.AuthorizationDao;
import com.fujitsu.ph.tsup.authz.domain.EmployeeAuth;
import com.fujitsu.ph.tsup.common.domain.Employee;

/**
 * @author j.macabudbud
 *
 */
@ExtendWith(SpringExtension.class)
class AuthorizationServiceTest {
	@TestConfiguration
	static class TestContextConfiguration {
		@Bean
		AuthorizationService authorizationService() {
			return new AuthorizationServiceImpl();
		}
	}

	@Autowired
	private AuthorizationService service;

	@MockBean
	private AuthorizationDao dao;

	@Test
	void testFindByUsename() {
		String username = "l.lorenzo";

		Set<EmployeeAuth> expected = createAuth();
		when(dao.findByUsername(username)).thenReturn(expected);

		Set<EmployeeAuth> employee = service.findByUsername(username);
		assertEquals(expected, employee);
	}
	
	@Test
	void testFindDetailsByUsername() {
		String username = "l.lorenzo";
		Employee expected = createEmployee();
		when(dao.findDetailsByUsername(username)).thenReturn(expected);
		
		Employee employee = service.findDetailsByUsername(username);
		assertEquals(expected, employee);
		
	}
	
	private Employee createEmployee() {
		return new Employee.Builder(1L, "A012514", "JAY IAN", "MACABUDBUD", 
									"j.macabudbud@fujitsu.com", "j.macabudbud").build();
	}

	private Set<EmployeeAuth> createAuth() {
		Set<String> authzSet1 = new HashSet<>();
		authzSet1.add("PMO");

		Set<String> authzSet2 = new HashSet<>();
		authzSet2.add("MEMBER");

		Set<EmployeeAuth> user = new HashSet<EmployeeAuth>();
		user.add(new EmployeeAuth.Builder(1L, "l.lorenzo", authzSet1).build());
		user.add(new EmployeeAuth.Builder(2L, "l.lorenzo", authzSet2).build());
		return user;
	}

}
