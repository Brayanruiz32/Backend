package com.backend.services;

import java.util.List;

public interface IService<T> {

    //tipico crud
    List<T> encontrarTodo();

    T encontrar(Long id);

    T crear(T data);

    T actualizar(Long id, T data);

    void eliminar(Long id);

}
