package com.fujitsu.ph.tsup.domain.cabiling;

import java.util.Set;

public interface EmployeeService {
	void save(Employee employee);
    Set<Employee> findAll();
    Employee findById(Long id);

}