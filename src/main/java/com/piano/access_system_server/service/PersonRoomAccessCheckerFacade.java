package com.piano.access_system_server.service;

import com.piano.access_system_server.entity.Person;
import com.piano.access_system_server.entity.PersonRoom;
import com.piano.access_system_server.entity.Room;

import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * User: Svetlana Chernyshkova
 * Time: 2021-08-15 17:04
 */
public class PersonRoomAccessCheckerFacade {
    private final PersonService personService = new PersonService();
    private final RoomService roomService = new RoomService();
    private final PersonRoomService personRoomService = new PersonRoomService();

    public boolean isPersonCanMove(PersonRoom personRoom) {
        Person person = personRoom.getPerson();
        Room room = personRoom.getRoom();
        if (!personService.checkIfPersonExists(person)) {
            System.out.println("Person " + person.getId() + " doesn't exists");
            throw new IllegalArgumentException("Person " + person.getId() + " doesn't exists");
        }
        if (!roomService.checkIfRoomExists(room)) {
            System.out.println("Room " + room.getId() + " doesn't exists");
            throw new IllegalArgumentException("Room " + room.getId() + " doesn't exists");
        }
        try {
            return personRoomService.isPersonAllowedToMove(personRoom);
        } catch (SQLException | NamingException throwables) {
            throw new RuntimeException("Can't connect to the DB");
        }
    }
}
