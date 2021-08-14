package com.example.access_system_server.service;

import com.example.access_system_server.dao.RoomDAO;
import com.example.access_system_server.entity.Room;

/**
 * User: Svetlana Chernyshkova
 * Time: 2021-08-14 00:17
 */
public class RoomService {
    private final RoomDAO roomDAO;

    public RoomService() {
        this.roomDAO = new RoomDAO();
    }

    public boolean checkIfRoomExists(Room room) {
        return roomDAO.findEntityById(room.getId()) != null;
    }
}
