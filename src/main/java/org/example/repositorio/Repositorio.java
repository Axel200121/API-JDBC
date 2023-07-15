package org.example.repositorio;

import java.util.List;

public interface Repositorio<T>{

    //Creacion de las funciones a usar
    List<T> listar();
    T porId(Long id);
    void guardar(T t);
    void eliminar(Long id);

}
