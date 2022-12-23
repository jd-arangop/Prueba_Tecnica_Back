delete from CLIENTE_ENTITY;
delete from DETALLE_ENTITY;
delete from FACTURA_ENTITY;
delete from PRODUCTO_ENTITY;

insert into CLIENTE_ENTITY (id_Cliente, nombre, apellido, direccion, fecha_Nacimiento, telefono, email) values (1, 'Juan', 'Arango', 'Bogota','2001-11-24','3222218080','j.aran2411@gmail.com');
insert into CLIENTE_ENTITY (id_Cliente, nombre, apellido, direccion, fecha_Nacimiento, telefono, email) values (2, 'Ximena', 'Hernandez', 'Fusagasuga', '1998-11-23','3123798651','ximeli23@gmail.com');
insert into CLIENTE_ENTITY (id_Cliente, nombre, apellido, direccion, fecha_Nacimiento, telefono, email) values (3, 'Ofelia', 'Pulido', 'Fusagasuga', '1973-10-05','3124207571','ofelarap@gmail.com');
insert into CLIENTE_ENTITY (id_Cliente, nombre, apellido, direccion, fecha_Nacimiento, telefono, email) values (4, 'David', 'Arango', 'Leticia', '1998-11-23','3123798651','jd.arangop@gmail.com');

insert into PRODUCTO_ENTITY (id_producto, nombre, precio, stock) values (1,'PlayStation4',1990000,20);
insert into PRODUCTO_ENTITY (id_producto, nombre, precio, stock) values (2,'Xbox One X',2190000, 35);
insert into PRODUCTO_ENTITY (id_producto, nombre, precio, stock) values (3,'Nintendo Switch', 1890000, 50);
insert into PRODUCTO_ENTITY (id_producto, nombre, precio, stock) values (4,'Steamdeck', 2590000, 3);
