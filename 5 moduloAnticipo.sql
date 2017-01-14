USE aserradero;

-- Submódulo Anticipo cliente-- Submódulo Anticipo cliente-- Submódulo Anticipo cliente
-- Submódulo Anticipo cliente-- Submódulo Anticipo cliente-- Submódulo Anticipo cliente

-- vista de anticipo clientes
DROP VIEW IF EXISTS VISTA_ANTICIPO_CLIENTE;
CREATE VIEW VISTA_ANTICIPO_CLIENTE AS
SELECT
	id_anticipo_c,
    fecha,
    id_cliente,
    (SELECT CONCAT(nombre, ' ', apellido_paterno, ' ', apellido_materno) FROM PERSONA WHERE id_persona = SUBSTRING(A.id_cliente,1,18) LIMIT 1) AS cliente,
    id_empleado,
    (SELECT CONCAT(nombre, ' ', apellido_paterno, ' ', apellido_materno) FROM PERSONA WHERE id_persona = SUBSTRING(A.id_empleado,1,18) LIMIT 1) AS empleado,
    monto_anticipo,
    (SELECT id_jefe FROM EMPLEADO WHERE id_empleado = A.id_empleado LIMIT 1) AS id_jefe
FROM ANTICIPO_CLIENTE AS A;

-- Submódulo Anticipo proveedor-- Submódulo Anticipo proveedor-- Submódulo Anticipo proveedor
-- Submódulo Anticipo proveedor-- Submódulo Anticipo proveedor-- Submódulo Anticipo proveedor

-- vista de anticipo proveedores
DROP VIEW IF EXISTS VISTA_ANTICIPO_PROVEEDOR;
CREATE VIEW VISTA_ANTICIPO_PROVEEDOR AS
SELECT id_anticipo_P,
		fecha,
        id_proveedor,
        (SELECT concat (nombre,' ',apellido_paterno,' ',apellido_materno) FROM PERSONA WHERE PERSONA.id_persona = SUBSTRING(ANTICIPO_PROVEEDOR.id_proveedor,1,18)) as proveedor,
        id_empleado,
        (SELECT concat (nombre,' ',apellido_paterno,' ',apellido_materno) FROM PERSONA WHERE PERSONA.id_persona = SUBSTRING(ANTICIPO_PROVEEDOR.id_empleado,1,18)) as empleado,
        (SELECT id_jefe FROM EMPLEADO WHERE id_empleado = ANTICIPO_PROVEEDOR.id_empleado) as id_jefe,
        monto_anticipo
FROM ANTICIPO_PROVEEDOR;

-- Submódulo cuenta por cobrar y pagar a proveedores -- Submódulo cuenta por cobrar y pagar a proveedores-- Submódulo cuenta por cobrar y pagar a proveedores
-- Submódulo cuenta por cobrar y pagar a proveedores -- Submódulo cuenta por cobrar y pagar a proveedores-- Submódulo cuenta por cobrar y pagar a proveedores

-- funcion para consultar $ costo total de madera en rollo por cada proveedor (pagados y no pagados)
-- Se resta el dinero de pagos
DROP FUNCTION IF EXISTS C_MADERA_ENTRADA_ROLLO;
DELIMITER //
CREATE FUNCTION C_MADERA_ENTRADA_ROLLO (_id_proveedor VARCHAR(30))
RETURNS DECIMAL(20,2)
BEGIN
	DECLARE _monto_madera DECIMAL(20,2);
    DECLARE _m_primario DECIMAL(20,2);
    DECLARE _m_secundario DECIMAL(20,2);
    DECLARE _m_terciario DECIMAL(20,2);
    DECLARE _pago DECIMAL(20,2);
    
    -- consultamos si han entrado madera del proveedor
    IF EXISTS (SELECT id_proveedor FROM ENTRADA_M_ROLLO WHERE id_proveedor = _id_proveedor LIMIT 1) THEN 
		-- consultamos el monto total de las maderas que han entrado
        -- SELECT SUM((volumen_primario * costo_primario)) INTO m_primario, SUM((volumen_secundario * costo_secundario)), SUM((volumen_terciario * costo_terciario)) FROM ENTRADA_M_ROLLO GROUP BY id_proveedor;
        SELECT SUM((volumen_primario * costo_primario)) INTO _m_primario FROM ENTRADA_M_ROLLO WHERE id_proveedor = _id_proveedor GROUP BY id_proveedor;
        SELECT SUM((volumen_secundario * costo_secundario)) INTO _m_secundario FROM ENTRADA_M_ROLLO WHERE id_proveedor = _id_proveedor GROUP BY id_proveedor;
        SELECT SUM((volumen_terciario * costo_terciario)) INTO _m_terciario FROM ENTRADA_M_ROLLO WHERE id_proveedor = _id_proveedor GROUP BY id_proveedor;
        
        SET _monto_madera = _m_primario + _m_secundario + _m_terciario;
	ELSE -- No hay entradas
		SET _monto_madera = 0;
    END IF;
    
    -- Consultamos si hay pagos al proveedor
    IF EXISTS(SELECT id_proveedor FROM PAGO_COMPRA WHERE id_proveedor = _id_proveedor LIMIT 1) THEN
		SELECT SUM(monto_pago) INTO _pago FROM PAGO_COMPRA WHERE id_proveedor =_id_proveedor GROUP BY id_proveedor;
	ELSE
		SET _pago = 0;
    END IF;

    
    RETURN (- _monto_madera + _pago);
END;//
DELIMITER ;


-- funcion para consultar $ monto total de los anticipos dados al proveedor
DROP FUNCTION IF EXISTS C_ANTICIPO_PROVEEDOR;
DELIMITER //
CREATE FUNCTION C_ANTICIPO_PROVEEDOR (_id_proveedor VARCHAR(30))
RETURNS DECIMAL(20,2)
BEGIN
	DECLARE _monto_total DECIMAL(20,2);
    
	-- consultamos si han entrado madera del proveedor
    IF EXISTS (SELECT id_proveedor FROM ANTICIPO_PROVEEDOR WHERE id_proveedor = _id_proveedor LIMIT 1) THEN 
		-- consultamos el monto total de los anticipo
        SELECT SUM(monto_anticipo) INTO _monto_total FROM ANTICIPO_PROVEEDOR WHERE id_proveedor = _id_proveedor GROUP BY id_proveedor;
		-- retornamos el monto total
        RETURN _monto_total;
	ELSE 
		RETURN 0;
    END IF;
END;//
DELIMITER ;

-- Muestra cuentas por cobrar y por pagar a los proveedores
-- : los negativos representan cuenta por pagar
-- : los positivos son cuentas por cobrar
DROP VIEW IF EXISTS CUENTAS_PROVEEDOR;
CREATE VIEW CUENTAS_PROVEEDOR AS 
SELECT
	id_proveedor,
    proveedor,
    id_jefe,
    ROUND(((SELECT C_MADERA_ENTRADA_ROLLO(id_proveedor)) + (SELECT C_ANTICIPO_PROVEEDOR(id_proveedor)) ),2) AS monto
FROM PERSONAL_PROVEEDOR;

-- Cuentas por pagar a proveedores
DROP VIEW IF EXISTS C_POR_PAGAR_PROVEEDOR;
CREATE VIEW C_POR_PAGAR_PROVEEDOR AS
SELECT
	id_proveedor AS id_persona, -- se renombra para utilizar la misma clase con cuentas del cliente
    proveedor AS persona,
    id_jefe,
    ABS(monto) AS monto
FROM CUENTAS_PROVEEDOR WHERE monto < 0;

-- Cuentas por cobrar a proveedores
DROP VIEW IF EXISTS C_POR_COBRAR_PROVEEDOR;
CREATE VIEW C_POR_COBRAR_PROVEEDOR AS
SELECT
	id_proveedor AS id_persona,
    proveedor AS persona,
    id_jefe,
    ABS(monto) AS monto
FROM CUENTAS_PROVEEDOR WHERE monto > 0;
-- Submódulo cuentas por cobrar y pagar de clientes -- Submódulo cuentas por cobrar y pagar de clientes -- Submódulo cuentas por cobrar y pagar de clientes 
-- Submódulo cuentas por cobrar y pagar de clientes -- Submódulo cuentas por cobrar y pagar de clientes -- Submódulo cuentas por cobrar y pagar de clientes 


-- funcion para consultar $ costo total de madera vendidas al cliente tanto en mayoreo como por paquete
DROP FUNCTION IF EXISTS COSTO_VENTA_CLIENTE;
DELIMITER //
CREATE FUNCTION COSTO_VENTA_CLIENTE(_id_cliente VARCHAR(30))
RETURNS DECIMAL(20,2)
BEGIN
	DECLARE _monto_paquete 	DECIMAL(20,2);
    DECLARE _monto_mayoreo 	DECIMAL(20,2);
    DECLARE _monto_extra 	DECIMAL(20,2);

	-- ventas por paquete al cliente
    IF EXISTS (SELECT id_cliente FROM VENTA,VENTA_PAQUETE WHERE VENTA.id_venta = VENTA_PAQUETE.id_venta AND id_cliente = _id_cliente LIMIT 1) THEN 
		-- monto total de la venta
        SELECT SUM(monto) INTO _monto_paquete 
        FROM VENTA, VENTA_PAQUETE 
        WHERE VENTA.id_venta = VENTA_PAQUETE.id_venta AND id_cliente = _id_cliente 
        GROUP BY id_cliente;
	ELSE
		SET _monto_paquete = 0;
    END IF;
    
    -- ventas por mayoreo al cliente
    IF EXISTS (SELECT id_cliente FROM VENTA,VENTA_MAYOREO WHERE VENTA.id_venta = VENTA_MAYOREO.id_venta AND id_cliente = _id_cliente LIMIT 1) THEN 
		-- monto total de la venta
        SELECT SUM(monto) INTO _monto_mayoreo 
        FROM VENTA, VENTA_MAYOREO 
        WHERE VENTA.id_venta = VENTA_MAYOREO.id_venta AND id_cliente = _id_cliente 
        GROUP BY id_cliente;
	ELSE
		SET _monto_mayoreo = 0;
    END IF;
    
    -- ventas extra al cliente
    IF EXISTS (SELECT id_cliente FROM VENTA,VENTA_EXTRA WHERE VENTA.id_venta = VENTA_EXTRA.id_venta AND id_cliente = _id_cliente LIMIT 1) THEN 
		-- monto total de la venta
        SELECT SUM(monto) INTO _monto_extra 
        FROM VENTA, VENTA_EXTRA 
        WHERE VENTA.id_venta = VENTA_EXTRA.id_venta AND id_cliente = _id_cliente 
        GROUP BY id_cliente;
	ELSE
		SET _monto_extra = 0;
    END IF;
    
    
    RETURN (_monto_mayoreo + _monto_paquete + _monto_extra);
END;//
DELIMITER ;


-- funcion para consultar $ monto total de los anticipos dados por el cliente
DROP FUNCTION IF EXISTS C_ANTICIPO_CLIENTE;
DELIMITER //
CREATE FUNCTION C_ANTICIPO_CLIENTE (_id_cliente VARCHAR(30))
RETURNS DECIMAL(20,2)
BEGIN
	DECLARE _anticipo DECIMAL(20,2);
    DECLARE _pago DECIMAL(20,2);
    
	-- consultamos si han entrado anticipos
    IF EXISTS (SELECT id_cliente FROM ANTICIPO_CLIENTE WHERE id_cliente = _id_cliente LIMIT 1) THEN 
		-- consultamos el monto total de los anticipo
        SELECT SUM(monto_anticipo) INTO _anticipo FROM ANTICIPO_CLIENTE WHERE id_cliente = _id_cliente GROUP BY id_cliente;
	ELSE 
		SET _anticipo = 0;
    END IF;
    
    -- Consultamos si se han hecho pagos
    IF EXISTS (SELECT id_cliente FROM VENTA WHERE id_cliente = _id_cliente LIMIT 1) THEN 
		-- consultamos el monto total de los pagos
        SELECT SUM(pago) INTO _pago FROM VENTA WHERE id_cliente = _id_cliente GROUP BY id_cliente;
	ELSE 
		SET _pago = 0;
    END IF;
    
    
    RETURN (_anticipo + _pago);
END;//
DELIMITER ;


-- Muestra cuentas por cobrar y por pagar a los clientes
-- : los negativos representan cuenta por pagar
-- : los positivos son cuentas por cobrar
DROP VIEW IF EXISTS CUENTAS_CLIENTE;
CREATE VIEW CUENTAS_CLIENTE AS
SELECT
	id_cliente,
    cliente,
    ROUND(((SELECT COSTO_VENTA_CLIENTE(id_cliente))-(SELECT C_ANTICIPO_CLIENTE(id_cliente))),2) AS monto,
    id_jefe
FROM PERSONAL_CLIENTE;

-- Cuentas por pagar a clientes
DROP VIEW IF EXISTS C_POR_PAGAR_CLIENTE;
CREATE VIEW C_POR_PAGAR_CLIENTE AS
SELECT
	id_cliente AS id_persona,
    cliente AS persona,
    id_jefe,
    ABS(monto) AS monto
FROM CUENTAS_CLIENTE WHERE monto < 0;

-- Cuentas por cobrar a clientes
DROP VIEW IF EXISTS C_POR_COBRAR_CLIENTE;
CREATE VIEW C_POR_COBRAR_CLIENTE AS
SELECT
	id_cliente AS id_persona,
    cliente AS persona,
    id_jefe,
    ABS(monto) AS monto
FROM CUENTAS_CLIENTE WHERE monto > 0;

