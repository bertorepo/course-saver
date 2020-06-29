DROP DATABASE tsup

CREATE DATABASE tsup
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;
-- SEQUENCE: tsup."EMPLOYEE_AUTH_ID_seq"

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

-- Table: tsup."EMPLOYEE_AUTH"

-- DROP TABLE tsup."EMPLOYEE_AUTH";

CREATE TABLE tsup."EMPLOYEE_AUTH"
(
    ID bigint NOT NULL DEFAULT nextval('tsup."EMPLOYEE_AUTH_ID_seq"'::regclass),
    AUTH_NAME character varying(50) COLLATE pg_catalog."default",
    USERNAME character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT "EMPLOYEE_AUTH_pkey" PRIMARY KEY (ID)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE tsup."EMPLOYEE_AUTH"
    OWNER to postgres;
    
        
-- Table: tsup."EMPLOYEE"

DROP TABLE tsup.EMPLOYEE;

CREATE TABLE tsup.EMPLOYEE
(
    ID bigint NOT NULL DEFAULT nextval('tsup."EMPLOYEE_ID_seq"'::regclass),
    NUMBER character varying(50) COLLATE pg_catalog."default",
    LAST_NAME character varying(50) COLLATE pg_catalog."default",
    FIRST_NAME character varying(50) COLLATE pg_catalog."default",
    EMAIL_ADDRESS character varying(50) COLLATE pg_catalog."default",
    USERNAME character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT "EMPLOYEE_pkey" PRIMARY KEY (ID)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE tsup.EMPLOYEE
    OWNER to postgres;