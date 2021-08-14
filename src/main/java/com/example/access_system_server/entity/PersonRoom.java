package com.example.access_system_server.entity;

import java.time.LocalDateTime;

/**
 * User: Svetlana Chernyshkova
 * Time: 2021-08-11 10:32
 */
public class PersonRoom {
    private long id;
    private Person person;
    private Room room;
    private boolean entrance;
    private LocalDateTime createDate;

    public PersonRoom(long id, Person person, Room room, boolean entrance, LocalDateTime createDate) {
        this.id = id;
        this.person = person;
        this.room = room;
        this.entrance = entrance;
        this.createDate = createDate;
    }

    public PersonRoom(Person person, Room room, boolean entrance, LocalDateTime createDate) {
        this.person = person;
        this.room = room;
        this.entrance = entrance;
        this.createDate = createDate;
    }

    public long getId() {
        return id;
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
