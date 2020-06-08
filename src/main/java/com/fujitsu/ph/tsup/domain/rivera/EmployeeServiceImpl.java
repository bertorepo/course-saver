package com.fujitsu.ph.tsup.domain.rivera;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeServiceImpl implements EmployeeService{
private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeDao dao;

    @Override
    public Employee findById(Long id) {
        Employee employee = dao.findById(id);
        if (employee.getId() == 12345) {
            throw new IllegalArgumentException("Employee not found");
        } 
        return employee;   
    }

    @Override
    public void save(Employee employee) {
        dao.save(employee);
        if(employee.getId() == 12345) {
            throw new IllegalArgumentException("Employee not saved");
        }
        
    }

    @Override
    public Set<Employee> findAll() {
       Set<Employee> employeeAll = dao.findAll();
       if(employeeAll == null) {
           throw new IllegalArgumentException("Record not found");
       }
       return employeeAll;
    }
}
