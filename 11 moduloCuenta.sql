USE aserradero;

-- funcion para consultar $ valor estimado de bienes inmuebles
-- : terrenos y vehículos
DROP FUNCTION IF EXISTS VALOR_INMUEBLES;
DELIMITER //
CREATE FUNCTION VALOR_INMUEBLES(_id_administrador VARCHAR(30))
RETURNS DECIMAL(20,2)
BEGIN
	DECLARE _valor_terreno DECIMAL(20,2);
    DECLARE _valor_vehiculo DECIMAL(20,2);
    
    -- consultamos si existen registros: terrenos
    IF EXISTS (SELECT id_jefe FROM TERRENO,EMPLEADO WHERE TERRENO.id_empleado = EMPLEADO.id_empleado AND id_jefe = _id_administrador LIMIT 1) THEN 
		-- consultamos el valor estimado de terrenos
        SELECT SUM(valor_estimado) INTO _valor_terreno FROM TERRENO,EMPLEADO WHERE TERRENO.id_empleado = EMPLEADO.id_empleado AND id_jefe = _id_administrador GROUP BY id_jefe;
	ELSE -- No hay registros
		SET _valor_terreno = 0;
    END IF;
    
    -- consultamos si existen registros: vehiculos
    IF EXISTS (SELECT id_jefe FROM VEHICULO,EMPLEADO WHERE VEHICULO.id_empleado = EMPLEADO.id_empleado AND id_jefe = _id_administrador LIMIT 1) THEN 
        SELECT SUM(costo) INTO _valor_vehiculo FROM VEHICULO,EMPLEADO WHERE VEHICULO.id_empleado = EMPLEADO.id_empleado AND id_jefe = _id_administrador GROUP BY id_jefe;
	ELSE -- No hay registros
		SET _valor_vehiculo = 0;
    END IF;

    
    RETURN (_valor_terreno + _valor_vehiculo);
END;//
DELIMITER ;

-- funcion para consultar $ pago empleados
DROP FUNCTION IF EXISTS C_PAGOS_EMPLEADO;
DELIMITER //
CREATE FUNCTION C_PAGOS_EMPLEADO(_id_administrador VARCHAR(30))
RETURNS DECIMAL(20,2)
BEGIN
	DECLARE _pago DECIMAL(20,2);
    
    -- consultamos si existen registros
    IF EXISTS (SELECT id_jefe FROM PAGO_EMPLEADO,EMPLEADO WHERE PAGO_EMPLEADO.id_empleado = EMPLEADO.id_empleado AND id_jefe = _id_administrador LIMIT 1) THEN 
        SELECT SUM(monto) INTO _pago FROM PAGO_EMPLEADO,EMPLEADO WHERE PAGO_EMPLEADO.id_empleado = EMPLEADO.id_empleado AND id_jefe = _id_administrador GROUP BY id_jefe;
	ELSE -- No hay registros
		SET _pago = 0;
    END IF;
        
    RETURN (_pago);
END;//
DELIMITER ;


-- funcion para consultar $ pago empleados
DROP FUNCTION IF EXISTS C_GASTOS;
DELIMITER //
CREATE FUNCTION C_GASTOS(_id_administrador VARCHAR(30))
RETURNS DECIMAL(20,2)
BEGIN
	DECLARE _renta DECIMAL(20,2);
    DECLARE _luz DECIMAL(20,2);
    DECLARE _otros DECIMAL(20,2);
        
    -- Pago renta
    IF EXISTS (SELECT id_jefe FROM PAGO_RENTA,EMPLEADO WHERE PAGO_RENTA.id_empleado = EMPLEADO.id_empleado AND id_jefe = _id_administrador LIMIT 1) THEN 
        SELECT SUM(monto) INTO _renta FROM PAGO_RENTA,EMPLEADO WHERE PAGO_RENTA.id_empleado = EMPLEADO.id_empleado AND id_jefe = _id_administrador GROUP BY id_jefe;
	ELSE -- No hay registros
		SET _renta = 0;
    END IF;
    
    -- Pago luz
    IF EXISTS (SELECT id_jefe FROM PAGO_LUZ,EMPLEADO WHERE PAGO_LUZ.id_empleado = EMPLEADO.id_empleado AND id_jefe = _id_administrador LIMIT 1) THEN 
        SELECT SUM(monto) INTO _luz FROM PAGO_LUZ,EMPLEADO WHERE PAGO_LUZ.id_empleado = EMPLEADO.id_empleado AND id_jefe = _id_administrador GROUP BY id_jefe;
	ELSE -- No hay registros
		SET _luz = 0;
    END IF;
    
    -- otros gastos
    IF EXISTS (SELECT id_jefe FROM OTRO_GASTO,EMPLEADO WHERE OTRO_GASTO.id_empleado = EMPLEADO.id_empleado AND id_jefe = _id_administrador LIMIT 1) THEN 
        SELECT SUM(monto) INTO _otros FROM OTRO_GASTO,EMPLEADO WHERE OTRO_GASTO.id_empleado = EMPLEADO.id_empleado AND id_jefe = _id_administrador GROUP BY id_jefe;
	ELSE -- No hay registros
		SET _otros = 0;
    END IF;
        
    RETURN (_renta + _luz + _otros);
END;//
DELIMITER ;


-- funcion para consultar $ cuentas por pagar a clientes y a proveedores
DROP FUNCTION IF EXISTS C_CUENTAS_POR_PAGAR;
DELIMITER //
CREATE FUNCTION C_CUENTAS_POR_PAGAR(_id_administrador VARCHAR(30))
RETURNS DECIMAL(20,2)
BEGIN
    DECLARE _cpproveedor DECIMAL(20,2);
    DECLARE _cpcliente DECIMAL(20,2);
    
    -- proveedores
    IF EXISTS (SELECT id_jefe FROM C_POR_PAGAR_PROVEEDOR WHERE id_jefe = _id_administrador LIMIT 1) THEN 
        SELECT SUM(monto) INTO _cpproveedor FROM C_POR_PAGAR_PROVEEDOR WHERE id_jefe = _id_administrador GROUP BY id_jefe;
	ELSE -- No hay registros
		SET _cpproveedor = 0;
    END IF;
    
    -- clientes
    IF EXISTS (SELECT id_jefe FROM C_POR_PAGAR_CLIENTE WHERE id_jefe = _id_administrador LIMIT 1) THEN 
        SELECT SUM(monto) INTO _cpcliente FROM C_POR_PAGAR_CLIENTE WHERE id_jefe = _id_administrador GROUP BY id_jefe;
	ELSE -- No hay registros
		SET _cpcliente = 0;
    END IF;
        
    RETURN (_cpproveedor + _cpcliente);
END;//
DELIMITER ;


-- funcion para consultar $ cuentas por cobrar a clientes y a proveedores
DROP FUNCTION IF EXISTS C_CUENTAS_POR_COBRAR;
DELIMITER //
CREATE FUNCTION C_CUENTAS_POR_COBRAR(_id_administrador VARCHAR(30))
RETURNS DECIMAL(20,2)
BEGIN
    DECLARE _ccproveedor DECIMAL(20,2); -- Cuenta por Cobrar a Proveedores
    DECLARE _cccliente DECIMAL(20,2); -- Cuenta por Cobrar a Clientes
    
    -- proveedores
    IF EXISTS (SELECT id_jefe FROM C_POR_COBRAR_PROVEEDOR WHERE id_jefe = _id_administrador LIMIT 1) THEN 
        SELECT SUM(monto) INTO _ccproveedor FROM C_POR_COBRAR_PROVEEDOR WHERE id_jefe = _id_administrador GROUP BY id_jefe;
	ELSE -- No hay registros
		SET _ccproveedor = 0;
    END IF;
    
    -- clientes
    IF EXISTS (SELECT id_jefe FROM C_POR_COBRAR_CLIENTE WHERE id_jefe = _id_administrador LIMIT 1) THEN 
        SELECT SUM(monto) INTO _cccliente FROM C_POR_COBRAR_CLIENTE WHERE id_jefe = _id_administrador GROUP BY id_jefe;
	ELSE -- No hay registros
		SET _cccliente = 0;
    END IF;
        
    RETURN (_ccproveedor + _cccliente);
END;//
DELIMITER ;


-- funcion para consultar $ los pagos de compra = pagos de entrada madera en rollo
DROP FUNCTION IF EXISTS C_PAGOS_COMPRA;
DELIMITER //
CREATE FUNCTION C_PAGOS_COMPRA(_id_administrador VARCHAR(30))
RETURNS DECIMAL(20,2)
BEGIN
    DECLARE _pago DECIMAL(20,2);
        
    -- proveedores
    IF EXISTS (SELECT id_jefe FROM PAGO_COMPRA,PROVEEDOR WHERE PAGO_COMPRA.id_proveedor = PROVEEDOR.id_proveedor AND id_jefe = _id_administrador LIMIT 1) THEN 
		SELECT 
			SUM(monto_pago) INTO _pago
		FROM PAGO_COMPRA,PROVEEDOR 
        WHERE PAGO_COMPRA.id_proveedor = PROVEEDOR.id_proveedor AND id_jefe = _id_administrador 
        GROUP BY id_jefe;
	ELSE -- No hay registros
		SET _pago = 0;
    END IF;
    
    RETURN (_pago);
END;//
DELIMITER ;

-- funcion para consultar $ los pagos de venta a los clientes
DROP FUNCTION IF EXISTS C_PAGOS_VENTA;
DELIMITER //
CREATE FUNCTION C_PAGOS_VENTA(_id_administrador VARCHAR(30))
RETURNS DECIMAL(20,2)
BEGIN
    DECLARE _pago DECIMAL(20,2);
    
    -- clientes
    IF EXISTS (SELECT id_jefe FROM VENTA,EMPLEADO WHERE VENTA.id_empleado = EMPLEADO.id_empleado AND id_jefe = _id_administrador LIMIT 1) THEN 
		SELECT 
			SUM(pago) INTO _pago
        FROM VENTA,EMPLEADO 
        WHERE VENTA.id_empleado = EMPLEADO.id_empleado AND id_jefe = _id_administrador
        GROUP BY id_jefe;
	ELSE -- No hay registros
		SET _pago = 0;
    END IF;
    
    RETURN (_pago);
END;//
DELIMITER ;

-- funcion para consultar $ los préstamos = prestamo - pago_préstamo
DROP FUNCTION IF EXISTS C_PRESTAMOS;
DELIMITER //
CREATE FUNCTION C_PRESTAMOS(_id_administrador VARCHAR(30))
RETURNS DECIMAL(20,2)
BEGIN
	DECLARE _prestamo DECIMAL(20,2);
    DECLARE _pago DECIMAL(20,2);
    
    -- prestamos
    IF EXISTS (SELECT id_jefe FROM PRESTAMO,EMPLEADO WHERE PRESTAMO.id_empleado = EMPLEADO.id_empleado AND id_jefe = _id_administrador LIMIT 1) THEN 
		SELECT 
			SUM(monto_prestamo) INTO _prestamo
        FROM PRESTAMO,EMPLEADO 
        WHERE PRESTAMO.id_empleado = EMPLEADO.id_empleado AND id_jefe = _id_administrador
        GROUP BY id_jefe;
	ELSE -- No hay registros
		SET _prestamo = 0;
    END IF;
    
    -- pago prestamos
    IF EXISTS (SELECT id_jefe FROM PAGO_PRESTAMO,EMPLEADO WHERE PAGO_PRESTAMO.id_empleado = EMPLEADO.id_empleado AND id_jefe = _id_administrador LIMIT 1) THEN 
		SELECT 
			SUM(monto_pago) INTO _pago
        FROM PAGO_PRESTAMO,EMPLEADO 
        WHERE PAGO_PRESTAMO.id_empleado = EMPLEADO.id_empleado AND id_jefe = _id_administrador
        GROUP BY id_jefe;
	ELSE -- No hay registros
		SET _pago = 0;
    END IF;
    
    RETURN (_prestamo - _pago);
END;//
DELIMITER ;

-- funcion para consultar $ costo total de inventario madera en rollo
DROP FUNCTION IF EXISTS C_INVENTARIO_M_ROLLO;
DELIMITER //
CREATE FUNCTION C_INVENTARIO_M_ROLLO(_id_administrador VARCHAR(30))
RETURNS DECIMAL(20,2)
BEGIN
	DECLARE _inventario DECIMAL(20,2);
        
    IF EXISTS (SELECT id_jefe FROM INVENTARIO_M_ROLLO WHERE id_jefe = _id_administrador LIMIT 1) THEN 
		SELECT 
			SUM(costo_total) INTO _inventario
        FROM INVENTARIO_M_ROLLO
        WHERE id_jefe = _id_administrador
        GROUP BY id_jefe;
	ELSE -- No hay registros
		SET _inventario = 0;
    END IF;
        
    RETURN (_inventario);
END;//
DELIMITER ;

-- funcion para consultar $ costo total de inventario madera en rollo
DROP FUNCTION IF EXISTS C_INVENTARIO_M_ASERRADA;
DELIMITER //
CREATE FUNCTION C_INVENTARIO_M_ASERRADA(_id_administrador VARCHAR(30))
RETURNS DECIMAL(20,2)
BEGIN
	DECLARE _inventario DECIMAL(20,2);
        
    IF EXISTS (SELECT id_administrador FROM INVENTARIO_M_ASERRADA WHERE id_administrador = _id_administrador LIMIT 1) THEN 
		SELECT 
			SUM(costo_total) INTO _inventario
        FROM INVENTARIO_M_ASERRADA
        WHERE id_administrador = _id_administrador
        GROUP BY id_administrador;
	ELSE -- No hay registros
		SET _inventario = 0;
    END IF;
        
    RETURN (_inventario);
END;//
DELIMITER ;

-- funcion para consultar $ total de anticipo de clientes
DROP FUNCTION IF EXISTS C_T_ANTICIPO_CLIENTE;
DELIMITER //
CREATE FUNCTION C_T_ANTICIPO_CLIENTE(_id_administrador VARCHAR(30))
RETURNS DECIMAL(20,2)
BEGIN
	DECLARE _anticipo DECIMAL(20,2);
        
    IF EXISTS (SELECT id_jefe FROM ANTICIPO_CLIENTE,CLIENTE WHERE ANTICIPO_CLIENTE.id_cliente = CLIENTE.id_cliente AND id_jefe = _id_administrador LIMIT 1) THEN 
		SELECT 
			SUM(monto_anticipo) INTO _anticipo
        FROM ANTICIPO_CLIENTE,CLIENTE 
        WHERE ANTICIPO_CLIENTE.id_cliente = CLIENTE.id_cliente AND id_jefe = _id_administrador
        GROUP BY id_jefe;
	ELSE -- No hay registros
		SET _anticipo = 0;
    END IF;
        
    RETURN (_anticipo);
END;//
DELIMITER ;

-- funcion para consultar $ total de anticipo a proveedores
DROP FUNCTION IF EXISTS C_T_ANTICIPO_PROVEEDOR;
DELIMITER //
CREATE FUNCTION C_T_ANTICIPO_PROVEEDOR(_id_administrador VARCHAR(30))
RETURNS DECIMAL(20,2)
BEGIN
	DECLARE _anticipo DECIMAL(20,2);
        
    IF EXISTS (SELECT id_jefe FROM ANTICIPO_PROVEEDOR,PROVEEDOR WHERE ANTICIPO_PROVEEDOR.id_proveedor = PROVEEDOR.id_proveedor AND id_jefe = _id_administrador LIMIT 1) THEN 
		SELECT 
			SUM(monto_anticipo) INTO _anticipo
        FROM ANTICIPO_PROVEEDOR,PROVEEDOR 
        WHERE ANTICIPO_PROVEEDOR.id_proveedor = PROVEEDOR.id_proveedor AND id_jefe = _id_administrador
        GROUP BY id_jefe;
	ELSE -- No hay registros
		SET _anticipo = 0;
    END IF;
        
    RETURN (_anticipo);
END;//
DELIMITER ;

SELECT * FROM ANTICIPO_PROVEEDOR,PROVEEDOR;
DROP VIEW IF EXISTS BALANCE_CUENTA;
CREATE VIEW BALANCE_CUENTA AS
SELECT 
	id_administrador,
    FORMAT(cuenta_inicial,2) as cuenta_inicial,
    FORMAT((SELECT VALOR_INMUEBLES(id_administrador)),2) as bienes_inmuebles,
    FORMAT((SELECT C_PAGOS_EMPLEADO(id_administrador)),2) as pago_empleado,
    FORMAT((SELECT C_GASTOS(id_administrador)),2) as gastos,
    FORMAT((SELECT C_CUENTAS_POR_PAGAR(id_administrador)),2) as cuenta_por_pagar,
    FORMAT((SELECT C_CUENTAS_POR_COBRAR(id_administrador)),2) as cuenta_por_cobrar,
    FORMAT((SELECT C_T_ANTICIPO_CLIENTE(id_administrador)),2) as anticipo_cliente, -- total de anticipo clientes
    FORMAT((SELECT C_T_ANTICIPO_PROVEEDOR(id_administrador)),2) as anticipo_proveedor, -- total de anticipo proveedores
    FORMAT((SELECT C_PAGOS_COMPRA(id_administrador)),2) as pagos_compra,
    FORMAT((SELECT C_PAGOS_VENTA(id_administrador)),2) as venta_en_efectivo, -- cobro de venta: a la hora de venta
    FORMAT((SELECT C_PRESTAMOS(id_administrador)),2) as prestamo,
    FORMAT((SELECT C_INVENTARIO_M_ROLLO(id_administrador)),2) as inventario_m_rollo,
    FORMAT((SELECT C_INVENTARIO_M_ASERRADA(id_administrador)),2) as inventario_m_aserrada
FROM ADMINISTRADOR;

DROP VIEW IF EXISTS CUENTA_TOTAL;
CREATE VIEW CUENTA_TOTAL AS 
SELECT 
	id_administrador,
    FORMAT(( cuenta_inicial + 
    (SELECT VALOR_INMUEBLES(id_administrador) +
    (SELECT C_INVENTARIO_M_ROLLO(id_administrador))+
    (SELECT C_INVENTARIO_M_ASERRADA(id_administrador)) +
    (SELECT C_PAGOS_VENTA(id_administrador)) +
    (SELECT C_CUENTAS_POR_COBRAR(id_administrador)) +
    (SELECT C_T_ANTICIPO_CLIENTE(id_administrador)) -
    (SELECT C_T_ANTICIPO_PROVEEDOR(id_administrador)) -
    (SELECT C_PRESTAMOS(id_administrador)) -
    (SELECT C_PAGOS_EMPLEADO(id_administrador)) -
    (SELECT C_GASTOS(id_administrador)) -
	(SELECT C_CUENTAS_POR_PAGAR(id_administrador)) -
    (SELECT C_PAGOS_COMPRA(id_administrador)))),2) AS cuenta_total
FROM ADMINISTRADOR;

SELECT * FROM BALANCE_CUENTA;