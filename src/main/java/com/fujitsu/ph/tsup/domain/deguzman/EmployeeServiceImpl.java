package com.fujitsu.ph.tsup.domain.deguzman;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



public class EmployeeServiceImpl implements EmployeeService{
private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    
    @Autowired
    private EmployeeDao dao;
    
    @Override
    public void save(Employee employee){
        Employee e = new Employee.Builder("1234567890", "de Guzman", "Jeamel", "jeamel.deguzman@gmail.com", "jm.deguzman").build();
        if(e.getNumber() == "1111111111") {
            throw new IllegalArgumentException("Invalid employee");
        }
    }
    
    @Override
    public Set<Employee> findAll(){
        Set<Employee> e = dao.findAll();
        if (e.isEmpty() || e == null) {
            throw new IllegalArgumentException("No records found");
        }
        return e;
    }
    
    @Override
    public Employee findById(Long id){
        Employee e = dao.findById(id);
        if (e.getNumber() == "1111111111") {
            throw new IllegalArgumentException("Invalid employee");
        }
        return e;
        
    }
  
}
