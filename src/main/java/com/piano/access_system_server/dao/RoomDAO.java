package com.piano.access_system_server.dao;

import com.piano.access_system_server.entity.Room;
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
 * Time: 2021-08-13 23:40
 */
public class RoomDAO implements DAO<Long, Room> {
    public static final String SQL_SELECT_ALL_ROOM = "SELECT * FROM room";
    public static final String SQL_SELECT_ROOM_BY_ID = "SELECT * FROM room WHERE id=?";

    @Override
    public List<Room> findAll() throws SQLException, NamingException {
        try (Connection connection = ConnectorDB.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_ROOM);
            List<Room> roomList = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong(1);
                roomList.add(new Room(id));
            }
            return roomList;
        }
    }

    @Override
    public Room findEntityById(Long id) throws SQLException, NamingException {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_ROOM_BY_ID)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            Room room = null;
            if (rs.next()) {
                room = new Room(id);
            }
            return room;
        }
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Room entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(Room entity) {
        throw new UnsupportedOperationException();
    }
}
