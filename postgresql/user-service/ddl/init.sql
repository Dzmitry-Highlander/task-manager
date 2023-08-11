DO $$
BEGIN
CREATE ROLE manager WITH
    LOGIN
    SUPERUSER
    INHERIT
    CREATEDB
    CREATEROLE
    REPLICATION;
EXCEPTION WHEN duplicate_object THEN RAISE NOTICE '%, skipping', SQLERRM USING ERRCODE = SQLSTATE;
END
$$;

CREATE DATABASE users;
GRANT ALL PRIVILEGES ON DATABASE users TO manager;

\c users;

CREATE SCHEMA users;

CREATE TABLE users.users
(
    user_id uuid,
    dt_create timestamp(6) without time zone,
    dt_update timestamp(6) without time zone,
    email character varying(255) NOT NULL,
    fio character varying(255) NOT NULL,
    role character varying(35) NOT NULL,
    status character varying(35) NOT NULL,
    password character varying(250) NOT NULL,
    PRIMARY KEY (user_id),
    UNIQUE (email)
);

CREATE TABLE users.activator
(
    activator_id uuid,
    dt_create timestamp(6) without time zone,
    email character varying(255) NOT NULL,
    code character varying(4) NOT NULL,
    dt_expiration timestamp(6) without time zone,
    PRIMARY KEY (activator_id),
    UNIQUE (email)
);

ALTER TABLE IF EXISTS users.activator
    OWNER to manager;

ALTER TABLE IF EXISTS users.users
    OWNER to manager;