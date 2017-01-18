USE aserradero;

-- Submódulo clasificación madera aserrada-- Submódulo clasificación madera aserrada-- Submódulo clasificación madera aserrada-- Submódulo clasificación madera aserrada
-- Submódulo clasificación madera aserrada-- Submódulo clasificación madera aserrada-- Submódulo clasificación madera aserrada-- Submódulo clasificación madera aserrada

-- calcular volumen de una clasificacion de madera al insertar
DROP TRIGGER IF EXISTS MADERA_ASERRADA_CLASIF;
DELIMITER //
CREATE TRIGGER MADERA_ASERRADA_CLASIF BEFORE INSERT ON MADERA_ASERRADA_CLASIF
FOR EACH ROW
BEGIN
    SET NEW.volumen = TRUNCATE(((NEW.grueso * NEW.ancho * NEW.largo)/12),3);
END;//
DELIMITER ;

-- calcular volumen de una clasificacion de madera al actualiza
DROP TRIGGER IF EXISTS MADERA_ASERRADA_CLASIF_ACTUALIZAR;
DELIMITER //
CREATE TRIGGER MADERA_ASERRADA_CLASIF_ACTUALIZAR BEFORE UPDATE ON MADERA_ASERRADA_CLASIF
FOR EACH ROW
BEGIN
    SET NEW.volumen = TRUNCATE(((NEW.grueso * NEW.ancho * NEW.largo)/12),3);
END;//
DELIMITER ;

-- Vista de clasificación de madera aserrada
DROP VIEW IF EXISTS V_MADERA_ASERRADA_CLASIF;
CREATE VIEW V_MADERA_ASERRADA_CLASIF AS 
SELECT 
	id_administrador,
    id_empleado,
    (select concat (nombre,' ',apellido_paterno,' ',apellido_materno) FROM PERSONA WHERE id_persona = SUBSTRING(id_empleado,1,18) LIMIT 1) as empleado,
    id_madera,
    grueso,
    grueso_f,
    ancho,
    ancho_f,
    largo,
    largo_f,
    volumen,
    costo_por_volumen
FROM MADERA_ASERRADA_CLASIF;

DROP VIEW IF EXISTS V_ENTRADA_M_ASERRADA;
CREATE VIEW V_ENTRADA_M_ASERRADA AS
SELECT
	id_entrada,
    fecha,
    id_madera,
    num_piezas,
    id_empleado,
    (select concat (nombre,' ',apellido_paterno,' ',apellido_materno) FROM PERSONA WHERE id_persona = SUBSTRING(id_empleado,1,18) LIMIT 1) as empleado,
    id_administrador
FROM ENTRADA_MADERA_ASERRADA;

-- funcion para consultar cantidad de madera vendidad a partir de su id_madera e id_administrador
DROP FUNCTION IF EXISTS C_MADERA_VENDIDA;
DELIMITER //
CREATE FUNCTION C_MADERA_VENDIDA (_id_admin VARCHAR(30),_id_madera VARCHAR(30))
RETURNS INT
BEGIN
	DECLARE _num_piezas_m INT; -- vendidas por mayoreo
    DECLARE _num_piezas_p INT; -- vendidas por paquete
    
    -- consultamos maderas vendidas por mayoreo
    IF EXISTS (SELECT id_madera FROM VENTA_MAYOREO WHERE id_madera = _id_madera AND id_administrador = _id_admin) THEN 
		SELECT 
			SUM(num_piezas) INTO _num_piezas_m
			FROM VENTA_MAYOREO 
			WHERE id_madera= _id_madera and id_administrador = _id_admin
			GROUP BY id_administrador, id_madera;
	ELSE 
		SET _num_piezas_m = 0;
    END IF;
    
    -- consultamos maderas vendidas por paquete
    IF EXISTS (SELECT id_madera FROM VENTA_PAQUETE WHERE id_madera = _id_madera AND id_administrador = _id_admin) THEN 
			SELECT 
				SUM(num_piezas) INTO _num_piezas_p
				FROM VENTA_PAQUETE 
				WHERE id_madera= _id_madera and id_administrador = _id_admin
				GROUP BY id_administrador, id_madera;
	ELSE 
		SET _num_piezas_p = 0;
	END IF;
    RETURN (_num_piezas_m + _num_piezas_p);
END;//
DELIMITER ;

-- vista para facilitar el inventario de madera aserrada
DROP VIEW IF EXISTS TOTAL_M_A_ENTRADA;
CREATE VIEW TOTAL_M_A_ENTRADA AS
SELECT 
	id_administrador,
    id_madera,
    SUM(num_piezas) AS num_piezas
FROM ENTRADA_MADERA_ASERRADA 
GROUP BY id_administrador,id_madera;

-- Vista de inventario madera aserrada
DROP VIEW IF EXISTS INVENTARIO_M_ASERRADA;
CREATE VIEW INVENTARIO_M_ASERRADA AS
SELECT 
	E.id_administrador,
    E.id_madera,
    (E.num_piezas - (SELECT C_MADERA_VENDIDA (E.id_administrador,E.id_madera))) AS num_piezas,
    CLAS.volumen AS volumen_unitario,
    CLAS.costo_por_volumen,
    ROUND(((E.num_piezas - (SELECT C_MADERA_VENDIDA (E.id_administrador,E.id_madera))) * CLAS.volumen),3) AS volumen_total,
    ROUND(((E.num_piezas - (SELECT C_MADERA_VENDIDA (E.id_administrador,E.id_madera))) * CLAS.volumen * CLAS.costo_por_volumen),2) AS costo_total
FROM TOTAL_M_A_ENTRADA AS E, MADERA_ASERRADA_CLASIF AS CLAS
WHERE E.id_administrador = CLAS.id_administrador AND E.id_madera = CLAS.id_madera;

INSERT INTO MADERA_ASERRADA_CLASIF VALUES
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'clase-12', '0.75', '3/4', '12.00', '12', '8.25', '8 1/4', '0', '12.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'clase-10', '0.75', '3/4', '10.00', '10', '8.25', '8 1/4', '0', '10.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'clase-8', '0.75', '3/4', '8.00', '8', '8.25', '8 1/4', '0', '8.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'clase-6', '0.75', '3/4', '6.00', '6', '8.25', '8 1/4', '0', '6.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'clase-4', '0.75', '3/4', '4.00', '4', '8.25', '8 1/4', '0', '4.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tercera-12', '.75', '3/4', '12.00', '12', '8.25', '8 1/4', '0', '12.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tercera-10', '.75', '3/4', '10.00', '10', '8.25', '8 1/4', '0', '10.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tercera-8', '.75', '3/4', '8.00', '8', '8.25', '8 1/4', '0', '8.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tercera-6', '.75', '3/4', '6.00', '6', '8.25', '8 1/4', '0', '6.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tercera-4', '.75', '3/4', '4.00', '4', '8.25', '8 1/4', '0', '4.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'cuarta-12', '.75', '3/4', '12.00', '12', '8.25', '8 1/4', '0', '12.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'cuarta-10', '.75', '3/4', '10.00', '10', '8.25', '8 1/4', '0', '10.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'cuarta-8', '.75', '3/4', '8.00', '8', '8.25', '8 1/4', '0', '8.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'cuarta-6', '.75', '3/4', '6.00', '6', '8.25', '8 1/4', '0', '6.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'cuarta-4', '.75', '3/4', '4.00', '4', '8.25', '8 1/4', '0', '4.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'quinta-12', '.75', '3/4', '12.00', '12', '8.25', '8 1/4', '0', '12.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'quinta-10', '.75', '3/4', '10.00', '10', '8.25', '8 1/4', '0', '10.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'quinta-8', '.75', '3/4', '8.00', '8', '8.25', '8 1/4', '0', '8.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'quinta-6', '.75', '3/4', '6.00', '6', '8.25', '8 1/4', '0', '6.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'quinta-4', '.75', '3/4', '4.00', '4', '8.25', '8 1/4', '0', '4.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tablon-12', '1.5', '1 1/2', '12.00', '12', '8.25', '8 1/4', '0', '12.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tablon-10', '1.5', '1 1/2', '10.00', '10', '8.25', '8 1/4', '0', '10.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tablon-8', '1.5', '1 1/2', '8.00', '8', '8.25', '8 1/4', '0', '8.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tablon-6', '1.5', '1 1/2', '6.00', '6', '8.25', '8 1/4', '0', '6.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tablon-4', '1.5', '1 1/2', '4.00', '4', '8.25', '8 1/4', '0', '4.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'polin-3 1/2', '3.5', '3 1/2', '3.5', '3 1/2', '8.25', '8 1/4', '0', '10.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'barrote-3 1/2', '3.5', '3 1/2', '3.5', '3 1/2', '8.25', '8 1/4', '0', '10.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas7-12', '.75', '3/4', '12.00', '12', '7', '"7"', '0', '12.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas7-10', '.75', '3/4', '10.00', '10', '7', '"7"', '0', '10.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas7-8', '.75', '3/4', '8.00', '8', '7', '"7"', '0', '8.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas7-6', '.75', '3/4', '6.00', '6', '7', '"7"', '0', '6.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas7-4', '.75', '3/4', '4.00', '4', '7', '"7"', '0', '4.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas6-12', '.75', '3/4', '12.00', '12', '6', '"6"', '0', '12.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas6-10', '.75', '3/4', '10.00', '10', '6', '"6"', '0', '10.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas6-8', '.75', '3/4', '8.00', '8', '6', '"6"', '0', '8.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas6-6', '.75', '3/4', '6.00', '6', '6', '"6"', '0', '6.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas6-4', '.75', '3/4', '4.00', '4', '6', '"6"', '0', '4.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas5-12', '.75', '3/4', '12.00', '12', '5', '"5"', '0', '12.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas5-10', '.75', '3/4', '10.00', '10', '5', '"5"', '0', '10.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas5-8', '.75', '3/4', '8.00', '8', '5', '"5"', '0', '8.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas5-6', '.75', '3/4', '6.00', '6', '5', '"5"', '0', '6.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas5-4', '.75', '3/4', '4.00', '4', '5', '"5"', '0', '4.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas4-12', '.75', '3/4', '12.00', '12', '4', '"4"', '0', '12.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas4-10', '.75', '3/4', '10.00', '10', '4', '"4"', '0', '10.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas4-8', '.75', '3/4', '8.00', '8', '4', '"4"', '0', '8.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas4-6', '.75', '3/4', '6.00', '6', '4', '"4"', '0', '6.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas4-4', '.75', '3/4', '4.00', '4', '4', '"4"', '0', '4.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas3-12', '.75', '3/4', '12.00', '12', '3', '"3"', '0', '12.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas3-10', '.75', '3/4', '10.00', '10', '3', '"3"', '0', '10.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas3-8', '.75', '3/4', '8.00', '8', '3', '"3"', '0', '8.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas3-6', '.75', '3/4', '6.00', '6', '3', '"3"', '0', '6.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas3-4', '.75', '3/4', '4.00', '4', '3', '"3"', '0', '4.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas2-12', '.75', '3/4', '12.00', '12', '2', '"2"', '0', '12.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas2-10', '.75', '3/4', '10.00', '10', '2', '"2"', '0', '10.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas2-8', '.75', '3/4', '8.00', '8', '2', '"2"', '0', '8.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas2-6', '.75', '3/4', '6.00', '6', '2', '"2"', '0', '6.00'),
('PAXA20160913HOCSXNPAXA2016', 'PAXA20160913HOCSXNPAXA2016', 'tabletas2-4', '.75', '3/4', '4.00', '4', '2', '"2"', '0', '4.00');
