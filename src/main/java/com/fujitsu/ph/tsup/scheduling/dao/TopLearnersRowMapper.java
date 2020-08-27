package com.fujitsu.ph.tsup.scheduling.dao;

//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: CourseScheduleDetailRowMapper.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 08/27/2020 | WS) JC.Jimenez  | New Creation
//
//
//=======================================================

/**
* <pre>
* Custom RowMapper Class for findTopLearners()
* <pre>
* @version 0.01
* @author jc.jimenez
*
*/

import java.sql.ResultSet;
import java.sql.SQLException;

import com.fujitsu.ph.tsup.scheduling.model.TopLearnersForm;

import org.springframework.jdbc.core.RowMapper;

public class TopLearnersRowMapper implements RowMapper <TopLearnersForm>{
    
    @Override
    public TopLearnersForm mapRow(ResultSet tpl, int rowNum) throws SQLException {
        TopLearnersForm topLearnersForm = new TopLearnersForm();
   
        int id = tpl.getInt("PARTICIPANT_ID");
        int place = tpl.getRow();
        String participantName = tpl.getString("PARTICIPANT_LAST_NAME") + ", " + tpl.getString("PARTICIPANT_FIRST_NAME");
        
        topLearnersForm.setId(id);
        topLearnersForm.setParticipantName(participantName);
        topLearnersForm.setPlace(place);
        return topLearnersForm;
    }
}