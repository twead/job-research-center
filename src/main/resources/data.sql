insert into roles (role_name) values ('ROLE_ADMIN');
insert into roles (role_name) values ('ROLE_EMPLOYER');
insert into roles (role_name) values ('ROLE_EMPLOYEE');

INSERT INTO users(
activation, date_of_born, email, is_enabled, login_verification, login_verification_code, name, password, phone_number, reset_password_code, update_email, update_email_verification_code, role_id)
VALUES (null, null, 'admin@teszt.hu', true,false, null, 'Teszt Admin', '$2a$10$EG2vBDw7su8CyN9cSC1Y7uIEYphnXZ6sQW7QDjeNzcTaOj5k54mo6', null, null, null, null, 1);
INSERT INTO users(
activation, date_of_born, email, is_enabled, login_verification, login_verification_code, name, password, phone_number, reset_password_code, update_email, update_email_verification_code, role_id)
VALUES (null, null, 'teszt.employer1@teszt.hu', true, false, null, 'Teszt Employer-1', '$2a$10$EG2vBDw7su8CyN9cSC1Y7uIEYphnXZ6sQW7QDjeNzcTaOj5k54mo6', null, null, null, null, 2);
INSERT INTO users(
activation, date_of_born, email, is_enabled, login_verification, login_verification_code, name, password, phone_number, reset_password_code, update_email, update_email_verification_code, role_id)
VALUES (null, null, 'teszt.employer2@teszt.hu', true,false, null,'Teszt Employer-2', '$2a$10$EG2vBDw7su8CyN9cSC1Y7uIEYphnXZ6sQW7QDjeNzcTaOj5k54mo6', null, null, null, null, 2);
INSERT INTO users(
activation, date_of_born, email, is_enabled, login_verification, login_verification_code, name, password, phone_number, reset_password_code, update_email, update_email_verification_code, role_id)
VALUES (null, null, 'teszt.employer3@teszt.hu', true, false, null,'Teszt Employer-3', '$2a$10$EG2vBDw7su8CyN9cSC1Y7uIEYphnXZ6sQW7QDjeNzcTaOj5k54mo6', null, null, null, null, 2);
INSERT INTO users(
activation, date_of_born, email, is_enabled, login_verification, login_verification_code, name, password, phone_number, reset_password_code, update_email, update_email_verification_code, role_id)
VALUES (null, null, 'teszt.employer4@teszt.hu', true, false, null,'Teszt Employer-4', '$2a$10$EG2vBDw7su8CyN9cSC1Y7uIEYphnXZ6sQW7QDjeNzcTaOj5k54mo6', null, null, null, null, 2);
INSERT INTO users(
activation, date_of_born, email, is_enabled, login_verification, login_verification_code, name, password, phone_number, reset_password_code, update_email, update_email_verification_code, role_id)
VALUES (null, null, 'teszt.employer5@teszt.hu', true, false, null,'Teszt Employer-5', '$2a$10$EG2vBDw7su8CyN9cSC1Y7uIEYphnXZ6sQW7QDjeNzcTaOj5k54mo6', null, null, null, null, 2);
INSERT INTO users(
activation, date_of_born, email, is_enabled, login_verification, login_verification_code, name, password, phone_number, reset_password_code, update_email, update_email_verification_code, role_id)
VALUES (null, null, 'teszt.employer6@teszt.hu', true, false, null,'Teszt Employer-6', '$2a$10$EG2vBDw7su8CyN9cSC1Y7uIEYphnXZ6sQW7QDjeNzcTaOj5k54mo6', null, null, null, null, 2);
INSERT INTO users(
 activation, date_of_born, email, is_enabled, login_verification, login_verification_code, name, password, phone_number, reset_password_code, update_email, update_email_verification_code, role_id)
VALUES (null, null, 'teszt.employer7@teszt.hu', true, false, null,'Teszt Employer-7', '$2a$10$EG2vBDw7su8CyN9cSC1Y7uIEYphnXZ6sQW7QDjeNzcTaOj5k54mo6', null, null, null, null, 2);
INSERT INTO users(
activation, date_of_born, email, is_enabled, login_verification, login_verification_code, name, password, phone_number, reset_password_code, update_email, update_email_verification_code, role_id)
VALUES (null, null, 'teszt.employer8@teszt.hu', true, false, null,'Teszt Employer-8', '$2a$10$EG2vBDw7su8CyN9cSC1Y7uIEYphnXZ6sQW7QDjeNzcTaOj5k54mo6', null, null, null, null, 2);

INSERT INTO employers(
picture, validated, user_id)
VALUES (null, true, 2);
INSERT INTO employers(
picture, validated, user_id)
VALUES (null, true, 3);
INSERT INTO employers(
picture, validated, user_id)
VALUES (null, true, 4);
INSERT INTO employers(
picture, validated, user_id)
VALUES (null, false, 5);
INSERT INTO employers(
picture, validated, user_id)
VALUES (null, false, 6);
INSERT INTO employers(
picture, validated, user_id)
VALUES (null, false, 7);
INSERT INTO employers(
picture, validated, user_id)
VALUES (null, false, 8);
INSERT INTO employers(
picture, validated, user_id)
VALUES (null, true, 9);

INSERT INTO users(
activation, date_of_born, email, is_enabled, login_verification, login_verification_code, name, password, phone_number, reset_password_code, update_email, update_email_verification_code, role_id)
VALUES (null, null, 'teszt.employee1@teszt.hu', true, false, null,'Teszt Employee-1', '$2a$10$EG2vBDw7su8CyN9cSC1Y7uIEYphnXZ6sQW7QDjeNzcTaOj5k54mo6', null, null, null, null, 3);
INSERT INTO users(
activation, date_of_born, email, is_enabled, login_verification, login_verification_code, name, password, phone_number, reset_password_code, update_email, update_email_verification_code, role_id)
VALUES (null, null, 'teszt.employee2@teszt.hu', true, false, null,'Teszt Employee-2', '$2a$10$EG2vBDw7su8CyN9cSC1Y7uIEYphnXZ6sQW7QDjeNzcTaOj5k54mo6', null, null, null, null, 3);
INSERT INTO users(
activation, date_of_born, email, is_enabled, login_verification, login_verification_code, name, password, phone_number, reset_password_code, update_email, update_email_verification_code, role_id)
VALUES (null, null, 'teszt.employee3@teszt.hu', true, false, null,'Teszt Employee-3', '$2a$10$EG2vBDw7su8CyN9cSC1Y7uIEYphnXZ6sQW7QDjeNzcTaOj5k54mo6', null, null, null, null, 3);
INSERT INTO users(
activation, date_of_born, email, is_enabled, login_verification, login_verification_code, name, password, phone_number, reset_password_code, update_email, update_email_verification_code, role_id)
VALUES (null, null, 'teszt.employee4@teszt.hu', true, false, null,'Teszt Employee-4', '$2a$10$EG2vBDw7su8CyN9cSC1Y7uIEYphnXZ6sQW7QDjeNzcTaOj5k54mo6', null, null, null, null, 3);
INSERT INTO users(
activation, date_of_born, email, is_enabled, login_verification, login_verification_code, name, password, phone_number, reset_password_code, update_email, update_email_verification_code, role_id)
VALUES (null, null, 'teszt.employee5@teszt.hu', true, false, null,'Teszt Employee-5', '$2a$10$EG2vBDw7su8CyN9cSC1Y7uIEYphnXZ6sQW7QDjeNzcTaOj5k54mo6', null, null, null, null, 3);
INSERT INTO users(
activation, date_of_born, email, is_enabled, login_verification, login_verification_code, name, password, phone_number, reset_password_code, update_email, update_email_verification_code, role_id)
VALUES (null, null, 'teszt.employee6@teszt.hu', true, false, null,'Teszt Employee-6', '$2a$10$EG2vBDw7su8CyN9cSC1Y7uIEYphnXZ6sQW7QDjeNzcTaOj5k54mo6', null, null, null, null, 3);
INSERT INTO users(
activation, date_of_born, email, is_enabled, login_verification, login_verification_code, name, password, phone_number, reset_password_code, update_email, update_email_verification_code, role_id)
VALUES (null, null, 'teszt.employee7@teszt.hu', true, false, null,'Teszt Employee-7', '$2a$10$EG2vBDw7su8CyN9cSC1Y7uIEYphnXZ6sQW7QDjeNzcTaOj5k54mo6', null, null, null, null, 3);
INSERT INTO users(
activation, date_of_born, email, is_enabled, login_verification, login_verification_code, name, password, phone_number, reset_password_code, update_email, update_email_verification_code, role_id)
VALUES (null, null, 'teszt.employee8@teszt.hu', true, false, null,'Teszt Employee-8', '$2a$10$EG2vBDw7su8CyN9cSC1Y7uIEYphnXZ6sQW7QDjeNzcTaOj5k54mo6', null, null, null, null, 3);


INSERT INTO advertisements(
available, city, date_of_upload, description, payment, title, type, employer_id)
VALUES (true, null, '2021-09-27 11:46:51.997', 'Lorem ipsum dolor sit amet, inermis epicuri probatus ne sit, amet case scribentur qui at, persecuti dissentiunt vis eu. Ne audire reprehendunt mea, te sed case etiam aliquando. Nam te labitur apeirian theophrastus, te nulla animal definitionem cum. Pri ei erant appellantur, congue populo nam et. Cum consetetur voluptatibus cu, nemore doctus eruditi ad nec, cu suas placerat molestiae quo.
At cum porro feugiat consequat. An cum putant urbanitas neglegentur. Eu choro oblique petentium sit, etiam equidem scriptorem his at. Vix exerci aliquam no, ne sit diam veniam neglegentur, probo quando ei per.
', 0, 'Java tesztelői pozíció', 'Java tesztelés', 1);
INSERT INTO advertisements(
available, city, date_of_upload, description, payment, title, type, employer_id)
VALUES (true, 'Kecskemét', '2021-09-27 11:47:51.997', 'Lorem ipsum dolor sit amet, inermis epicuri probatus ne sit, amet case scribentur qui at, persecuti dissentiunt vis eu. Ne audire reprehendunt mea, te sed case etiam aliquando. Nam te labitur apeirian theophrastus, te nulla animal definitionem cum. Pri ei erant appellantur, congue populo nam et. Cum consetetur voluptatibus cu, nemore doctus eruditi ad nec, cu suas placerat molestiae quo.
At cum porro feugiat consequat. An cum putant urbanitas neglegentur. Eu choro oblique petentium sit, etiam equidem scriptorem his at. Vix exerci aliquam no, ne sit diam veniam neglegentur, probo quando ei per.
', 250000, 'Gépjármű szerelés', 'Autószerelés', 2);
INSERT INTO advertisements(
available, city, date_of_upload, description, payment, title, type, employer_id)
VALUES (true, 'Pécs', '2021-09-27 11:48:51.997', 'Lorem ipsum dolor sit amet, inermis epicuri probatus ne sit, amet case scribentur qui at, persecuti dissentiunt vis eu. Ne audire reprehendunt mea, te sed case etiam aliquando. Nam te labitur apeirian theophrastus, te nulla animal definitionem cum. Pri ei erant appellantur, congue populo nam et. Cum consetetur voluptatibus cu, nemore doctus eruditi ad nec, cu suas placerat molestiae quo.
At cum porro feugiat consequat. An cum putant urbanitas neglegentur. Eu choro oblique petentium sit, etiam equidem scriptorem his at. Vix exerci aliquam no, ne sit diam veniam neglegentur, probo quando ei per.
', 0, 'Hr gyakornoki pozíció', 'Hr', 1);
INSERT INTO advertisements(
available, city, date_of_upload, description, payment, title, type, employer_id)
VALUES (true, 'Kecskemét', '2021-09-27 11:49:51.997', 'Lorem ipsum dolor sit amet, inermis epicuri probatus ne sit, amet case scribentur qui at, persecuti dissentiunt vis eu. Ne audire reprehendunt mea, te sed case etiam aliquando. Nam te labitur apeirian theophrastus, te nulla animal definitionem cum. Pri ei erant appellantur, congue populo nam et. Cum consetetur voluptatibus cu, nemore doctus eruditi ad nec, cu suas placerat molestiae quo.
At cum porro feugiat consequat. An cum putant urbanitas neglegentur. Eu choro oblique petentium sit, etiam equidem scriptorem his at. Vix exerci aliquam no, ne sit diam veniam neglegentur, probo quando ei per.
', 300000, 'Java Backend fejlesztés', 'Java Spring Boot', 1);
INSERT INTO advertisements(
available, city, date_of_upload, description, payment, title, type, employer_id)
VALUES (true, 'Visegrád', '2021-09-27 11:50:51.997', 'Lorem ipsum dolor sit amet, inermis epicuri probatus ne sit, amet case scribentur qui at, persecuti dissentiunt vis eu. Ne audire reprehendunt mea, te sed case etiam aliquando. Nam te labitur apeirian theophrastus, te nulla animal definitionem cum. Pri ei erant appellantur, congue populo nam et. Cum consetetur voluptatibus cu, nemore doctus eruditi ad nec, cu suas placerat molestiae quo.
At cum porro feugiat consequat. An cum putant urbanitas neglegentur. Eu choro oblique petentium sit, etiam equidem scriptorem his at. Vix exerci aliquam no, ne sit diam veniam neglegentur, probo quando ei per.
', 0, 'Cukrász állás pályakezdőknek', 'Cukrász', 3);
INSERT INTO advertisements(
available, city, date_of_upload, description, payment, title, type, employer_id)
VALUES (true, 'Visegrád', '2021-09-27 11:51:51.997', 'Lorem ipsum dolor sit amet, inermis epicuri probatus ne sit, amet case scribentur qui at, persecuti dissentiunt vis eu. Ne audire reprehendunt mea, te sed case etiam aliquando. Nam te labitur apeirian theophrastus, te nulla animal definitionem cum. Pri ei erant appellantur, congue populo nam et. Cum consetetur voluptatibus cu, nemore doctus eruditi ad nec, cu suas placerat molestiae quo.
At cum porro feugiat consequat. An cum putant urbanitas neglegentur. Eu choro oblique petentium sit, etiam equidem scriptorem his at. Vix exerci aliquam no, ne sit diam veniam neglegentur, probo quando ei per.
', 300000, 'Karbantartó ezermester péküzemben!', 'Karbantartói munka', 3);


INSERT INTO messages(
	date_of_sending, from_employer, message, employer_id, user_id)
	VALUES ('2021-09-27 18:19:05.722', true, 'Vel mazim tamquam ea, cu has simul legere putent, ea aperiam accusam platonem his. Ei elitr intellegat eum. Vis sumo repudiare prodesset ea, an probo aperiri ceteros vis. No eros sadipscing liberavisse usu. Mediocrem petentium philosophia te vix.
Viris scriptorem vel te, everti iuvaret facilis ut quo. Id semper dolorem vis, ad cum tollit appellantur conclusionemque, wisi idque pro ex. Noster discere vel et. Mel movet ocurreret constituam ei.', 1, 10);	
INSERT INTO messages(
	 date_of_sending, from_employer, message, employer_id, user_id)
	VALUES ( '2021-09-27 18:20:05.722', false, ' Id semper dolorem vis, ad cum tollit appellantur conclusionemque, wisi idque pro ex. Noster discere vel et. Mel movet ocurreret constituam ei.',  1, 10);	
INSERT INTO messages(
	 date_of_sending, from_employer, message, employer_id, user_id)
	VALUES ( '2021-09-27 18:21:05.722', false, 'Vel mazim tamquam ea, cu has simul legere putent, ea aperiam accusam platonem his. Ei elitr intellegat eum. Vis sumo repudiare prodesset ea, an probo aperiri ceteros vis. No eros sadipscing liberavisse usu. Mediocrem petentium philosophia te vix.
Viris scriptorem vel te, everti iuvaret facilis ut quo. Id semper dolorem vis, ad cum tollit appellantur conclusionemque, wisi idque pro ex. Noster discere vel et. Mel movet ocurreret constituam ei.',  2, 10);	
INSERT INTO messages(
	 date_of_sending, from_employer, message, employer_id, user_id)
	VALUES ( '2021-09-27 18:22:05.722', true, 'Vel mazim tamquam ea, cu has simul legere putent, ea aperiam accusam platonem his. Ei elitr intellegat eum. Vis sumo repudiare prodesset ea, an probo aperiri ceteros vis. No eros sadipscing liberavisse usu. Mediocrem petentium philosophia te vix.
Viris scriptorem vel te, everti iuvaret facilis ut quo. Id semper dolorem vis, ad cum tollit appellantur conclusionemque, wisi idque pro ex. Noster discere vel et. Mel movet ocurreret constituam ei.', 1, 12);	
INSERT INTO messages(
	 date_of_sending, from_employer, message, employer_id, user_id)
	VALUES ( '2021-09-27 18:23:05.722', true, 'Vel mazim tamquam ea, cu has simul legere putent, ea aperiam accusam platonem his. Ei elitr intellegat eum. Vis sumo repudiare prodesset ea, an probo aperiri ceteros vis. No eros sadipscing liberavisse usu. Mediocrem petentium philosophia te vix.
Viris scriptorem vel te, everti iuvaret facilis ut quo. Id semper dolorem vis, ad cum tollit appellantur conclusionemque, wisi idque pro ex. Noster discere vel et. Mel movet ocurreret constituam ei.',  2, 10);	
INSERT INTO messages(
	 date_of_sending, from_employer, message, employer_id, user_id)
	VALUES ( '2021-09-27 18:24:05.722', true, 'Vel mazim tamquam ea, cu has simul legere putent, ea aperiam accusam platonem his. Ei elitr intellegat eum. Vis sumo repudiare prodesset ea, an probo aperiri ceteros vis. No eros sadipscing liberavisse usu. Mediocrem petentium philosophia te vix.
Viris scriptorem vel te, everti iuvaret facilis ut quo. Id semper dolorem vis, ad cum tollit appellantur conclusionemque, wisi idque pro ex. Noster discere vel et. Mel movet ocurreret constituam ei.', 1, 11);	
INSERT INTO messages(
	 date_of_sending, from_employer, message, employer_id, user_id)
	VALUES ('2021-09-27 18:25:05.722', true, 'Vel mazim tamquam ea, cu has simul legere putent, ea aperiam accusam platonem his. Ei elitr intellegat eum. Vis sumo repudiare prodesset ea, an probo aperiri ceteros vis. No eros sadipscing liberavisse usu. Mediocrem petentium philosophia te vix.
Viris scriptorem vel te, everti iuvaret facilis ut quo. Id semper dolorem vis, ad cum tollit appellantur conclusionemque, wisi idque pro ex. Noster discere vel et. Mel movet ocurreret constituam ei.', 1, 13);	
INSERT INTO messages(
	date_of_sending, from_employer, message, employer_id, user_id)
	VALUES ('2021-09-27 18:26:05.722', true, 'Vel mazim tamquam ea, cu has simul legere putent, ea aperiam accusam platonem his. Ei elitr intellegat eum. Vis sumo repudiare prodesset ea, an probo aperiri ceteros vis. No eros sadipscing liberavisse usu. Mediocrem petentium philosophia te vix.
Viris scriptorem vel te, everti iuvaret facilis ut quo. Id semper dolorem vis, ad cum tollit appellantur conclusionemque, wisi idque pro ex. Noster discere vel et. Mel movet ocurreret constituam ei.', 1, 10);	

INSERT INTO applications(
	available, comment, date_of_application, key, pdf, advertisement_id, user_id)
	VALUES(true, 'At cum porro feugiat consequat. An cum putant urbanitas neglegentur. Eu choro oblique petentium sit, etiam equidem scriptorem his at. Vix exerci aliquam no, ne sit diam veniam neglegentur, probo quando ei per.', '2021-09-27 18:30:05.722', 'nemletezikvbhgfl', 'nem_letezo_pdf', 1, 10);
INSERT INTO applications(
	available, comment, date_of_application, key, pdf, advertisement_id, user_id)
	VALUES(true, null, '2021-09-27 18:30:05.722', 'nemletezikvbhgfl', 'nem_letezo_pdf', 1, 11);
INSERT INTO applications(
	available, comment, date_of_application, key, pdf, advertisement_id, user_id)
	VALUES(true, null, '2021-09-27 18:30:05.722', 'nemletezikvbhgfl', 'nem_letezo_pdf', 1, 12);
INSERT INTO applications(
	available, comment, date_of_application, key, pdf, advertisement_id, user_id)
	VALUES(true, 'At cum porro feugiat consequat. An cum putant urbanitas neglegentur. Eu choro oblique petentium sit, etiam equidem scriptorem his at. Vix exerci aliquam no, ne sit diam veniam neglegentur, probo quando ei per.', '2021-09-27 18:30:05.722', 'nemletezikvbhgfl', 'nem_letezo_pdf', 1, 13);
INSERT INTO applications(
	available, comment, date_of_application, key, pdf, advertisement_id, user_id)
	VALUES(true, null, '2021-09-27 18:30:05.722', 'nemletezikvbhgfl', 'nem_letezo_pdf', 1, 14);
INSERT INTO applications(
	available, comment, date_of_application, key, pdf, advertisement_id, user_id)
	VALUES(true, 'At cum porro feugiat consequat. An cum putant urbanitas neglegentur. Eu choro oblique petentium sit, etiam equidem scriptorem his at. Vix exerci aliquam no, ne sit diam veniam neglegentur, probo quando ei per.', '2021-09-27 18:30:05.722', 'nemletezikvbhgfl', 'nem_letezo_pdf', 2, 10);
INSERT INTO applications(
	available, comment, date_of_application, key, pdf, advertisement_id, user_id)
	VALUES(true, null, '2021-09-27 18:30:05.722', 'nemletezikvbhgfl', 'nem_letezo_pdf', 3, 10);
INSERT INTO applications(
	available, comment, date_of_application, key, pdf, advertisement_id, user_id)
	VALUES(true, null, '2021-09-27 18:30:05.722', 'nemletezikvbhgfl', 'nem_letezo_pdf', 2, 12);
INSERT INTO applications(
	available, comment, date_of_application, key, pdf, advertisement_id, user_id)
	VALUES(true, 'At cum porro feugiat consequat. An cum putant urbanitas neglegentur. Eu choro oblique petentium sit, etiam equidem scriptorem his at. Vix exerci aliquam no, ne sit diam veniam neglegentur, probo quando ei per.', '2021-09-27 18:30:05.722', 'nemletezikvbhgfl', 'nem_letezo_pdf', 2, 13);
INSERT INTO applications(
	available, comment, date_of_application, key, pdf, advertisement_id, user_id)
	VALUES(true, null, '2021-09-27 18:30:05.722', 'nemletezikvbhgfl', 'nem_letezo_pdf', 2, 14);
INSERT INTO applications(
	available, comment, date_of_application, key, pdf, advertisement_id, user_id)
	VALUES(true, 'At cum porro feugiat consequat. An cum putant urbanitas neglegentur. Eu choro oblique petentium sit, etiam equidem scriptorem his at. Vix exerci aliquam no, ne sit diam veniam neglegentur, probo quando ei per.', '2021-09-27 18:30:05.722', 'nemletezikvbhgfl', 'nem_letezo_pdf', 3, 11);
INSERT INTO applications(
	available, comment, date_of_application, key, pdf, advertisement_id, user_id)
	VALUES(true, null, '2021-09-27 18:30:05.722', 'nemletezikvbhgfl', 'nem_letezo_pdf', 3, 12);
INSERT INTO applications(
	available, comment, date_of_application, key, pdf, advertisement_id, user_id)
	VALUES(true, null, '2021-09-27 18:30:05.722', 'nemletezikvbhgfl', 'nem_letezo_pdf', 3, 13);
INSERT INTO applications(
	available, comment, date_of_application, key, pdf, advertisement_id, user_id)
	VALUES(true, null, '2021-09-27 18:30:05.722', 'nemletezikvbhgfl', 'nem_letezo_pdf', 6, 14);
INSERT INTO applications(
	available, comment, date_of_application, key, pdf, advertisement_id, user_id)
	VALUES(true, null, '2021-09-27 18:30:05.722', 'nemletezikvbhgfl', 'nem_letezo_pdf', 6, 11);
INSERT INTO applications(
	available, comment, date_of_application, key, pdf, advertisement_id, user_id)
	VALUES(true, null, '2021-09-27 18:30:05.722', 'nemletezikvbhgfl', 'nem_letezo_pdf', 5, 12);
	
	