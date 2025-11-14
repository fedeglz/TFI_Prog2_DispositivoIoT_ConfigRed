
package ar.edu.utn.prog2int.Service;

import ar.edu.utn.prog2int.Config.DataBaseConnection;
import ar.edu.utn.prog2int.Dao.DispositivoIoTDAO;
import ar.edu.utn.prog2int.Models.DispositivoIoT;
import java.sql.Connection;

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
    public void actualizar (DispositivoIoT dispositivo) throws Exception {
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
