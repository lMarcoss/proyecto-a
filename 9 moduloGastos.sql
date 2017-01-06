-- Vistas del módulo gastos
USE aserradero;

DROP VIEW IF EXISTS VISTA_PAGO_RENTA;
CREATE VIEW VISTA_PAGO_RENTA AS 
SELECT
	id_pago_renta,
    fecha,
    nombre_persona,
    id_empleado,
    (SELECT concat(nombre, " ", apellido_paterno, " ", apellido_materno) FROM PERSONA WHERE id_persona = SUBSTRING(PAGO_RENTA.id_empleado,1,18)) as empleado,
    (SELECT id_jefe FROM EMPLEADO WHERE id_empleado = PAGO_RENTA.id_empleado) AS id_jefe,
    monto,
    observacion
FROM PAGO_RENTA
ORDER BY fecha DESC;

DROP VIEW IF EXISTS VISTA_PAGO_LUZ;
CREATE VIEW VISTA_PAGO_LUZ AS 
SELECT 
	id_pago_luz,
    fecha,
    id_empleado,
    (SELECT concat(nombre, " ", apellido_paterno, " ", apellido_materno) FROM PERSONA WHERE id_persona = SUBSTRING(PAGO_LUZ.id_empleado,1,18)) as empleado,
    (SELECT id_jefe FROM EMPLEADO WHERE id_empleado = PAGO_LUZ.id_empleado) AS id_jefe,
    monto,
    observacion
FROM PAGO_LUZ 
ORDER BY fecha DESC;

DROP VIEW IF EXISTS VISTA_OTRO_GASTO;
CREATE VIEW VISTA_OTRO_GASTO AS 
SELECT 
	id_gasto,
    fecha,
    id_empleado,
    (SELECT concat(nombre, " ", apellido_paterno, " ", apellido_materno) FROM PERSONA WHERE id_persona = SUBSTRING(OTRO_GASTO.id_empleado,1,18)) as empleado,
    (SELECT id_jefe FROM EMPLEADO WHERE id_empleado = OTRO_GASTO.id_empleado) AS id_jefe,
    nombre_gasto,
    monto,
    observacion
FROM OTRO_GASTO 
ORDER BY fecha DESC;
