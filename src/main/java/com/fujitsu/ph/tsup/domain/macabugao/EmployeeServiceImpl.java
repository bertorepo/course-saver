package com.fujitsu.ph.tsup.domain.macabugao;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeDao employeeDao;
	
	public void save(Employee employee) {
		try {
			employeeDao.save(employee);
		} catch (DataAccessException ex) {
			throw new IllegalApplicationException("Employee not found", ex);
		}
		
	}

	public Set<Employee> findAll() {		
		try {
			return employeeDao.findAll();
		} catch (DataAccessException ex) {
			throw new IllegalApplicationException("Cannot find Employee", ex);
		}
	}

	public Employee findById(Long id) {
		try {
			return employeeDao.findById(id);
		} catch (DataAccessException ex) {
			throw new IllegalApplicationException("Id not found", ex);
		}
	}


}
