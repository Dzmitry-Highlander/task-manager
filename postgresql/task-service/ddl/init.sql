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

CREATE DATABASE task;
GRANT ALL PRIVILEGES ON DATABASE task TO manager;

\c task;

CREATE SCHEMA task;

CREATE TABLE task.task
(
    task_id uuid,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    project_id uuid,
    title character varying(255) NOT NULL,
    description character varying(35) NOT NULL,
    status character varying(35) NOT NULL,
    implementer_id uuid,
    PRIMARY KEY (task_id)
);

CREATE TABLE task.project
(
    project_id uuid,
    dt_create timestamp(6) without time zone,
    dt_update timestamp(6) without time zone,
    name character varying(255) NOT NULL,
    description character varying(255) NOT NULL,
    manger_id uuid,
    stuff_id uuid[],
    status character varying(255) NOT NULL,
    PRIMARY KEY (project_id)
);

ALTER TABLE IF EXISTS task.task
    OWNER to manager;

ALTER TABLE IF EXISTS task.project
    OWNER to manager;