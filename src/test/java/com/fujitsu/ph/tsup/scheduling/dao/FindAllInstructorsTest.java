package com.fujitsu.ph.tsup.scheduling.dao;
/**
* <pre>
* The Unit Testing of schedule dao
* <pre>
* @version 0.01
* @author jc.jimenez
* @author j.macabugao
*/

//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: ScheduleServiceTest.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+-----------------+---------------
//0.01    | 07/02/2020 | WS) J. Macabugao | New Creation
//0.01    | 07/03/2020 | WS) JC. Jimenez  | Update
//
//
//=======================================================
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;


@JdbcTest
@ActiveProfiles({ "postgres" })
@AutoConfigureTestDatabase(replace = Replace.NONE)

class FindAllInstructorsTest {

	/**
     * ScheduleDao as dependency
     */
	@Autowired
	private ScheduleDao scheduleDao;
	
	/**
     * Test Configuration
     */
	@TestConfiguration
    static class TestContextConfiguration {
        
		/**
    	 * ScheduleDao
    	 * @return ScheduleDaoImpl
    	 */
        @Bean
        public ScheduleDao scheduleDao() {
        	return new ScheduleDaoImpl();
        }
    }
	

	/**
     * <pre>
     * testFindAllInstructors with valid values
     * scheduleDao.findAllInstructors and test if the methods find all the instrutors
     * <pre>
     */
	@Test
    void testFindAllInstructors() {
		Set<InstructorForm> instructorFormSet = scheduleDao.findAllInstructors();
		
		for(InstructorForm instructors : instructorFormSet) {
		    System.out.println("Instructor ID: "+ instructors.getId());
		    System.out.println("Instructor Name: "+ instructors.getName()+"\n");
		}
		System.out.println("InstructorForm Size:"+ instructorFormSet.size()+"\n");
		assertNotNull(instructorFormSet.size());
	}
}