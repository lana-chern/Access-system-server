package com.piano.access_system_server.service;

import com.piano.access_system_server.dao.PersonRoomDAO;
import com.piano.access_system_server.entity.Person;
import com.piano.access_system_server.entity.PersonRoom;
import com.piano.access_system_server.entity.Room;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * User: Svetlana Chernyshkova
 * Time: 2021-08-14 00:18
 */
public class PersonRoomService {
    private final PersonRoomDAO personRoomDAO;

    public PersonRoomService() {
        this.personRoomDAO = new PersonRoomDAO();
    }

    public void create(PersonRoom personRoom) throws SQLException, NamingException {
        personRoomDAO.create(personRoom);
    }

    public boolean isPersonAllowedToMove(PersonRoom personRoom) throws SQLException, NamingException {
        if (personRoom.isEntrance()) {
            return isPersonAllowedToEnterRoom(personRoom.getPerson(), personRoom.getRoom());
        } else {
            return isPersonAllowedToLeaveRoom(personRoom.getPerson(), personRoom.getRoom());
        }
    }

    private boolean isPersonAllowedToEnterRoom(Person person, Room room) throws SQLException, NamingException {
        PersonRoom lastPersonRoom = personRoomDAO.findLastEntityByPersonRoom(person, room);
        if (lastPersonRoom == null || !lastPersonRoom.isEntrance()) {
            create(new PersonRoom(person, room, true, LocalDateTime.now()));
            return isIdDivisibleByRoomId(person, room);
        }
        return false;
    }

    private boolean isPersonAllowedToLeaveRoom(Person person, Room room) throws SQLException, NamingException {
        PersonRoom lastPersonRoom = personRoomDAO.findLastEntityByPersonRoom(person, room);
        if (lastPersonRoom != null && lastPersonRoom.isEntrance()) {
            create(new PersonRoom(person, room, false, LocalDateTime.now()));
            return isTheSameRoomToOut(lastPersonRoom, room);
        }
        return false;
    }

    private boolean isIdDivisibleByRoomId(Person person, Room room) {
        return person.getId() % room.getId() == 0;
    }

    private boolean isTheSameRoomToOut(PersonRoom lastPersonRoom, Room room) {
        return lastPersonRoom.getRoom().equals(room);
    }
}
