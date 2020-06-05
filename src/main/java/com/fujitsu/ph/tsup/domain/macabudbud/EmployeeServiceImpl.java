package com.fujitsu.ph.tsup.domain.macabudbud;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao dao;

	public Employee getEmployee(Long id) {
		Employee employee = dao.findById(id);
		if (employee.getId() != id) {
			throw new IllegalArgumentException("Employee not found.");
		}
		return employee;
	}

	@Override
	public void save(Employee employee) {
		dao.save(employee);
		if (employee == null) {
			throw new IllegalArgumentException("Unable to save employee.");
		}
	}

	@Override
	public Set<Employee> findAll() {
		try {
			return dao.findAll();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Unable to find employee.");
		}
	}

	@Override
	public Employee findById(Long id) {
		try {
			return dao.findById(id);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Unable to find employee by the the given id.");
		}
	}

}
