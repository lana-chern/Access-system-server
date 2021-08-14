package com.example.access_system_server.dao;

import com.example.access_system_server.entity.Person;
import com.example.access_system_server.entity.PersonRoom;
import com.example.access_system_server.entity.Room;
import com.example.access_system_server.service.connection.ConnectorDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Svetlana Chernyshkova
 * Time: 2021-08-13 23:53
 */
public class PersonRoomDAO implements DAO<Long, PersonRoom> {
    public static final String SQL_SELECT_ALL_PERSON_ROOM = "SELECT * FROM person_room";
    public static final String SQL_SELECT_PERSON_ROOM_BY_PERSON_ROOM_ENTRANCE = "SELECT * FROM person_room WHERE person_id = ?" +
            "AND room_id = ? ORDER BY id DESC LIMIT 1";
    public static final String SQL_CREATE_PERSON_ROOM = "INSERT INTO person_room(person_id, room_id, entrance, create_date) " +
            "VALUES(?,?,?,?)";

    @Override
    public List<PersonRoom> findAll() {
        List<PersonRoom> personRoomList = new ArrayList<>();
        try (Connection connection = ConnectorDB.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_PERSON_ROOM);
            while (rs.next()) {
                long id = rs.getLong(1);
                long personId = rs.getLong(2);
                long roomId = rs.getLong(3);
                boolean entrance = rs.getBoolean(4);
                LocalDateTime createDate = rs.getTimestamp(5).toLocalDateTime();
                personRoomList.add(new PersonRoom(id, new Person(personId), new Room(roomId), entrance, createDate));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return personRoomList;
    }

    @Override
    public PersonRoom findEntityById(Long id) {
        throw new UnsupportedOperationException();
    }

    public PersonRoom findLastEntityByPersonRoom(Person person, Room room) {
        PersonRoom personRoom = null;
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_PERSON_ROOM_BY_PERSON_ROOM_ENTRANCE)) {
            statement.setLong(1, person.getId());
            statement.setLong(2, room.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong(1);
                boolean entrance = rs.getBoolean(4);
                LocalDateTime createDate = rs.getTimestamp(5).toLocalDateTime();
                personRoom = new PersonRoom(id, person, room, entrance, createDate);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return personRoom;
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(PersonRoom entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(PersonRoom entity) {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_PERSON_ROOM)) {
            statement.setLong(1, entity.getPerson().getId());
            statement.setLong(2, entity.getRoom().getId());
            statement.setBoolean(3, entity.isEntrance());
            statement.setTimestamp(4, Timestamp.valueOf(entity.getCreateDate()));
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
