CREATE TABLE IF NOT EXISTS users(
	id BIGINT NOT NULL AUTO_INCREMENT,
	username VARCHAR(45) NOT NULL,
	password VARCHAR(100) NOT NULL, 
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	email VARCHAR(255),
	phoneNum VARCHAR(20),
	UNIQUE(Username),
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS user_roles(
	id BIGINT NOT NULL AUTO_INCREMENT,
	user_id BIGINT NOT NULL,
	role ENUM('admin', 'employee', 'employer'),
	PRIMARY KEY(id),
	FOREIGN KEY(user_id) REFERENCES users(id)
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
	desciption VARCHAR(2000) NOT NULL,
	posted_on DATE NOT NULL,
	employer_id BIGINT NOT NULL,
	company_id BIGINT NOT NULL,
	industry VARCHAR(255) NOT NULL,
	status ENUM('active', 'non-active') NOT NULL,
	location VARCHAR(255) NOT NULL,
	skills VARCHAR(2000) NOT NULL,
	salary BIGINT,
	employment_type ENUM('full_time_job', 'part_time_job', 'remote_job', 'intership'),
	PRIMARY KEY(id),
	FOREIGN KEY(employer_id) REFERENCES employers(id)
);