package com.fujitsu.ph.tsup.scheduling.dao;
//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: CourseRowMapper.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 06/26/2020 | WS) J.Macabugao  | New Creation
//
//
//=======================================================

/**
* <pre>
* Custom RowMapper Class for findAllVenues()
* <pre>
* @version 0.01
* @author j.macabugao
*
*/

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.scheduling.model.VenueForm;

public class VenueRowMapper implements RowMapper<VenueForm>{

	
	/**
     * <pre>
     * Maps the Rows returned by ResultSet
     * <pre>
     * @param ResultSet rs
     * @param int rowNum
     * @throws SQLException
     */
	@Override
	public VenueForm mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		VenueForm venueForm = new VenueForm();
		
		Long venueId = rs.getLong("venueId");
		String venueName = rs.getString("venueName");
		venueForm.setId(venueId);
		venueForm.setName(venueName);
		
		
		return venueForm;
	}

}
