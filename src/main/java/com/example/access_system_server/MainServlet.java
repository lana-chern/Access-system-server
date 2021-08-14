package com.example.access_system_server;

import com.example.access_system_server.entity.Person;
import com.example.access_system_server.entity.PersonRoom;
import com.example.access_system_server.entity.Room;
import com.example.access_system_server.service.PersonRoomService;
import com.example.access_system_server.service.PersonService;
import com.example.access_system_server.service.RoomService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * User: Svetlana Chernyshkova
 * Time: 2021-08-11 09:28
 */
@WebServlet("/check")
public class MainServlet extends HttpServlet {
    private final PersonService personService = new PersonService();
    private final RoomService roomService = new RoomService();
    private final PersonRoomService personRoomService = new PersonRoomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String personIdString = req.getParameter("keyId");
        String roomIdString = req.getParameter("roomId");
        String entranceString = req.getParameter("entrance");
        int roomId;
        int personId;
        boolean entrance;
        try {
            roomId = Integer.parseInt(roomIdString);
            personId = Integer.parseInt(personIdString);
            if (entranceString == null || !entranceString.equalsIgnoreCase("true")
                    && !entranceString.equalsIgnoreCase("false")) {
                throw new IllegalArgumentException("Entrance value doesn't present or wrong");
            }
            entrance = Boolean.parseBoolean(entranceString);
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        Person person = new Person(personId);
        Room room = new Room(roomId);
        PersonRoom personRoom = new PersonRoom(person, room, entrance, LocalDateTime.now());
        if (!personService.checkIfPersonExists(person)) {
            System.out.println("Person " + person.getId() + " doesn't exists");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        if (!roomService.checkIfRoomExists(room)) {
            System.out.println("Room " + room.getId() + " doesn't exists");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        if (personRoomService.isPersonAllowedToMove(personRoom)) {
            System.out.println("Person " + person.getId() + " is allowed to enter/leave the room " + room.getId());
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            System.out.println("Person " + person.getId() + " is not allowed to enter/leave the room " + room.getId());
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
