insert into email_template(
	id, 
	template_subdir, 
	category, 
	css_subpath, 
	customer_id, 
	customer_template_id, 
	description, 
	friendly_name, 
	html_filename, 
	images_subpath, 
	txt_filename) 
values (
	1, --id
	'slate/Receipt', --base_subdir
	'receipts', --category
	null, --css_path
	'1', --customer_id
	'factura-1', --customer_template_id
	'esta es una plantilla de prueba', --description
	'Factura de pedido en linea', --friendly_name
	'receipt',--html_filename: relativo a la base de plantillas y sin extensión
	null,--images_subpath: relativo a base_subdir o es el mismo de la plantilla si es nulo
	'txt_filename');--txt_filename
	
insert into email_template(
	id, 
	template_subdir, 
	category, 
	css_subpath, 
	customer_id, 
	customer_template_id, 
	description, 
	friendly_name, 
	html_filename, 
	images_subpath, 
	txt_filename) 
values (
	2,
	'slate/Simple Announcement/', --base_subdir
	'receipts', 
	null, 
	'1', 
	'factura-2', 
	'Esta es otra plantilla de prueba', 
	'Factura de pedido en linea #2', 
	'simple-announcement', 
	null,--images_subpath: relativo a base_subdir o es el mismo de la plantilla si es nulo
	'txt_filename');
	
	
/* Plantillas para un segundo cliente (customerId=2)*/	
insert into email_template(
	id, 
	template_subdir, 
	category, 
	css_subpath, 
	customer_id, 
	customer_template_id, 
	description, 
	friendly_name, 
	html_filename, 
	images_subpath, 
	txt_filename) 
values (
	3, --id
	'slate/Receipt', --base_subdir
	'receipts', --category
	null, --css_path
	'8', --customer_id
	'factura-1', --customer_template_id
	'esta es una plantilla de prueba', --description
	'Factura de pedido en linea', --friendly_name
	'receipt',--html_filename: relativo a la base de plantillas y sin extensión
	null,--images_subpath: relativo a base_subdir o es el mismo de la plantilla si es nulo
	'txt_filename');--txt_filename
	
insert into email_template(
	id, 
	template_subdir, 
	category, 
	css_subpath, 
	customer_id, 
	customer_template_id, 
	description, 
	friendly_name, 
	html_filename, 
	images_subpath, 
	txt_filename) 
values (
	4,
	'slate/Simple Announcement/', --base_subdir
	'receipts', 
	null, 
	'8', 
	'factura-2', 
	'Esta es otra plantilla de prueba', 
	'Factura de pedido en linea #2', 
	'simple-announcement', 
	null,--images_subpath: relativo a base_subdir o es el mismo de la plantilla si es nulo
	'txt_filename');
	
insert into user_info(
	id,
	username,
	first_name,
	last_name,
	password,
	customer_id,
	sender_token,
	provider_token,
	provider_subaccount_id
) values(
	1000,
	'demo',
	'Demo',
	'Aldeamo',
	'$2a$10$iK2EZsLc.U/Qx4o.zlzzzuXkfc73Lw4VC1/Qf6t1GOENS189KvE0O',
	8,
	'd818dc203eeb82b7dc814ab050dcb63486b4acac',
	'd818dc203eeb82b7dc814ab050dcb63486b4acac',
	'2'
);