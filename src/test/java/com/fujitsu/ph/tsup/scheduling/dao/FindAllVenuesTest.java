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

import com.fujitsu.ph.tsup.scheduling.model.VenueForm;

@JdbcTest
@ActiveProfiles({ "postgres" })
@AutoConfigureTestDatabase(replace = Replace.NONE)

class FindAllVenuesTest {

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
     * testFindAllVenues with valid values
     * scheduleDao.findAllVenues and test if the methods find all the venues
     * <pre>
     */
	@Test
	 void testFindAllVenues() {
		Set<VenueForm> venueFormSet = scheduleDao.findAllVenues();
		
		for(VenueForm venues : venueFormSet) {
		    System.out.println("Venue ID: "+ venues.getId());
		    System.out.println("Venue Name: "+ venues.getName()+"\n");
		}
		System.out.println("VenueForm Size:" + venueFormSet.size()+"\n");
		assertNotNull(venueFormSet.size());
		 
	 }
}
	