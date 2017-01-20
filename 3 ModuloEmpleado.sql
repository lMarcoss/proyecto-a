USE aserradero;

-- Disparador para crear id_jefe: para administrador y para empleado
DROP TRIGGER IF EXISTS EMPLEADO;
DELIMITER //
CREATE TRIGGER EMPLEADO BEFORE INSERT ON EMPLEADO
FOR EACH ROW
BEGIN
	DECLARE id_administrador VARCHAR(26);
	IF(NEW.rol = 'Administrador')THEN
		SET id_administrador = CONCAT(NEW.id_empleado,SUBSTRING(NEW.id_empleado,1,8));
        -- Actualizamos el id_empleado y el id_jefe
        SET NEW.id_jefe = id_administrador;
        SET NEW.id_empleado = id_administrador;
	ELSE 
		SET NEW.id_empleado = CONCAT(NEW.id_empleado,SUBSTRING(NEW.id_jefe,1,8));
    END IF;
END;//
DELIMITER ;

-- Insertar administrador con cuenta inicial 0, en la tabla Administrador 
DROP TRIGGER IF EXISTS EMPLEADO_ADMIN;
DELIMITER //
CREATE TRIGGER EMPLEADO_ADMIN AFTER INSERT ON EMPLEADO
FOR EACH ROW
BEGIN
	IF(NEW.rol = 'Administrador')THEN
        INSERT INTO ADMINISTRADOR (id_administrador,cuenta_inicial) VALUES(NEW.id_jefe,0.00);
    END IF;
END;//
DELIMITER ;

-- lista de administradores
DROP VIEW IF EXISTS PERSONAL_ADMINISTRADOR;
CREATE VIEW PERSONAL_ADMINISTRADOR AS
SELECT 
	id_empleado AS id_administrador,
    CONCAT(nombre,' ', apellido_paterno, ' ',apellido_materno) AS nombre,
    (SELECT cuenta_inicial FROM ADMINISTRADOR WHERE id_administrador = id_empleado) AS cuenta_inicial
FROM EMPLEADO AS E,PERSONA AS P 
WHERE E.id_persona = P.id_persona AND rol = 'Administrador';

-- lista de empleados
DROP VIEW IF EXISTS PERSONAL_EMPLEADO;
CREATE VIEW PERSONAL_EMPLEADO AS
SELECT 
	E.id_empleado AS id_empleado,
    E.id_persona AS id_persona,
    CONCAT(nombre,' ', apellido_paterno, ' ',apellido_materno) as empleado,
    E.id_jefe AS id_jefe,
    rol,
    estatus
FROM EMPLEADO AS E,PERSONA AS P WHERE E.id_persona = P.id_persona;

-- Lista de pago a empleados
DROP VIEW IF EXISTS VISTA_PAGO_EMPLEADO;
CREATE VIEW VISTA_PAGO_EMPLEADO AS
SELECT
	id_pago_empleado,
    fecha,
    E.id_empleado AS id_empleado,
    CONCAT(nombre,' ', apellido_paterno, ' ',apellido_materno) as empleado,
    E.id_jefe AS id_jefe,
    monto,
    observacion
FROM PAGO_EMPLEADO AS PG, EMPLEADO AS E, PERSONA AS P
WHERE PG.id_empleado = E.id_empleado AND E.id_persona = P.id_persona;
