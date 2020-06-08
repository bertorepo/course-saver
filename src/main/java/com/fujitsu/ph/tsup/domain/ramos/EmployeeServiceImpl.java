package com.fujitsu.ph.tsup.domain.ramos;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeServiceImpl implements EmployeeService {
	private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeDao dao;

	@Override
	public void save(Employee employee) {
		dao.save(employee);
		if (employee.getEmployeeNumber() == "012345678") {
			throw new IllegalArgumentException("Employee number not saved");
		}
	}

	@Override
	public Set<Employee> findAll() {
        Set<Employee> employee = dao.findAll();
        if (employee.isEmpty() || employee == null) {
            throw new IllegalArgumentException("Invalid employee");
        }
        return employee;
	}

	@Override
	public Employee findById(Long id) {
        Employee employee = dao.findById(id);
        if (employee.getId() == 101) {
            throw new IllegalArgumentException("Employee not found");
        } 
        return employee;   
	}

}
