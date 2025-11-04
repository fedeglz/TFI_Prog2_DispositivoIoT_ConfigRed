CREATE DATABASE IF NOT EXISTS iot_db;
USE iot_db;

CREATE TABLE configuracion_red (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    eliminado BOOLEAN DEFAULT FALSE,
    ip VARCHAR(45) NOT NULL,
    mascara VARCHAR(45) NOT NULL,
    gateway VARCHAR(45),
    dns_primario VARCHAR(45),
    dhcp_habilitado BOOLEAN NOT NULL
);

CREATE TABLE dispositivo_iot (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    eliminado BOOLEAN DEFAULT FALSE,
    serial VARCHAR(50) NOT NULL UNIQUE,
    modelo VARCHAR(50) NOT NULL,
    ubicacion VARCHAR(120),
    firmware_version VARCHAR(30),
    configuracion_red_id BIGINT UNIQUE,
    FOREIGN KEY (configuracion_red_id)
        REFERENCES configuracion_red(id)
        ON DELETE CASCADE
);
