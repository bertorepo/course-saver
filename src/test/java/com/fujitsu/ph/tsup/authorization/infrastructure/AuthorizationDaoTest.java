package com.fujitsu.ph.tsup.authorization.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

import com.fujitsu.ph.tsup.authz.dao.AuthorizationDao;
import com.fujitsu.ph.tsup.authz.dao.AuthorizationDaoImpl;
import com.fujitsu.ph.tsup.authz.domain.EmployeeAuth;
import com.fujitsu.ph.tsup.common.domain.Auth;

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

}
