USE aserradero;

DROP VIEW IF EXISTS ENTRADA_MADERA_ROLLO_HOY;
CREATE VIEW ENTRADA_MADERA_ROLLO_HOY AS
SELECT 
	id_jefe,
	SUM(num_pieza_primario) AS num_pieza_primario,
	SUM(volumen_primario) AS volumen_primario,
	SUM(num_pieza_secundario) AS num_pieza_secundario,
	SUM(volumen_secundario) AS volumen_secundario,
	SUM(num_pieza_terciario) AS num_pieza_terciario,
	SUM(volumen_terciario) AS volumen_terciario
FROM ENTRADA_M_ROLLO AS E,PROVEEDOR AS P 
WHERE E.id_proveedor = P.id_proveedor
	AND fecha = CURDATE()
GROUP BY id_jefe;

DROP VIEW IF EXISTS SALIDA_MADERA_ROLLO_HOY;
CREATE VIEW SALIDA_MADERA_ROLLO_HOY AS
SELECT 
	id_jefe,
	SUM(num_pieza_primario) AS num_pieza_primario,
	SUM(volumen_primario) AS volumen_primario,
	SUM(num_pieza_secundario) AS num_pieza_secundario,
	SUM(volumen_secundario) AS volumen_secundario,
	SUM(num_pieza_terciario) AS num_pieza_terciario,
	SUM(volumen_terciario) AS volumen_terciario
FROM SALIDA_M_ROLLO AS S,EMPLEADO AS E 
WHERE S.id_empleado = E.id_empleado
	AND fecha = CURDATE()
GROUP BY id_jefe;

DROP VIEW IF EXISTS ENTRADA_MADERA_ASERRADA_HOY;
CREATE VIEW ENTRADA_MADERA_ASERRADA_HOY AS
SELECT 
	id_administrador,
    id_madera,
    SUM(num_piezas) AS num_piezas
FROM ENTRADA_MADERA_ASERRADA
WHERE fecha = CURDATE()
GROUP BY id_administrador,id_madera;

DROP VIEW IF EXISTS MONTO_VENTAS_HOY;
CREATE VIEW MONTO_VENTAS_HOY AS 
(SELECT id_administrador,VENTA.id_venta,SUM(monto) AS monto FROM VENTA,VENTA_PAQUETE WHERE VENTA.id_venta = VENTA_PAQUETE.id_venta GROUP BY id_administrador,id_venta)
UNION
(SELECT id_administrador,VENTA.id_venta,SUM(monto) AS monto FROM VENTA,VENTA_MAYOREO WHERE VENTA.id_venta = VENTA_MAYOREO.id_venta GROUP BY id_administrador,id_venta)
UNION
(SELECT id_jefe as id_administrador,VENTA.id_venta,SUM(monto) AS monto FROM VENTA,VENTA_EXTRA,EMPLEADO AS E WHERE VENTA.id_venta = VENTA_EXTRA.id_venta AND E.id_empleado = VENTA.id_empleado GROUP BY id_jefe,id_venta);

DROP VIEW IF EXISTS VENTAS_HOY;
CREATE VIEW VENTAS_HOY AS 
SELECT 
	MVH.id_administrador AS id_administrador,
    fecha,
    MVH.id_venta AS id_venta,
    pago,
    monto
FROM VENTA,MONTO_VENTAS_HOY as MVH 
WHERE VENTA.id_venta = MVH.id_venta
	AND fecha = CURDATE();

SELECT * FROM PAGO_RENTA,EMPLEADO WHERE PAGO_RENTA.id_empleado = EMPLEADO.id_empleado;
SELECT * FROM PAGO_LUZ,EMPLEADO WHERE PAGO_LUZ.id_empleado = EMPLEADO.id_empleado;
SELECT * FROM OTRO_GASTO,EMPLEADO WHERE OTRO_GASTO.id_empleado = EMPLEADO.id_empleado;

-- funcion para consultar $ GASTOS diarios
DROP FUNCTION IF EXISTS C_GASTOS_HOY;
DELIMITER //
CREATE FUNCTION C_GASTOS_HOY(_id_administrador VARCHAR(30))
RETURNS DECIMAL(20,2)
BEGIN
	DECLARE _renta DECIMAL(20,2);
    DECLARE _luz DECIMAL(20,2);
    DECLARE _otros DECIMAL(20,2);
    
    -- RENTA
    IF EXISTS (SELECT id_jefe FROM PAGO_RENTA,EMPLEADO WHERE PAGO_RENTA.id_empleado = EMPLEADO.id_empleado AND id_jefe = _id_administrador and fecha = CURDATE() LIMIT 1) THEN 
        SELECT 
			SUM(monto) INTO _renta
        FROM PAGO_RENTA,EMPLEADO 
        WHERE PAGO_RENTA.id_empleado = EMPLEADO.id_empleado
			AND id_jefe = _id_administrador and fecha = CURDATE()
		GROUP BY id_jefe;
	ELSE -- No hay registros
		SET _renta = 0;
    END IF;
    
    -- LUZ
    IF EXISTS (SELECT id_jefe FROM PAGO_LUZ,EMPLEADO WHERE PAGO_LUZ.id_empleado = EMPLEADO.id_empleado AND id_jefe = _id_administrador and fecha = CURDATE() LIMIT 1) THEN 
        SELECT 
			SUM(monto) INTO _luz
        FROM PAGO_LUZ,EMPLEADO 
        WHERE PAGO_LUZ.id_empleado = EMPLEADO.id_empleado
			AND id_jefe = _id_administrador and fecha = CURDATE()
		GROUP BY id_jefe;
	ELSE -- No hay registros
		SET _luz = 0;
    END IF;
    
      -- OTROS GASTOS
    IF EXISTS (SELECT id_jefe FROM OTRO_GASTO,EMPLEADO WHERE OTRO_GASTO.id_empleado = EMPLEADO.id_empleado AND id_jefe = _id_administrador and fecha = CURDATE() LIMIT 1) THEN 
        SELECT 
			SUM(monto) INTO _otros
        FROM OTRO_GASTO,EMPLEADO 
        WHERE OTRO_GASTO.id_empleado = EMPLEADO.id_empleado
			AND id_jefe = _id_administrador and fecha = CURDATE()
		GROUP BY id_jefe;
	ELSE -- No hay registros
		SET _otros = 0;
    END IF;


    
    RETURN (_renta + _luz + _otros);
END;//
DELIMITER ;

SELECT C_GASTOS_HOY('MASL19931106HOCRNNMASL1993');

-- SELECT * FROM OTRO_GASTO;