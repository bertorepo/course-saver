package com.fujitsu.ph.tsup.domain.cabiling;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao dao;

	@Override
	public void save(Employee employee) {
		try {
			dao.save(employee);
		} catch (DataAccessException ex) {
			throw new EmployeeException("Employee not saved", ex);
		}
	}

	@Override
	public Set<Employee> findAll() {
		try {
			return dao.findAll();
		} catch (DataAccessException ex) {
			throw new EmployeeException("No employee record found", ex);
		}
	}

	@Override
	public Employee findById(Long id) {
		try {
			return dao.findById(id);
		} catch (DataAccessException ex) {
			throw new EmployeeException("No employee found by that id", ex);
		}
	}
	

}
