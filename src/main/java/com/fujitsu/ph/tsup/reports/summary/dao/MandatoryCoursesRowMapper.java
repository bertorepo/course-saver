//==================================================================================================                                                                                                                                                                            
// Project Name : Training Sign Up
// System Name  : MandatoryCoursesRowMapper                                                                                                                                                             
// Class Name   : MandatoryCoursesRowMapper.java                                                                                                                                                                            
//                                                                                                                                                                          
// <<Modification History>>                                                                                                                                                                             
// Version | Date       | Updated By            | Content                                                                                                                                                                           
//---------+------------+-----------------------+---------------------------------------------------                                                                                                                                                                            
// 1.0.0   | 2021/02/22 | WS)J.Barbadillo       | New Creation             
//==================================================================================================
package com.fujitsu.ph.tsup.reports.summary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.reports.summary.model.MandatoryCourses;
import com.fujitsu.ph.tsup.scheduling.model.TopLearnersForm;

/**
 * <pre>
 *  The RowMapper for MandatoryCourses
 * </pre>
 * 
 * @author j.barbadillo
 * @version 1.0.0
 */
public class MandatoryCoursesRowMapper implements RowMapper<MandatoryCourses> {

    /**
     * <pre>
     *  Maps the Rows returned by ResultSet
     * </pre>
     * 
     * @param ResultSet rs
     * @param int rowNum
     * @throws SQLException
     */
    public MandatoryCourses mapRow(ResultSet rs, int rowNum) throws SQLException {

        
        MandatoryCourses mandatoryCourses = new MandatoryCourses();
        Long id = rs.getLong("ID");
        String name = rs.getString("COURSE_NAME");
//        Long totalNoOfJDUMem = rs.getLong("TOTAL_NUMBER_OF_JDU");
//        Long totalNoOfJDUMemFin = rs.getLong("TOTAL_NUMBER_OF_JDU_WHO_FINISHED_TRAINING");
//        Long totalNoOfJDUMemFinLastWk = rs.getLong("TOTAL_NUMBER_OF_JDU_WHO_FINISHED_TRAINING_LASTWEEK");

//        MandatoryCourses mandatoryCourses = new MandatoryCourses.Builder(id, name, totalNoOfJDUMem,
//                totalNoOfJDUMemFin, totalNoOfJDUMemFinLastWk).build();

        //MandatoryCourses mandatoryCourses = new MandatoryCourses.Builder(id, name).build();
        
        mandatoryCourses.setId(id);
        mandatoryCourses.setName(name);
        
        return mandatoryCourses;
        
        
//        int id = tpl.getInt("PARTICIPANT_ID");
//        int place = tpl.getRow();
//        String participantName = tpl.getString("PARTICIPANT_LAST_NAME") + ", " + tpl.getString("PARTICIPANT_FIRST_NAME");
//        
//        topLearnersForm.setId(id);
//        topLearnersForm.setParticipantName(participantName);
//        topLearnersForm.setPlace(place);
//        return topLearnersForm;

    }

}
