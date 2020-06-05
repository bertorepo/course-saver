package com.fujitsu.ph.tsup.domain.angara;

import java.util.Set;

public interface EmployeeService {
    void save(Employee employeeId);
    Set<Employee> findAll();
    Employee findById (Long employeeId);

}
