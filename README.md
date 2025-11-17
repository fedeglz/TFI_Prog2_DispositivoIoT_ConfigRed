# Trabajo Final Integrador — Programación II (UTN)

### Tema: **Dispositivo IoT → Configuración de Red**

---

## Carrera: Tecnicatura Universitaria en Programación  
**Materia:** Programación II  
**Año:** 2025  
**Institución:** Universidad Tecnológica Nacional (UTN)

---

## Integrantes del Grupo

| Nombre y Apellido 
|--------------------------
| Francisco López 
| Gonzalo Luna 
| Juan Ignacio Malatesta 
| Federico González 

---

## Objetivo del Proyecto

El propósito del sistema es **gestionar dispositivos IoT y sus configuraciones de red asociadas**, aplicando los principios de **Programación Orientada a Objetos (POO)** y acceso a datos mediante **JDBC**.  

Permite realizar operaciones **CRUD completas** (crear, leer, actualizar y eliminar lógicamente) sobre ambas entidades, garantizando la **integridad referencial** y el **control transaccional** ante errores.  

---

## Arquitectura del Proyecto

El proyecto está desarrollado bajo una **arquitectura en capas (5 niveles)**:

1. **Models:** Clases de entidades (`ConfiguracionRed`, `DispositivoIoT`).  
2. **DAO:** Acceso a datos con JDBC (`GenericDAO`, `ConfiguracionRedDAO`, `DispositivoIoTDAO`).  
3. **Service:** Lógica de negocio y manejo de transacciones (`ConfiguracionRedService`, `DispositivoIoTService`).  
4. **Config:** Conexión a base de datos y variables de entorno (`DataBaseConnection`, `.env`).  
5. **Main:** Interfaz de consola (`AppMenu`) que permite ejecutar todas las operaciones CRUD.

---

## Descripción del Dominio

Cada **Dispositivo IoT** pertenece a una **Configuración de Red** (relación 1→1).  
Una configuración almacena la información necesaria para la conexión en red (IP, máscara, gateway, DNS, DHCP), mientras que el dispositivo mantiene datos técnicos como modelo, firmware, ubicación y número de serie.

Esta estructura permite simular la gestión de una red IoT real, en la que cada dispositivo depende de una configuración de red activa.

---

### UML del Proyecto

**Figura 1:** Arquitectura general del sistema (por capas)  
**Figura 2:** Diagrama de clases – DispositivoIoT  
**Figura 3:** Diagrama de clases – ConfiguracionRed  
**Figura 4:** Relación 1 a 1 entre DispositivoIoT y ConfiguracionRed

---

## Requisitos del Sistema

-  **Java JDK 17+**  
-  **NetBeans 17+** o IDE equivalente  
-  **MySQL 8+**  
-  Archivo `.env` con credenciales de conexión

---

## Archivo `.env`

El archivo `.env` se usa para proteger las credenciales de conexión (no se sube al repositorio).  
Debe ubicarse en la **raíz del proyecto** como (.env.ejemplo) y contiene lo siguiente:

```env
DB_URL=jdbc:mysql://localhost:3306/iot_db?useSSL=false&serverTimezone=UTC
DB_USER=usuario
DB_PASSWORD=contraseña
```
debe renombrarse a `.env` antes de ejecutar.

---

## Base de Datos (MySQL)

El proyecto incluye un script SQL completo para crear la base de datos y cargar datos iniciales de prueba.

Archivo:  
	`/sql/TFI_DispositivoIoT_ConfigRed.sql`

### Pasos para ejecutarlo:
1. Abrir **MySQL Workbench** o cualquier cliente MySQL.
2. Ir a **File → Open SQL Script**.
3. Seleccionar el archivo:  `/sql/TFI_DispositivoIoT_ConfigRed.sql`


---

## Ejecucion del Proyecto 

1. Clonar el repositorio o abrirlo en NetBeans.

2. Crear el archivo .env según el ejemplo.

3. Compilar y ejecutar la clase principal:

	Main.java


4. Desde el menú principal, seleccionar una de las opciones:

========= MENÚ PRINCIPAL =========
1. CRUD Configuración de Red
2. CRUD Dispositivo IoT
3. Probar Rollback Transaccional
0. Salir

---

## Prueba de Rollback
El sistema incluye una operación transaccional en la capa de servicio (DispositivoIoTService).
Simula un fallo al insertar un dispositivo con una clave foránea inválida, provocando un rollback de toda la transacción.

---

## Conclusiones

Este trabajo integrador demuestra la aplicación de conceptos de:
- Programación Orientada a Objetos (POO)  
- Acceso a datos con JDBC y control transaccional  
- Uso de variables de entorno para proteger credenciales  
- Diseño por capas con separación clara de responsabilidades  

El resultado es un sistema funcional, modular y escalable, que aplica correctamente las buenas prácticas de desarrollo.

---

## Herramientas Utilizadas

- **Java 17 / JDBC**
- **MySQL 8.0 / Workbench**
- **NetBeans 17**
- **dotenv-java**
- **GitHub**
