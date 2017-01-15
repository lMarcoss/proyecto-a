USE aserradero;

-- Disparador para insertar costos de madera entrada
DROP TRIGGER IF EXISTS MODIFICAR_ENTRADA_M_ROLLO;
DELIMITER //
CREATE TRIGGER MODIFICAR_ENTRADA_M_ROLLO BEFORE INSERT ON ENTRADA_M_ROLLO
FOR EACH ROW
BEGIN
    DECLARE _costo_primario DECIMAL(15,2);
    DECLARE _costo_secundario DECIMAL(15,2);
    DECLARE _costo_terciario DECIMAL(15,2);
    
    -- Verificamos si existe los costos de clasificación madera entrada
    IF NOT EXISTS (SELECT costo FROM CLASIFICACION_M_ROLLO WHERE clasificacion = 'Primario' AND id_proveedor = NEW.id_proveedor) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Clasificación primaria no existe';
    END IF;
    IF NOT EXISTS (SELECT costo FROM CLASIFICACION_M_ROLLO WHERE clasificacion = 'Secundario' AND id_proveedor = NEW.id_proveedor) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Clasificación secundaria no existe';
    END IF;
    IF NOT EXISTS (SELECT costo FROM CLASIFICACION_M_ROLLO WHERE clasificacion = 'Terciario' AND id_proveedor = NEW.id_proveedor) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Clasificación terciaria no existe';
    END IF;
    
	-- consultamos los costos de cada tipo de volumen;
    SELECT costo INTO _costo_primario FROM CLASIFICACION_M_ROLLO WHERE clasificacion = 'Primario' AND id_proveedor = NEW.id_proveedor;
    SELECT costo INTO _costo_secundario FROM CLASIFICACION_M_ROLLO WHERE clasificacion = 'Secundario' AND id_proveedor = NEW.id_proveedor;
    SELECT costo INTO _costo_terciario FROM CLASIFICACION_M_ROLLO WHERE clasificacion = 'Terciario' AND id_proveedor = NEW.id_proveedor;
	
    -- actualizamos los costo de volumen madera en cada EntradaMadera
    SET NEW.costo_primario = _costo_primario;
    SET NEW.costo_secundario = _costo_secundario;
    SET NEW.costo_terciario = _costo_terciario;
    
END;//
DELIMITER ;


-- Actualizar datos cada que se modifica una entrada de madera
DROP TRIGGER IF EXISTS ACTUALIZAR_ENTRADA_M_ROLLO;
DELIMITER //
CREATE TRIGGER ACTUALIZAR_ENTRADA_M_ROLLO BEFORE UPDATE ON ENTRADA_M_ROLLO
FOR EACH ROW
BEGIN
    DECLARE _costo_primario DECIMAL(15,2);
    DECLARE _costo_secundario DECIMAL(15,2);
    DECLARE _costo_terciario DECIMAL(15,2);
    DECLARE _monto_total_new DECIMAL(15,2);			-- monto para actualizar pago compra
    DECLARE _monto_total_old DECIMAL(15,2);			-- monto para actualizar pago compra
    DECLARE _volumen_total_old DECIMAL(15,3);
    
    -- Verificamos si existe los costos de clasificación madera entrada
    -- : si existe consultamos costos nuevos
    IF NOT EXISTS (SELECT costo FROM CLASIFICACION_M_ROLLO WHERE clasificacion = 'Primario' AND id_proveedor = NEW.id_proveedor) THEN
        SET _costo_primario = OLD.costo_primario;
	ELSE -- EXISTE
		SELECT costo INTO _costo_primario FROM CLASIFICACION_M_ROLLO WHERE clasificacion = 'Primario' AND id_proveedor = NEW.id_proveedor;
    END IF;
    
    IF NOT EXISTS (SELECT costo FROM CLASIFICACION_M_ROLLO WHERE clasificacion = 'Secundario' AND id_proveedor = NEW.id_proveedor) THEN
		SET _costo_secundario = OLD.costo_secundario;
	ELSE -- EXISTE
		SELECT costo INTO _costo_secundario FROM CLASIFICACION_M_ROLLO WHERE clasificacion = 'Secundario' AND id_proveedor = NEW.id_proveedor;
    END IF;
    
    IF NOT EXISTS (SELECT costo FROM CLASIFICACION_M_ROLLO WHERE clasificacion = 'Terciario' AND id_proveedor = NEW.id_proveedor) THEN
		SET _costo_terciario = OLD.costo_terciario;
	ELSE -- EXISTE
		SELECT costo INTO _costo_terciario FROM CLASIFICACION_M_ROLLO WHERE clasificacion = 'Terciario' AND id_proveedor = NEW.id_proveedor;
    END IF;
    	
    -- actualizamos los costo de volumen madera en cada EntradaMadera
    SET NEW.costo_primario = _costo_primario;
    SET NEW.costo_secundario = _costo_secundario;
    SET NEW.costo_terciario = _costo_terciario;
END;//
DELIMITER ;

-- clasificación madera en rollo
DROP VIEW IF EXISTS V_CLASIFICACION_M_ROLLO;
CREATE VIEW V_CLASIFICACION_M_ROLLO AS 
SELECT 
	id_proveedor,
    (SELECT CONCAT(nombre, ' ', apellido_paterno, ' ', apellido_materno) FROM PERSONA WHERE id_persona = SUBSTRING(id_proveedor,1,18) LIMIT 1) AS proveedor,
    (SELECT id_jefe FROM PROVEEDOR WHERE id_proveedor = CLASIFICACION_M_ROLLO.id_proveedor LIMIT 1) AS id_jefe,
    clasificacion,
    costo
FROM CLASIFICACION_M_ROLLO;

-- entrada madera en rollo
DROP VIEW IF EXISTS VISTA_ENTRADA_M_ROLLO;
CREATE VIEW VISTA_ENTRADA_M_ROLLO AS
SELECT 
	id_entrada,
    fecha,
    id_proveedor,
    (select concat (nombre,' ',apellido_paterno,' ',apellido_materno) from PERSONA where id_persona = SUBSTRING(id_proveedor,1,18) LIMIT 1) as proveedor,
    id_chofer,
    (select concat (nombre,' ',apellido_paterno,' ',apellido_materno) from PERSONA where id_persona = SUBSTRING(id_chofer,1,18) LIMIT 1) as chofer,
    id_empleado,
    (select concat (nombre,' ',apellido_paterno,' ',apellido_materno) from PERSONA where id_persona = SUBSTRING(id_empleado,1,18) LIMIT 1) as empleado,
    (select id_jefe from EMPLEADO where EMPLEADO.id_empleado = EMR.id_empleado limit 1) as id_jefe ,
    num_pieza_primario,
    volumen_primario,
    costo_primario,
    num_pieza_secundario,
    volumen_secundario,
    costo_secundario,
    num_pieza_terciario,
    volumen_terciario,
    costo_terciario,
    (num_pieza_primario + num_pieza_secundario + num_pieza_terciario) AS num_pieza_total,
    ROUND((volumen_primario + volumen_secundario + volumen_terciario),3) as volumen_total,
    ROUND((volumen_primario * costo_primario + volumen_secundario * costo_secundario + volumen_terciario * costo_terciario),2) as costo_total,
    id_pago
FROM ENTRADA_M_ROLLO AS EMR
ORDER BY fecha;

-- Salida madera en rollo
DROP VIEW IF EXISTS VISTA_SALIDA_M_ROLLO;
CREATE VIEW VISTA_SALIDA_M_ROLLO AS
SELECT 
	id_salida,
    fecha,
    id_empleado,
    (select concat (nombre,' ',apellido_paterno,' ',apellido_materno) FROM PERSONA WHERE id_persona = SUBSTRING(id_empleado,1,18) LIMIT 1) as empleado,
	(select id_jefe from EMPLEADO where id_empleado = SMR.id_empleado LIMIT 1) as id_jefe,
    num_pieza_primario,
    volumen_primario,
    num_pieza_secundario,
    volumen_secundario,
    num_pieza_terciario,
    volumen_terciario,
    (num_pieza_primario + num_pieza_secundario + num_pieza_terciario) AS num_pieza_total,
    ROUND((volumen_primario + volumen_secundario + volumen_terciario),3) AS volumen_total
FROM SALIDA_M_ROLLO as SMR;
    


-- Funciones para facilitar la vista inventario de madera en rollo

-- funcion para consultar número de piezas de clasificación primario de madera en rollo que ha salido
DROP FUNCTION IF EXISTS C_P_PRIMARIO;
DELIMITER //
CREATE FUNCTION C_P_PRIMARIO (_id_jefe VARCHAR(30))
RETURNS INT
BEGIN
	DECLARE _num_pieza INT;
    
	-- consultamos si han salido madera rollo
    IF EXISTS (SELECT num_pieza_primario FROM VISTA_SALIDA_M_ROLLO WHERE id_jefe = _id_jefe LIMIT 1) THEN 
		SELECT SUM(num_pieza_primario) INTO _num_pieza FROM VISTA_SALIDA_M_ROLLO WHERE id_jefe = _id_jefe GROUP BY id_jefe;
        RETURN _num_pieza;
	ELSE 
		RETURN 0;
    END IF;
END;//
DELIMITER ;

-- funcion para consultar número de piezas de clasificación secundario de madera en rollo que ha salido
DROP FUNCTION IF EXISTS C_P_SECUNDARIO;
DELIMITER //
CREATE FUNCTION C_P_SECUNDARIO (_id_jefe VARCHAR(30))
RETURNS INT
BEGIN
	DECLARE _num_pieza INT;
    
	-- consultamos si han salido madera rollo
    IF EXISTS (SELECT num_pieza_secundario FROM VISTA_SALIDA_M_ROLLO WHERE id_jefe = _id_jefe LIMIT 1) THEN 
		SELECT SUM(num_pieza_secundario) INTO _num_pieza FROM VISTA_SALIDA_M_ROLLO WHERE id_jefe = _id_jefe GROUP BY id_jefe;
        RETURN _num_pieza;
	ELSE 
		RETURN 0;
    END IF;
END;//
DELIMITER ;


-- funcion para consultar número de piezas de clasificación terciaria de madera en rollo que ha salido
DROP FUNCTION IF EXISTS C_P_TERCIARIO;
DELIMITER //
CREATE FUNCTION C_P_TERCIARIO (_id_jefe VARCHAR(30))
RETURNS INT
BEGIN
	DECLARE _num_pieza INT;
    
	-- consultamos si han salido madera rollo
    IF EXISTS (SELECT num_pieza_terciario FROM VISTA_SALIDA_M_ROLLO WHERE id_jefe = _id_jefe LIMIT 1) THEN 
		SELECT SUM(num_pieza_terciario) INTO _num_pieza FROM VISTA_SALIDA_M_ROLLO WHERE id_jefe = _id_jefe GROUP BY id_jefe;
        RETURN _num_pieza;
	ELSE 
		RETURN 0;
    END IF;
END;//
DELIMITER ;



-- funcion para consultar volumen primario de madera en rollo que ha salido
DROP FUNCTION IF EXISTS C_V_PRIMARIO;
DELIMITER //
CREATE FUNCTION C_V_PRIMARIO (_id_jefe VARCHAR(30))
RETURNS DECIMAL(15,3)
BEGIN
	DECLARE _volumen DECIMAL(15,3);
    
	-- consultamos si han salido madera rollo
    IF EXISTS (SELECT volumen_primario FROM VISTA_SALIDA_M_ROLLO WHERE id_jefe = _id_jefe LIMIT 1) THEN 
		SELECT ROUND(SUM(volumen_primario),3) INTO _volumen FROM VISTA_SALIDA_M_ROLLO WHERE id_jefe = _id_jefe GROUP BY id_jefe;
        RETURN _volumen;
	ELSE 
		RETURN 0;
    END IF;
END;//
DELIMITER ;


-- funcion para consultar volumen secundario de madera en rollo que ha salido
DROP FUNCTION IF EXISTS C_V_SECUNDARIO;
DELIMITER //
CREATE FUNCTION C_V_SECUNDARIO (_id_jefe VARCHAR(30))
RETURNS DECIMAL(15,3)
BEGIN
	DECLARE _volumen DECIMAL(15,3);
    
	-- consultamos si han salido madera rollo
    IF EXISTS (SELECT volumen_secundario FROM VISTA_SALIDA_M_ROLLO WHERE id_jefe = _id_jefe LIMIT 1) THEN 
		SELECT ROUND(SUM(volumen_secundario),3) INTO _volumen FROM VISTA_SALIDA_M_ROLLO WHERE id_jefe = _id_jefe GROUP BY id_jefe;
        RETURN _volumen;
	ELSE 
		RETURN 0;
    END IF;
END;//
DELIMITER ;



-- funcion para consultar volumen terciario de madera en rollo que ha salido
DROP FUNCTION IF EXISTS C_V_TERCIARIO;
DELIMITER //
CREATE FUNCTION C_V_TERCIARIO (_id_jefe VARCHAR(30))
RETURNS DECIMAL(15,3)
BEGIN
	DECLARE _volumen DECIMAL(15,3);
    
	-- consultamos si han salido madera rollo
    IF EXISTS (SELECT volumen_terciario FROM VISTA_SALIDA_M_ROLLO WHERE id_jefe = _id_jefe LIMIT 1) THEN 
		SELECT ROUND(SUM(volumen_terciario),3) INTO _volumen FROM VISTA_SALIDA_M_ROLLO WHERE id_jefe = _id_jefe GROUP BY id_jefe;
        RETURN _volumen;
	ELSE 
		RETURN 0;
    END IF;
END;//
DELIMITER ;



-- VISTAS PARA FACILITAR LA CONSULTA DE INVENTARIO MADERA EN ROLLO

-- Consulta el total de madera en rollo entrada y le resta la salida
DROP VIEW IF EXISTS TOTAL_ENTRADA_SALIDA_M_ROLLO;
CREATE VIEW TOTAL_ENTRADA_SALIDA_M_ROLLO AS
SELECT
	id_jefe,
    (SUM(num_pieza_primario) 	- 	C_P_PRIMARIO(id_jefe)) AS num_pieza_primario,
    (SUM(volumen_primario) 		- 	C_V_PRIMARIO(id_jefe)) AS volumen_primario,
    (SUM(num_pieza_secundario) 	- 	C_P_SECUNDARIO(id_jefe)) AS num_pieza_secundario,
    (SUM(volumen_secundario) 	- 	C_V_SECUNDARIO(id_jefe)) AS volumen_secundario,
    (SUM(num_pieza_terciario) 	- 	C_P_TERCIARIO(id_jefe)) AS num_pieza_terciario,
    (SUM(volumen_terciario) 	- 	C_V_TERCIARIO(id_jefe)) AS volumen_terciario
FROM VISTA_ENTRADA_M_ROLLO
GROUP BY id_jefe;

-- INVENTARIO DE MADERA EN ROLLO con costos primario y secundario
DROP VIEW IF EXISTS TOTAL_ENT_SAL_M_ROLLO_COSTO;
CREATE VIEW TOTAL_ENT_SAL_M_ROLLO_COSTO AS
SELECT 
	id_jefe,
	num_pieza_primario,
    volumen_primario,
    ROUND((SELECT AVG(costo_primario) FROM ENTRADA_M_ROLLO WHERE id_jefe = TESMR.id_jefe LIMIT 1),2) AS costo_primario,
    num_pieza_secundario,
    volumen_secundario,
    ROUND((SELECT AVG(costo_secundario) FROM ENTRADA_M_ROLLO WHERE id_jefe = TESMR.id_jefe LIMIT 1),2) AS costo_secundario,
    num_pieza_terciario,
    volumen_terciario,
    ROUND((SELECT AVG(costo_terciario) FROM ENTRADA_M_ROLLO WHERE id_jefe = TESMR.id_jefe LIMIT 1),2) AS costo_terciario,
    (num_pieza_primario + num_pieza_secundario + num_pieza_terciario) AS num_pieza_total,
    (volumen_primario + volumen_secundario + volumen_terciario)		  AS volumen_total
FROM TOTAL_ENTRADA_SALIDA_M_ROLLO AS TESMR;

-- Inventario de madera en rollo con costos totales
DROP VIEW IF EXISTS INVENTARIO_M_ROLLO;
CREATE VIEW INVENTARIO_M_ROLLO AS 
SELECT
	id_jefe,
    num_pieza_primario,
    volumen_primario,
    costo_primario,
    ROUND((volumen_primario * costo_primario),2) AS costo_total_primario,
    num_pieza_secundario,
    volumen_secundario,
    costo_secundario,
    ROUND((volumen_secundario* costo_secundario),2) AS costo_total_secundario,
    num_pieza_terciario,
    volumen_terciario,
    costo_terciario,
    ROUND((volumen_terciario * costo_terciario),2) AS costo_total_terciario,
    num_pieza_total,
    volumen_total,
    ROUND((volumen_primario * costo_primario + volumen_secundario * costo_secundario + volumen_terciario * costo_terciario),2) AS costo_total
FROM TOTAL_ENT_SAL_M_ROLLO_COSTO;

-- Submódulo pago compra-- Submódulo pago compra-- Submódulo pago compra-- Submódulo pago compra-- Submódulo pago compra
-- Submódulo pago compra-- Submódulo pago compra-- Submódulo pago compra-- Submódulo pago compra-- Submódulo pago compra


-- actualizar los id_pago en la tabla ENTRADA_M_ROLLO: 
-- actualizamos los id_pago = 0 con el valor nuevo
DROP TRIGGER IF EXISTS ACTUALIZAR_ENTRADA_MADERA;
DELIMITER //
CREATE TRIGGER ACTUALIZAR_ENTRADA_MADERA AFTER INSERT ON PAGO_COMPRA
FOR EACH ROW
BEGIN
	-- actualizamos todos los registros que tienen como id_pago = 0
	UPDATE ENTRADA_M_ROLLO SET id_pago = NEW.id_pago WHERE id_pago = 0 AND id_proveedor = new.id_proveedor;
END;//
DELIMITER ;

-- Muestra todos los pagos que se han hecho
DROP VIEW IF EXISTS VISTA_PAGO_COMPRA;
CREATE VIEW VISTA_PAGO_COMPRA AS
SELECT 
	id_pago,
    fecha,
    id_proveedor,
    (select concat (nombre,' ',apellido_paterno,' ',apellido_materno) FROM PERSONA WHERE PERSONA.id_persona = SUBSTRING(PAGO_COMPRA.id_proveedor,1,18) LIMIT 1) as proveedor,
    (SELECT id_jefe FROM PROVEEDOR WHERE id_proveedor = PAGO_COMPRA.id_proveedor LIMIT 1) AS id_administrador,
    monto_pago,
    monto_por_pagar,
    monto_por_cobrar
FROM PAGO_COMPRA;

-- Procedimiento para obtener cuenta por cobrar al proveedor
DROP FUNCTION IF EXISTS OBTENER_CUENTA_POR_COBRAR;
DELIMITER //
CREATE FUNCTION OBTENER_CUENTA_POR_COBRAR (_id_proveedor CHAR(30))
RETURNS DECIMAL(20,2)
BEGIN
	DECLARE _cuenta decimal(20,2); -- Cuenta por cobrar al proveedor
    
    -- Si existe cuenta por cobrar al proveedor
    IF EXISTS (SELECT id_persona FROM C_POR_COBRAR_PROVEEDOR WHERE id_persona = _id_proveedor LIMIT 1) THEN
        SELECT SUM(monto) INTO _cuenta FROM C_POR_COBRAR_PROVEEDOR WHERE id_persona = _id_proveedor GROUP BY id_persona;
	ELSE 
		SET _cuenta = 0;
    END IF;

    return _cuenta;
END;//
DELIMITER ;

-- Procedimiento cuenta por pagar al proveedor
DROP FUNCTION IF EXISTS OBTENER_CUENTA_POR_PAGAR;
DELIMITER //
CREATE FUNCTION OBTENER_CUENTA_POR_PAGAR (_id_proveedor CHAR(30))
RETURNS DECIMAL(20,2)
BEGIN
	DECLARE _cuenta decimal(20,2); -- Cuenta por cobrar al proveedor
    
    -- Si existe cuenta por pagar al proveedor
    IF EXISTS (SELECT id_persona FROM C_POR_PAGAR_PROVEEDOR WHERE id_persona = _id_proveedor LIMIT 1) THEN
        SELECT SUM(monto) INTO _cuenta FROM C_POR_PAGAR_PROVEEDOR WHERE id_persona = _id_proveedor GROUP BY id_persona;
	ELSE 
		SET _cuenta = 0;
    END IF;
    return _cuenta;
END;//
DELIMITER ;

DROP VIEW IF EXISTS CUENTA_PAGO;
CREATE VIEW CUENTA_PAGO AS
SELECT 
	id_jefe,
	id_proveedor,
    (SELECT CONCAT(nombre, ' ', apellido_paterno, ' ', apellido_materno) FROM PERSONA WHERE id_persona = SUBSTRING(id_proveedor,1,18) LIMIT 1) AS proveedor,
    SUM(costo_total) AS monto_total_madera,
    (OBTENER_CUENTA_POR_PAGAR(id_proveedor)) AS cuenta_por_pagar,
    (OBTENER_CUENTA_POR_COBRAR(id_proveedor)) AS cuenta_por_cobrar
FROM VISTA_ENTRADA_M_ROLLO 
WHERE id_pago = 0
GROUP BY id_jefe,id_proveedor;
