package com.fujitsu.ph.tsup.domain.balanon;

import java.util.Set;

public interface EmployeeService {
    void save(Employee Id);

    Set<Employee> findall();

    Employee findById(Long Id);

}
