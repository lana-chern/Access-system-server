package com.example.access_system_server.service;

import com.example.access_system_server.dao.PersonDAO;
import com.example.access_system_server.entity.Person;

/**
 * User: Svetlana Chernyshkova
 * Time: 2021-08-14 00:16
 */
public class PersonService {
    private final PersonDAO personDAO;

    public PersonService() {
        this.personDAO = new PersonDAO();
    }

    public boolean checkIfPersonExists(Person person) {
        return personDAO.findEntityById(person.getId()) != null;
    }
}
