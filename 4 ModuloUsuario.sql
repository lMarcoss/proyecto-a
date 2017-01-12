USE aserradero;

DROP VIEW IF EXISTS VISTA_USUARIO;
CREATE VIEW VISTA_USUARIO AS
SELECT 
	nombre_usuario,
    contrasenia,
    metodo,
    email,
    EMPLEADO.id_empleado AS id_empleado,
    id_persona,
    (select concat (nombre,' ',apellido_paterno,' ',apellido_materno) FROM PERSONA WHERE EMPLEADO.id_persona = id_persona LIMIT 1) as empleado,
    id_jefe,
    rol,
    estatus
FROM USUARIO,EMPLEADO
WHERE USUARIO.id_empleado = EMPLEADO.id_empleado;
select *from EMPLEADO;
INSERT INTO USUARIO VALUES
('MASL19931106HOCRNNMASL1993','admin',sha1('admin'),'sha1','hola'),
('COCR19990708HOCRRCMASL1993','richard',sha1('richard'),'sha1','hola'); -- Usuario richard contrasenia: richard