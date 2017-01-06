USE aserradero;

-- Disparador para crear id_jefe: para administrador
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
		id_administrador, 
		(SELECT concat (nombre,' ',apellido_paterno,' ',apellido_materno) FROM PERSONA WHERE id_persona = SUBSTRING(id_administrador,1,18))as nombre,
        cuenta_inicial
FROM ADMINISTRADOR;

-- lista de empleados
DROP VIEW IF EXISTS PERSONAL_EMPLEADO;
CREATE VIEW PERSONAL_EMPLEADO AS
SELECT id_empleado,
		id_persona,
        (select concat (nombre,' ',apellido_paterno,' ',apellido_materno) as nombre FROM PERSONA WHERE PERSONA.id_persona = EMPLEADO.id_persona) as empleado,
        id_jefe,
        (select concat (nombre,' ',apellido_paterno,' ',apellido_materno) as nombre FROM PERSONA WHERE id_persona = SUBSTRING(id_jefe,1,18)) as jefe, 
        rol,estatus 
FROM EMPLEADO;

-- Lista de pago a empleados
DROP VIEW IF EXISTS VISTA_PAGO_EMPLEADO;
CREATE VIEW VISTA_PAGO_EMPLEADO AS 
SELECT id_pago_empleado,
		fecha,
        EMPLEADO.id_empleado AS id_empleado,
        (select concat (nombre,' ',apellido_paterno,' ',apellido_materno) as nombre FROM PERSONA WHERE id_persona = EMPLEADO.id_persona)as empleado,
        EMPLEADO.id_jefe AS id_jefe,
        monto,
        observacion 
FROM PAGO_EMPLEADO,EMPLEADO WHERE PAGO_EMPLEADO.id_empleado = EMPLEADO.id_empleado;

INSERT INTO EMPLEADO VALUES('MASL19931106HOCRNN','MASL19931106HOCRNN','MASL19931106HOCRNN','Administrador','Activo');
