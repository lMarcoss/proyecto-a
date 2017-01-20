USE aserradero;

-- Disparador para insertar un usuario inicial
DROP TRIGGER IF EXISTS USUARIO_INICIAL;
DELIMITER //
CREATE TRIGGER USUARIO_INICIAL BEFORE INSERT ON USUARIO
FOR EACH ROW
BEGIN
	DECLARE _id_empleado  VARCHAR(26);
	IF(NEW.id_empleado = 'registro_inicial')THEN
		SELECT id_empleado INTO _id_empleado FROM EMPLEADO WHERE id_persona = 'MASL931106HOCRNN09' AND rol = 'Administrador' LIMIT 1;
        SET NEW.id_empleado = _id_empleado;
    END IF;
END;//
DELIMITER ;

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
