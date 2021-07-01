/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.jdu.service;

import java.util.Set;

import com.fujitsu.ph.tsup.jdu.domain.Jdu;

//=======================================================
//Project Name: Training Sign Up
//Class Name: JduService.java
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
public interface JduService {

	/**
	 * <pre>
	 * Finds all JDUs
	 * <pre>
	 *
	 * @return Set of JDUs
	 */
	Set<Jdu> findAllJdus();

	/**
	 * <pre>
	 * Finds all JDUs with specific string in name
	 * <pre>
	 *
	 * @param jduName
	 * @return Set of JDUs with specific string
	 */
	Set<Jdu> findJduByName(String jduName);

	/**
	 * <pre>
	 * Creates a new JDU
	 * <pre>
	 *
	 * @param newJdu
	 */
	void createJdu(Jdu newJdu);

	/**
	 * <pre>
	 * Updates an existing JDU
	 * <pre>
	 *
	 * @param updatedJdu
	 */
	void updateJdu(Jdu updatedJdu);

	/**
	 * <pre>
	 * Deletes an existing JDU
	 * <pre>
	 *
	 * @param id
	 */
	void deleteDepartment(Long id);
}
