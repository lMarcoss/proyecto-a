use aserradero;

INSERT INTO PERSONA (id_persona,nombre,apellido_paterno,apellido_materno,nombre_localidad,nombre_municipio,estado,sexo,fecha_nacimiento,telefono) VALUES 
-- ('COHA820724HOCRNN02', 'Antonio', 'Cortés', 'Hernández', 'Los Pocitos', 'Miahuatlan de porfirio diaz', 'Oaxaca', 'H', '1982-07-24', ''),
('COCR19990708HOCRRC', 'Ricardo', 'Cortes', 'Cruz', 'Xitla', 'Santa Cruz Xitla', 'Oaxaca', 'H', '1999-07-08', '9876543219'),
('VALLADARESELVIA123', 'Elvia', 'Valladares', 'Hernández', 'Xitla', 'Santa Cruz Xitla', 'Oaxaca', 'M', '1999-07-08', '9876543219');


INSERT INTO EMPLEADO (id_empleado,id_persona,id_jefe,rol,estatus) VALUES
-- ('COHA820724HOCRNN02','COHA820724HOCRNN02','COHA820724HOCRNN02','Administrador','Activo'),
('COCR19990708HOCRRC', 'COCR19990708HOCRRC', 'COHA820724HOCRNN02COHA8207', 'Empleado', 'Activo'),
('VALLADARESELVIA123', 'VALLADARESELVIA123', 'COHA820724HOCRNN02COHA8207', 'Empleado', 'Activo');

INSERT INTO USUARIO (id_empleado, nombre_usuario, contrasenia) VALUES
-- ('COHA820724HOCRNN02COHA8207','Antonio',concat(sha2('^4%m@C*&%c#L+=antonio$|2A!T>A0',224))),
('VALLADARESELVIA123COHA8207','Elvia',concat(sha2('^4%m@C*&%c#L+=elvia$|2A!T>A0',224))),
('COCR19990708HOCRRCCOHA8207','richard',concat(sha2('^4%m@C*&%c#L+=richard$|2A!T>A0',224)));-- Usuario richard contrasenia: richard

