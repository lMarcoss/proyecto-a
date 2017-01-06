Use aserradero;

-- Insertamos datos ficticios
INSERT INTO MUNICIPIO (nombre_municipio, estado,telefono) VALUES 
("Miahuatlan de porfirio diaz","Oaxaca",'9876543210'),
("Santa Cruz Mixtepec","Oaxaca",'9876543211'),
("Oaxaca de Juarez","Oaxaca",'9876543212'),
('Santa Cruz Xitla', "Oaxaca",'951901872');

INSERT INTO LOCALIDAD (nombre_localidad,nombre_municipio,estado,telefono) VALUES 
("San Mateo Mixtepec","Santa Cruz Mixtepec",'Oaxaca','9870654321'),
("Santa Cruz Monjas","Miahuatlan de porfirio diaz",'Oaxaca','9870654322'),
('Xitla', 'Santa Cruz Xitla', 'Oaxaca','4435628711');

INSERT INTO PERSONA (id_persona,nombre,apellido_paterno,apellido_materno,nombre_localidad,nombre_municipio,estado,sexo,fecha_nacimiento,telefono) VALUES 
("MASL19931106HOCRNN","Leonardo","Marcos","Santiago","San Mateo Mixtepec","Santa Cruz Mixtepec",'Oaxaca',"H","1993-11-06","9876543210"),
("COXN20160915HOCRXX","Noe","Cortes","","Santa Cruz Monjas","Miahuatlan de porfirio diaz",'Oaxaca',"H","2016-09-15","1234567890"),
("MAXP20160916HOCRXD","Pedro","Martinez","","Santa Cruz Monjas","Miahuatlan de porfirio diaz",'Oaxaca',"H","2016-09-16","1234567890"),
("PAXA20160913HOCSXN","Antonio","Pascual","","Santa Cruz Monjas","Miahuatlan de porfirio diaz",'Oaxaca',"H","2016-09-13","1234567890"),
("PEXF20160910HOCRXR","Francisco","Perez","","Santa Cruz Monjas","Miahuatlan de porfirio diaz",'Oaxaca',"H","2016-09-10","1234567890");


DROP VIEW IF EXISTS VISTA_MUNICIPIO;
CREATE VIEW VISTA_MUNICIPIO AS
SELECT 
	nombre_municipio,
    estado,
    telefono
FROM MUNICIPIO;

DROP VIEW IF EXISTS VISTA_LOCALIDAD;
CREATE VIEW VISTA_LOCALIDAD AS 
SELECT 
	nombre_localidad,
    LOCALIDAD.nombre_municipio as nombre_municipio,
    LOCALIDAD.telefono AS telefono_localidad,
    MUNICIPIO.estado
FROM LOCALIDAD,MUNICIPIO
WHERE LOCALIDAD.nombre_municipio = MUNICIPIO.nombre_municipio
ORDER BY nombre_localidad;

-- lista a todo el personal Cliente id_cliente y nombre completo, id_jefe y nombre completo
DROP VIEW IF EXISTS PERSONAL_CLIENTE;
CREATE VIEW PERSONAL_CLIENTE AS
SELECT id_cliente,
		id_persona,
        (select concat (nombre,' ',apellido_paterno,' ',apellido_materno) FROM PERSONA WHERE PERSONA.id_persona = CLIENTE.id_persona) as cliente,
        id_jefe,
        (select concat (nombre,' ',apellido_paterno,' ',apellido_materno) as nombre FROM PERSONA WHERE id_persona = SUBSTRING(id_jefe,1,18)) as jefe
	FROM CLIENTE,ADMINISTRADOR 
	WHERE CLIENTE.id_jefe = id_administrador
    ORDER BY cliente;
-- SELECT * FROM PERSONAL_CLIENTE;

-- lista a todo el personal Proveedor id_proveedor y nombre completo, id_jefe y nombre completo
DROP VIEW IF EXISTS PERSONAL_PROVEEDOR;
CREATE VIEW PERSONAL_PROVEEDOR AS 
SELECT id_proveedor,
		id_persona,
        (select concat (nombre,' ',apellido_paterno,' ',apellido_materno) FROM PERSONA WHERE id_persona = PROVEEDOR.id_persona) as proveedor,
		id_jefe,
        (select concat (nombre,' ',apellido_paterno,' ',apellido_materno) as nombre FROM PERSONA WHERE id_persona = SUBSTRING(id_jefe,1,18)) as jefe
FROM PROVEEDOR,ADMINISTRADOR 
WHERE PROVEEDOR.id_jefe = id_administrador
ORDER BY proveedor;
-- SELECT * FROM PERSONAL_PROVEEDOR;

-- lista de vehículos con nombre completo del empleado
DROP VIEW IF EXISTS VISTA_VEHICULO;
CREATE VIEW VISTA_VEHICULO AS
SELECT id_vehiculo, 
		matricula, 
        tipo, 
        color, 
        carga_admitida, 
        motor, 
        modelo, 
        costo, 
        VEHICULO.id_empleado, 
        (select concat (nombre,' ',apellido_paterno,' ',apellido_materno) FROM EMPLEADO,PERSONA where EMPLEADO.id_persona = PERSONA.id_persona and EMPLEADO.id_empleado = VEHICULO.id_empleado) as empleado, 
        (select id_jefe FROM EMPLEADO,ADMINISTRADOR WHERE EMPLEADO.id_jefe = ADMINISTRADOR.id_administrador and EMPLEADO.id_empleado = VEHICULO.id_empleado) as id_jefe
	FROM VEHICULO;
-- SELECT * FROM VISTA_VEHICULO;

-- Disparador para crear id_cliente
DROP TRIGGER IF EXISTS CLIENTE;
DELIMITER //
CREATE TRIGGER CLIENTE BEFORE INSERT ON CLIENTE
FOR EACH ROW
BEGIN
	SET NEW.id_cliente = CONCAT(NEW.id_cliente,SUBSTRING(NEW.id_jefe,1,8));
END;//
DELIMITER ;

-- Disparador para crear id_proveedor
DROP TRIGGER IF EXISTS PROVEEDOR;
DELIMITER //
CREATE TRIGGER PROVEEDOR BEFORE INSERT ON PROVEEDOR
FOR EACH ROW
BEGIN
	SET NEW.id_proveedor = CONCAT(NEW.id_proveedor,SUBSTRING(NEW.id_jefe,1,8));
END;//
DELIMITER ;

-- SELECT * FROM PERSONA;

DROP VIEW IF EXISTS VISTA_PERSONA;
CREATE VIEW VISTA_PERSONA AS 
SELECT 
	id_persona,
    concat(nombre, ' ',apellido_paterno, ' ', apellido_materno) AS nombre,
    nombre_localidad,
    nombre_municipio,
    estado,
    direccion,
    sexo,
    fecha_nacimiento,
    telefono
FROM PERSONA
ORDER BY nombre;


DROP VIEW IF EXISTS VISTA_TERRENO;
CREATE VIEW VISTA_TERRENO AS 
SELECT 
	id_terreno,
    nombre,
    dimension,
    direccion,
    nombre_localidad,
    nombre_municipio,
    estado,
    valor_estimado,
    id_empleado,
    (SELECT CONCAT(nombre, ' ', apellido_paterno, ' ', apellido_materno) FROM PERSONA WHERE id_persona = SUBSTRING(id_empleado,1,18) LIMIT 1) AS empleado,
    (SELECT id_jefe FROM EMPLEADO WHERE id_empleado = TERRENO.id_empleado LIMIT 1) AS id_jefe
FROM TERRENO
ORDER BY nombre;
