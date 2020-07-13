package com.fujitsu.ph.tsup.authz.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fujitsu.ph.tsup.authz.domain.EmployeeAuth;
import com.fujitsu.ph.tsup.common.domain.Auth;
import com.fujitsu.ph.tsup.common.domain.Employee;

@RunWith(SpringRunner.class)
@JdbcTest
@ActiveProfiles({ "postgres-test" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AuthorizationDaoTest {
	@Autowired
	private AuthorizationDao dao;

	@TestConfiguration
	static class TestContextConfiguration {
		@Bean
		public AuthorizationDao employeeDao() {
			return new AuthorizationDaoImpl();
		}
	}

	@Test
	public void testFindByUsername() {
		String username = "l.lorenzo";
		Set<EmployeeAuth> result = dao.findByUsername(username);
		assertTrue(result.size() == 2);
		for (EmployeeAuth value : result) {
			assertEquals(value.getUsername(), username);
			if (value.getAuthzSet().toString().equalsIgnoreCase("PMO")) {
				assertEquals(value.getAuthzSet().toString(), Auth.PMO.toString());
			} else if (value.getAuthzSet().toString().equalsIgnoreCase("INSTRUCTOR")) {
				assertEquals(value.getAuthzSet().toString(), Auth.INSTRUCTOR.toString());
			} else if (value.getAuthzSet().toString().equalsIgnoreCase("MEMBER")) {
				assertEquals(value.getAuthzSet().toString(), Auth.MEMBER.toString());
			}
		}
	}
	
	@Test
	public void testFindDetailsByUsername() {
		String username = "j.macabudbud";
		Employee employee = dao.findDetailsByUsername(username);
		assertEquals(employee.getId(), 4L);
		assertEquals(employee.getUsername(), username);
		assertEquals(employee.getEmailAddress(), "j.macabudbud@fujitsu.com");
		assertEquals(employee.getFirstName(), "JAY IAN");
		assertEquals(employee.getLastName(), "MACABUDBUD");
		assertEquals(employee.getNumber(), "A15893");
		
	}

}
