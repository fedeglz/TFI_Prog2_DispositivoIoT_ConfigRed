package ar.edu.utn.prog2int.Dao;

import java.util.List;

public interface GenericDAO<T> {
 

    void insertar(T entity) throws Exception;

    void actualizar(T entity) throws Exception;

    void eliminar(int id) throws Exception; // baja lógica o física

    T getById(int id) throws Exception;

    List<T> getAll() throws Exception;
    
}
