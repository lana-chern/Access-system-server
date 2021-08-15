# Access System Server

## Settings

### 1. Environment Variables

You have to set environment variables to run the application:

    1. ACCESS_SYSTEM_DB_PATH -- path to DB, for example: jdbc:postgresql://localhost:5432/app_db
    1. ACCESS_SYSTEM_DB_USER -- username for DB
    2. ACCESS_SYSTEM_DB_PASSWORD -- password for DB

### 2. Create DB
You can create DB, using scripts in resources/Scripts.sql

### 3. DB Driver
You have to put jdbc driver into tomcat/lib https://jdbc.postgresql.org/download.html (I'm using postgresql-jdbc4-9.1-903.jar)