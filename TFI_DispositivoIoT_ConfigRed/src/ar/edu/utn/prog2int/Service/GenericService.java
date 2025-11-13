package ar.edu.utn.prog2int.Service;

import java.sql.Connection;
import java.util.List;
import ar.edu.utn.prog2int.Config.DataBaseConnection;
import ar.edu.utn.prog2int.Dao.GenericDAO;


public abstract class GenericService<T> {
    
    protected GenericDAO<T> dao;
    
    public GenericService(GenericDAO<T> dao) {
        this.dao = dao;
    }

    public void insertar(T entity) throws Exception {
        try (Connection con = DataBaseConnection.getConnection()) {
            con.setAutoCommit(false);

            dao.insertar(entity);

            con.commit();
            System.out.println("Transacción completada: inserción exitosa.");

        } catch (Exception e) {
            System.out.println("Error en la inserción: " + e.getMessage());
            throw e;
        }
    }

    public void actualizar(T entity) throws Exception {
        try (Connection con = DataBaseConnection.getConnection()) {
            con.setAutoCommit(false);

            dao.actualizar(entity);

            con.commit();
            System.out.println("Transacción completada: actualización exitosa.");

        } catch (Exception e) {
            System.out.println("Error en la actualización: " + e.getMessage());
            throw e;
        }
    }

    public void eliminar(int id) throws Exception {
        try (Connection con = DataBaseConnection.getConnection()) {
            con.setAutoCommit(false);

            dao.eliminar(id);

            con.commit();
            System.out.println("Transacción completada: eliminación lógica exitosa.");

        } catch (Exception e) {
            System.out.println("Error en la eliminación: " + e.getMessage());
            throw e;
        }
    }

    public T getById(int id) throws Exception {
        return dao.getById(id);
    }

    public List<T> getAll() throws Exception {
        return dao.getAll();
    }
    
    
}
