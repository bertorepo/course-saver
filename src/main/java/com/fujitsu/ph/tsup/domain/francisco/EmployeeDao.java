package com.fujitsu.ph.tsup.domain.francisco;

import java.util.Set;

public interface EmployeeDao {
    void save(Employee employee);

    Set<Employee> findAll();

    Employee findById(Long id);
}
