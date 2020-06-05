package com.fujitsu.ph.tsup.domain.yu;

import java.util.Set;

public interface EmployeeDao {
    void save(Employee id);
    Set<Employee> findAll();
    Employee findById(Long id);

}
