package ar.edu.utn.prog2int.Dao;

import java.sql.Connection;
import java.util.List;

public interface GenericDAO<T> {

    void insertar(T entity, Connection con) throws Exception;

    void actualizar(T entity, Connection con) throws Exception;

    void eliminar(Long id, Connection con) throws Exception; // baja lógica o física

    T getById(Long id) throws Exception;

    List<T> getAll() throws Exception;

}
