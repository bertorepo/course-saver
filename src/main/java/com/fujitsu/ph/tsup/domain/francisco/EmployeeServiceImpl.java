package com.fujitsu.ph.tsup.domain.francisco;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public void save(Employee employee) {
        try {
            employeeDao.save(employee);
        } catch (IllegalArgumentException e) {
            throw new  ApplicationException("Application Error!", e);
        }
    }

    @Override
    public Set<Employee> findAll() {
        try {
            return employeeDao.findAll();
        } catch (IllegalArgumentException e) {
            throw new ApplicationException("Application Error!", e);
        }
    }

    @Override
    public Employee findById(Long id) {
        try {
            return employeeDao.findById(id);
        } catch (IllegalArgumentException e) {
            throw new ApplicationException("Application Error!", e);
        }
    }
}
