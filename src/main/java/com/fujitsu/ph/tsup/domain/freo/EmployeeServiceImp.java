package com.fujitsu.ph.tsup.domain.freo;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public  class EmployeeServiceImp implements EmployeeService {

	@Autowired
	private EmployeeDao empdao;
	
	@Autowired
	public void save(Employee employee) {
		empdao.save(employee);	
	}
	
	@Override
	public Set<Employee> findAll()  {
		   Set<Employee> ee = empdao.findAll();
	        if (ee.isEmpty() || ee == null) {
	            throw new IllegalArgumentException("Employee not found");
	        }
	        return ee;
	    }
		
	@Override
	public Employee findById(Long Id) {
		 Employee ee = empdao.findById(Id);
	        if (ee.getEmployeeNumber() == "") {
	            throw new IllegalArgumentException("Employee not found");
	        }
	        return ee;
	}


}
