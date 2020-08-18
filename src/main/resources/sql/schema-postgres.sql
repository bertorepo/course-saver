--DROP DATABASE tsup;

/*CREATE DATABASE tsup
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1; */
-- SEQUENCE: tsup."EMPLOYEE_AUTH_ID_seq"

-- DROP SCHEMA tsup;
    
CREATE SCHEMA tsup
    AUTHORIZATION postgres;

-- DROP SEQUENCE tsup."EMPLOYEE_AUTH_ID_seq";

CREATE SEQUENCE tsup."EMPLOYEE_AUTH_ID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE tsup."EMPLOYEE_AUTH_ID_seq"
    OWNER TO postgres;
    
-- SEQUENCE: tsup."EMPLOYEE_ID_seq"

-- DROP SEQUENCE tsup."EMPLOYEE_ID_seq";

CREATE SEQUENCE tsup."EMPLOYEE_ID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE tsup."EMPLOYEE_ID_seq"
    OWNER TO postgres;
    
-- SEQUENCE: tsup."EMPLOYEE_ID_seq"

-- DROP SEQUENCE tsup."COURSE_ID_seq";

CREATE SEQUENCE tsup."COURSE_ID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE tsup."COURSE_ID_seq"
    OWNER TO postgres;
    
-- DROP SEQUENCE tsup."VENUE_ID_seq";

CREATE SEQUENCE tsup."VENUE_ID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE tsup."VENUE_ID_seq"
    OWNER TO postgres;
    
-- DROP SEQUENCE tsup."COURSE_SCHEDULE_ID_seq";

CREATE SEQUENCE tsup."COURSE_SCHEDULE_ID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE tsup."COURSE_SCHEDULE_ID_seq"
    OWNER TO postgres;
    
-- DROP SEQUENCE tsup."COURSE_SCHEDULE_DETAIL_ID_seq";

CREATE SEQUENCE tsup."COURSE_SCHEDULE_DETAIL_ID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE tsup."COURSE_SCHEDULE_DETAIL_ID_seq"
    OWNER TO postgres;
    
-- DROP SEQUENCE tsup."COURSE_PARTICIPANT_ID_seq";

CREATE SEQUENCE tsup."COURSE_PARTICIPANT_ID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE tsup."COURSE_PARTICIPANT_ID_seq"
    OWNER TO postgres;
    
-- DROP SEQUENCE tsup."COURSE_NON_PARTICIPANT_ID_seq";

CREATE SEQUENCE tsup."COURSE_NON_PARTICIPANT_ID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE tsup."COURSE_NON_PARTICIPANT_ID_seq"
    OWNER TO postgres;
    
-- DROP SEQUENCE tsup."COURSE_ATTENDANCE_ID_seq";

CREATE SEQUENCE tsup."COURSE_ATTENDANCE_ID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE tsup."COURSE_ATTENDANCE_ID_seq"
    OWNER TO postgres;
    
CREATE SEQUENCE tsup."DEPARTMENT_ID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE tsup."DEPARTMENT_ID_seq"
    OWNER TO postgres;
    
CREATE TABLE tsup.DEPARTMENT
(
    id NOT NULL DEFAULT nextval('tsup."DEPARTMENT_ID_seq"'::regclass),
    department_name character varying(50) NOT NULL,
    CONSTRAINT "DEPARTMENT_pkey" PRIMARY KEY (id),
    CONSTRAINT "DEPARTMENT_NAME_unique" UNIQUE (department_name)
)

TABLESPACE pg_default;

ALTER TABLE tsup.DEPARTMENT
    OWNER to postgres;

-- Table: tsup.EMPLOYEE_AUTH

-- DROP TABLE tsup.EMPLOYEE_AUTH;

CREATE TABLE tsup.EMPLOYEE_AUTH
(
    ID bigint NOT NULL DEFAULT nextval('tsup."EMPLOYEE_AUTH_ID_seq"'::regclass),
    AUTH_NAME character varying(50) COLLATE pg_catalog."default" NOT NULL,
    USERNAME character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT "EMPLOYEE_AUTH_pkey" PRIMARY KEY (ID)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE tsup.EMPLOYEE_AUTH
    OWNER to postgres;
    
        
-- Table: tsup.EMPLOYEE

--DROP TABLE tsup.EMPLOYEE;

CREATE TABLE tsup.EMPLOYEE
(
    id bigint NOT NULL DEFAULT nextval('tsup."EMPLOYEE_ID_seq"'::regclass),
    "number" character varying(10) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    first_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    email_address character varying(50) COLLATE pg_catalog."default" NOT NULL,
    username character varying(50) COLLATE pg_catalog."default" NOT NULL,
    department_id bigint NOT NULL DEFAULT nextval('tsup."DEPARTMENT_ID_seq"'::regclass),
    CONSTRAINT "EMPLOYEE_pkey" PRIMARY KEY (id),
    CONSTRAINT "EMAIL_ADDRESS_unique" UNIQUE (email_address),
    CONSTRAINT "NUMBER_unique" UNIQUE ("number"),
    CONSTRAINT "USER_NAME_unique" UNIQUE (username),
    CONSTRAINT "DEPARTMENT_ID_fkey" FOREIGN KEY (department_id) REFERENCES tsup.department (id) MATCH SIMPLE 
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE tsup.EMPLOYEE
    OWNER to postgres;
    
-- Table: tsup.COURSE

--DROP TABLE tsup.COURSE;

CREATE TABLE tsup.COURSE
(
    ID bigint NOT NULL DEFAULT nextval('tsup."COURSE_ID_seq"'::regclass),
    NAME character varying(100) COLLATE pg_catalog."default",
    DETAIL character varying(200) COLLATE pg_catalog."default",
    CONSTRAINT "COURSE_pkey" PRIMARY KEY (ID),
    CONSTRAINT "COURSE_NAME_unique" UNIQUE (NAME)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE tsup.COURSE
    OWNER to postgres;
    
-- Table: tsup.VENUE

--DROP TABLE tsup.VENUE;

CREATE TABLE tsup.VENUE
(
    ID bigint NOT NULL DEFAULT nextval('tsup."VENUE_ID_seq"'::regclass),
    NAME character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT "VENUE_pkey" PRIMARY KEY (ID),
    CONSTRAINT "VENUE_NAME_unique" UNIQUE (NAME)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE tsup.VENUE
    OWNER to postgres;

--DROP TABLE tsup.COURSE_SCHEDULE;
    
CREATE TABLE tsup.COURSE_SCHEDULE
(
    ID bigint NOT NULL DEFAULT nextval('tsup."COURSE_SCHEDULE_ID_seq"'::regclass),
	COURSE_ID bigint NOT NULL DEFAULT nextval('tsup."COURSE_ID_seq"'::regclass),
	INSTRUCTOR_ID bigint NOT NULL DEFAULT nextval('tsup."EMPLOYEE_ID_seq"'::regclass),
	VENUE_ID bigint NOT NULL DEFAULT nextval('tsup."VENUE_ID_seq"'::regclass),
	MIN_REQUIRED integer NOT NULL,
	MAX_ALLOWED integer NOT NULL,
	STATUS character varying(1) NOT NULL COLLATE pg_catalog."default",
	CONSTRAINT "COURSE_SCHEDULE_pkey" PRIMARY KEY (ID),
	CONSTRAINT "COURSE_ID_fkey" FOREIGN KEY (COURSE_ID) REFERENCES tsup.COURSE(ID) MATCH SIMPLE ON DELETE CASCADE,
    CONSTRAINT "EMPLOYEE_ID_fkey" FOREIGN KEY (INSTRUCTOR_ID) REFERENCES tsup.EMPLOYEE(ID) MATCH SIMPLE ON DELETE CASCADE,
    CONSTRAINT "VENUE_ID_fkey" FOREIGN KEY (VENUE_ID) REFERENCES tsup.VENUE(ID) MATCH SIMPLE ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)

TABLESPACE pg_default;

ALTER TABLE tsup.COURSE_SCHEDULE
    OWNER to postgres;
    
--DROP TABLE tsup.COURSE_SCHEDULE_DETAIL
    
CREATE TABLE tsup.COURSE_SCHEDULE_DETAIL
(
    ID bigint NOT NULL DEFAULT nextval('tsup."COURSE_SCHEDULE_DETAIL_ID_seq"'::regclass),
	COURSE_SCHEDULE_ID bigint NOT NULL DEFAULT nextval('tsup."COURSE_SCHEDULE_ID_seq"'::regclass),
	SCHEDULED_START_DATETIME timestamp with time zone NOT NULL,
	SCHEDULED_END_DATETIME timestamp with time zone NOT NULL,
	DURATION numeric(5, 2) NOT NULL,
	rescheduled_start_datetime timestamp with time zone,
    rescheduled_end_datetime timestamp with time zone,
	CONSTRAINT "COURSE_SCHEDULE_DETAIL_pkey" PRIMARY KEY (ID),
	CONSTRAINT "COURSE_SCHEDULE_ID_fkey" FOREIGN KEY (COURSE_SCHEDULE_ID) REFERENCES tsup.COURSE_SCHEDULE(ID) MATCH SIMPLE ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)

TABLESPACE pg_default;

ALTER TABLE tsup.COURSE_SCHEDULE_DETAIL
    OWNER to postgres;
    
--DROP TABLE tsup.COURSE_PARTICIPANT;
    
CREATE TABLE tsup.COURSE_PARTICIPANT
(
    ID bigint NOT NULL DEFAULT nextval('tsup."COURSE_PARTICIPANT_ID_seq"'::regclass),
	COURSE_SCHEDULE_ID bigint NOT NULL DEFAULT nextval('tsup."COURSE_SCHEDULE_ID_seq"'::regclass),
	PARTICIPANT_ID bigint NOT NULL DEFAULT nextval('tsup."EMPLOYEE_ID_seq"'::regclass),
	REGISTRATION_DATE timestamp with time zone NOT NULL,
	CONSTRAINT "COURSE_PARTICIPANT_pkey" PRIMARY KEY (ID),
	CONSTRAINT "COURSE_SCHEDULE_ID_fkey" FOREIGN KEY (COURSE_SCHEDULE_ID) REFERENCES tsup.COURSE_SCHEDULE(ID) MATCH SIMPLE ON DELETE CASCADE,
    CONSTRAINT "EMPLOYEE_ID_fkey" FOREIGN KEY (PARTICIPANT_ID) REFERENCES tsup.EMPLOYEE(ID) MATCH SIMPLE ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)

TABLESPACE pg_default;

ALTER TABLE tsup.COURSE_PARTICIPANT
    OWNER to postgres;
   
--DROP TABLE tsup.COURSE_NON_PARTICIPANT;

CREATE TABLE tsup.COURSE_NON_PARTICIPANT
(
    ID bigint NOT NULL DEFAULT nextval('tsup."COURSE_NON_PARTICIPANT_ID_seq"'::regclass),
	COURSE_SCHEDULE_ID bigint NOT NULL DEFAULT nextval('tsup."COURSE_SCHEDULE_ID_seq"'::regclass),
	PARTICIPANT_ID bigint NOT NULL DEFAULT nextval('tsup."EMPLOYEE_ID_seq"'::regclass),
	REGISTRATION_DATE timestamp with time zone NOT NULL,
	REASON character varying(100) NOT NULL COLLATE pg_catalog."default",
	DECLINE_DATE timestamp with time zone NOT NULL,
	CONSTRAINT "COURSE_NON_PARTICIPANT_pkey" PRIMARY KEY (ID),
	CONSTRAINT "COURSE_SCHEDULE_ID_fkey" FOREIGN KEY (COURSE_SCHEDULE_ID) REFERENCES tsup.COURSE_SCHEDULE(ID) MATCH SIMPLE ON DELETE CASCADE,
    CONSTRAINT "EMPLOYEE_ID_fkey" FOREIGN KEY (PARTICIPANT_ID) REFERENCES tsup.EMPLOYEE(ID) MATCH SIMPLE ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)

TABLESPACE pg_default;

ALTER TABLE tsup.COURSE_NON_PARTICIPANT
    OWNER to postgres;
    
--DROP TABLE tsup.COURSE_ATTENDANCE;
    
CREATE TABLE tsup.COURSE_ATTENDANCE
(
    id bigint NOT NULL DEFAULT nextval('tsup."COURSE_ATTENDANCE_ID_seq"'::regclass),
    course_schedule_detail_id bigint NOT NULL DEFAULT nextval('tsup."COURSE_SCHEDULE_DETAIL_ID_seq"'::regclass),
    participant_id bigint NOT NULL DEFAULT nextval('tsup."EMPLOYEE_ID_seq"'::regclass),
    status character varying(1) COLLATE pg_catalog."default" NOT NULL,
    log_in_datetime timestamp with time zone NOT NULL,
    log_out_datetime timestamp with time zone,
    email character varying COLLATE pg_catalog."default",
    CONSTRAINT "COURSE_ATTENDANCE_pkey" PRIMARY KEY (id),
    CONSTRAINT "COURSE_SCHEDULE_DETAIL_ID_fkey" FOREIGN KEY (course_schedule_detail_id)
        REFERENCES tsup.course_schedule_detail (id) MATCH SIMPLE ON DELETE CASCADE,
    CONSTRAINT "EMPLOYEE_ID_fkey" FOREIGN KEY (participant_id)
        REFERENCES tsup.employee (id) MATCH SIMPLE ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)

TABLESPACE pg_default;

ALTER TABLE tsup.COURSE_ATTENDANCE
    OWNER to postgres;
    
INSERT INTO tsup.DEPARTMENT(
	id, department_name)
	VALUES (1, 'FDC-Apps Dev Manila');
	
INSERT INTO tsup.DEPARTMENT(
	id, department_name)
	VALUES (2, 'FDC-G3CC');
	
INSERT INTO tsup.DEPARTMENT(
	id, department_name)
	VALUES (3, 'FDC-Apps Dev Cebu');
    
INSERT INTO tsup.employee(
	id, number, last_name, first_name, email_address, username, department_id)
	VALUES (1, '12345678', 'LORENZO', 'LOYCE', 'l.lorenzo@fujitsu.ph', 'l.lorenzo', 1);
INSERT INTO tsup.employee(
	id, number, last_name, first_name, email_address, username, department_id)
	VALUES (2, '22222222', 'DE LEON', 'JC', 'jc.deleon@fujitsu.ph', 'jc.deleon', 1);
INSERT INTO tsup.employee(
	id, number, last_name, first_name, email_address, username, department_id)
	VALUES (3, '33333333', 'DE GUZMAN', 'GENEVIEVE', 'g.deguzman@fujitsu.ph', 'g.deguzman', 1);
	
INSERT INTO tsup.employee_auth(
	id, auth_name, username)
	VALUES (1, 'PMO', 'l.lorenzo');
INSERT INTO tsup.employee_auth(
	id, auth_name, username)
	VALUES (2, 'Instructor', 'g.deguzman');
INSERT INTO tsup.employee_auth(
	id, auth_name, username)
	VALUES (3, 'Member', 'jc.deleon');

INSERT INTO tsup.course(
	id, name, detail)
	VALUES (1, 'Understanding UI', 'Detail');
INSERT INTO tsup.course(
	id, name, detail)
	VALUES (2, 'Understanding SS', 'Detail');
INSERT INTO tsup.course(
	id, name, detail)
	VALUES (3, 'Goal Setting', 'Detail');

INSERT INTO tsup.venue(
	id, name)
	VALUES (1, 'EcoTower');
INSERT INTO tsup.venue(
	id, name)
	VALUES (2, 'Two/Neo');
INSERT INTO tsup.venue(
	id, name)
	VALUES (3, 'Online');
	
INSERT INTO tsup.course_schedule(
	id, course_id, instructor_id, venue_id, min_required, max_allowed, status)
	VALUES (1, 1, 1, 1, 10, 20, 'A');
INSERT INTO tsup.course_schedule(
	id, course_id, instructor_id, venue_id, min_required, max_allowed, status)
	VALUES (2, 2, 2, 2, 5, 10, 'A');
INSERT INTO tsup.course_schedule(
	id, course_id, instructor_id, venue_id, min_required, max_allowed, status)
	VALUES (3, 3, 3, 3, 20, 100, 'A');

INSERT INTO tsup.course_schedule_detail(
	id, course_schedule_id, scheduled_start_datetime, scheduled_end_datetime, duration)
	VALUES (1, 1, '2020-07-01 10:00:00', '2020-07-01 10:00:00', 2);
INSERT INTO tsup.course_schedule_detail(
	id, course_schedule_id, scheduled_start_datetime, scheduled_end_datetime, duration)
	VALUES (2, 2, '2020-07-01 10:00:00', '2020-07-01 10:00:00', 2);
INSERT INTO tsup.course_schedule_detail(
	id, course_schedule_id, scheduled_start_datetime, scheduled_end_datetime, duration)
	VALUES (3, 3, '2020-07-01 10:00:00', '2020-07-01 10:00:00', 2);
	
INSERT INTO tsup.course_non_participant(
	id, course_schedule_id, participant_id, registration_date, reason, decline_date)
	VALUES (1, 1, 3, '2020-07-01 10:00:00', 'Reason', '2020-07-01 10:00:00');
INSERT INTO tsup.course_non_participant(
	id, course_schedule_id, participant_id, registration_date, reason, decline_date)
	VALUES (2, 3, 1, '2020-07-01 10:00:00', 'Reason', '2020-07-01 10:00:00');
INSERT INTO tsup.course_non_participant(
	id, course_schedule_id, participant_id, registration_date, reason, decline_date)
	VALUES (3, 2, 3, '2020-07-01 10:00:00', 'Reason', '2020-07-01 10:00:00');
	
INSERT INTO tsup.course_attendance(
	id, course_schedule_detail_id, participant_id, status, log_in_datetime, log_out_datetime, email)
	VALUES (1, 1, 3, 'P', '2020-07-01 10:00:00', '2020-07-01 10:00:00', 'g.deguzman@fujitsu.ph');
INSERT INTO tsup.course_attendance(
	id, course_schedule_detail_id, participant_id, status, log_in_datetime, log_out_datetime, email)
	VALUES (2, 2, 3, 'P', '2020-07-01 10:00:00', '2020-07-01 10:00:00', 'g.deguzman@fujitsu.ph');
INSERT INTO tsup.course_attendance(
	id, course_schedule_detail_id, participant_id, status, log_in_datetime, log_out_datetime, email)
	VALUES (3, 3, 3, 'P', '2020-07-01 10:00:00', '2020-07-01 10:00:00', 'g.deguzman@fujitsu.ph');

INSERT INTO tsup.course_participant(
	id, course_schedule_id, participant_id, registration_date)
	VALUES (1, 1, 3, '2020-07-01 10:00:00');
INSERT INTO tsup.course_participant(
	id, course_schedule_id, participant_id, registration_date)
	VALUES (2, 2, 2, '2020-07-01 10:00:00');
INSERT INTO tsup.course_participant(
	id, course_schedule_id, participant_id, registration_date)
	VALUES (3, 2, 1, '2020-07-01 10:00:00');