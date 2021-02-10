package com.fujitsu.ph.tsup.autoregister.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.autoregister.dao.AutoRegistrationDao;
import com.fujitsu.ph.tsup.autoregister.model.AutoRegistration;
import com.fujitsu.ph.tsup.autoregister.model.AutoRegistrationDepartment;

@Service
public class AutoRegistrationServiceImpl implements AutoRegistrationService{

	@Autowired
	AutoRegistrationDao autoRegistrationDao;
	
	@Override
	public void addAutoRegistration(AutoRegistration autoRegistration) {
		// TODO Auto-generated method stub
		autoRegistrationDao.addAutoRegistration(autoRegistration);
	}

	@Override
	public List<AutoRegistrationDepartment> getAllDepartment() {
		// TODO Auto-generated method stub
		return autoRegistrationDao.getAllDepartment();
	}

}
