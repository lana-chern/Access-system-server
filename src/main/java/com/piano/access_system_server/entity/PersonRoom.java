package com.piano.access_system_server.entity;

import java.time.LocalDateTime;

/**
 * User: Svetlana Chernyshkova
 * Time: 2021-08-11 10:32
 */
public class PersonRoom {
    private final Person person;
    private final Room room;
    private final boolean entrance;
    private final LocalDateTime createDate;

    public PersonRoom(Person person, Room room, boolean entrance, LocalDateTime createDate) {
        this.person = person;
        this.room = room;
        this.entrance = entrance;
        this.createDate = createDate;
    }

    public Person getPerson() {
        return person;
    }

    public Room getRoom() {
        return room;
    }

    public boolean isEntrance() {
        return entrance;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }
}
