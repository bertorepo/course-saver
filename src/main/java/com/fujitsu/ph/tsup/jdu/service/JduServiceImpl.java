package com.fujitsu.ph.tsup.jdu.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.jdu.dao.JduDao;
import com.fujitsu.ph.tsup.jdu.domain.Jdu;

@Service
public class JduServiceImpl implements JduService {
	
	@Autowired
	JduDao jduDao;
	
	@Override
	public Set<Jdu> findAllJdus() {
		return jduDao.findAllJdus();
	}

}
