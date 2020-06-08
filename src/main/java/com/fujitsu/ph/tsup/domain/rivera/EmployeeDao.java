package com.fujitsu.ph.tsup.domain.rivera;

import java.util.Set;

public interface EmployeeDao {
	
	void save(Employee employee);
	Long saveEmployee();
	Set<Employee> findAll();
	
	Employee findById(Long id);

}
