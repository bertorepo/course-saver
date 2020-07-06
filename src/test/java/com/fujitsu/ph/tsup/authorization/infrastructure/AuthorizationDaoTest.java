package com.fujitsu.ph.tsup.authorization.infrastructure;

import static org.junit.Assert.assertTrue;

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

import com.fujitsu.ph.tsup.authz.core.model.EmployeeAuth;
import com.fujitsu.ph.tsup.authz.dao.AuthorizationDao;
import com.fujitsu.ph.tsup.authz.dao.AuthorizationDaoImpl;

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
		EmployeeAuth result = dao.findByUsername(username);
		System.out.println(result);
		assertTrue(result.getUsername().equals(username));
		assertTrue(result.getAuthzSet().contains("PMO"));
	}

}
