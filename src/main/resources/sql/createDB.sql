/**
 * Author:  Zacharie BARBECOT <z.barbecot@gmail.com>
 * Created: 28 janv. 2016
 */

/**
 * TABLE GROUP
 */
CREATE TABLE rw_group (
    grp_id SERIAL PRIMARY KEY NOT NULL,
    grp_name VARCHAR(13) NOT NULL
);

INSERT INTO rw_group (grp_name)VALUES ('Administrator');
INSERT INTO rw_group (grp_name)VALUES ('Candidate');
INSERT INTO rw_group (grp_name)VALUES ('Employer');

/**
 * TABLE USER
 * PASSWORD HASH SHA-256
 */
CREATE TABLE rw_user (
    usr_id SERIAL PRIMARY KEY NOT NULL,
    usr_email VARCHAR(254) NOT NULL,
    usr_password CHAR(64) NOT NULL,
    usr_created TIMESTAMP NOT NULL,
    usr_updated TIMESTAMP,
    usr_view INT NOT NULL,
    usr_display_prf INT,
    usr_display_edu INT,
    usr_display_emp INT,
    usr_display_crs INT,
    usr_display_itr INT,
    grp_id INT NOT NULL REFERENCES rw_group(grp_id)
);

INSERT INTO rw_user (usr_email, usr_password, usr_created, usr_view, usr_display_prf, usr_display_edu, usr_display_emp, usr_display_crs, usr_display_itr, grp_id) VALUES ('z.barbecot@gmail.com', 'f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9', current_timestamp, 0, 2, 2, 2, 2, 2, 2);
INSERT INTO rw_user (usr_email, usr_password, usr_created, usr_view, grp_id) VALUES ('didier.bertille@gmail.com', 'f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9', current_timestamp, 0, 1);
INSERT INTO rw_user (usr_email, usr_password, usr_created, usr_view, usr_display_prf, usr_display_edu, usr_display_emp, usr_display_crs, usr_display_itr, grp_id) VALUES ('sara.kridane@gmail.com', 'f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9', current_timestamp, 0, 0, 0, 0, 0, 0, 2);
INSERT INTO rw_user (usr_email, usr_password, usr_created, usr_view, grp_id) VALUES ('daniele.varacca@u-pec.fr', 'f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9', current_timestamp, 0, 3);

CREATE VIEW rw_user_group AS
    SELECT u.usr_email as email, u.usr_password as password, g.grp_name as grp_name FROM rw_user u, rw_group g WHERE u.grp_id = g.grp_id;

/**
 * TABLE PROFILE
 * 
 */
CREATE TABLE rw_profil (
    prf_lname VARCHAR(65),
    prf_fname VARCHAR(65),
    prf_email VARCHAR(254),
    prf_job VARCHAR(254),
    prf_birthday DATE,
    prf_address TEXT,
    prf_zip CHAR(5),
    prf_city VARCHAR(65),
    prf_tel VARCHAR(14),
    prf_link VARCHAR(2083),
    prf_facebook VARCHAR(64),
    prf_twitter VARCHAR(64),
    prf_linkedin VARCHAR(64),
    usr_id INT PRIMARY KEY NOT NULL REFERENCES rw_user(usr_id)
);

INSERT INTO rw_profil (prf_lname, prf_fname, prf_email, prf_job, prf_birthday, prf_address, prf_zip, prf_city, prf_tel, usr_id) VALUES('BARBECOT', 'Zacharie', 'z.barbecot@gmail.com', 'Etudiant' , DATE '1989-12-24', '10, Avenue de la Résistance', '93100', 'Montreuil', '06.35.50.56.62', 1);

/**
 * TABLE EDUCATION & TRAINING
 * 
 */
CREATE TABLE rw_education (
    edu_id SERIAL PRIMARY KEY NOT NULL,
    edu_formation VARCHAR(256) NOT NULL,
    edu_place VARCHAR(256),
    edu_start DATE,
    edu_end DATE,
    edu_content TEXT,
    usr_id INT NOT NULL REFERENCES rw_user(usr_id)
);

INSERT INTO rw_education (edu_formation, edu_place, edu_start, edu_end, edu_content, usr_id) VALUES('Master 2 Logiciel', 'UPEM', DATE '01-09-2015', DATE '01-09-2016', 'J2EE, Java, Android, RMI, Big Data', 1);
INSERT INTO rw_education (edu_formation, edu_place, edu_start, edu_end, edu_content, usr_id) VALUES('Master 1 Sécurité des Systèmes Informatiques', 'UPEC', DATE '01-09-2014', DATE '01-06-2015', 'Java, Certificat, Machine de Turing', 1);
INSERT INTO rw_education (edu_formation, edu_place, edu_start, edu_end, edu_content, usr_id) VALUES('Licence Informatique', 'UPEC', DATE '01-09-2011', DATE '01-09-2014', 'Java, ', 1);

/**
 * TABLE EMPLOYMENT
 * 
 */
CREATE TABLE rw_employment (
    emp_id SERIAL PRIMARY KEY NOT NULL,
    emp_job VARCHAR(256) NOT NULL,
    emp_place VARCHAR(256),
    emp_start DATE,
    emp_end DATE,
    emp_content TEXT,
    usr_id INT NOT NULL REFERENCES rw_user(usr_id)
);

INSERT INTO rw_employment (emp_job, emp_place, emp_start, emp_end, emp_content, usr_id) VALUES('Mission Finance', 'CGI', DATE '01-04-2016', DATE '01-09-2016', 'J2EE, Java, MongoDB, Angular ', 1);
INSERT INTO rw_employment (emp_job, emp_place, emp_start, emp_end, emp_content, usr_id) VALUES('Dév Web', 'Les Ateliers Optentiel', DATE '01-05-2014', DATE '01-07-2016', 'HTML, CSS, PHP, WordPress, Bootstrap', 1);

/**
 * TABLE CORE SKILL
 * 
 */
CREATE TABLE rw_coreskill (
    crs_id SERIAL PRIMARY KEY NOT NULL,
    crs_name VARCHAR(64) NOT NULL,
    crs_level INT,
    usr_id INT NOT NULL REFERENCES rw_user(usr_id)
);

INSERT INTO rw_coreskill (crs_name, crs_level, usr_id) VALUES ('Java', 5, 1);
INSERT INTO rw_coreskill (crs_name, crs_level, usr_id) VALUES ('J2EE', 5, 1);
INSERT INTO rw_coreskill (crs_name, crs_level, usr_id) VALUES ('HTML', 4, 1);
INSERT INTO rw_coreskill (crs_name, crs_level, usr_id) VALUES ('CSS', 3, 1);
INSERT INTO rw_coreskill (crs_name, crs_level, usr_id) VALUES ('PHP', 3, 1);
INSERT INTO rw_coreskill (crs_name, crs_level, usr_id) VALUES ('C++', 2, 1);
INSERT INTO rw_coreskill (crs_name, crs_level, usr_id) VALUES ('SQL', 4, 1);
INSERT INTO rw_coreskill (crs_name, crs_level, usr_id) VALUES ('Javascript', 3, 1);
INSERT INTO rw_coreskill (crs_name, crs_level, usr_id) VALUES ('Ocaml', 1, 1);
INSERT INTO rw_coreskill (crs_name, crs_level, usr_id) VALUES ('Python', 1, 1);
INSERT INTO rw_coreskill (crs_name, crs_level, usr_id) VALUES ('Perl', 0, 1);

/**
 * TABLE INTERESTS
 * 
 */
CREATE TABLE rw_interests (
    itr_id SERIAL PRIMARY KEY NOT NULL,
    itr_name VARCHAR(64) NOT NULL,
    itr_content TEXT,
    usr_id INT NOT NULL REFERENCES rw_user(usr_id)
);

INSERT INTO rw_interests (itr_name, itr_content, usr_id) VALUES ('Cinéma', 'Thriller, Drame, Comédie', 1);
INSERT INTO rw_interests (itr_name, itr_content, usr_id) VALUES ('Jeux vidéo', 'RPG, HnS', 1);
INSERT INTO rw_interests (itr_name, itr_content, usr_id) VALUES ('Voyage', 'Italie, Grèce, Suisse, Belgique', 1);
