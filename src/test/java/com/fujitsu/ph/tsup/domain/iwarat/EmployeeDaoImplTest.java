package com.fujitsu.ph.tsup.domain.iwarat;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

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
@ActiveProfiles({ "postgres-test-iwarat" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EmployeeDaoImplTest {

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
        Employee employee1 = new Employee.Builder("10131219", "Harvey", "Iwarat", "j.iwarat@fujitus.com", "j.iwarat")
                .build();
        employeeDao.save(employee1);
        Long id1 = employeeDao.GeneratedKeyHolderId();
        System.out.println("ID:" + id1);

        Employee databaseEmployee1 = employeeDao.findById(id1);
        assertEquals("10131219", databaseEmployee1.getNumber());
        assertEquals("Iwarat", databaseEmployee1.getLastName());
        assertEquals("Harvey", databaseEmployee1.getFirstName());
        assertEquals("j.iwarat@fujitus.com", databaseEmployee1.getEmailAddress());
        assertEquals("j.iwarat", databaseEmployee1.getUsername());

        Employee employee2 = new Employee.Builder("12191013", "Jhon", "Iwarat", "jh.iwarat@fujitus.com", "jh.iwarat")
                .build();
        employeeDao.save(employee2);
        Long id2 = employeeDao.GeneratedKeyHolderId();
        System.out.println("ID:" + id2);

        Employee databaseEmployee2 = employeeDao.findById(id2);
        assertEquals("12191013", databaseEmployee2.getNumber());
        assertEquals("Iwarat", databaseEmployee2.getLastName());
        assertEquals("Jhon", databaseEmployee2.getFirstName());
        assertEquals("jh.iwarat@fujitus.com", databaseEmployee2.getEmailAddress());
        assertEquals("jh.iwarat", databaseEmployee2.getUsername());

        Set<Employee> employeeSet = employeeDao.findAll();
        assertNotNull(employeeSet.size());
    }

    @Test
    void Test_NotFound() {

        assertThrows(EmptyResultDataAccessException.class, () -> {
            employeeDao.findById(7L);
        });
    }

}
