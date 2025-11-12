package ar.edu.utn.prog2int.Dao;

import java.util.List;

public interface GenericDAO<T> {
 

    void insert(T entity) throws Exception;

    void update(T entity) throws Exception;

    void delete(int id) throws Exception; // baja lógica o física

    T getById(int id) throws Exception;

    List<T> getAll() throws Exception;
    
}
