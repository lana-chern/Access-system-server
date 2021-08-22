package com.piano.access_system_server.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return id == room.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
