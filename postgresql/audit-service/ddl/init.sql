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

CREATE DATABASE audit;
GRANT ALL PRIVILEGES ON DATABASE audit TO manager;

\c audit;

CREATE SCHEMA audit;

CREATE TABLE audit.audit
(
    audit_id uuid,
    dt_create timestamp(6) without time zone,
    user_id uuid,
    email character varying(255) NOT NULL,
    fio character varying(255) NOT NULL,
    role character varying(35) NOT NULL,
    text character varying(250),
    status character varying(35) NOT NULL,
    id character varying(250),
    PRIMARY KEY (user_id)
);

ALTER TABLE IF EXISTS audit.audit
    OWNER to manager;