package com.fujitsu.ph.tsup.authz.core.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.authz.infrastructure.EmployeeRoleRepository;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
	@Autowired
	private EmployeeRoleRepository employeeRoleRepository;
	
	@Override
	public List<String> findByUserName(String userName) {
		return employeeRoleRepository.findByUserName(userName);
	}

}
