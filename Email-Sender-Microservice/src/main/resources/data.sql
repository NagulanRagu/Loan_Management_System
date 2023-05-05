insert into email_template (template_name, subject, body) 
	values ('confirmationToken', 'Confirmation Email', 
	'<div>\n
		<h1>Confirmation Mail</h1>\n
		<p>This is the mail for confirming the mail id. Please click the below link to confirm</p>\n
		<a href="${link}" target="_blank">Confirmation Link</a>\n
		<p>Thank you</p>\n
	 </div>');