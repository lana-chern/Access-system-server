package com.piano.access_system_server.dao;

import com.piano.access_system_server.entity.Person;
import com.piano.access_system_server.service.connection.ConnectorDB;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Svetlana Chernyshkova
 * Time: 2021-08-12 23:32
 */
public class PersonDAO implements DAO<Long, Person> {
    private static final String SQL_SELECT_ALL_PERSON = "SELECT * FROM person";
    private static final String SQL_SELECT_PERSON_BY_ID = "SELECT * FROM person WHERE id=?";

    @Override
    public List<Person> findAll() throws SQLException, NamingException {
        try (Connection connection = ConnectorDB.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_PERSON);
            List<Person> personList = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong(1);
                personList.add(new Person(id));
            }
            return personList;
        }
    }

    @Override
    public Person findEntityById(Long id) throws SQLException, NamingException {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_PERSON_BY_ID)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            Person person = null;
            if (rs.next()) {
                person = new Person(id);
            }
            return person;
        }
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Person entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(Person entity) {
        throw new UnsupportedOperationException();
    }
}
