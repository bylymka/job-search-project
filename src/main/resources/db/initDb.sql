CREATE TABLE IF NOT EXISTS users(
	id BIGINT NOT NULL AUTO_INCREMENT,
	username VARCHAR(45) NOT NULL,
	password VARCHAR(100) NOT NULL, 
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	email VARCHAR(255),
	phone_num VARCHAR(20),
	is_banned TINYINT(1) DEFAULT 0x00,
	UNIQUE(username),
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS roles(
	id INT NOT NULL AUTO_INCREMENT,
	role varchar(50),
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS user_roles(
	user_id BIGINT NOT NULL,
	role_id INT NOT NULL,
	FOREIGN KEY(user_id) REFERENCES users(id),
	FOREIGN KEY(role_id) REFERENCES roles(id)
);


CREATE TABLE IF NOT EXISTS companies(
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	code_EDRPOU BIGINT NOT NULL,
	adress VARCHAR(500),
	description VARCHAR(2000),
	industry VARCHAR(255),
	employees_num INT,
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS employers(
	id BIGINT NOT NULL AUTO_INCREMENT,
	user_id BIGINT NOT NULL,
	company_id BIGINT NOT NULL,
	position VARCHAR(255) NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(user_id) REFERENCES users(id),
	FOREIGN KEY(company_id) REFERENCES companies(id)
);

CREATE TABLE IF NOT EXISTS employees(
	id BIGINT NOT NULL AUTO_INCREMENT,
	user_id BIGINT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE  IF NOT EXISTS resumes(
	id BIGINT NOT NULL AUTO_INCREMENT,
	employee_id BIGINT NOT NULL,
	desired_position VARCHAR(255) NOT NULL,
	city VARCHAR(255) NOT NULL,
	work_experience VARCHAR(2000),
	skills VARCHAR(2000),
	PRIMARY KEY(id),
	FOREIGN KEY(employee_id) REFERENCES employees(id)
);

CREATE TABLE IF NOT EXISTS jobs(
	id BIGINT NOT NULL AUTO_INCREMENT,
	job_title VARCHAR(255) NOT NULL,
	desciption VARCHAR(2000),
	posted_on DATE NOT NULL,
	employer_id BIGINT NOT NULL,
	company_id BIGINT NOT NULL,
	industry VARCHAR(255) NOT NULL,
	status ENUM('active', 'non-active') DEFAULT 'active',
	location VARCHAR(255) NOT NULL,
	skills VARCHAR(2000) NOT NULL,
	salary INT,
	employment_type ENUM('full_time_job', 'part_time_job', 'remote_job', 'intership'),
	PRIMARY KEY(id),
	FOREIGN KEY(employer_id) REFERENCES employers(id)
);

INSERT INTO roles (role) VALUES('ROLE_ADMIN');
INSERT INTO roles (role) VALUES('ROLE_EMPLOYEE');
INSERT INTO roles (role) VALUES('ROLE_EMPLOYER');

CREATE TABLE job_applicants (
	job_id bigint,
	resume_id bigint,
	FOREIGN KEY(job_id) REFERENCES jobs(id),
	FOREIGN KEY(resume_id) REFERENCES resumes(id)
)