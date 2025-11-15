package ar.edu.utn.prog2int.Service;

import ar.edu.utn.prog2int.Config.DataBaseConnection;
import ar.edu.utn.prog2int.Dao.DispositivoIoTDAO;
import ar.edu.utn.prog2int.Models.DispositivoIoT;
import java.sql.Connection;
import java.sql.SQLException;

public class DispositivoIoTService extends GenericService<DispositivoIoT> {

    public DispositivoIoTService() {
        super(new DispositivoIoTDAO());
    }

    @Override
    public void insertar(DispositivoIoT dispositivo) throws Exception {
        Connection con = null;

        try {
            con = DataBaseConnection.getConnection();
            con.setAutoCommit(false); // iniciamos la transacion

            dao.insertar(dispositivo, con); // DAO usa la coneccion activa
            System.out.println("Dispositivo insertado correctamente (Service).");

        } catch (Exception e) {
            if (con != null) {
                con.rollback();
                System.out.println("Error al insertar, rollback realizado.");

            }
            throw e;
        } finally {
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }
    }

    @Override
    public void actualizar(DispositivoIoT dispositivo) throws Exception {
        Connection con = null;

        try {
            con = DataBaseConnection.getConnection();
            con.setAutoCommit(false);

            dao.actualizar(dispositivo, con);

            con.commit();
            System.out.println("Se actualizo correctamente (Service).");

        } catch (Exception e) {
            if (con != null) {
                con.rollback();
                System.out.println("Error al actualizar, rollback realizado");

            }
            throw e;
        } finally {
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        Connection con = null;
        try {
            con = DataBaseConnection.getConnection();
            con.setAutoCommit(false);

            dao.eliminar(id, con);

            con.commit();
            System.out.println("Se elimino correctamente (Service).");

        } catch (Exception e) {
            if (con != null) {
                con.rollback();
                System.out.println("Error, no se elimino, roolback realizado.");
            }
            throw e;
        } finally {
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }

    }

    public void pruebaRollbackTransaccional() {
        System.out.println("\n=== PRUEBA DE ROLLBACK TRANSACCIONAL (Service) ===");

        try (Connection con = DataBaseConnection.getConnection()) {
            con.setAutoCommit(false); // Desactiva autocommit para controlar la transacción

            try {
                // 1️⃣ Insertar configuración válida
                String sql1 = "INSERT INTO configuracion_red (ip, mascara, gateway, dns_primario, dhcp_habilitado) "
                        + "VALUES ('172.16.0.10', '255.255.255.0', '172.16.0.1', '8.8.8.8', true)";
                con.prepareStatement(sql1).executeUpdate();
                System.out.println("Configuración insertada correctamente.");

                // 2️⃣ Insertar dispositivo con id_configuración inexistente
                String sql2 = "INSERT INTO dispositivo_iot (eliminado, serial, modelo, ubicacion, firmware_version, id_configuracion) "
                        + "VALUES (false, 'RB-SVC-001', 'ESP32-RB', 'Laboratorio Rollback', 'v1.0', 999)";
                con.prepareStatement(sql2).executeUpdate();

                // Si no falla (no debería pasar), hacemos commit
                con.commit();
                System.out.println("Transacción completada correctamente (no debería verse este mensaje).");

            } catch (SQLException e) {
                System.err.println("Error detectado durante la transacción: " + e.getMessage());
                System.out.println("Ejecutando rollback desde Service...");
                con.rollback();
                System.out.println("Rollback ejecutado correctamente (Service).");
            }

        } catch (SQLException e) {
            System.err.println("Error general en la conexión o rollback: " + e.getMessage());
        }

    }
}
