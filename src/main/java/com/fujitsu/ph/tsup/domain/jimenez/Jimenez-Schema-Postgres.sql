/* CREATE SCHEMA jcjimenez
    AUTHORIZATION postgres; */

-- DROP SCHEMA jimenez CASCADE;

-- DROP TABLE jimenez.course_schedule CASCADE;

CREATE TABLE jimenez.course_schedule
(
	id bigserial NOT NULL,
	course_id bigserial NOT NULL,
	instructor_id bigserial NOT NULL,
	venue_id bigserial NOT NULL,
	min_required INT NOT NULL,
	max_required INT NOT null,
	status VARCHAR(1) NOT null,
	PRIMARY KEY(id)
)
;

ALTER TABLE jimenez.course_schedule
    OWNER to postgres;
	
-- DROP TABLE jimenez.course;
	
CREATE TABLE jimenez.course
(
	id bigserial NOT NULL,
	name VARCHAR(100) NOT NULL,
	PRIMARY KEY(id)
)
;

ALTER TABLE jimenez.course
    OWNER to postgres;
	
-- DROP TABLE jimenez.venue;
	
CREATE TABLE jimenez.venue
(
	id bigserial NOT NULL,
	name VARCHAR(100) NOT NULL,
	PRIMARY KEY(id)
)
;

ALTER TABLE jimenez.venue
    OWNER to postgres;
	
-- DROP TABLE jimenez.employee;
	
CREATE TABLE jimenez.employee
(
	id bigserial NOT NULL,
	number VARCHAR(10) NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	email_address VARCHAR(50) NOT NULL,
	username VARCHAR(50) NOT NULL,
	PRIMARY KEY(id)
)
;

ALTER TABLE jimenez.employee
    OWNER to postgres;