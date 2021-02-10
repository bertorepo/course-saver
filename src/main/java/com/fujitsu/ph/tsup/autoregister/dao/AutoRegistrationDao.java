package com.fujitsu.ph.tsup.autoregister.dao;

import java.util.List;

import com.fujitsu.ph.tsup.autoregister.model.AutoRegistration;
import com.fujitsu.ph.tsup.autoregister.model.AutoRegistrationDepartment;

public interface AutoRegistrationDao {
	void addAutoRegistration(AutoRegistration autoRegistration);
	List<AutoRegistrationDepartment> getAllDepartment();
}
