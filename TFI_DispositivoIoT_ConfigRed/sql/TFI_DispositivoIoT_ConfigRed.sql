-- =======================================================================
--  TFI - PROGRAMACIÓN II (UTN)
--  Proyecto: Dispositivo IoT → Configuración de Red
--  Descripción: - Crea la base de datos, tablas y relaciones.
--               - Inserta datos de prueba iniciales para testear el CRUD.
-- =======================================================================

-- Crear base de datos (si no existe)
CREATE DATABASE IF NOT EXISTS iot_db;

-- Usar la base de datos
USE iot_db;


--  TABLA: configuracion_red
--  Almacena los datos de configuración de red de cada dispositivo
CREATE TABLE configuracion_red (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ip VARCHAR(50) NOT NULL UNIQUE,
    mascara VARCHAR(50) NOT NULL,
    gateway VARCHAR(50) NOT NULL,
    dns_primario VARCHAR(50) NOT NULL,
    dhcp_habilitado BOOLEAN NOT NULL,
    eliminado BOOLEAN DEFAULT FALSE
);


--  TABLA: dispositivo_iot
--  Contiene los dispositivos IoT asociados a configuraciones de red
CREATE TABLE dispositivo_iot (
    id INT AUTO_INCREMENT PRIMARY KEY,
    eliminado BOOLEAN DEFAULT FALSE,
    serial VARCHAR(100) NOT NULL UNIQUE,
    modelo VARCHAR(100) NOT NULL,
    ubicacion VARCHAR(100),
    firmware_version VARCHAR(100),
    id_configuracion INT UNIQUE,
    FOREIGN KEY (id_configuracion) REFERENCES configuracion_red(id)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);


--  FIN DEL SCRIPT DE CREACIÓN

SELECT 'Tablas creadas correctamente en la base de datos iot_db.' AS Resultado;

USE iot_db;


--  DATOS DE PRUEBA: configuracion_red
INSERT INTO configuracion_red (ip, mascara, gateway, dns_primario, dhcp_habilitado, eliminado)
VALUES
('192.168.0.200', '255.255.255.0', '192.168.0.1', '8.8.8.8', TRUE, FALSE),
('192.168.1.50', '255.255.255.0', '192.168.1.1', '1.1.1.1', FALSE, FALSE),
('10.10.10.10', '255.255.255.0', '10.10.10.1', '8.8.4.4', FALSE, FALSE);


--  DATOS DE PRUEBA: dispositivo_iot
INSERT INTO dispositivo_iot (eliminado, serial, modelo, ubicacion, firmware_version, id_configuracion)
VALUES
(FALSE, 'ESP32-WROOM-001', 'ESP32-WROOM', 'Oficina Central', 'v1.0.3', 1),
(FALSE, 'RPi4-002', 'Raspberry Pi 4', 'Laboratorio', 'v3.1.0', 2),
(FALSE, 'NANO33-003', 'Arduino Nano 33', 'Depósito', 'v2.0.1', 3);


--  FIN DEL SCRIPT DE INSERCIÓN

SELECT 'Datos de prueba insertados correctamente en iot_db.' AS Resultado;
