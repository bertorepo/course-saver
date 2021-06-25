package com.fujitsu.ph.tsup.jdu.service;

import java.util.Collection;
import java.util.Set;

import com.fujitsu.ph.tsup.department.domain.Department;
import com.fujitsu.ph.tsup.jdu.domain.Jdu;

public interface JduService {

	Set<Jdu> findAllJdus();

}
