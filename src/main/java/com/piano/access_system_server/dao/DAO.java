package com.piano.access_system_server.dao;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

/**
 * User: Svetlana Chernyshkova
 * Time: 2021-08-12 23:28
 */
public interface DAO<K extends Number, T> {
    List<T> findAll() throws SQLException, NamingException;

    T findEntityById(K id) throws SQLException, NamingException;

    void delete(K id);

    void delete(T entity);

    void create(T entity) throws SQLException, NamingException;
}
