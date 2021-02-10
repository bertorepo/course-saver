package com.fujitsu.ph.tsup.autoregister.service;

import java.util.List;

import com.fujitsu.ph.tsup.autoregister.model.AutoRegistration;
import com.fujitsu.ph.tsup.autoregister.model.AutoRegistrationDepartment;

public interface AutoRegistrationService {
	void addAutoRegistration(AutoRegistration autoRegistration);	
	List<AutoRegistrationDepartment> getAllDepartment();
}
