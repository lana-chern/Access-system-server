package com.example.access_system_server.dao;

import java.util.List;

/**
 * User: Svetlana Chernyshkova
 * Time: 2021-08-12 23:28
 */
public interface DAO<K extends Number, T> {
    List<T> findAll();

    T findEntityById(K id);

    void delete(K id);

    void delete(T entity);

    void create(T entity);
}
