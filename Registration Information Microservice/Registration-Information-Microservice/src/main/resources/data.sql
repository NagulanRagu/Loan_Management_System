insert into guarantor_address ("house number", street, city, state, pincode) 
	values ('63B-72B', 'Chinnarasingam Street', 'Vadasery', 'TamilNadu', '629001');
insert into guarantor_address ("house number", street, city, state, pincode) 
	values ('63B-72B', 'Chinnarasingam Street', 'Vadasery', 'TamilNadu', '629001');
insert into guarantor_address ("house number", street, city, state, pincode) 
	values ('63B-72B', 'Chinnarasingam Street', 'Vadasery', 'TamilNadu', '629001');
insert into guarantor_info ("guarantor name", "guarantor email id", "guarantor phone number", guarantor_address_id) 
	values ('Ravikumar K', 'ravikumaravel00@gmail.com', '8012646056', 1);
insert into guarantor_info ("guarantor name", "guarantor email id", "guarantor phone number", guarantor_address_id) 
	values ('Ravikumar K', 'ravikumaravel00@gmail.com', '8012646056', 2);
insert into guarantor_info ("guarantor name", "guarantor email id", "guarantor phone number", guarantor_address_id) 
	values ('Ravikumar K', 'ravikumaravel00@gmail.com', '8012646056', 3);
insert into loan_registered (asked_loan_amount, borrower_name, emi_amount, issued_date, loan_type, payment_period, provided_loan_amount, status, guarantor_info_id)
	values ('4,00,000', 'Nagulan R U', '9,727', '2023-03-30', 'Personal', 60, '4,00,000', 'Accepted', 1);
insert into loan_registered (asked_loan_amount, borrower_name,  loan_type, payment_period, status, guarantor_info_id)
	values ('40,00,000', 'Nagulan R U', 'House', 240, 'Rejected', 2);
insert into loan_registered (asked_loan_amount, borrower_name, loan_type, payment_period, status, guarantor_info_id)
	values ('10,00,000', 'Nagulan R U', 'Vechile', 120, 'Pending', 3);