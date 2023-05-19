insert into email_template (template_name, subject, body) 
	values ('confirmationToken', 'Confirmation Email', 
	'<div>
		<h1>Confirmation Mail</h1>\n
		<p>This is the mail for confirming the mail id. Please click the below link to confirm</p>
		<a href="${link}" target="_blank">Confirmation Link</a>
		<p>Thank you</p>
	 </div>');