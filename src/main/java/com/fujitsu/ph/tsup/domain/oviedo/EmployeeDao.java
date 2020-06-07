package com.fujitsu.ph.tsup.domain.oviedo;

import java.util.Set;

public interface EmployeeDao {
	 void save(Employee employee);
	    Long saveEmployee(Employee employee);
	    Set<Employee> findAll();
	    Employee findById(Long id);
}
