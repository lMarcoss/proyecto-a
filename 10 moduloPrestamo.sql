use aserradero;
-- Módulo Prestamo - Registros-- Módulo Prestamo - Registros-- Módulo Prestamo - Registros-- Módulo Prestamo - Registros-- Módulo Prestamo - Registros
-- Módulo Prestamo - Registros-- Módulo Prestamo - Registros-- Módulo Prestamo - Registros-- Módulo Prestamo - Registros-- Módulo Prestamo - Registros


-- funcion para generar el id_del prestador al insertar un prestamo
DROP FUNCTION IF EXISTS GENERAR_ID;
DELIMITER //
CREATE FUNCTION GENERAR_ID (_id VARCHAR(26), _id_empleado VARCHAR(26))
RETURNS VARCHAR(26)
BEGIN
	DECLARE _id_administrador	VARCHAR(26);
    DECLARE _id_nuevo			VARCHAR(26);
        
    -- consultamos el id del administrador
    SELECT id_jefe INTO _id_administrador FROM EMPLEADO WHERE id_empleado = _id_empleado LIMIT 1;
    
    -- contatenamos el _id con las primeras 8 caracteres del id del administrador
    SET _id_nuevo = CONCAT(_id, SUBSTRING(_id_administrador, 1, 8));
    
	RETURN _id_nuevo;
END;//
DELIMITER ;

-- disparador para generar el id_del prestador antes de insertar un prestamo
DROP TRIGGER IF EXISTS CREAR_ID;
DELIMITER //
CREATE TRIGGER CREAR_ID BEFORE INSERT ON PRESTAMO
FOR EACH ROW
BEGIN
	SET NEW.id_prestador = (SELECT GENERAR_ID(NEW.id_prestador, NEW.id_empleado));
END;//
DELIMITER ;


-- funcion para consultar el monto por pagar de un prestamo
DROP FUNCTION IF EXISTS MONTO_A_PAGAR;
DELIMITER //
CREATE FUNCTION MONTO_A_PAGAR (_id_prestamo INT)
RETURNS DECIMAL(15,2)
BEGIN
	DECLARE _monto_prestamo		 	decimal(15,2);
	DECLARE _monto_pagar		 	decimal(15,2);
    DECLARE _monto_pagado		 	decimal(15,2);
        
    -- consultamos el monto del prestamo
    SELECT monto_prestamo INTO _monto_prestamo FROM PRESTAMO WHERE id_prestamo = _id_prestamo;
    
	-- Consultamos monto por pagar del prestamo
    IF EXISTS (SELECT monto_pago FROM PAGO_PRESTAMO WHERE id_prestamo = _id_prestamo LIMIT 1) THEN 
		SELECT SUM(monto_pago) INTO _monto_pagado FROM PAGO_PRESTAMO WHERE id_prestamo = _id_prestamo GROUP BY id_prestamo;
        SET _monto_pagar = _monto_prestamo - _monto_pagado;
        RETURN _monto_pagar;
	ELSE 
		RETURN _monto_prestamo;
    END IF;
END;//
DELIMITER ;


DROP VIEW IF EXISTS VISTA_PRESTAMO ;
CREATE VIEW VISTA_PRESTAMO AS
SELECT
	id_prestamo,
    fecha,
    id_prestador,
    (SELECT CONCAT(nombre, " ", apellido_paterno, " ", apellido_materno) FROM PERSONA WHERE id_persona = SUBSTRING(PRESTAMO.id_prestador,1,18) LIMIT 1) AS prestador,
    id_empleado,
    (select concat (nombre,' ',apellido_paterno,' ',apellido_materno) FROM PERSONA WHERE id_persona = SUBSTRING(PRESTAMO.id_empleado,1,18) LIMIT 1)as empleado,
    (select id_jefe FROM EMPLEADO WHERE id_empleado = id_empleado LIMIT 1) as id_administrador,
    monto_prestamo,
    interes,
    ROUND((ROUND((SELECT MONTO_A_PAGAR(id_prestamo)),2) * (interes/100)),2) AS interes_mensual,
    ROUND((monto_prestamo - (SELECT MONTO_A_PAGAR(id_prestamo))),2) AS monto_pagado,
    ROUND((SELECT MONTO_A_PAGAR(id_prestamo)),2) AS monto_por_pagar
FROM PRESTAMO;


-- Submódulo pago préstamo-- Submódulo pago préstamo-- Submódulo pago préstamo-- Submódulo pago préstamo-- Submódulo pago préstamo-- Submódulo pago préstamo
-- Submódulo pago préstamo-- Submódulo pago préstamo-- Submódulo pago préstamo-- Submódulo pago préstamo-- Submódulo pago préstamo-- Submódulo pago préstamo

DROP VIEW IF EXISTS VISTA_PAGO_PRESTAMO;
CREATE VIEW VISTA_PAGO_PRESTAMO AS
SELECT 	
		PP.id_pago,
		PP.id_prestamo,
        PP.fecha,
        (SELECT id_jefe FROM EMPLEADO WHERE id_empleado = PP.id_empleado limit 1) AS id_administrador,
        PP.id_empleado,
        (select concat (nombre,' ',apellido_paterno,' ',apellido_materno) FROM PERSONA WHERE id_persona = SUBSTRING(PP.id_empleado,1,18))as empleado,
        P.id_prestador,
        (select concat (nombre,' ',apellido_paterno,' ',apellido_materno) FROM PERSONA WHERE id_persona = SUBSTRING(P.id_prestador,1,18))as prestador,
        P.monto_prestamo,
        PP.monto_pago,        
        ROUND((SELECT MONTO_A_PAGAR(PP.id_prestamo)),2) as monto_por_pagar
FROM PAGO_PRESTAMO AS PP,PRESTAMO AS P WHERE PP.id_prestamo = P.id_prestamo;

