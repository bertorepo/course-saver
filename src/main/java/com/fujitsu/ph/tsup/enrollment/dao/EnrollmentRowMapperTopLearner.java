package com.fujitsu.ph.tsup.enrollment.dao;

//=================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enrollment
//Class Name   :EnrollmentRowMapperTopLearner.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+--------------------------------------------------
//0.01    | 09/07/2020 | WS) J.Yu              | New Creation
//=================================================================================================
/**
* <pre>
* Rowmapper for TopLearner
* <pre>
* 
* @version 0.01
* @author j.yu                       
*/

import com.fujitsu.ph.tsup.enrollment.model.TopLearnerForm;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class EnrollmentRowMapperTopLearner implements RowMapper <TopLearnerForm> {

    @Override
    public TopLearnerForm mapRow(ResultSet rs, int rowNum) throws SQLException {
        TopLearnerForm topLearnerForm = new TopLearnerForm();
   
        int id = rs.getInt("PARTICIPANT_ID");
        int place = rs.getRow();
        String participantName = rs.getString("PARTICIPANT_LAST_NAME") + ", " + rs.getString("PARTICIPANT_FIRST_NAME");
        
        topLearnerForm.setId(id);
        topLearnerForm.setParticipantName(participantName);
        topLearnerForm.setPlace(place);
        return topLearnerForm;
    }
}