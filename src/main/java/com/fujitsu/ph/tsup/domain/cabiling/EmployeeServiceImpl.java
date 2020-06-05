package com.fujitsu.ph.tsup.domain.cabiling;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao dao;

	@Override
	public void save(Employee employee) {
		try {
			dao.save(employee);
		} catch (IllegalArgumentException ex) {
			throw new NewException("Employee not saved", ex);
		}
	}

	@Override
	public Set<Employee> findAll() {
		try {
			return dao.findAll();
		} catch (IllegalArgumentException ex) {
			throw new NewException("No employee record found", ex);
		}
	}

	@Override
	public Employee findById(Long id) {
		try {
			return dao.findById(id);
		} catch (IllegalArgumentException ex) {
			throw new NewException("No employee found by that id", ex);
		}
	}
	

}
