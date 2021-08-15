package com.piano.access_system_server.entity;

/**
 * User: Svetlana Chernyshkova
 * Time: 2021-08-11 10:32
 */
public class Room {
    private final long id;

    public Room(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                '}';
    }
}
