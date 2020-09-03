package com.fujitsu.ph.tsup.attendance.domain;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :CourseParticipantTest.java
//
//<<Modification History>>
//Version | Date       | Updated By                                      | Content
//--------+------------+-------------------------------------------------+--------------------------
//0.01    | 07/06/2020 |  WS) K.Abad, WS) J.Iwarat, WS) R.Ramos          | New Creation
//0.02    | 09/02/2020 |  WS) K.Abad, WS) J.Iwarat, WS) R.Ramos          | Update
//==================================================================================================
/**
* <pre>
* The Course Participant Test for builder.
* In this class, it test all the validations per method 
* 
* <pre>
* 
* @version 0.02
* @author k.abad
* @author j.iwarat
* @author r.ramos
* 
*/

public class CourseParticipantTest {
    
    /**
     * <pre>
     * It test the builder of course participant
     * 
     * </pre>
     * 
     */
    @Test
    void testBuilder() {
        CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 1000L, "java", 
                "Lorenzo, Loyce", "WFH", 20L, "Abad, Kenneth", ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                ZonedDateTime.parse("2020-07-03T17:30:00Z"), 2.0f, ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                "k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();
        
        assertNotNull(courseParticipant);
        assertEquals(courseParticipant.getCourseName(), "java");
        assertEquals(courseParticipant.getInstructorName(), "Lorenzo, Loyce");
    }
    
    /**
     * <pre> 
     * It test the validation for id based if it is null. If it is invalid, then
     * throw an argument with the corresponding message.
     * 
     * <pre>
     * 
     */
    @Test
    void testId_IsNull() {
        IllegalArgumentException argument = assertThrows(IllegalArgumentException.class, () -> {
            new CourseParticipant.Builder(null, 1000L, "java", "Lorenzo, Loyce", "WFH", 20L, "Abad, Kenneth", 
                    ZonedDateTime.parse("2020-07-03T17:30:00Z"), ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                    2.0f, ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();           
        });
        
        assertThat(argument.getMessage(), equalTo("Id should not be empty"));
    }
    
    /**
     * <pre> 
     * It test the validation for id based if it is zero. If it is invalid, then
     * throw an argument with the corresponding message.
     * 
     * <pre>
     * 
     */
    @Test
    void testId_IsZero() {
        IllegalArgumentException argument = assertThrows(IllegalArgumentException.class, () -> {
            new CourseParticipant.Builder(0L, 1000L, "java", "Lorenzo, Loyce", "WFH", 20L, "Abad, Kenneth", 
                    ZonedDateTime.parse("2020-07-03T17:30:00Z"), ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                    2.0f, ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();          
        });
        
        assertThat(argument.getMessage(), equalTo("Id should not be empty"));
    }
    
    /**
     * <pre> 
     * It test the validation for id based if it is valid.
     * 
     * <pre>
     * 
     */
    @Test
    void testId_IsValid() {
        CourseParticipant courseParticipant = new CourseParticipant.Builder(12345L, 1000L, "java", "Lorenzo, Loyce", 
                "WFH", 20L, "Abad, Kenneth", ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                ZonedDateTime.parse("2020-07-03T17:30:00Z"), 2.0f, 
                ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();
        
        assertEquals(courseParticipant.getId(), 12345L);
        assertNotEquals(courseParticipant.getId(), 11111L);
        assertNotSame(courseParticipant.getId(), 50000L);
    }
    
    /**
     * <pre> 
     * It test the validation for course schedule id based if it is null. If it is invalid, then
     * throw an argument with the corresponding message.
     * 
     * <pre>
     * 
     */
    @Test
    void testCourseScheduleId_IsNull() {
        IllegalArgumentException argument = assertThrows(IllegalArgumentException.class, () -> {
            new CourseParticipant.Builder(1L, null, "java", "Lorenzo, Loyce", "WFH", 20L, "Abad, Kenneth", 
                    ZonedDateTime.parse("2020-07-03T17:30:00Z"), ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                    2.0f, ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();           
        });
        
        assertThat(argument.getMessage(), equalTo("Course schedule id id should not be empty"));  
    }
    
    /**
     * <pre> 
     * It test the validation for course schedule id based if it is zero. If it is invalid, then
     * throw an argument with the corresponding message.
     * 
     * <pre>
     * 
     */
    @Test
    void testCourseScheduleId_IsZero() {
        IllegalArgumentException argument = assertThrows(IllegalArgumentException.class, () -> {
            new CourseParticipant.Builder(1L, 0L, "java", "Lorenzo, Loyce", "WFH", 20L, "Abad, Kenneth", 
                    ZonedDateTime.parse("2020-07-03T17:30:00Z"), ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                    2.0f, ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();            
        });
        
        assertThat(argument.getMessage(), equalTo("Course schedule id id should not be empty"));
    }
    
    /**
     * <pre> 
     * It test the validation for course schedule id based if it is valid.
     * 
     * <pre>
     * 
     */
    @Test
    void testCourseScheduleId_IsValid() {
        CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 2000L, "java", "Lorenzo, Loyce", 
                "WFH", 20L, "Abad, Kenneth", ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                ZonedDateTime.parse("2020-07-03T17:30:00Z"), 2.0f, 
                ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();
        
        assertEquals(courseParticipant.getCourseScheduleId(), 2000L);
        assertNotEquals(courseParticipant.getCourseScheduleId(), 132L);
        assertNotSame(courseParticipant.getCourseScheduleId(), 11111L);
    }
    
    /**
     * <pre> 
     * It test the validation for course name based if it is null. If it is invalid, then
     * throw an argument with the corresponding message.
     * 
     * <pre>
     * 
     */
    @Test
    void testCourseName_IsNull() {
        IllegalArgumentException argument = assertThrows(IllegalArgumentException.class, () -> {
            new CourseParticipant.Builder(1L, 1000L, null, "Lorenzo, Loyce", "WFH", 20L, "Abad, Kenneth", 
                    ZonedDateTime.parse("2020-07-03T17:30:00Z"), ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                    2.0f, ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();         
        });
        
        assertThat(argument.getMessage(), equalTo("Course Name should not be empty"));  
    }
    
    /**
     * <pre> 
     * It test the validation for course name based if it is empty. If it is invalid, then
     * throw an argument with the corresponding message.
     * 
     * <pre>
     * 
     */
    @Test
    void testCourseName_IsEmpty() {
        IllegalArgumentException argument = assertThrows(IllegalArgumentException.class, () -> {
            new CourseParticipant.Builder(1L, 1000L, "", "Lorenzo, Loyce", "WFH", 20L, "Abad, Kenneth", 
                    ZonedDateTime.parse("2020-07-03T17:30:00Z"), ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                    2.0f, ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();          
        });
        
        assertThat(argument.getMessage(), equalTo("Course Name should not be empty"));  
    }
    
    /**
     * <pre> 
     * It test the validation for course name based if it is valid.
     * 
     * <pre>
     * 
     */
    @Test
    void testCourseName_IsValid() {
        CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 1000L, "SpringBoot", "Lorenzo, Loyce", 
                "WFH", 20L, "Abad, Kenneth",ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                ZonedDateTime.parse("2020-07-03T17:30:00Z"), 2.0f, 
                ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();
        
        assertEquals(courseParticipant.getCourseName(), "SpringBoot");
        assertNotEquals(courseParticipant.getCourseName(), "java");
        assertNotSame(courseParticipant.getCourseName(), "javascript");
        assertSame(courseParticipant.getCourseName(), "SpringBoot");
    }
    
    /**
     * <pre> 
     * It test the validation for instructor name based if it is null. If it is invalid, then
     * throw an argument with the corresponding message.
     * 
     * <pre>
     * 
     */
    @Test
    void testInstructorName_IsNull() {
        IllegalArgumentException argument = assertThrows(IllegalArgumentException.class, () -> {
            new CourseParticipant.Builder(1L, 1000L, "java", null, "WFH", 20L, "Abad, Kenneth", 
                    ZonedDateTime.parse("2020-07-03T17:30:00Z"), ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                    2.0f, ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();          
        });
        
        assertThat(argument.getMessage(), equalTo("Instructor Name should not be empty"));  
    }
    
    /**
     * <pre> 
     * It test the validation for instructor name based if it is empty. If it is invalid, then
     * throw an argument with the corresponding message.
     * 
     * <pre>
     * 
     */
    @Test
    void testInstructorName_IsEmpty() {
        IllegalArgumentException argument = assertThrows(IllegalArgumentException.class, () -> {
            new CourseParticipant.Builder(1L, 1000L, "java", "", "WFH", 20L, "Abad, Kenneth", 
                    ZonedDateTime.parse("2020-07-03T17:30:00Z"), ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                    2.0f, ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();          
        });
        
        assertThat(argument.getMessage(), equalTo("Instructor Name should not be empty"));  
    }
    
    /**
     * <pre> 
     * It test the validation for instructor name based if it is valid.
     * 
     * <pre>
     * 
     */
    @Test
    void testInstructorName_IsValid() {
        CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 1000L, "java", "Lorenzo, Loyce", 
                "WFH", 20L, "Abad, Kenneth", ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                ZonedDateTime.parse("2020-07-03T17:30:00Z"), 2.0f, 
                ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();
        
        assertEquals(courseParticipant.getInstructorName(), "Lorenzo, Loyce");
        assertNotEquals(courseParticipant.getInstructorName(), "De Guzman, Gene");
        assertNotSame(courseParticipant.getInstructorName(), "De Leon, John Carlo");
        assertSame(courseParticipant.getInstructorName(), "Lorenzo, Loyce");
    }
    
    /**
     * <pre> 
     * It test the validation for venue name based if it is null. If it is invalid, then
     * throw an argument with the corresponding message.
     * 
     * <pre>
     * 
     */
    @Test
    void testVenueName_IsNull() {
        IllegalArgumentException argument = assertThrows(IllegalArgumentException.class, () -> {
            new CourseParticipant.Builder(1L, 1000L, "java", "Lorenzo, Loyce", null, 20L, "Abad, Kenneth", 
                    ZonedDateTime.parse("2020-07-03T17:30:00Z"), ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                    2.0f, ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();            
        });
        
        assertThat(argument.getMessage(), equalTo("Venue Name should not be empty"));  
    }
    
    /**
     * <pre> 
     * It test the validation for venue name based if it is empty. If it is invalid, then
     * throw an argument with the corresponding message.
     * 
     * <pre>
     * 
     */
    @Test
    void testVenueName_IsEmpty() {
        IllegalArgumentException argument = assertThrows(IllegalArgumentException.class, () -> {
            new CourseParticipant.Builder(1L, 1000L, "java", "Lorenzo, Loyce", "", 20L, "Abad, Kenneth", 
                    ZonedDateTime.parse("2020-07-03T17:30:00Z"), ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                    2.0f, ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();           
        });
        
        assertThat(argument.getMessage(), equalTo("Venue Name should not be empty"));  
    }
    
    /**
     * <pre> 
     * It test the validation for venue name based if it is valid.
     * 
     * <pre>
     * 
     */
    @Test
    void testVenueName_IsValid() {
        CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 1000L, "java", "Lorenzo, Loyce", 
                "Online", 20L, "Abad, Kenneth", ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                ZonedDateTime.parse("2020-07-03T17:30:00Z"), 2.0f, 
                ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();
        
        assertEquals(courseParticipant.getVenueName(), "Online");
        assertNotEquals(courseParticipant.getVenueName(), "WFH");
        assertNotSame(courseParticipant.getVenueName(), "Taguig");
        assertSame(courseParticipant.getVenueName(), "Online");
    }
    
    /**
     * <pre> 
     * It test the validation for participant id based if it is null. If it is invalid, then
     * throw an argument with the corresponding message.
     * 
     * <pre>
     * 
     */
    @Test
    void testParticipantId_IsNull() {
        IllegalArgumentException argument = assertThrows(IllegalArgumentException.class, () -> {
            new CourseParticipant.Builder(1L, 1000L, "java", "Lorenzo, Loyce", "WFH", null, "Abad, Kenneth", 
                    ZonedDateTime.parse("2020-07-03T17:30:00Z"), ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                    2.0f, ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();            
        });
        
        assertThat(argument.getMessage(), equalTo("Participant should not be empty"));  
    }
    
    /**
     * <pre> 
     * It test the validation for participant id based if it is zero. If it is invalid, then
     * throw an argument with the corresponding message.
     * 
     * <pre>
     * 
     */
    @Test
    void testParticipantId_IsZero() {
        IllegalArgumentException argument = assertThrows(IllegalArgumentException.class, () -> {
            new CourseParticipant.Builder(1L, 1000L, "java", "Lorenzo, Loyce", "WFH", 0L, "Abad, Kenneth", 
                    ZonedDateTime.parse("2020-07-03T17:30:00Z"), ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                    2.0f, ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();           
        });
        
        assertThat(argument.getMessage(), equalTo("Participant should not be empty"));  
    }
    
    /**
     * <pre> 
     * It test the validation for participant id based if it is valid.
     * 
     * <pre>
     * 
     */
    @Test
    void testParticipantId_IsValid() {
        CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 1000L, "java", "Lorenzo, Loyce", 
                "WFH", 22L, "Abad, Kenneth", ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                ZonedDateTime.parse("2020-07-03T17:30:00Z"),2.0f, 
                ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();
        
        assertEquals(courseParticipant.getParticipantId(), 22L);
        assertNotEquals(courseParticipant.getParticipantId(), 11L);
        assertNotSame(courseParticipant.getParticipantId(), 5555L);
    }
    
    /**
     * <pre> 
     * It test the validation for participant name based if it is null. If it is invalid, then
     * throw an argument with the corresponding message.
     * 
     * <pre>
     * 
     */
    @Test
    void testParticipantName_IsNull() {
        IllegalArgumentException argument = assertThrows(IllegalArgumentException.class, () -> {
            new CourseParticipant.Builder(1L, 1000L, "java", "Lorenzo, Loyce", "WFH", 20L, null, 
                    ZonedDateTime.parse("2020-07-03T17:30:00Z"), ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                    2.0f, ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();           
        });
        
        assertThat(argument.getMessage(), equalTo("Participant Name should not be empty"));  
    }
    
    /**
     * <pre> 
     * It test the validation for participant name based if it is empty. If it is invalid, then
     * throw an argument with the corresponding message.
     * 
     * <pre>
     * 
     */
    @Test
    void testParticipantName_IsEmpty() {
        IllegalArgumentException argument = assertThrows(IllegalArgumentException.class, () -> {
            new CourseParticipant.Builder(1L, 1000L, "java", "Lorenzo, Loyce", "WFH", 20L, "", 
                    ZonedDateTime.parse("2020-07-03T17:30:00Z"), ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                    2.0f, ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();           
        });
        
        assertThat(argument.getMessage(), equalTo("Participant Name should not be empty"));  
    }
    
    /**
     * <pre> 
     * It test the validation for participant name based if it is valid.
     * 
     * <pre>
     * 
     */
    @Test
    void testParticipantName_IsValid() {
        CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 1000L, "java", "Lorenzo, Loyce", 
                "WFH", 20L, "Abad, Kenneth",ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                ZonedDateTime.parse("2020-07-03T17:30:00Z"), 2.0f, 
                ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();
        
        assertEquals(courseParticipant.getParticipantName(), "Abad, Kenneth");
        assertNotEquals(courseParticipant.getParticipantName(), "Pedro");
        assertNotSame(courseParticipant.getParticipantName(), "Juan");
        assertSame(courseParticipant.getParticipantName(), "Abad, Kenneth");
    }
    
    /**
     * <pre> 
     * It test the validation for scheduled start date and time based if it is null. If it is invalid, then
     * throw an argument with the corresponding message.
     * 
     * <pre>
     * 
     */
    @Test
    void testScheduledStartDateTime_IsNull() {
        IllegalArgumentException argument = assertThrows(IllegalArgumentException.class, () -> {
            new CourseParticipant.Builder(1L, 1000L, "java", "Lorenzo, Loyce", "WFH", 20L, "Abad, Kenneth", 
                    null, ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                    2.0f, ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();           
        });
        
        assertThat(argument.getMessage(), equalTo("Scheduled start date should not be empty"));  
    }
    
    /**
     * <pre> 
     * It test the validation for scheduled start date and time based if it is valid.
     * 
     * <pre>
     * 
     */
    @Test
    void testScheduledStartDateTime_IsValid() {
        CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 1000L, "java", "Lorenzo, Loyce", 
                "WFH", 20L, "Abad, Kenneth", ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                ZonedDateTime.parse("2020-07-03T17:30:00Z"), 2.0f, 
                ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();
        
        assertEquals(courseParticipant.getScheduledStartDateTime(), ZonedDateTime.parse("2020-07-03T17:30:00Z"));
        assertNotEquals(courseParticipant.getScheduledStartDateTime(), ZonedDateTime.now());
        assertNotSame(courseParticipant.getScheduledStartDateTime(), ZonedDateTime.now().plusSeconds(22));
        
    }
    
    /**
     * <pre> 
     * It test the validation for scheduled end date and time based if it is null. If it is invalid, then
     * throw an argument with the corresponding message.
     * 
     * <pre>
     * 
     */
    @Test
    void testScheduledEndDateTime_IsNull() {
        IllegalArgumentException argument = assertThrows(IllegalArgumentException.class, () -> {
            new CourseParticipant.Builder(1L, 1000L, "java", "Lorenzo, Loyce", "WFH", 20L, "Abad, Kenneth", 
                    ZonedDateTime.parse("2020-07-03T17:30:00Z"), null, 
                    2.0f, ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();           
        });
        
        assertThat(argument.getMessage(), equalTo("Scheduled end date and time should not be empty"));  
    }
    
    /**
     * <pre> 
     * It test the validation for scheduled end date and time based if it is greater than or equal 
     * to scheduled start date and time. If it is invalid, then
     * throw an argument with the corresponding message.
     * 
     * <pre>
     * 
     */
    @Test
    void testScheduledEndDateTime_IsAfter() {
        IllegalArgumentException argument = assertThrows(IllegalArgumentException.class, () -> {
            new CourseParticipant.Builder(1L, 1000L, "java", "Lorenzo, Loyce", "WFH", 20L, "Abad, Kenneth", 
                    ZonedDateTime.parse("2020-07-03T17:30:00Z"), ZonedDateTime.parse("2020-06-03T17:30:00Z"), 
                    2.0f, ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();           
        });
        
        assertThat(argument.getMessage(), equalTo("Scheduled end date and time should be greater than or equal to the the scheduled start date and time"));  
    }
    
    /**
     * <pre> 
     * It test the validation for scheduled end date and time based if it is valid.
     * 
     * <pre>
     * 
     */
    @Test
    void testScheduledEndDateTime_IsValid() {
        CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 1000L, "java", "Lorenzo, Loyce", 
                "WFH", 20L, "Abad, Kenneth", ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                ZonedDateTime.parse("2020-07-03T17:30:00Z"),2.0f, 
                ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();
        
        assertEquals(courseParticipant.getScheduledEndDateTime(), ZonedDateTime.parse("2020-07-03T17:30:00Z"));
        assertNotEquals(courseParticipant.getScheduledEndDateTime(), ZonedDateTime.now());
        assertNotSame(courseParticipant.getScheduledEndDateTime(), ZonedDateTime.now().plusDays(5));
    }
    
    /**
     * <pre> 
     * It test the validation for registration date based if it is null. If it is invalid, then
     * throw an argument with the corresponding message.
     * 
     * <pre>
     * 
     */
    @Test
    void testRegistrationDate_IsNull() {
        IllegalArgumentException argument = assertThrows(IllegalArgumentException.class, () -> {
            new CourseParticipant.Builder(1L, 1000L, "java", "Lorenzo, Loyce", "WFH", 20L, "Abad, Kenneth", 
                    ZonedDateTime.parse("2020-07-03T17:30:00Z"), ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                    2.0f, null,"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();          
        });
        
        assertThat(argument.getMessage(), equalTo("Registration date should not be empty"));  
    }
    
    /**
     * <pre> 
     * It test the validation for registration date based if it is valid.
     * 
     * <pre>
     * 
     */
    @Test
    void testRegistrationDate_IsValid() {
        CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 1000L, "java", "Lorenzo, Loyce", 
                "WFH", 20L, "Abad, Kenneth", ZonedDateTime.parse("2020-07-03T17:30:00Z"), 
                ZonedDateTime.parse("2020-07-03T17:30:00Z"), 2.0f, 
                ZonedDateTime.parse("2020-07-03T17:30:00Z"),"k.abad@fujitsu.com", "220054288", 1L, "G3CC").build();
        
        assertEquals(courseParticipant.getRegistrationDate(), ZonedDateTime.parse("2020-07-03T17:30:00Z"));
        assertNotEquals(courseParticipant.getRegistrationDate(), ZonedDateTime.now());
        assertNotSame(courseParticipant.getRegistrationDate(), ZonedDateTime.now().plusHours(10));
   }   
}
