package com.fujitsu.ph.tsup.domain.rivera;

import java.util.Set;

public interface EmployeeDao {
	
	void save(Employee employee);
	
	Set<Employee> findaAll();
	
	Employee findById(Long id);

}
