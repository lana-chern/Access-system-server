package com.piano.access_system_server.service;

import com.piano.access_system_server.dao.RoomDAO;
import com.piano.access_system_server.entity.Room;

import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * User: Svetlana Chernyshkova
 * Time: 2021-08-14 00:17
 */
public class RoomService {
    private final RoomDAO roomDAO;

    public RoomService() {
        this.roomDAO = new RoomDAO();
    }

    public boolean checkIfRoomExists(Room room) throws SQLException, NamingException {
            return roomDAO.findEntityById(room.getId()) != null;
    }
}
