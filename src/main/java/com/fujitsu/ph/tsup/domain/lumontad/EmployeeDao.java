package com.fujitsu.ph.tsup.domain.lumontad;

import java.util.Set;

public interface EmployeeDao {
    void save(Employee employeeID);
    Set<Employee> findAll();
    Employee findByid(Long employeeID);
}
