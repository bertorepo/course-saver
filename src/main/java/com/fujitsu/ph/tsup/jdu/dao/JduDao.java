package com.fujitsu.ph.tsup.jdu.dao;

import java.util.Set;

import com.fujitsu.ph.tsup.jdu.domain.Jdu;

public interface JduDao {

	Set<Jdu> findAllJdus();

	Set<Jdu> findJduByName(String jduName);

	void createJdu(Jdu newJdu);

	void updateJdu(Jdu updatedJdu);

	void deleteJdu(Long id);

}
