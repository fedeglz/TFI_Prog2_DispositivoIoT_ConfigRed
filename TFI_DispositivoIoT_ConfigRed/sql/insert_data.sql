USE iot_db;

INSERT INTO configuracion_red (ip, mascara, gateway, dns_primario, dhcp_habilitado)
VALUES
('192.168.0.10', '255.255.255.0', '192.168.0.1', '8.8.8.8', TRUE),
('10.0.0.20', '255.0.0.0', '10.0.0.1', '1.1.1.1', FALSE);

INSERT INTO dispositivo_iot (serial, modelo, ubicacion, firmware_version, configuracion_red_id)
VALUES
('ESP32-001', 'ESP32', 'Laboratorio A', 'v1.0', 1),
('ARDU-002', 'Arduino Nano', 'Oficina', 'v2.1', 2);

