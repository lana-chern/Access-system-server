package com.piano.access_system_server;

import com.piano.access_system_server.entity.Person;
import com.piano.access_system_server.entity.PersonRoom;
import com.piano.access_system_server.entity.Room;
import com.piano.access_system_server.service.PersonRoomAccessCheckerFacade;

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
    PersonRoomAccessCheckerFacade personRoomAccessCheckerFacade = new PersonRoomAccessCheckerFacade();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        PersonRoom personRoom = convertParamsFromRequest(req);
        if (personRoomAccessCheckerFacade.isPersonCanMove(personRoom)) {
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    private PersonRoom convertParamsFromRequest(HttpServletRequest req) {
        String personIdString = req.getParameter("keyId");
        String roomIdString = req.getParameter("roomId");
        String entranceString = req.getParameter("entrance");

        int personId = Integer.parseInt(personIdString);
        int roomId = Integer.parseInt(roomIdString);
        if (entranceString == null || !entranceString.equalsIgnoreCase("true")
                && !entranceString.equalsIgnoreCase("false")) {
            throw new IllegalArgumentException("Entrance value doesn't present or wrong");
        }
        boolean entrance = Boolean.parseBoolean(entranceString);

        return new PersonRoom(new Person(personId), new Room(roomId), entrance, LocalDateTime.now());
    }
}
