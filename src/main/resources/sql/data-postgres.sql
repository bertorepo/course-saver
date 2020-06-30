SET search_path = tsup;
INSERT INTO EMPLOYEE(ID, NUMBER, LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, USERNAME) 
	VALUES (1, 'A10210', 'LORENZO', 'LOYCE', 'l.lorenzo', 'l.lorenzo@gujitsu.ph');
INSERT INTO EMPLOYEE(ID, NUMBER, LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, USERNAME) 
	VALUES (2, 'A10456', 'DE LEON', 'JC', 'jc.deleon', 'jc.deleon@gujitsu.ph');
INSERT INTO EMPLOYEE(ID, NUMBER, LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, USERNAME) 
	VALUES (3, 'A15893', 'MACABUDBUD', 'JAY IAN', 'j.macabudbud', 'j.macabudbud@gujitsu.ph');

--Access Type
INSERT INTO EMPLOYEE_AUTH(ID, AUTH_NAME, USERNAME) VALUES (1, 'PMO', 'l.lorenzo');
INSERT INTO EMPLOYEE_AUTH(ID, AUTH_NAME, USERNAME) VALUES (2, 'INSTRUCTOR', 'jc.deleon');
INSERT INTO EMPLOYEE_AUTH(ID, AUTH_NAME, USERNAME) VALUES (3, 'PMO', 'j.macabudbud');

--Courses
INSERT INTO COURSE(ID, NAME) VALUES (1, 'SpringBoot');
INSERT INTO COURSE(ID, NAME) VALUES (2, 'Git');
INSERT INTO COURSE(ID, NAME) VALUES (3, 'Ruby on Rails');

--Venues 
INSERT INTO VENUE(ID, NAME) VALUES (1, 'Virtual Training');
INSERT INTO VENUE(ID, NAME) VALUES (2, 'Two/NEO 10th floor');
INSERT INTO VENUE(ID, NAME) VALUES (3, 'EcoTower 22nd Floor');

--Course Schedule
INSERT INTO COURSE_SCHEDULE(ID, COURSE_ID, INSTRUCTOR_ID, VENUE_ID, MIN_REQUIRED, MAX_ALLOWED, STATUS)
	VALUES (1, 1, 1, 1, 1, 20, 'A');
INSERT INTO COURSE_SCHEDULE(ID, COURSE_ID, INSTRUCTOR_ID, VENUE_ID, MIN_REQUIRED, MAX_ALLOWED, STATUS)
	VALUES (2, 2, 2, 2, 1, 20, 'A');
INSERT INTO COURSE_SCHEDULE(ID, COURSE_ID, INSTRUCTOR_ID, VENUE_ID, MIN_REQUIRED, MAX_ALLOWED, STATUS)
	VALUES (3, 3, 3, 3, 1, 20, 'A');
	
--Course Schedule Details
INSERT INTO COURSE_SCHEDULE_DETAILS(ID, COURSE_SCHEDULE_ID, SCHEDULED_START_DATETIME, SCHEDULED_END_DATETIME, 
	DURATION)
	VALUES (1, 1, NOW(), NOW() + interval '8 hours', 9);
INSERT INTO COURSE_SCHEDULE_DETAILS(ID, COURSE_SCHEDULE_ID, SCHEDULED_START_DATETIME, SCHEDULED_END_DATETIME, 
	DURATION)
	VALUES (2, 2, NOW(), NOW() + interval '8 hours', 9);
INSERT INTO COURSE_SCHEDULE_DETAILS(ID, COURSE_SCHEDULE_ID, SCHEDULED_START_DATETIME, SCHEDULED_END_DATETIME, 
	DURATION)
	VALUES (2, 2, NOW(), NOW() + interval '8 hours', 9);
	
--Course Participant
INSERT INTO COURSE_PARTICIPANT(ID, COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE) 
	VALUES (1, 1, 3, NOW());
INSERT INTO COURSE_PARTICIPANT(ID, COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE) 
	VALUES (2, 2, 3, NOW());
INSERT INTO COURSE_PARTICIPANT(ID, COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE) 
	VALUES (3, 3, 2, NOW());
	
--Course Non Participant
	
INSERT INTO COURSE_NON_PARTICIPANT(ID, COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE, REASON, DECLINE_DATE) 
	VALUES (1, 1, 3, NOW(), 'DUMMY DATA', NOW());
INSERT INTO COURSE_NON_PARTICIPANT(ID, COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE, REASON, DECLINE_DATE) 
	VALUES (2, 2, 3, NOW(), 'DUMMY DATA', NOW());
INSERT INTO COURSE_NON_PARTICIPANT(ID, COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE, REASON, DECLINE_DATE) 
	VALUES (3, 3, 2, NOW(), 'DUMMY DATA', NOW());
	
--Course Attendance
INSERT INTO COURSE_ATTENDANCE(ID, COURSE_SCHEDULE_DETAIL_ID, PARTICIPANT ID, STATUS, LOG_IN_DATETIME, 
		LOG_OUT_DATETIME, EMAIL)
	VALUES (1, 1, 3, 'A', NOW(), NOW(), 'j.macabudbud@gujitsu.ph');
INSERT INTO COURSE_ATTENDANCE(ID, COURSE_SCHEDULE_DETAIL_ID, PARTICIPANT ID, STATUS, LOG_IN_DATETIME, 
		LOG_OUT_DATETIME, EMAIL)
	VALUES (1, 2, 3, 'A', NOW(), NOW(), 'j.macabudbud@gujitsu.ph');
INSERT INTO COURSE_ATTENDANCE(ID, COURSE_SCHEDULE_DETAIL_ID, PARTICIPANT ID, STATUS, LOG_IN_DATETIME, 
		LOG_OUT_DATETIME, EMAIL)
	VALUES (1, 3, 2, 'A', NOW(), NOW(), 'jc.deleon@gujitsu.ph');