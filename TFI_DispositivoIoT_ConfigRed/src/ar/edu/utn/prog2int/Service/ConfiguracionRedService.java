package ar.edu.utn.prog2int.Service;

import ar.edu.utn.prog2int.Config.DataBaseConnection;
import java.sql.Connection;

import ar.edu.utn.prog2int.Dao.ConfiguracionRedDAO;
import ar.edu.utn.prog2int.Models.ConfiguracionRed;

public class ConfiguracionRedService extends GenericService<ConfiguracionRed> {

    public ConfiguracionRedService() {
        super(new ConfiguracionRedDAO());
    }

    @Override
    public void insertar(ConfiguracionRed c) throws Exception {
        Connection con = null;
        try {
            con = DataBaseConnection.getConnection();
            con.setAutoCommit(false); // Desactivamos autocommit (modo transacción)

            dao.insertar(c, con); // Ahora el DAO usa la conexión abierta

            con.commit(); // Confirmamos los cambios
            System.out.println("Inserción realizada correctamente (Service).");

        } catch (Exception e) {
            if (con != null) {
                con.rollback(); // Si hay error, deshacemos los cambios
                System.out.println("Error en inserción, se hizo rollback.");
            }
            throw e;
        } finally {
            if (con != null) {
                con.setAutoCommit(true); // Restablecemos el modo normal
                con.close(); // Cerramos conexión
            }
        }
    }

    @Override
    public void actualizar(ConfiguracionRed c) throws Exception {
        Connection con = null;
        try {
            con = DataBaseConnection.getConnection();
            con.setAutoCommit(false);

            dao.actualizar(c, con);

            con.commit();
            System.out.println("Configuración actualizada correctamente (Service).");

        } catch (Exception e) {
            if (con != null) {
                try {
                    con.rollback();
                    System.out.println("Error en rollback: " + e.getMessage());
                } catch (Exception ex) {
                    System.out.println("Error al intentar hacer rollback: " + ex.getMessage());
                }
            }
            throw e;

        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (Exception ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
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
            System.out.println("Configuracion eliminada correctamente.");
            
        } catch (Exception e) {
            if(con != null){
                try {
                    con.rollback();
                    System.out.println("Error en rollback " + e.getMessage());
                } catch (Exception ex) {
                    System.out.println("Error al intentar hacer rollback " + ex.getMessage());
                }
            }
            throw e;            
        } finally {
            try {
                if (con != null && !con.isClosed()){
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (Exception ex) {
                System.out.println("Error al cerrar la conexión." + ex.getMessage());
            }
        }
        
    }
    
    
}