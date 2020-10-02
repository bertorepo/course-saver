SET search_path = tsup;

INSERT INTO tsup.DEPARTMENT(department_name)
	VALUES ('FDC-Apps Dev Manila');
	
INSERT INTO tsup.DEPARTMENT(department_name)
	VALUES ('FDC-G3CC');
	
INSERT INTO tsup.DEPARTMENT(department_name)
	VALUES ('FDC-Apps Dev Cebu');

--EMPLOYEE
INSERT INTO tsup.employee( number, last_name, first_name, email_address, username, department_id)
	VALUES ('12345678', 'Lorenzo', 'Loyce', 'l.lorenzo@fujitsu.com', 'l.lorenzo', 1);
INSERT INTO tsup.employee( number, last_name, first_name, email_address, username, department_id)
	VALUES ('22222222', 'De Leon', 'JC', 'jc.deleon@fujitsu.com', 'jc.deleon', 1);
INSERT INTO tsup.employee(number, last_name, first_name, email_address, username, department_id)
	VALUES ('33333333', 'De Guzman', 'Genevieve', 'g.deguzman@fujitsu.com', 'g.deguzman', 1);
INSERT INTO tsup.EMPLOYEE(NUMBER, LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, USERNAME, DEPARTMENT_ID) 
	VALUES ('A10210', 'Lazo', 'Josephine Noreen', 'j.lazo@fujitsu.com', 'j.lazo', 1);
INSERT INTO tsup.EMPLOYEE(NUMBER, LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, USERNAME, DEPARTMENT_ID) 
	VALUES ('A10456', 'Jimenez', 'John Carlo', 'jc.jimenez@fujitsu.com', 'jc.jimenez', 1);
INSERT INTO tsup.EMPLOYEE(NUMBER, LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, USERNAME, DEPARTMENT_ID) 
	VALUES ('A12435', 'Macabudbud', 'Jay Ian', 'j.macabudbud@fujitsu.com', 'j.macabudbud', 1);
INSERT INTO tsup.EMPLOYEE(NUMBER, LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, USERNAME, DEPARTMENT_ID) 
	VALUES ('A15893', 'Abad', 'Kenneth', 'k.abad@fujitsu.com', 'k.abad', 1);
INSERT INTO tsup.EMPLOYEE(NUMBER, LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, USERNAME, DEPARTMENT_ID) 
	VALUES ('A17843', 'Lumontad', 'Mark', 'm.lumontad@fujitsu.com', 'm.lumontad', 1);
INSERT INTO tsup.EMPLOYEE(NUMBER, LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, USERNAME, DEPARTMENT_ID) 
	VALUES ('A19075', 'Ramos', 'Ramon', 'r.ramos@fujitsu.com', 'r.ramos', 1);

--Access Type
INSERT INTO tsup.employee_auth(auth_name, username)
	VALUES ('PMO', 'l.lorenzo');
INSERT INTO tsup.employee_auth(auth_name, username)
	VALUES ('Instructor', 'g.deguzman');
INSERT INTO tsup.employee_auth(auth_name, username)
	VALUES ('Member', 'jc.deleon');
INSERT INTO tsup.EMPLOYEE_AUTH(AUTH_NAME, USERNAME) VALUES ('PMO', 'j.lazo');
INSERT INTO tsup.EMPLOYEE_AUTH(AUTH_NAME, USERNAME) VALUES ('Instructor', 'j.macabudbud');
INSERT INTO tsup.EMPLOYEE_AUTH(AUTH_NAME, USERNAME) VALUES ('PMO', 'jc.jimenez');
INSERT INTO tsup.EMPLOYEE_AUTH(AUTH_NAME, USERNAME) VALUES ('PMO', 'k.abad');
INSERT INTO tsup.EMPLOYEE_AUTH(AUTH_NAME, USERNAME) VALUES ('PMO', 'm.lumontad');
INSERT INTO tsup.EMPLOYEE_AUTH(AUTH_NAME, USERNAME) VALUES ('PMO', 'r.ramos');

--Courses
INSERT INTO tsup.COURSE(NAME, DETAIL) VALUES ('SpringBoot', 'DETAILS');
INSERT INTO tsup.COURSE(NAME, DETAIL) VALUES ('Git', 'DETAILS');
INSERT INTO tsup.COURSE(NAME, DETAIL) VALUES ('Ruby on Rails', 'DETAILS');
INSERT INTO tsup.course(name, detail)
	VALUES ('Understanding UI', 'Detail');
INSERT INTO tsup.course(name, detail)
	VALUES ('Understanding SS', 'Detail');
INSERT INTO tsup.course(name, detail)
	VALUES ('Goal Setting', 'Detail');
	
	
--Venues 
INSERT INTO tsup.VENUE(NAME) VALUES ('Virtual Training');
INSERT INTO tsup.VENUE(NAME) VALUES ('Two/NEO 10th floor');
INSERT INTO tsup.VENUE(NAME) VALUES ('EcoTower 22nd Floor');
INSERT INTO tsup.venue(name)
	VALUES ('EcoTower');
INSERT INTO tsup.venue(name)
	VALUES ('Two/Neo');
INSERT INTO tsup.venue(name)
	VALUES ('Online');
	
--Course Schedule
INSERT INTO tsup.COURSE_SCHEDULE(COURSE_ID, INSTRUCTOR_ID, VENUE_ID, MIN_REQUIRED, MAX_ALLOWED, STATUS)
	VALUES (1, 1, 1, 1, 20, 'O');
INSERT INTO tsup.COURSE_SCHEDULE(COURSE_ID, INSTRUCTOR_ID, VENUE_ID, MIN_REQUIRED, MAX_ALLOWED, STATUS)
	VALUES (2, 2, 2, 1, 20, 'O');
INSERT INTO tsup.COURSE_SCHEDULE(COURSE_ID, INSTRUCTOR_ID, VENUE_ID, MIN_REQUIRED, MAX_ALLOWED, STATUS)
	VALUES (3, 3, 3, 1, 20, 'O');	
INSERT INTO tsup.course_schedule(course_id, instructor_id, venue_id, min_required, max_allowed, status)
	VALUES (1, 1, 1, 10, 20, 'O');
INSERT INTO tsup.course_schedule(course_id, instructor_id, venue_id, min_required, max_allowed, status)
	VALUES (2, 2, 2, 5, 10, 'O');
INSERT INTO tsup.course_schedule(course_id, instructor_id, venue_id, min_required, max_allowed, status)
	VALUES (3, 3, 3, 20, 100, 'O');

--Course Schedule Details
INSERT INTO tsup.COURSE_SCHEDULE_DETAIL(COURSE_SCHEDULE_ID, SCHEDULED_START_DATETIME, SCHEDULED_END_DATETIME, 
	DURATION)
	VALUES (1, '2020-08-11 10:00:00', '2020-07-01 19:00:00', 9);
INSERT INTO tsup.COURSE_SCHEDULE_DETAIL(COURSE_SCHEDULE_ID, SCHEDULED_START_DATETIME, SCHEDULED_END_DATETIME, 
	DURATION)
	VALUES (2, '2020-08-11 10:00:00', '2020-07-01 19:00:00', 9);
INSERT INTO tsup.COURSE_SCHEDULE_DETAIL(COURSE_SCHEDULE_ID, SCHEDULED_START_DATETIME, SCHEDULED_END_DATETIME, 
	DURATION)
	VALUES (2, '2020-08-11 10:00:00', '2020-07-01 19:00:00', 9);
INSERT INTO tsup.course_schedule_detail(course_schedule_id, scheduled_start_datetime, scheduled_end_datetime, duration)
	VALUES (1, '2020-07-01 10:00:00', '2020-07-01 11:00:00', 2);
INSERT INTO tsup.course_schedule_detail(course_schedule_id, scheduled_start_datetime, scheduled_end_datetime, duration)
	VALUES (2, '2020-07-01 10:00:00', '2020-07-01 11:00:00', 2);
INSERT INTO tsup.course_schedule_detail(course_schedule_id, scheduled_start_datetime, scheduled_end_datetime, duration)
	VALUES (3, '2020-07-01 10:00:00', '2020-07-01 11:00:00', 2);
	
	
--Course Non Participant
INSERT INTO tsup.COURSE_NON_PARTICIPANT(COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE, REASON, DECLINE_DATE) 
	VALUES (1, 3, '2020-08-11 10:00:00',  'DUMMY DATA', '2020-07-01 19:00:00');
INSERT INTO tsup.COURSE_NON_PARTICIPANT(COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE, REASON, DECLINE_DATE) 
	VALUES (2, 3, '2020-08-11 10:00:00',  'DUMMY DATA', '2020-07-01 19:00:00');
INSERT INTO tsup.COURSE_NON_PARTICIPANT(COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE, REASON, DECLINE_DATE) 
	VALUES (3, 2, '2020-08-11 10:00:00',  'DUMMY DATA', '2020-07-01 19:00:00');
INSERT INTO tsup.course_non_participant(course_schedule_id, participant_id, registration_date, reason, decline_date)
	VALUES (1, 3, '2020-07-01 10:00:00', 'Reason', '2020-07-01 11:00:00');
INSERT INTO tsup.course_non_participant(course_schedule_id, participant_id, registration_date, reason, decline_date)
	VALUES (3, 1, '2020-07-01 10:00:00', 'Reason', '2020-07-01 11:00:00');
INSERT INTO tsup.course_non_participant(course_schedule_id, participant_id, registration_date, reason, decline_date)
	VALUES (2, 3, '2020-07-01 10:00:00', 'Reason', '2020-07-01 11:00:00');
	
--Course Participant
INSERT INTO tsup.COURSE_PARTICIPANT(COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE) 
	VALUES (1, 3, '2020-08-11 10:00:00');
INSERT INTO tsup.COURSE_PARTICIPANT(COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE) 
	VALUES (2, 3, '2020-08-11 10:00:00');
INSERT INTO tsup.COURSE_PARTICIPANT(COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE) 
	VALUES (3, 2, '2020-08-11 10:00:00');
INSERT INTO tsup.course_participant(course_schedule_id, participant_id, registration_date)
	VALUES (1, 3, '2020-07-01 10:00:00');
INSERT INTO tsup.course_participant(course_schedule_id, participant_id, registration_date)
	VALUES (2, 2, '2020-07-01 10:00:00');
INSERT INTO tsup.course_participant(course_schedule_id, participant_id, registration_date)
	VALUES (2, 1, '2020-07-01 10:00:00');
	
--Course Attendance
INSERT INTO tsup.COURSE_ATTENDANCE(COURSE_SCHEDULE_DETAIL_ID, PARTICIPANT_ID, STATUS, LOG_IN_DATETIME, 
		LOG_OUT_DATETIME, EMAIL)
	VALUES (1, 5, 'A', '2020-08-11 10:00:00', '2020-07-01 19:00:00', 'j.macabudbud@fujitsu.ph');
INSERT INTO tsup.COURSE_ATTENDANCE(COURSE_SCHEDULE_DETAIL_ID, PARTICIPANT_ID, STATUS, LOG_IN_DATETIME, 
		LOG_OUT_DATETIME, EMAIL)
	VALUES (2, 6, 'A', '2020-08-11 10:00:00', '2020-07-01 19:00:00', 'jc.jimenez@fujitsu.ph');
INSERT INTO tsup.COURSE_ATTENDANCE(COURSE_SCHEDULE_DETAIL_ID, PARTICIPANT_ID, STATUS, LOG_IN_DATETIME, 
		LOG_OUT_DATETIME, EMAIL)
	VALUES (3, 7, 'A', '2020-08-11 10:00:00', '2020-07-01 19:00:00', 'k.abad@fujitsu.ph');
INSERT INTO tsup.course_attendance(course_schedule_detail_id, participant_id, status, log_in_datetime, log_out_datetime, email)
	VALUES (1, 3, 'P', '2020-07-01 10:00:00', '2020-07-01 11:00:00', 'g.deguzman@fujitsu.ph');
INSERT INTO tsup.course_attendance(course_schedule_detail_id, participant_id, status, log_in_datetime, log_out_datetime, email)
	VALUES (2, 3, 'P', '2020-07-01 10:00:00', '2020-07-01 11:00:00', 'g.deguzman@fujitsu.ph');
INSERT INTO tsup.course_attendance(course_schedule_detail_id, participant_id, status, log_in_datetime, log_out_datetime, email)
	VALUES (3, 3, 'P', '2020-07-01 10:00:00', '2020-07-01 11:00:00', 'g.deguzman@fujitsu.ph');