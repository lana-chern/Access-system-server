package com.piano.access_system_server.service;

import com.piano.access_system_server.dao.PersonDAO;
import com.piano.access_system_server.entity.Person;

import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * User: Svetlana Chernyshkova
 * Time: 2021-08-14 00:16
 */
public class PersonService {
    private final PersonDAO personDAO;

    public PersonService() {
        this.personDAO = new PersonDAO();
    }

    public boolean checkIfPersonExists(Person person) throws SQLException, NamingException {
            return personDAO.findEntityById(person.getId()) != null;
    }
}
