SET search_path = tsup;

INSERT INTO tsup.DEPARTMENT(department_name)
	VALUES ('FDC-Apps Dev Manila');
	
INSERT INTO tsup.DEPARTMENT(department_name)
	VALUES ('FDC-G3CC');
	
INSERT INTO tsup.DEPARTMENT(department_name)
	VALUES ('FDC-Apps Dev Cebu');
	

--Course Category
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Microsoft Web Applications','Detail for Microsoft Web Applications');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Web Development Technologies','Detail for Web Development Technologies');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('IBM Websphere','Detail for IBM Websphere');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Java 2 Programming','Detail for Java 2 Programming');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Java EE7','Detail for Java EE7');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Microsoft .NET Software Development Tools','Detail for Microsoft .NET Software Development Tools');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Generic Languages','Detail for Generic Languages');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Programming Best Practices','Detail for Programming Best Practices');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Software Design','Detail for Software Design');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Software Development Principles','Detail for Software Development Principles');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Software Testing','Detail for Software Testing');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Microsoft Windows Store Apps','Detail for Microsoft Windows Store Apps');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Microsoft C#','Detail for Microsoft C#');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Eclipse','Detail for Eclipse');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Microsoft LINQ','Detail for Microsoft LINQ');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Microsoft Enterprise Library','Detail for Microsoft Enterprise Library');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Microsoft ORM','Detail for Microsoft ORM');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Algorithms','Detail for Algorithms');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Mobile Application Development','Detail for Mobile Application Development');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Windows Embedded','Detail for Windows Embedded');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Configuration Management','Detail for Configuration Management');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Defensive Programming','Detail for Defensive Programming');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Apache','Detail for Apache');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Go','Detail for Go');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('Software Requirements','Detail for Software Requirements');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('JDU Standardization Training','Details for JDU Standardization Training');
--Added 2021/06/18
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('JDU BAS Standardization for Developers Training','Dev training');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('BSE Standardization','BSE training');
INSERT INTO tsup.COURSE_CATEGORY(CATEGORY, DETAIL) 
	VALUES('JDU Standardization for PMs/TLs Training','PM/TL training');

--Role Type
INSERT INTO tsup.MEMBER_ROLE(role_type, role_desc)
	VALUES ('PM', 'Project Manager');
INSERT INTO tsup.MEMBER_ROLE(role_type, role_desc)
	VALUES ('BSE', 'Bridge Software Engineer');
INSERT INTO tsup.MEMBER_ROLE(role_type, role_desc)
	VALUES ('TL', 'Team Lead');
INSERT INTO tsup.MEMBER_ROLE(role_type, role_desc)
	VALUES ('Dev', 'Developer');

--EMPLOYEE
INSERT INTO tsup.employee( number, last_name, first_name, email_address, username, department_id, member_role_id,employment_date)
	VALUES ('12345678', 'Lorenzo', 'Loyce', 'l.lorenzo@fujitsu.com', 'l.lorenzo', 2, 4, '2017-06-15');
INSERT INTO tsup.employee( number, last_name, first_name, email_address, username, department_id, member_role_id,employment_date)
	VALUES ('22222222', 'De Leon', 'JC', 'jc.deleon@fujitsu.com', 'jc.deleon', 2, 4, '2017-06-15');
INSERT INTO tsup.employee(number, last_name, first_name, email_address, username, department_id, member_role_id,employment_date)
	VALUES ('33333333', 'De Guzman', 'Genevieve', 'g.deguzman@fujitsu.com', 'g.deguzman', 2, 4, '2017-06-15');
INSERT INTO tsup.EMPLOYEE(NUMBER, LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, USERNAME, department_id, member_role_id,employment_date)
	VALUES ('A10210', 'Lazo', 'Josephine Noreen', 'j.lazo@fujitsu.com', 'j.lazo', 2, 4, '2017-06-15');
INSERT INTO tsup.EMPLOYEE(NUMBER, LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, USERNAME, department_id, member_role_id,employment_date)
	VALUES ('A10456', 'Jimenez', 'John Carlo', 'jc.jimenez@fujitsu.com', 'jc.jimenez', 2, 4, '2017-06-15');
INSERT INTO tsup.EMPLOYEE(NUMBER, LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, USERNAME, department_id, member_role_id,employment_date)
	VALUES ('A12435', 'Macabudbud', 'Jay Ian', 'j.macabudbud@fujitsu.com', 'j.macabudbud', 2, 4, '2017-06-15');
INSERT INTO tsup.EMPLOYEE(NUMBER, LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, USERNAME, department_id, member_role_id,employment_date)
	VALUES ('A15893', 'Abad', 'Kenneth', 'k.abad@fujitsu.com', 'k.abad', 2, 4, '2017-06-15');
INSERT INTO tsup.EMPLOYEE(NUMBER, LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, USERNAME, department_id, member_role_id,employment_date)
	VALUES ('A17843', 'Lumontad', 'Mark', 'm.lumontad@fujitsu.com', 'm.lumontad', 2, 4, '2017-06-15');
INSERT INTO tsup.EMPLOYEE(NUMBER, LAST_NAME, FIRST_NAME, EMAIL_ADDRESS, USERNAME, department_id, member_role_id,employment_date)
	VALUES ('A19075', 'Ramos', 'Ramon', 'r.ramos@fujitsu.com', 'r.ramos', 2, 4, '2017-06-15');

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
INSERT INTO tsup.COURSE(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('SpringBoot', 'DETAILS', 1, 'No', '-');
INSERT INTO tsup.COURSE(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Git', 'DETAILS',1, 'No', '-');
INSERT INTO tsup.COURSE(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Ruby on Rails', 'DETAILS',1, 'No', '-');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Goal Setting', 'Detail',26, 'No', '-');
--Added 2021/06/18
--JDU BAS STANDARDIZATION FOR DEVS TRAINING
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Standardization', 'Detail',74,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Understanding UI', 'Detail',74,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Understanding UI - Practical', 'Detail',74,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Understanding SS', 'Detail',74,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Understanding SS - Practical', 'Detail',74,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Q&A Session - Standardization, Understanding UI, and Understanding SS', 'Detail',74,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('How to use Git', 'Detail',74,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('How to use Yakushin', 'Detail',74,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Coding Standards', 'Detail',74,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Developer Testing', 'Detail',74,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Q&A Session - How to use Git, How to use Yakushin, Coding Standards, and Developer Testing', 'Detail',74,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Junit Practical', 'Detail',74,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Peer Review Report', 'Detail',74,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('PT Bug List', 'Detail',74,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('How to Implement Peer Review Points', 'Detail',74,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Q&A Session - Junit Practical, Peer Review Report, PT Bug List and How to Implement Peer Review Points', 'Detail',74,'Yes', 'Immediate');
--BSE STANDARDIZATION
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Minutes Creation', 'Detail',75,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Business Email', 'Detail',75,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Basic Japanese Etiquette', 'Detail',75,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Client Visit Process', 'Detail',75,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Translation Guidelines', 'Detail',75,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Interpretation for JP Clients', 'Detail',75,'Yes', 'Immediate');
--JDU BAS STANDARDIZATION FOR TL/PM TRAINING
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('PM Flow', 'Detail',76,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Project Plan', 'Detail',76,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Estimation', 'Detail',76,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Man Hour Management', 'Detail',76,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('PA/PFA', 'Detail',76,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('WBS Upload', 'Detail',76,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Weekly Status Report', 'Detail',76,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Yakushin Dashboard', 'Detail',76,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Furikaeri', 'Detail',76,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Quality Analysis Tool', 'Detail',76,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Productivity Analysis', 'Detail',76,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Audit', 'Detail',76,'Yes', 'Immediate');
INSERT INTO tsup.course(NAME, DETAIL, COURSE_CATEGORY_ID, MANDATORY, DEADLINE) VALUES ('Information Security', 'Detail',76,'Yes', 'Immediate');
	
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
	VALUES (1, '2020-07-01 19:00:00',  '2020-08-11 10:00:00',  9);
INSERT INTO tsup.COURSE_SCHEDULE_DETAIL(COURSE_SCHEDULE_ID, SCHEDULED_START_DATETIME, SCHEDULED_END_DATETIME, 
	DURATION)
	VALUES (2, '2020-07-01 19:00:00', '2020-08-11 10:00:00', 9);
INSERT INTO tsup.COURSE_SCHEDULE_DETAIL(COURSE_SCHEDULE_ID, SCHEDULED_START_DATETIME, SCHEDULED_END_DATETIME, 
	DURATION)
	VALUES (2, '2020-07-01 19:00:00', '2020-08-11 10:00:00', 9);
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

