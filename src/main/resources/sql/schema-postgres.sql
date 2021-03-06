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
    
-- DROP SEQUENCE tsup."CERTIFICATE_ID_seq";

CREATE SEQUENCE tsup."CERTIFICATE_ID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE tsup."CERTIFICATE_ID_seq"
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
    
-- DROP SEQUENCE tsup."COURSE_CATEGORY_ID_seq";

CREATE SEQUENCE tsup."COURSE_CATEGORY_ID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1; 

ALTER SEQUENCE tsup."COURSE_CATEGORY_ID_seq"
    OWNER TO postgres;
    
CREATE TABLE tsup.DEPARTMENT
(
    id bigint NOT NULL DEFAULT nextval('tsup."DEPARTMENT_ID_seq"'::regclass),
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
    
-- Table: tsup.COURSE_CATEGORY

--DROP TABLE tsup.COURSE_CATEGORY;

CREATE TABLE tsup.COURSE_CATEGORY
(
    ID bigint NOT NULL DEFAULT nextval('tsup."COURSE_CATEGORY_ID_seq"'::regclass),
    CATEGORY character varying(100) COLLATE pg_catalog."default",
    DETAIL character varying(200) COLLATE pg_catalog."default",
    CONSTRAINT "COURSE_CATEGORY_pkey" PRIMARY KEY (ID),
    CONSTRAINT "COURSE_CATEGORY_unique" UNIQUE (CATEGORY)
)
WITH (
    OIDS = FALSE
)

TABLESPACE pg_default;

ALTER TABLE tsup.COURSE_CATEGORY
    OWNER to postgres;

CREATE SEQUENCE tsup."MEMBER_ROLEID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE tsup.MEMBER_ROLE
(
    id bigint NOT NULL DEFAULT nextval('tsup."MEMBER_ROLEID_seq"'::regclass),
    role_type character varying(40),
    role_desc character varying(120),
    deleted_at timestamp,
    PRIMARY KEY (id)

)
WITH (
    OIDS = FALSE
)


TABLESPACE pg_default;

ALTER TABLE tsup.MEMBER_ROLE
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
    member_role_id bigint NOT NULL DEFAULT nextval('tsup."MEMBER_ROLEID_seq"'::regclass),
    employment_date date NOT NULL,
    CONSTRAINT "EMPLOYEE_pkey" PRIMARY KEY (id),
    CONSTRAINT "EMAIL_ADDRESS_unique" UNIQUE (email_address),
    CONSTRAINT "NUMBER_unique" UNIQUE ("number"),
    CONSTRAINT "USER_NAME_unique" UNIQUE (username),
    CONSTRAINT "DEPARTMENT_ID_fkey" FOREIGN KEY (department_id) REFERENCES tsup.department (id) MATCH SIMPLE, 
    CONSTRAINT "MEMBER_ROLE_ID_fkey" FOREIGN KEY (member_role_id) REFERENCES tsup.member_role (id) MATCH SIMPLE 
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
    NAME character varying(150) COLLATE pg_catalog."default",
    DETAIL character varying(200) COLLATE pg_catalog."default",
    course_category_id bigint NOT NULL DEFAULT nextval('tsup."COURSE_CATEGORY_ID_seq"'::regclass),
    CONSTRAINT "COURSE_pkey" PRIMARY KEY (ID),
    CONSTRAINT "COURSE_NAME_unique" UNIQUE (NAME),
    CONSTRAINT "COURSE_CATEGORY_ID_fkey" FOREIGN KEY (course_category_id) REFERENCES tsup.COURSE_CATEGORY (id) MATCH SIMPLE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE tsup.COURSE
    OWNER to postgres;
    
ALTER TABLE tsup.COURSE
	ADD COLUMN MANDATORY character varying(5) COLLATE pg_catalog."default",
	ADD COLUMN DEADLINE character varying(30) COLLATE pg_catalog."default";

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
    log_in_datetime timestamp with time zone,
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

CREATE TABLE tsup.CERTIFICATE_UPLOAD
(
    ID bigint NOT NULL DEFAULT nextval('tsup."CERTIFICATE_ID_seq"'::regclass),
	EMPLOYEE_ID bigint NOT NULL DEFAULT nextval('tsup."EMPLOYEE_ID_seq"'::regclass),
	COURSE_ID bigint NOT NULL DEFAULT nextval('tsup."COURSE_ID_seq"'::regclass),
	CERTIFICATE character varying(200) COLLATE pg_catalog."default",
	UPLOAD_DATE timestamp with time zone NOT NULL,
	fileDownloadUri character varying(200) COLLATE pg_catalog."default",
    CONSTRAINT "CERTIFICATE_pkey" PRIMARY KEY (ID),
	CONSTRAINT "COURSE_ID_fkey" FOREIGN KEY (COURSE_ID) REFERENCES tsup.COURSE(ID) MATCH SIMPLE ON DELETE CASCADE,
	CONSTRAINT "EMPLOYEE_ID_fkey" FOREIGN KEY (EMPLOYEE_ID) REFERENCES tsup.EMPLOYEE(ID) MATCH SIMPLE ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)

TABLESPACE pg_default;

ALTER TABLE tsup.CERTIFICATE_UPLOAD
    OWNER to postgres;

--Added 2021/06/18
SET search_path = tsup;
ALTER TABLE venue
ADD COLUMN overlap boolean;

--NEW COLUMN MANDATORY TYPE and NEW TABLE FOR JDU TYPE 

CREATE SEQUENCE "JDU_TYPE_ID_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1; 
ALTER SEQUENCE "JDU_TYPE_ID_seq"  
    OWNER TO postgres;
    
CREATE TABLE JDU_TYPE
(
    ID bigint NOT NULL DEFAULT nextval('tsup."JDU_TYPE_ID_seq"'::regclass) ,
    JDU_NAME character varying(100) COLLATE pg_catalog."default",
    TIMEZONE character varying(10) COLLATE pg_catalog."default",
    CONSTRAINT "JDU_TYPE_pkey" PRIMARY KEY (id),
    CONSTRAINT "JDU_TYPE_unique" UNIQUE (jdu_name)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default; 
ALTER TABLE JDU_TYPE
    OWNER to postgres;
    
ALTER TABLE department
    ADD COLUMN JDU_ID bigint NOT NULL DEFAULT 1,
    ADD CONSTRAINT "JDU_TYPE_fkey" FOREIGN KEY (JDU_ID) REFERENCES jdu_type(ID) MATCH SIMPLE ON DELETE CASCADE;
ALTER TABLE course
    ADD COLUMN DEPARTMENT_ID bigint NOT NULL DEFAULT 1,
    ADD CONSTRAINT "DEPARTMENT_fkey" FOREIGN KEY (DEPARTMENT_ID) REFERENCES department(ID) MATCH SIMPLE ON DELETE CASCADE;

--FOR MANDATORY_TYPE
ALTER TABLE course
    ADD COLUMN mandatory_type character varying(30) DEFAULT '-';

ALTER TABLE course
	ADD CONSTRAINT MANDATORY_TYPE_check 
	CHECK (mandatory_type = '-' or mandatory_type = 'JDU' or mandatory_type = 'GDC');
	

--Added 2021/07/05
--Function for getting non-attendees
--DROP FUNCTION tsup.GET_NON_ATTENDEES(mandatoryType VARCHAR, jdutype BIGINT);
CREATE OR REPLACE FUNCTION tsup.GET_NON_ATTENDEES(mandatoryType VARCHAR, jdutype BIGINT)
RETURNS TABLE (COURSE_ID BIGINT, COURSE_NAME VARCHAR, EMPLOYEE_ID BIGINT, EMPLOYEE_NAME TEXT)
language plpgsql
as $$
begin
	return query 
	SELECT DISTINCT C.id  AS COURSE_ID,
		   C.NAME AS COURSE_NAME,
		   E.id  AS EMPLOYEE_ID,
		   CONCAT(E.last_name,', ', E.first_name) As EMPLOYEE_NAME
	FROM   tsup.employee E,
		   tsup.course C
		   INNER JOIN tsup.department D
		   ON C.department_id = D.id
	WHERE  C.mandatory = 'Yes'
		   AND C.mandatory_type = mandatoryType
		   AND D.jdu_id = jdutype
		   AND ( C.id, C.NAME, E.last_name ) NOT IN(
		   SELECT DISTINCT C.id   AS COURSE_ID,
				   C.NAME AS COURSE_NAME,
				   E.last_name
			FROM   tsup.employee E
			INNER JOIN tsup.course_attendance CA
				   ON E.id = CA.participant_id
			   INNER JOIN tsup.course_schedule_detail CSD
					   ON CSD.id = CA.course_schedule_detail_id
			   INNER JOIN tsup.course_schedule CS
					   ON CS.id = CSD.course_schedule_id
			   INNER JOIN tsup.course C
					   ON C.id = CS.course_id
			   INNER JOIN tsup.department D
					   ON C.department_id = D.id
				WHERE  C.mandatory = 'Yes'
					   AND C.mandatory_type = mandatoryType
					   AND D.jdu_id = jdutype) 
	ORDER BY COURSE_ID, EMPLOYEE_ID;
end;$$
