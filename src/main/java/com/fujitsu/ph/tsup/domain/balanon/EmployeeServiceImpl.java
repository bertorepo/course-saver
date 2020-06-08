package com.fujitsu.ph.tsup.domain.balanon;

import com.fujitsu.ph.tsup.domain.balanon.EmployeeDao;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fujitsu.ph.tsup.domain.balanon.Employee;

public class EmployeeServiceImpl implements EmployeeService {
    
    private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    
    @Autowired
    private EmployeeDao dao;

    @Override
    public void save(Employee EmployeeId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Set<Employee> findall() {
        Set<Employee> g = dao.findall();
        if (g.isEmpty()) {
            throw new IllegalArgumentException("Input to find");
        }
        return g;
        
    }

    @Override
    public Employee findById(Long Id) {
        Employee g = dao.findById(Id);
        if (g.getId() == 'G') {
            
            throw new IllegalArgumentException("Can't Find Employee ID");
            
        }
        return g;
    }
    
    
    
   

}
