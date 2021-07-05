/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.jdu.dao;

import java.util.Set;

import com.fujitsu.ph.tsup.jdu.domain.Jdu;

//=======================================================
//Project Name: Training Sign Up
//Class Name: JduDao.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+------------------+---------------
//0.01    | 28/06/2021 | WS) dw.cardenas  | Created
//=======================================================

/**
 *
 * @author dw.cardenas
 *
 */
public interface JduDao {

	/**
	 * <pre>
	 * Finds all JDUs in the database
	 * <pre>
	 *
	 * @return
	 */
	Set<Jdu> findAllJdus();

	/**
	 * <pre>
	 * Finds specific JDU/s that contains string in jduName in the database
	 * <pre>
	 *
	 * @param jduName
	 * @return
	 */
	Set<Jdu> findJduByName(String jduName);

	/**
	 * <pre>
	 * Inserts a new JDU in the database
	 * <pre>
	 *
	 * @param newJdu
	 */
	void createJdu(Jdu newJdu);

	/**
	 * <pre>
	 * Updates an existing JDU in the database
	 * <pre>
	 *
	 * @param updatedJdu
	 */
	void updateJdu(Jdu updatedJdu);

	/**
	 * <pre>
	 * Deletes an existing JDU using JDU id in the database
	 * <pre>
	 *
	 * @param id
	 */
	void deleteJdu(Long id);

}
