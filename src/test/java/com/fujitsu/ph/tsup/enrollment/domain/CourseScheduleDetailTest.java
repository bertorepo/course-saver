package com.fujitsu.ph.tsup.enrollment.domain;

//====================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enrollment Process
//Class Name   :CourseScheduleDetailTest.java
//
//<<Modification History>>
//Version | Date       | Updated By | Content
//--------+------------+-----------------------+------
//0.01    | 07/03/2020 |  WS) J.Yu  | New Creation
//====================================================

import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
* <pre>
* JUnit Test for CourseScheduleDetail.java
* <pre>
* 
* @version 0.01
* @author j.yu
*/
public class CourseScheduleDetailTest {
  
      /**
       * Test case for Course Schedule Detail
       */
      @Test
      void testCourseScheduleDetail() {
          CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(1L, 1L,
                  ZonedDateTime.now(), ZonedDateTime.now().plusDays(5)).build();
          assertNotNull(courseScheduleDetail.getId());
          assertNotNull(courseScheduleDetail.getCourseScheduleId());
          assertNotNull(courseScheduleDetail.getScheduledStartDateTime());
          assertNotNull(courseScheduleDetail.getScheduledEndDateTime());
      }
      
      /**
       * Test case for when Id is valid
       */
      @Test
      void testIdIsValid() {
          CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(1L, 1L,
                  ZonedDateTime.now(), ZonedDateTime.now().plusDays(5)).build();
          assertEquals(1L, courseScheduleDetail.getId());    
      }
      
      /**
       * Test case for when Id is null
       */
      @Test
      void testIdIsNull() {
          Exception error = assertThrows(IllegalArgumentException.class, () -> {
              new CourseScheduleDetail.Builder(null, 1L, ZonedDateTime.now(), 
                      ZonedDateTime.now().plusDays(5)).build();});
          assertTrue(error.getMessage().equals("Id should not be empty"));
      }
      
      /**
       * Test case for when Id is empty
       */
      void testIdIsEmpty() {
          Exception error = assertThrows(IllegalArgumentException.class, () -> {
              new CourseScheduleDetail.Builder(0L, 1L, ZonedDateTime.now(), 
                      ZonedDateTime.now().plusDays(5)).build();});
          assertTrue(error.getMessage().equals("Id should not be empty"));
      }
      
      /**
       * Test case for when CourseScheduleId is valid
       */
      @Test
      void testCourseScheduleIdIsValid() {
          CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(1L, 1L,
                  ZonedDateTime.now(), ZonedDateTime.now().plusDays(5)).build();
          assertEquals(1L, courseScheduleDetail.getCourseScheduleId());
      }
      
      /**
       * Test case for when CourseScheduleId is null
       */
      @Test
      void testCourseScheduleIdIsNull() {
          Exception error = assertThrows(IllegalArgumentException.class, () -> {
              new CourseScheduleDetail.Builder(1L, null, ZonedDateTime.now(), 
                      ZonedDateTime.now().plusDays(5)).build();});
          assertTrue(error.getMessage().equals("Course Schedule Id should not be empty"));
      }
      
      /**
       * Test case for when CourseScheduleId is empty
       */
      @Test
      void testCourseScheduleIdIsEmpty() {
          Exception error = assertThrows(IllegalArgumentException.class, () -> {
              new CourseScheduleDetail.Builder(1L, 0L, ZonedDateTime.now(), 
                      ZonedDateTime.now().plusDays(5)).build();});
          assertTrue(error.getMessage().equals("Course Schedule Id should not be empty"));
      }
      
      /**
       * Test case for when ScheduledStartDateTime is valid
       */
      @Test
      void testScheduledStartDateTimeIsValid() {
          CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(1L, 1L,
                  ZonedDateTime.parse("2020-06-01T08:30:00Z"), ZonedDateTime.now().plusDays(5)).build();    
          assertEquals(ZonedDateTime.parse("2020-06-01T08:30:00Z"), courseScheduleDetail
                  .getScheduledStartDateTime());
      }
      
      /**
       * Test case for when ScheduledStartDateTime is null
       */
      @Test
      void testScheduledStartDateTimeIsNull() {
          Exception error = assertThrows(IllegalArgumentException.class, () -> {
              new CourseScheduleDetail.Builder(1L, 1L, null, ZonedDateTime.now().plusDays(5)).build();
          });
          assertTrue(error.getMessage().equals("Scheduled Start Date and Time should not be empty"));
      }
      
      /**
       * Test case for when ScheduledEndDateTime is valid
       */
      @Test
      void testScheduledEndDateTimeIsValid() {
          CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(1L, 1L,
                  ZonedDateTime.now(), ZonedDateTime.parse("2020-12-31T17:30:00Z")).build();
          assertEquals(ZonedDateTime.parse("2020-12-31T17:30:00Z"),courseScheduleDetail
                  .getScheduledEndDateTime());
      }
      
      /**
       * Test case for when ScheduledEndDateTime is null
       */
      @Test
      void testScheduledEndDateTimeIsNull() {
          Exception error = assertThrows(IllegalArgumentException.class, () -> {
              new CourseScheduleDetail.Builder(1L, 1L, ZonedDateTime.now(), null).build();
          });
          assertTrue(error.getMessage().equals("Scheduled End Date and Time should not be empty"));
      }
      
      /**
       * Test case for when ScheduledEndDateTime is less than ScheduledStartDateTime
       */
      @Test
      void testScheduledEndDateTimeIsLessThanScheduledStartDateTime() {
          Exception error = assertThrows(IllegalArgumentException.class, () -> {
              new CourseScheduleDetail.Builder(1L, 1L, ZonedDateTime.now(), 
                      ZonedDateTime.now().minusDays(5)).build();});
          assertTrue(error.getMessage().equals("Scheduled end date and time should be greater than or " + 
                                         "equal to the the scheduled start date and time"));
      }
      
      @Test
      void testComputeDuration() {
          CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(1L, 1L,
                  ZonedDateTime.now(), ZonedDateTime.now().plusDays(5)).build();
          assertEquals(120, courseScheduleDetail.getDuration());
      }
}