package com.fujitsu.ph.tsup.domain.freo;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public  class EmployeeServiceImp implements EmployeeService {

	@Autowired
	private EmployeeDao empdao;

	@Override
	public void save(Employee employee) {
		  if (employee.getId() != 0) {
	            empdao.save(employee);
	        } else if (employee.getId() <= 0) {
	            throw new EmployeeException("Employee Id should not be zero or less than zero.");
	        }
	}

	@Override
	public Set<Employee >findAll() {
		Set<Employee> EmployeeList = empdao.findAll();
        try {
            if(EmployeeList.isEmpty() || EmployeeList == null) {
                throw new EmployeeException("Can't find any Employee details on List");
            } else {
                return EmployeeList;
            }    
        } catch (DataAccessException ex) {
            throw new EmployeeException("Can't access Employee Details.");
        }
        
	}

	@Override
	public Employee findById(Long id) {
		 try {
	            return empdao.findById(id);
	        } catch (DataAccessException e) {
	            throw new EmployeeException("Employee ID not found!", e);
	        }
	}
}