CREATE TABLE person
(
    id BIGSERIAL NOT NULL
        CONSTRAINT person_pk
            PRIMARY KEY
);

CREATE UNIQUE INDEX person_id_uindex
    ON person (id);

CREATE TABLE room
(
    id BIGSERIAL NOT NULL
        CONSTRAINT room_pk
            PRIMARY KEY
);

CREATE UNIQUE INDEX room_id_uindex
    ON room (id);

CREATE TABLE person_room
(
    id          BIGSERIAL NOT NULL,
    person_id   BIGINT    NOT NULL
        CONSTRAINT person_room_person_id_fk
            REFERENCES person,
    room_id     BIGINT    NOT NULL
        CONSTRAINT person_room_room_id_fk
            REFERENCES room,
    entrance    BOOLEAN   NOT NULL,
    create_date TIMESTAMP NOT NULL
);

CREATE UNIQUE INDEX person_room_id_uindex
    ON person_room (id);

ALTER TABLE person_room
    ADD CONSTRAINT person_room_pk
        PRIMARY KEY (id);

DO
$$
    DECLARE
        quantity_of_person int;
        quantity_of_room   int;
    BEGIN
        quantity_of_person = 5;
        quantity_of_room = 10000;

        FOR i IN 1..quantity_of_person
            LOOP
                INSERT INTO person(id)
                SELECT i;
            END LOOP;

        FOR i IN 1..quantity_of_room
            LOOP
                INSERT INTO room(id)
                SELECT i;
            END LOOP;
    END;
$$