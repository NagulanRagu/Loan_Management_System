insert into address ("house number", street, city, state, pincode) 
	values ('63B-72B', 'Chinnarasingam Street, Vadasery', 'Nagercoil', 'TamilNadu', '629001');
insert into personal_information ("first name", "last name", "date of birth", gender, "aadhaar card number", "pan card number") 
	values ('Nagulan', 'R U', '2001-03-28', 'Male', '6101 8953 1282', 'AIUPU9900B');
insert into borrower_details (username, password, enabled) values ('Admin', '1234', 'true');
insert into borrower_details (username, password, "email id", "phone number", borrower_address_id, personal_information_id, enabled) 
	values ('Nagulan R U', '1234', 'runagulan88@gmail.com', '8870323658', 1, 1, 'true');
insert into borrower_role (role, borrower_id) values ('ROLE_ADMIN', 1);
insert into borrower_role (role, borrower_id) values ('ROLE_USER', 1);
insert into borrower_role (role, borrower_id) values ('ROLE_USER', 2);