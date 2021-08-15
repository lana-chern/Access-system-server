package com.piano.access_system_server.service;

import com.piano.access_system_server.entity.Person;
import com.piano.access_system_server.entity.PersonRoom;
import com.piano.access_system_server.entity.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * User: Svetlana Chernyshkova
 * Time: 2021-08-15 17:04
 */
public class PersonRoomAccessCheckerFacade {
    private static final Logger log = LoggerFactory.getLogger(PersonRoomAccessCheckerFacade.class);

    private final PersonService personService = new PersonService();
    private final RoomService roomService = new RoomService();
    private final PersonRoomService personRoomService = new PersonRoomService();

    public boolean isPersonCanMove(PersonRoom personRoom) {
        Person person = personRoom.getPerson();
        Room room = personRoom.getRoom();
        try {
            if (!personService.checkIfPersonExists(person)) {
                log.error("Person {} doesn't exists", person.getId());
                throw new IllegalArgumentException("Person " + person.getId() + " doesn't exists");
            }
            if (!roomService.checkIfRoomExists(room)) {
                log.error("Room {} doesn't exists", room.getId());
                throw new IllegalArgumentException("Room " + room.getId() + " doesn't exists");
            }
            log.debug(personRoom.toString());
            boolean isAllowed = personRoomService.isPersonAllowedToMove(personRoom);
            log.debug(personRoom.accessResultToString(isAllowed));
            return isAllowed;
        } catch (SQLException | NamingException e) {
            log.error("Can't get access to the DB", e);
            throw new RuntimeException("Can't get access to the DB", e);
        }
    }
}
