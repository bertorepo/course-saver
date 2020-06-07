package com.fujitsu.ph.tsup.domain.oviedo;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeServiceImpl implements EmployeeService {
	private Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

	@Autowired
	EmployeeDao dao;

	@Override
	public void save(Employee employee) {
		// TODO Auto-generated method stub
		dao.save(employee);
	}

	@Override
	public Set<Employee> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Employee findById(Long id) {
		// TODO Auto-generated method stub
		try {
			return dao.findById(id);
		}catch(IllegalArgumentException ex) {
			throw new ApplicationException("Employee not found",ex);
		}
	}
}
