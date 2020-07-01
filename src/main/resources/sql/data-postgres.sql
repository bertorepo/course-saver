SET search_path = tsup;
INSERT INTO tsup.EMPLOYEE(ID, NUMBER, LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, USERNAME) 
	VALUES (1, 'A10210', 'LORENZO', 'LOYCE', 'l.lorenzo', 'l.lorenzo@fujitsu.ph');
INSERT INTO tsup.EMPLOYEE(ID, NUMBER, LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, USERNAME) 
	VALUES (2, 'A10456', 'DE LEON', 'JC', 'jc.deleon', 'jc.deleon@fujitsu.ph');
INSERT INTO tsup.EMPLOYEE(ID, NUMBER, LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, USERNAME) 
	VALUES (3, 'A15893', 'MACABUDBUD', 'JAY IAN', 'j.macabudbud', 'j.macabudbud@fujitsu.ph');

--Access Type
INSERT INTO tsup.EMPLOYEE_AUTH(ID, AUTH_NAME, USERNAME) VALUES (1, 'PMO', 'l.lorenzo');
INSERT INTO tsup.EMPLOYEE_AUTH(ID, AUTH_NAME, USERNAME) VALUES (2, 'INSTRUCTOR', 'jc.deleon');
INSERT INTO tsup.EMPLOYEE_AUTH(ID, AUTH_NAME, USERNAME) VALUES (3, 'PMO', 'j.macabudbud');

--Courses
INSERT INTO tsup.COURSE(ID, NAME) VALUES (1, 'SpringBoot', 'DETAILS');
INSERT INTO tsup.COURSE(ID, NAME) VALUES (2, 'Git', 'DETAILS');
INSERT INTO tsup.COURSE(ID, NAME) VALUES (3, 'Ruby on Rails', 'DETAILS');

--Venues 
INSERT INTO tsup.VENUE(ID, NAME) VALUES (1, 'Virtual Training');
INSERT INTO tsup.VENUE(ID, NAME) VALUES (2, 'Two/NEO 10th floor');
INSERT INTO tsup.VENUE(ID, NAME) VALUES (3, 'EcoTower 22nd Floor');

--Course Schedule
INSERT INTO tsup.COURSE_SCHEDULE(ID, COURSE_ID, INSTRUCTOR_ID, VENUE_ID, MIN_REQUIRED, MAX_ALLOWED, STATUS)
	VALUES (1, 1, 1, 1, 1, 20, 'A');
INSERT INTO tsup.COURSE_SCHEDULE(ID, COURSE_ID, INSTRUCTOR_ID, VENUE_ID, MIN_REQUIRED, MAX_ALLOWED, STATUS)
	VALUES (2, 2, 2, 2, 1, 20, 'A');
INSERT INTO tsup.COURSE_SCHEDULE(ID, COURSE_ID, INSTRUCTOR_ID, VENUE_ID, MIN_REQUIRED, MAX_ALLOWED, STATUS)
	VALUES (3, 3, 3, 3, 1, 20, 'A');
	
--Course Schedule Details
INSERT INTO tsup.COURSE_SCHEDULE_DETAIL(ID, COURSE_SCHEDULE_ID, SCHEDULED_START_DATETIME, SCHEDULED_END_DATETIME, 
	DURATION)
	VALUES (1, 1, NOW(), NOW() + interval '8 hours', 9);
INSERT INTO tsup.COURSE_SCHEDULE_DETAIL(ID, COURSE_SCHEDULE_ID, SCHEDULED_START_DATETIME, SCHEDULED_END_DATETIME, 
	DURATION)
	VALUES (2, 2, NOW(), NOW() + interval '8 hours', 9);
INSERT INTO tsup.COURSE_SCHEDULE_DETAIL(ID, COURSE_SCHEDULE_ID, SCHEDULED_START_DATETIME, SCHEDULED_END_DATETIME, 
	DURATION)
	VALUES (3, 2, NOW(), NOW() + interval '8 hours', 9);
	
--Course Participant
INSERT INTO tsup.COURSE_PARTICIPANT(ID, COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE) 
	VALUES (1, 1, 3, NOW());
INSERT INTO tsup.COURSE_PARTICIPANT(ID, COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE) 
	VALUES (2, 2, 3, NOW());
INSERT INTO tsup.COURSE_PARTICIPANT(ID, COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE) 
	VALUES (3, 3, 2, NOW());
	
--Course Non Participant
	
INSERT INTO tsup.COURSE_NON_PARTICIPANT(ID, COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE, REASON, DECLINE_DATE) 
	VALUES (1, 1, 3, NOW(), 'DUMMY DATA', NOW());
INSERT INTO tsup.COURSE_NON_PARTICIPANT(ID, COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE, REASON, DECLINE_DATE) 
	VALUES (2, 2, 3, NOW(), 'DUMMY DATA', NOW());
INSERT INTO tsup.COURSE_NON_PARTICIPANT(ID, COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE, REASON, DECLINE_DATE) 
	VALUES (3, 3, 2, NOW(), 'DUMMY DATA', NOW());
	
--Course Attendance
INSERT INTO tsup.COURSE_ATTENDANCE(ID, COURSE_SCHEDULE_DETAIL_ID, PARTICIPANT_ID, STATUS, LOG_IN_DATETIME, 
		LOG_OUT_DATETIME, EMAIL)
	VALUES (1, 1, 3, 'A', NOW(), NOW(), 'j.macabudbud@fujitsu.ph');
INSERT INTO tsup.COURSE_ATTENDANCE(ID, COURSE_SCHEDULE_DETAIL_ID, PARTICIPANT_ID, STATUS, LOG_IN_DATETIME, 
		LOG_OUT_DATETIME, EMAIL)
	VALUES (2, 2, 3, 'A', NOW(), NOW(), 'j.macabudbud@fujitsu.ph');
INSERT INTO tsup.COURSE_ATTENDANCE(ID, COURSE_SCHEDULE_DETAIL_ID, PARTICIPANT_ID, STATUS, LOG_IN_DATETIME, 
		LOG_OUT_DATETIME, EMAIL)
	VALUES (3, 3, 2, 'A', NOW(), NOW(), 'jc.deleon@fujitsu.ph');