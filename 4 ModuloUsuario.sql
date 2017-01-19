USE aserradero;

DROP VIEW IF EXISTS VISTA_USUARIO;
CREATE VIEW VISTA_USUARIO AS
SELECT 
	nombre_usuario,
    contrasenia,
    EMPLEADO.id_empleado AS id_empleado,
    id_persona,
    (select concat (nombre,' ',apellido_paterno,' ',apellido_materno) FROM PERSONA WHERE EMPLEADO.id_persona = id_persona LIMIT 1) as empleado,
    id_jefe,
    rol,
    estatus
FROM USUARIO,EMPLEADO
WHERE USUARIO.id_empleado = EMPLEADO.id_empleado;

-- INSERT INTO USUARIO VALUES
-- ('COHA820724HOCRNN02COHA8207','Antonio',concat('^4%m@C*',sha2('&%c#L+=antonio$|2A',224),'!T>A0')),
-- ('VALLADARESELVIA123COCR1999','Elvia',concat('^4%m@C*',sha2('&%c#L+=elvia$|2A',224),'!T>A0')),
-- ('COCR19990708HOCRRCCOHA8207','richard',concat('^4%m@C*',sha2('&%c#L+=richard$|2A',224),'!T>A0'));-- Usuario richard contrasenia: richard