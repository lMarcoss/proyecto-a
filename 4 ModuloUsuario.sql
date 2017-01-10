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
INSERT INTO USUARIO VALUES('MASL19931106HOCRNNMASL1993','admin',sha1('admin'),'sha1','hola');
INSERT INTO USUARIO VALUES('COCR19931103HOCRRCMASL1993','ricardo',sha1('user'),'sha1','hola');
-- INSERT INTO USUARIO VALUES('PAXA20160913HOCSXNPAXA2016','hola',sha1('hola'),'sha1','hola');
-- INSERT INTO USUARIO VALUES('COXN20160915HOCRXXCOXN2016','admin1',sha1('admin1'),'sha1','hola');
-- SELECT * FROM EMPLEADO;
-- DELETE FROM USUARIO WHERE nombre_usuario = 'hola';
