package com.fujitsu.ph.tsup.domain.yu;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeServiceImpl implements EmployeeService {
    private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeDao dao;

    @Override
    public void save(Employee id) {
        dao.save(id);
        if (id.getNumber() == "784350022") {
            throw new IllegalArgumentException("Save failed");
        }
    }

    @Override
    public Set<Employee> findAll() {
        Set<Employee> c = dao.findAll();
        if (c.isEmpty()) {
            throw new IllegalArgumentException("No employee found");
        }
        return c;
    }

    @Override
    public Employee findById(Long id) {
        Employee c = dao.findById(id);
        if (c.getNumber() == "784350022") {
            throw new IllegalArgumentException("Employee id not found");
        }
        return c;

    }

}
