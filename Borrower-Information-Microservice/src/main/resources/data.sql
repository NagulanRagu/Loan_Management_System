insert into borrower_address (house_number, street, city, state, pincode) 
	values ('63B-72B', 'Chinnarasingam Street', 'Vadasery', 'TamilNadu', '629001');
insert into borrower_details (username, password) values ('Admin', '1234');
insert into borrower_details (firstname, lastname, username, password, email_id, phone_number, aadhaar_card, pan_card, borrower_address_id) 
	values ('Nagulan', 'R U', 'Nagulan R U', '1234', 'runagulan88@gmail.com', '8870323658', '6101 8953 1282', 'AIUPU9900B', 1);
insert into borrower_role (role, borrower_id) values ('ROLE_ADMIN', 1);
insert into borrower_role (role, borrower_id) values ('ROLE_USER', 1);
insert into borrower_role (role, borrower_id) values ('ROLE_USER', 2);