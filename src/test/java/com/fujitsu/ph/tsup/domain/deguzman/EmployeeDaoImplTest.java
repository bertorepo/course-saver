package com.fujitsu.ph.tsup.domain.deguzman;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ActiveProfiles;

@JdbcTest
@ActiveProfiles({"postgres-test-deguzman"})
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
        Employee employee1 = new Employee.Builder("1111111111", "de Guzman", "Jeamel", "j.deguzman@gmail.com", "jm.deguzman").build();
        Long e1 = employeeDao.saveLong(employee1);
        System.out.println("ID1:" + e1);

        Employee employee2 = new Employee.Builder("2222222222", "Balanon", "Jenalyn", "j.balanon@gmail.com", "j.balanon").build();
        Long e2 = employeeDao.saveLong(employee2);
        System.out.println("ID2:" + e2);

        Employee dbEmployee1 = employeeDao.findById(e1);
        assertEquals("1111111111", dbEmployee1.getNumber());
        assertEquals("de Guzman", dbEmployee1.getLastName());
        assertEquals("Jeamel", dbEmployee1.getFirstName());
        assertEquals("j.deguzman@gmail.com", dbEmployee1.getEmailAddress());
        assertEquals("jm.deguzman", dbEmployee1.getUserName());
        
        Employee dbEmployee2 = employeeDao.findById(e2);
        assertEquals("2222222222", dbEmployee2.getNumber());
        assertEquals("Balanon", dbEmployee2.getLastName());
        assertEquals("Jenalyn", dbEmployee2.getFirstName());
        assertEquals("j.balanon@gmail.com", dbEmployee2.getEmailAddress());
        assertEquals("j.balanon", dbEmployee2.getUserName());
        
        Set<Employee> e = employeeDao.findAll();
        assertNotNull(e.size());
    }

    @Test
    void test_NotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            employeeDao.findById(1L);
        });

    }

}
