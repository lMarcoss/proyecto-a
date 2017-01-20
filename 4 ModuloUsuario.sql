USE aserradero;

DROP VIEW IF EXISTS VISTA_USUARIO;
CREATE VIEW VISTA_USUARIO AS
SELECT 
	nombre_usuario,
    contrasenia,
    E.id_empleado AS id_empleado,
    P.id_persona AS id_persona,
    CONCAT(nombre,' ', apellido_paterno, ' ',apellido_materno) as empleado,
    id_jefe,
    rol,
    estatus
FROM USUARIO AS U,EMPLEADO AS E,PERSONA AS P
WHERE U.id_empleado = E.id_empleado AND E.id_persona = P.id_persona;
