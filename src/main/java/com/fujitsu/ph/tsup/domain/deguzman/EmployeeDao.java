package com.fujitsu.ph.tsup.domain.deguzman;

import java.util.Set;

public interface EmployeeDao {
    void save(Employee employee);
    Long saveLong(Employee employee);
    Set<Employee> findAll();
    Employee findById(Long id);
}
