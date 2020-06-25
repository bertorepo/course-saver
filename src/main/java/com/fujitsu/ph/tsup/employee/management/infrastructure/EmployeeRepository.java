package com.fujitsu.ph.tsup.employee.management.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fujitsu.ph.tsup.employee.management.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Query("SELECT * FROM employee WHERE email_address = :emailAddress")
    Optional<Employee> findByEmailAddress(@Param("emailAddress") String emailAddress);

    @Query("SELECT * FROM employee WHERE user_name = :userName")
    Optional<Employee> findByUserName(@Param("userName") String userName);

    @Query("SELECT * FROM employee WHERE id in (:ids) ORDER BY last_name, first_name, number")
    List<Employee> findAllById(@Param("ids") Iterable<Long> ids);

    @Query("SELECT * FROM employee ORDER BY last_name, first_name, number")
    List<Employee> findAll();

}
