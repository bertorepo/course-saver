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

	@Override
	public Set<Jdu> findJduByName(String jduName) {
		return jduDao.findJduByName(jduName);
	}

	@Override
	public void createJdu(Jdu newJdu) {
		try {
			jduDao.createJdu(newJdu);
		} catch (Exception ex) {
			String err = String.format("Failed to save new jdu: [%s]", ex.getMessage());
			throw new IllegalArgumentException(err);
		}
	}

	@Override
	public void updateJdu(Jdu updatedJdu) {
		try {
			jduDao.updateJdu(updatedJdu);
		} catch (Exception ex) {
			String err = String.format("Failed to update jdu: [%s]", ex.getMessage());
			throw new IllegalArgumentException(err);
		}
	}

	@Override
	public void deleteDepartment(Long id) {
		try {
			jduDao.deleteJdu(id);
		} catch (Exception ex) {
			String err = String.format("Failed to delete jdu: [%s]", ex.getMessage());
			throw new IllegalArgumentException(err);
		}
	}

}
