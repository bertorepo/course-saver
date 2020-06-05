package com.fujitsu.ph.tsup.domain.jimenez;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Employee findById(Long id) {
        try {
            return employeeDao.findById(id);
        } catch (DataAccessException ex) {
            throw new EmployeeException("Employee not found!", ex);
        }
    }
    
    @Override
    public Set<Employee> findAll() {
        Set<Employee> EmployeeList = employeeDao.findAll();
        try {
            if(EmployeeList.isEmpty() || EmployeeList == null) {
                throw new EmployeeException("Can't find Employee Details");
            } else {
                return EmployeeList;
            }    
        } catch (DataAccessException ex) {
            throw new EmployeeException("Can't access Employee Details.");
        }
        
    }

    

    @Override
    public void save(Employee Employee) {
        if (Employee.getId() != 0) {
            employeeDao.save(Employee);
        } else if (Employee.getId() <= 0) {
            throw new EmployeeException("Employee Id should not be zero or less than zero.");
        }
        
     }
    
    

}

